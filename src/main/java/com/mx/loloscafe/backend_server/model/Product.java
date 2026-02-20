package com.mx.loloscafe.backend_server.model;

import com.mx.loloscafe.backend_server.model.enums.ProductType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This annotation makes a value auto-incrementable
    private Integer id;

    @Column (name = "name_of", nullable = false, length = 100)
    private String nameOf;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ProductType type;

    @Column(name = "have_coffe", nullable = false)
    private Boolean hasCoffe = true;

    @Column(name = "url_image")
    private String urlImage;

    @Column (nullable = false)
    private Boolean available = true;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    public Product(Integer id, String nameOf, String description, ProductType type, String urlImage, Boolean hasCoffe, Boolean available, LocalDateTime dateCreation) {
        this.id = id;
        this.nameOf = nameOf;
        this.description = description;
        this.type = type;
        this.urlImage = urlImage;
        this.hasCoffe = hasCoffe;
        this.available = available;
        this.dateCreation = dateCreation;
    }

    public Product(){} // constructor single

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameOf() {
        return nameOf;
    }

    public void setNameOf(String nameOf) {
        this.nameOf = nameOf;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public boolean hasCoffe() {
        return hasCoffe;
    }

    public void setHasCoffe(Boolean hasCoffe) {
        this.hasCoffe = hasCoffe;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }


    //    name_of VARCHAR(100) NOT NULL,
//    description VARCHAR(255),
//    type ENUM('BEBIDA','POSTRE','EXTRA','PROMO') NOT NULL,
//    have_coffe TINYINT(1) NOT NULL DEFAULT 0,
//    url_image VARCHAR(255),
//    available TINYINT(1) NOT NULL DEFAULT 1,
//    date_creation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

}
