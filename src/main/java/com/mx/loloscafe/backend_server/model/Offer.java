package com.mx.loloscafe.backend_server.model;

import com.mx.loloscafe.backend_server.model.enums.DiscountType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import jakarta.validation.constraints.*;

// JPA entity mapped to the offers table.
@Entity
@Table(name = "offers")
public class Offer {

    // Primary key, auto-incremented by the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "name_of", nullable = false, length = 100)
    private String nameOf;

    @Size(max = 255)
    @Column(name = "description_of", length = 255)
    private String descriptionOf;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "discount_type", nullable = false)
    private DiscountType discountType;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @Column(name = "value_of", nullable = false, precision = 10, scale = 2)
    private BigDecimal valueOf;

    @NotNull
    @Column(nullable = false)
    private Boolean available = true;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

        @ManyToMany
        @JoinTable(
            name = "offer_product",
            joinColumns = @JoinColumn(name = "id_offer"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
        )
        private java.util.Set<Products> products = new java.util.HashSet<>();

        public java.util.Set<Products> getProducts() {
            return products;
        }
        public void setProducts(java.util.Set<Products> products) {
            this.products = products;
        }

    public Offer(Integer id, String nameOf, String descriptionOf, DiscountType discountType, BigDecimal valueOf, Boolean available, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.nameOf = nameOf;
        this.descriptionOf = descriptionOf;
        this.discountType = discountType;
        this.valueOf = valueOf;
        this.available = available;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Offer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOf() {
        return nameOf;
    }

    public void setNameOf(String nameOf) {
        this.nameOf = nameOf;
    }

    public String getDescriptionOf() {
        return descriptionOf;
    }

    public void setDescriptionOf(String descriptionOf) {
        this.descriptionOf = descriptionOf;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getValueOf() {
        return valueOf;
    }

    public void setValueOf(BigDecimal valueOf) {
        this.valueOf = valueOf;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", nameOf='" + nameOf + '\'' +
                ", descriptionOf='" + descriptionOf + '\'' +
                ", discountType=" + discountType +
                ", valueOf=" + valueOf +
                ", available=" + available +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Offer offer)) return false;
        return Objects.equals(id, offer.id)
                && Objects.equals(nameOf, offer.nameOf)
                && Objects.equals(descriptionOf, offer.descriptionOf)
                && discountType == offer.discountType
                && Objects.equals(valueOf, offer.valueOf)
                && Objects.equals(available, offer.available)
                && Objects.equals(startDate, offer.startDate)
                && Objects.equals(endDate, offer.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOf, descriptionOf, discountType, valueOf, available, startDate, endDate);
    }
}
