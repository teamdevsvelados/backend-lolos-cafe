package com.mx.loloscafe.backend_server.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

// JPA entity mapped to the offers table.
@Entity
@Table(name = "offers")
public class Offer {

    // Primary key, auto-incremented by the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_of", nullable = false, length = 100)
    private String nameOf;

    @Column(name = "value_of", nullable = false, precision = 10, scale = 2)
    private BigDecimal valueOf;

    public Offer(Integer id, String nameOf, BigDecimal valueOf) {
        this.id = id;
        this.nameOf = nameOf;
        this.valueOf = valueOf;
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

    public BigDecimal getValueOf() {
        return valueOf;
    }

    public void setValueOf(BigDecimal valueOf) {
        this.valueOf = valueOf;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", nameOf='" + nameOf + '\'' +
                ", valueOf=" + valueOf +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Offer offer)) return false;
        return Objects.equals(id, offer.id) && Objects.equals(nameOf, offer.nameOf) && Objects.equals(valueOf, offer.valueOf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOf, valueOf);
    }
}
