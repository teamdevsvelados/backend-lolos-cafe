package com.mx.loloscafe.backend_server.model;

import com.mx.loloscafe.backend_server.model.enums.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(
        name = "orders",
        indexes = {
                @Index(name = "idx_order_status", columnList = "status_of"),
                @Index(name = "idx_order_date", columnList = "date_creation")
        }
)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idOrder;

    @Column(name = "customer_name", length = 150)
    private String customerName;

    @Column(name = "customer_phone", length = 20)
    private String customerPhone;

    @Column(name = "customer_address", length = 255)
    private String customerAddress;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

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

    @Column(name = "available", nullable = false)
    private Boolean available = true;

    @CreationTimestamp
    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    //--User (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    public Order(Long idOrder, User user, LocalDateTime dateCreation, Boolean available, BigDecimal total, BigDecimal discount, BigDecimal subtotal, String generalNotes, OrderStatus statusOf, String paymentMethod, String customerAddress, String customerPhone, String customerName) {
        this.idOrder = idOrder;
        this.user = user;
        this.dateCreation = dateCreation;
        this.available = available;
        this.total = total;
        this.discount = discount;
        this.subtotal = subtotal;
        this.generalNotes = generalNotes;
        this.statusOf = statusOf;
        this.paymentMethod = paymentMethod;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerName = customerName;
    }

    public Order() {
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
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
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", statusOf=" + statusOf +
                ", generalNotes='" + generalNotes + '\'' +
                ", subtotal=" + subtotal +
                ", discount=" + discount +
                ", total=" + total +
                ", available=" + available +
                ", dateCreation=" + dateCreation +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(idOrder, order.idOrder) && Objects.equals(customerName, order.customerName) && Objects.equals(customerPhone, order.customerPhone) && Objects.equals(customerAddress, order.customerAddress) && Objects.equals(paymentMethod, order.paymentMethod) && statusOf == order.statusOf && Objects.equals(generalNotes, order.generalNotes) && Objects.equals(subtotal, order.subtotal) && Objects.equals(discount, order.discount) && Objects.equals(total, order.total) && Objects.equals(available, order.available) && Objects.equals(dateCreation, order.dateCreation) && Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, customerName, customerPhone, customerAddress, paymentMethod, statusOf, generalNotes, subtotal, discount, total, available, dateCreation, user);
    }
}
