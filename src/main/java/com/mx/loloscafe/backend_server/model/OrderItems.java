package com.mx.loloscafe.backend_server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private Products product;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size size;

    @ManyToMany
    @JoinTable(
            name = "order_item_options",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private Set<Option> options = new HashSet<>();

    @Column (nullable = false)
    @Min(1)
    private Integer quantity = 1;

    @Column(name = "item_notes")
    private String itemNotes;

    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "total_extras", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalExtras = BigDecimal.ZERO;

    @Column(name = "total_line", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalLine = BigDecimal.ZERO;

    // Constructor method


    public OrderItems(Integer id, Order order, Products product, Size size, Set<Option> options, Integer quantity, String itemNotes, BigDecimal basePrice, BigDecimal totalExtras, BigDecimal totalLine) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.size = size;
        this.options = options;
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

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
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

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
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
        if (this == o) return true;
        if (!(o instanceof OrderItems other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

