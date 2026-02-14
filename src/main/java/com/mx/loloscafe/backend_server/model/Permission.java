package com.mx.loloscafe.backend_server.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "permissions",
        uniqueConstraints = @UniqueConstraint(name = "uk_permissions_name_of", columnNames = "name_of"))
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SMALLINT UNSIGNED AUTO_INCREMENT
    private Integer id;

    @Column(name = "name_of", nullable = false, length = 50, unique = true)
    private String nameOf;

    public Permission() {}

    public Permission(Integer id, String nameOf) {
        this.id = id;
        this.nameOf = nameOf;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNameOf() { return nameOf; }
    public void setNameOf(String nameOf) { this.nameOf = nameOf; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permission p)) return false;
        return Objects.equals(id, p.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}