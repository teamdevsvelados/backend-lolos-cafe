package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.Order;
import com.mx.loloscafe.backend_server.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUser_Id(Integer userId);
    List<Order> findByStatusOf(OrderStatus status);
    List<Order> findByDateCreationBetween(LocalDateTime start, LocalDateTime end);
}
