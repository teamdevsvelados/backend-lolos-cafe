package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository <OrderItems, Integer> {
}
