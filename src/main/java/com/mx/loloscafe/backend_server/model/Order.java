package com.mx.loloscafe.backend_server.model;

import com.mx.loloscafe.backend_server.model.enums.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_of", nullable = false)
    private OrderStatus statusOf = OrderStatus.CREADO;

    @Column(name = "general_notes")
    private String generalNotes;

    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal subtotal;

    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal discount;

    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private BigDecimal total;

    @Column(name = "date_creation", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime dateCreation;

    //--User (N:1)
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public Order(Integer idOrder, LocalDateTime dateCreation, BigDecimal total, BigDecimal discount, BigDecimal subtotal, String generalNotes, OrderStatus statusOf) {
        this.idOrder = idOrder;
        this.dateCreation = dateCreation;
        this.total = total;
        this.discount = discount;
        this.subtotal = subtotal;
        this.generalNotes = generalNotes;
        this.statusOf = statusOf;
    }

    public Order() {
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public String getGeneralNotes() {
        return generalNotes;
    }

    public void setGeneralNotes(String generalNotes) {
        this.generalNotes = generalNotes;
    }

    public OrderStatus getStatusOf() {
        return statusOf;
    }

    public void setStatusOf(OrderStatus statusOf) {
        this.statusOf = statusOf;
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
                '}';
    }

    //User Getter and Setter

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(idOrder, order.idOrder) && statusOf == order.statusOf && Objects.equals(generalNotes, order.generalNotes) && Objects.equals(subtotal, order.subtotal) && Objects.equals(discount, order.discount) && Objects.equals(total, order.total) && Objects.equals(dateCreation, order.dateCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, statusOf, generalNotes, subtotal, discount, total, dateCreation);
    }
}
