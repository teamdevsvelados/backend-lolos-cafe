package com.mx.loloscafe.backend_server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_items")
public class OrderItems {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private Products products;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size size;

    @Column (nullable = false)
    @Min(1)
    private Integer quantity = 1;

    @Column(name = "item_notes", length = 255)
    private String itemNotes;

    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "total_extras", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalExtras = BigDecimal.ZERO;

    @Column(name = "total_line", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalLine;

    // Constructor method
    public OrderItems(Integer id, Order order, Products products, Size size, Integer quantity, String itemNotes, BigDecimal basePrice, BigDecimal totalExtras, BigDecimal totalLine) {
        this.id = id;
        this.order = order;
        this.products = products;
        this.size = size;
        this.quantity = quantity;
        this.itemNotes = itemNotes;
        this.basePrice = basePrice;
        this.totalExtras = totalExtras;
        this.totalLine = totalLine;
    }

    // Empty constructor method (Necessary for JPA)
    public OrderItems(){}

    // Setters and getters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getItemNotes() {
        return itemNotes;
    }

    public void setItemNotes(String itemNotes) {
        this.itemNotes = itemNotes;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getTotalExtras() {
        return totalExtras;
    }

    public void setTotalExtras(BigDecimal totalExtras) {
        this.totalExtras = totalExtras;
    }

    public BigDecimal getTotalLine() {
        return totalLine;
    }

    public void setTotalLine(BigDecimal totalLine) {
        this.totalLine = totalLine;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "id=" + id +
                ", order=" + order +
                ", products=" + products +
                ", size=" + size +
                ", quantity=" + quantity +
                ", itemNotes='" + itemNotes + '\'' +
                ", basePrice=" + basePrice +
                ", totalExtras=" + totalExtras +
                ", totalLine=" + totalLine +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderItems that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(order, that.order) && Objects.equals(products, that.products) && Objects.equals(size, that.size) && Objects.equals(quantity, that.quantity) && Objects.equals(itemNotes, that.itemNotes) && Objects.equals(basePrice, that.basePrice) && Objects.equals(totalExtras, that.totalExtras) && Objects.equals(totalLine, that.totalLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, products, size, quantity, itemNotes, basePrice, totalExtras, totalLine);
    }
}

