package com.mx.loloscafe.backend_server.model;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name="categories")
public class Categories {

    @Id // Definir la Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Esto hace que un valor sea autoincrementable
    private Integer id;

    @Column (name = "name_of", nullable = false, length = 50)
    private String nameOf;

    public Categories(Integer id, String nameOf) {
        this.id = id;
        this.nameOf = nameOf;
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
        return "Categories{" +
                "id=" + id +
                ", nameOf='" + nameOf + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Categories that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(nameOf, that.nameOf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameOf);
    }
}
