package com.mx.loloscafe.backend_server.controller;

import com.mx.loloscafe.backend_server.exceptions.OrderNotFoundException;
import com.mx.loloscafe.backend_server.exceptions.UserNotFoundException;
import com.mx.loloscafe.backend_server.model.Order;
import com.mx.loloscafe.backend_server.model.enums.OrderStatus;
import com.mx.loloscafe.backend_server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> findAll() {
        return orderService.getOrders();
    }

    @GetMapping("/orders/{idOrder}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer idOrder){
        try{
            return ResponseEntity.ok(orderService.findById(idOrder));
        } catch (OrderNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/orders/user/{userId}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable Integer userId) {
        try {
            List<Order> orders = orderService.getOrdersByUserId(userId);

            if (orders.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(orders);

        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/orders/range-date")
    public ResponseEntity<?> getOrdersByDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {

        List<Order> orders = orderService.getOrdersByDateRange(start, end);

        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable OrderStatus status) {

        List<Order> orders = orderService.getOrdersByStatus(status);

        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(orders);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order newOrder) {

        Order createdOrder = orderService.createOrder(newOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<?> updateOrder(
            @PathVariable Integer orderId,
            @RequestBody Order updatedOrder) {

        try {
            Order order = orderService.updateOrder(updatedOrder, orderId);
            return ResponseEntity.ok(order);

        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/orders/{idOrder}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer idOrder) {

        try {
            orderService.deleteOrder(idOrder);
            return ResponseEntity.noContent().build();

        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}
