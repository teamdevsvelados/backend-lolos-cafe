package com.mx.loloscafe.backend_server.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "sizes")
public class Size {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "name_of", nullable = false, length = 20)
    private String nameOf;

    @Column (nullable = false)
    private Boolean available = true;

    public Size(Integer id, String nameOf, Boolean available) {
        this.id = id;
        this.nameOf = nameOf;
        this.available = available;
    }

    public Size() {
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
        return "Sizes{" +
                "id=" + id +
                ", nameOf='" + nameOf + '\'' +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Size size)) return false;
        return Objects.equals(id, size.id) && Objects.equals(nameOf, size.nameOf) && Objects.equals(available, size.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOf, available);
    }
}
