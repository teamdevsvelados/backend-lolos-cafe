package com.mx.loloscafe.backend_server.service;

import com.mx.loloscafe.backend_server.exceptions.OrderNotFoundException;
import com.mx.loloscafe.backend_server.exceptions.UserNotFoundException;
import com.mx.loloscafe.backend_server.model.Order;
import com.mx.loloscafe.backend_server.model.enums.OrderStatus;
import com.mx.loloscafe.backend_server.repository.OrderRepository;
import com.mx.loloscafe.backend_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    //Get Orders
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    //Create order
    public Order createOrder(Order newOrder) {

        newOrder.setDateCreation(LocalDateTime.now());
        newOrder.setStatusOf(OrderStatus.CREADO);
        newOrder.setSubtotal(BigDecimal.ZERO);
        newOrder.setDiscount(BigDecimal.ZERO);
        newOrder.setTotal(BigDecimal.ZERO);

        return orderRepository.save(newOrder);
    }

    //Update Order
    public Order updateOrder(Order updatedOrder, Integer orderId) {
        return orderRepository.findById(orderId)
                .map(order -> {
                    order.setGeneralNotes(updatedOrder.getGeneralNotes());
                    order.setStatusOf(updatedOrder.getStatusOf());
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    //Delete order
    public void deleteOrder(Integer orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new OrderNotFoundException(orderId);
        }
    }

    //Get orders by user id
    public List<Order> getOrdersByUserId(Integer userId) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        return orderRepository.findByUser_Id(userId);

    }

    //Get orders by status
    public List<Order> getOrdersByStatus(OrderStatus status){
        return orderRepository.findByStatusOf(status);
    }

    //Find order by id
    public Order findById(Integer id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    //Orders by Date
    public List<Order> getOrdersByDateRange(LocalDateTime start, LocalDateTime end) {
        return orderRepository.findByDateCreationBetween(start, end);
    }

}
