package com.mx.loloscafe.backend_server.model;

import com.mx.loloscafe.backend_server.model.enums.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_of", nullable = false)
    private OrderStatus statusOf = OrderStatus.CREADO;

    @Column(name = "general_notes", length = 500)
    private String generalNotes;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal = new BigDecimal("0.00");

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal discount = new BigDecimal("0.00");

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total = new BigDecimal("0.00");

    @CreationTimestamp
    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    //--User (N:1)
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Order(Long idOrder, OrderStatus statusOf, String generalNotes, BigDecimal subtotal, BigDecimal discount, BigDecimal total, LocalDateTime dateCreation, User user) {
        this.idOrder = idOrder;
        this.statusOf = statusOf;
        this.generalNotes = generalNotes;
        this.subtotal = subtotal;
        this.discount = discount;
        this.total = total;
        this.dateCreation = dateCreation;
        this.user = user;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public OrderStatus getStatusOf() {
        return statusOf;
    }

    public void setStatusOf(OrderStatus statusOf) {
        this.statusOf = statusOf;
    }

    public String getGeneralNotes() {
        return generalNotes;
    }

    public void setGeneralNotes(String generalNotes) {
        this.generalNotes = generalNotes;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", statusOf=" + statusOf +
                ", generalNotes='" + generalNotes + '\'' +
                ", subtotal=" + subtotal +
                ", discount=" + discount +
                ", total=" + total +
                ", dateCreation=" + dateCreation +
                ", user=" + user +
                //", orderItems=" + orderItems +
                //", offer=" + offer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(idOrder, order.idOrder) && statusOf == order.statusOf && Objects.equals(generalNotes, order.generalNotes) && Objects.equals(subtotal, order.subtotal) && Objects.equals(discount, order.discount) && Objects.equals(total, order.total) && Objects.equals(dateCreation, order.dateCreation) && Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, statusOf, generalNotes, subtotal, discount, total, dateCreation, user);
    }
}
