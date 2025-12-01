package clover.orderservice.service;

import clover.orderservice.DAO.OrderRepository;
import clover.orderservice.entity.Order;
import clover.orderservice.entity.Product;
import clover.orderservice.entity.User;
import clover.orderservice.rest.ProductClient;
import clover.orderservice.rest.UserClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final UserClient userClient;
    private final ProductClient productClient;
    private final OrderRepository repo;


    public OrderService(UserClient userClient, ProductClient productClient, OrderRepository repo){
        this.userClient = userClient;
        this.productClient = productClient;
        this.repo = repo;
    }
    public Order placeOrder(Long userId, Long productId){
        User user = userClient.getUserById(userId);
        if(user==null) return null;
        Product product = productClient.getProductById(productId);
        if(product==null) return null;
        if(product.getQuantity()==0) return null;
        product.setQuantity(product.getQuantity()-1);
        Product updateProduct = productClient.updateProduct(productId, product);

        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setCost(product.getCost());
        order.setStatus("Order Placed Successfully");
        order.setProductName(product.getName());
        order.setUserName(user.getName());

        return repo.save(order);
    }

    public Order getById(Long id){
        return repo.findById(id).orElse(null);
    }

    public List<Order> getAllOrders(){
        return repo.findAll();
    }

    public String deleteOrderById(Long id){
        repo.deleteById(id);
        return "Deleted Order With id:"+id;
    }
}




























