package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
