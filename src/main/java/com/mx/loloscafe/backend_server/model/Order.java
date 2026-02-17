package com.mx.loloscafe.backend_server.model;

import com.mx.loloscafe.backend_server.model.enums.OrderStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal = new BigDecimal("0.00");

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal discount = new BigDecimal("0.00");

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total = new BigDecimal("0.00");

    @Column(name = "date_creation", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime dateCreation;

    //--User (N:1)
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    //--OrderItem (1:N)
    //@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<OrderItems> orderItems = new ArrayList<>();

    //--Offer (N:1)
    //@ManyToOne
    //@JoinColumn(name = "id_offer")
    //private Offer offer;


    public Order(Integer idOrder, User user, LocalDateTime dateCreation, BigDecimal total, BigDecimal discount, BigDecimal subtotal, String generalNotes, OrderStatus statusOf) {
        this.idOrder = idOrder;
        this.user = user;
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
    /*
    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }


    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

     */

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
