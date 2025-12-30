package clover.gateway.RateLimiter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.Duration;

@Component
public class RateLimitGatewayFilter  extends AbstractGatewayFilterFactory<RateLimitGatewayFilter.Config> {


    private final ReactiveStringRedisTemplate redisTemplate;
    public  RateLimitGatewayFilter(ReactiveStringRedisTemplate redisTemplate) {
        super(Config.class);
        this.redisTemplate = redisTemplate;
    }

    public static class Config {
        private int limit;
        private int windowSeconds;
        private boolean enabled;
        public int getLimit() {
            return limit;
        }
        public void setLimit(int limit) {
            this.limit = limit;
        }
        public int getWindowSeconds() {
            return windowSeconds;
        }
        public void setWindowSeconds(int windowSeconds) {
            this.windowSeconds = windowSeconds;
        }
        public boolean isEnabled() {return enabled;}
        public void setEnabled(boolean enabled) {this.enabled = enabled;}
    };

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if(!config.isEnabled()){ return chain.filter(exchange);}
            String key = buildKey(exchange);

            return redisTemplate.opsForValue()
                    .increment(key)
                    .flatMap(count -> {
                        if(count == 1){
                            redisTemplate.expire(
                                    key,
                                    Duration.ofSeconds(config.getWindowSeconds())
                            ).subscribe();
                        }

                        if(count > config.getLimit()){
                            System.out.println("Rate Limit Exceeded for key: " + key);
                            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                            exchange.getResponse().getHeaders().add("Retry-After", String.valueOf(config.getWindowSeconds()));
                            return exchange.getResponse().setComplete();
                        }
                        return chain.filter(exchange);
                    });
        };
    }

    private String buildKey(ServerWebExchange exchange) {
        String userId =  exchange.getRequest().getHeaders().getFirst("X-User-Id");
        if(userId == null){
            userId = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        }

        String path = exchange.getRequest().getPath().value();
        return "rl:" + userId + ":" + path;
    }
}
