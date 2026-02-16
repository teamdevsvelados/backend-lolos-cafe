package com.mx.loloscafe.backend_server.model;


import com.mx.loloscafe.backend_server.model.enums.ProductType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Products {
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
    private Boolean haveCoffe = true;

    @Column(name = "url_image")
    private String urlImage;

    @Column (nullable = false)
    private Boolean available = true;

    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

        @ManyToMany(mappedBy = "products")
        private java.util.Set<Offer> offers = new java.util.HashSet<>();

        public java.util.Set<Offer> getOffers() {
            return offers;
        }
        public void setOffers(java.util.Set<Offer> offers) {
            this.offers = offers;
        }

//    name_of VARCHAR(100) NOT NULL,
//    description VARCHAR(255),
//    type ENUM('BEBIDA','POSTRE','EXTRA','PROMO') NOT NULL,
//    have_coffe TINYINT(1) NOT NULL DEFAULT 0,
//    url_image VARCHAR(255),
//    available TINYINT(1) NOT NULL DEFAULT 1,
//    date_creation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

}
