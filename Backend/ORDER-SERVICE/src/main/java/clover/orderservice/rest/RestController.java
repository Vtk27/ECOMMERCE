package clover.orderservice.rest;


import clover.orderservice.DAO.OrderRepository;
import clover.orderservice.entity.Order;
import clover.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/orders")
public class RestController {
    private final OrderService orderService;

    public RestController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public Order postOrder(@RequestBody Order order){
        return orderService.placeOrder(order.getUserId(), order.getProductId());
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id){
        return orderService.getById(id);
    }
//
    @GetMapping
    public List<Order> getOrderList(){
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id){
        return orderService.deleteOrderById(id);
    }
}
