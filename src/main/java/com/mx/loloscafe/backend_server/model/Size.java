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

    public Size(Integer id, String nameOf) {
        this.id = id;
        this.nameOf = nameOf;
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

    @Override
    public String toString() {
        return "Sizes{" +
                "id=" + id +
                ", nameOf='" + nameOf + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Size sizes)) return false;
        return Objects.equals(id, sizes.id) && Objects.equals(nameOf, sizes.nameOf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOf);
    }
}
