package com.mx.loloscafe.backend_server.model;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name="categories")
public class Category {

    @Id // Primary key for the category
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented ID
    private Integer id;

    // Display name for the category
    @Column (name = "name_of", nullable = false, length = 50)
    private String nameOf;

    // Availability flag stored in the table if we want to add soft delete functionality in the future
    @Column(name = "available", nullable = false)
    private Boolean available;

    public Category() {
    }

    public Category(Integer id, String nameOf) {
        this.id = id;
        this.nameOf = nameOf;
    }

    public Category(Integer id, String nameOf, Boolean available) {
        this.id = id;
        this.nameOf = nameOf;
        this.available = available;
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", nameOf='" + nameOf + '\'' +
            ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Category that)) return false;
        return Objects.equals(id, that.id)
                && Objects.equals(nameOf, that.nameOf)
                && Objects.equals(available, that.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOf, available);
    }
}
