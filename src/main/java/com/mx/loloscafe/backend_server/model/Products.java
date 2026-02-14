package com.mx.loloscafe.backend_server.model;


import com.mx.loloscafe.backend_server.model.enums.ProductType;
import jakarta.persistence.*;

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


//    name_of VARCHAR(100) NOT NULL,
//    description VARCHAR(255),
//    type ENUM('BEBIDA','POSTRE','EXTRA','PROMO') NOT NULL,
//    have_coffe TINYINT(1) NOT NULL DEFAULT 0,
//    url_image VARCHAR(255),
//    available TINYINT(1) NOT NULL DEFAULT 1,
//    date_creation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,


}
