package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository <OrderItems, Integer> {

    // Gets all orderItems with the same orderId
    List<OrderItems> findByOrder_Id(Integer orderId);

    // Adds subtotal per orderId (necessary for order service)
    @Query("""
            SELECT COALESCE(SUM(oi.totalLine), 0)
            FROM OrderItems oi 
            WHERE oi.order.id = :orderId
            """)
    BigDecimal sumTotalLineByOrderId(@Param("orderId") Integer orderId);

}
