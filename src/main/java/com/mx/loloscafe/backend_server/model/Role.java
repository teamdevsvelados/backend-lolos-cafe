package com.mx.loloscafe.backend_server.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles",
        uniqueConstraints = @UniqueConstraint(name = "uk_roles_name_of", columnNames = "name_of"))
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // TINYINT UNSIGNED AUTO_INCREMENT
    private Integer id;

    @Column(name = "name_of", nullable = false, length = 30, unique = true)
    private String nameOf;

    private Set<Permission> permissions = new HashSet<>();

    public Role() {}

    public Role(Integer id, String nameOf) {
        this.id = id;
        this.nameOf = nameOf;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNameOf() { return nameOf; }
    public void setNameOf(String nameOf) { this.nameOf = nameOf; }

    public Set<Permission> getPermissions() { return permissions; }
    public void setPermissions(Set<Permission> permissions) { this.permissions = permissions; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role r)) return false;
        return Objects.equals(id, r.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}