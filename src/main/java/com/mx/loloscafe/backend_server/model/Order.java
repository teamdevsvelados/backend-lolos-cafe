package com.mx.loloscafe.backend_server.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idOrder;

    @Column(name = "general_notes")
    private String generalNotes;

    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private Long subtotal;

    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private Long discount;

    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private Long total;

    @Column(name = "date_creation", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime dateCreation;

    public Order(Integer idOrder, String generalNotes, Long subtotal, Long discount, Long total, LocalDateTime dateCreation) {
        this.idOrder = idOrder;
        this.generalNotes = generalNotes;
        this.subtotal = subtotal;
        this.discount = discount;
        this.total = total;
        this.dateCreation = dateCreation;
    }

    public Order() {
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public String getGeneralNotes() {
        return generalNotes;
    }

    public void setGeneralNotes(String generalNotes) {
        this.generalNotes = generalNotes;
    }

    public Long getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Long subtotal) {
        this.subtotal = subtotal;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", generalNotes='" + generalNotes + '\'' +
                ", subtotal=" + subtotal +
                ", discount=" + discount +
                ", total=" + total +
                ", dateCreation=" + dateCreation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(idOrder, order.idOrder) && Objects.equals(generalNotes, order.generalNotes) && Objects.equals(subtotal, order.subtotal) && Objects.equals(discount, order.discount) && Objects.equals(total, order.total) && Objects.equals(dateCreation, order.dateCreation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, generalNotes, subtotal, discount, total, dateCreation);
    }
}
