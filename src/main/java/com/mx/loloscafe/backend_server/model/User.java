package com.mx.loloscafe.backend_server.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="users")
public class User {

    @Id // Definir la Primary Key
    @GeneratedValue (strategy = GenerationType.IDENTITY) //Esto hace que un valor sea autoincrementable
    private Integer id;

    @Column (name = "name_of", nullable = false, length = 100)
    private String nameOf;

    @Column (nullable = false, unique = true, length = 150) //El nombre unicamente va si al crear la variable esta puede crear un conflicto
    private String email;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false)
    private Boolean available = true;

    public User(Integer id, String nameOf, String email, String password, Boolean available) {
        this.id = id;
        this.nameOf = nameOf;
        this.email = email;
        this.password = password;
        this.available = available;
    }

    // Java crea por default un constructor vacio, JPA busca un constructor vacio (El que creamos)
    public User() {
    }

    //Se crean los Getters y Setters

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nameOf='" + nameOf + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(nameOf, user.nameOf) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(available, user.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOf, email, password, available);
    }

}
