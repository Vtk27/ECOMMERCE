package clover.gateway.JWTFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JWTAuthFilter extends AbstractGatewayFilterFactory<JWTAuthFilter.Config> {

    public JWTAuthFilter(){
        super(Config.class);
    }
    private final String secret = "mysecretmysecretmysecretmysecret";
    public static class Config {};

    @Override
    public GatewayFilter apply(Config config){
        return ((exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();

            if(path.contains("/users/login") || path.contains("/users/signup")){
                return chain.filter(exchange);
            }

            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if(authHeader==null || !authHeader.startsWith("Bearer ")){
                return this.onError(exchange, "Missing or invalid Auth headers");
            }

            try{
                String token = authHeader.substring(7);
                Claims claims = Jwts.parser()
                        .setSigningKey(secret.getBytes())
                        .parseClaimsJws(token)
                        .getBody();

                exchange.getRequest().mutate()
                        .header("X-User-Id", claims.getSubject())
                        .build();
            }catch (Exception e){
                return this.onError(exchange, "Invalid Jwt token");
            }
            return chain.filter(exchange);
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange, String error){
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
