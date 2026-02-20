package com.mx.loloscafe.backend_server.model;

import com.mx.loloscafe.backend_server.model.enums.ProductType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This annotation makes a value auto-incrementable
    private Integer id;

    /// ////////////////////////
    ///      RELATIONS        //
    ////////////////////////////

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    @ManyToMany
    //M:N Product → Option
    @JoinTable(
            name = "option_product",
            joinColumns = @JoinColumn(name = "id_product"),
            inverseJoinColumns = @JoinColumn(name = "id_option")
    )
    private Set<Option> allowedOptions = new HashSet<>();


    /////// - Attributes -


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

    @Column(name = "date_creation", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime dateCreation;

    public Product(Integer id, Category category, Set<Option> allowedOptions, String nameOf, String description, ProductType type, Boolean hasCoffe, String urlImage, Boolean available, LocalDateTime dateCreation) {
        this.id = id;
        this.category = category;
        this.allowedOptions = allowedOptions;
        this.nameOf = nameOf;
        this.description = description;
        this.type = type;
        this.hasCoffe = hasCoffe;
        this.urlImage = urlImage;
        this.available = available;
        this.dateCreation = dateCreation;
    }

    public Product(){} // constructor single

    public Integer getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getHasCoffe() {
        return hasCoffe;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
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

    public Set<Option> getAllowedOptions() {
        return allowedOptions;
    }

    public void setAllowedOptions(Set<Option> allowedOptions) {
        this.allowedOptions = allowedOptions;
    }

    @PrePersist
    void prePersist() {
        if (dateCreation == null) {
            dateCreation = LocalDateTime.now();
        }
    }

    //    name_of VARCHAR(100) NOT NULL,
//    description VARCHAR(255),
//    type ENUM('BEBIDA','POSTRE','EXTRA','PROMO') NOT NULL,
//    have_coffe TINYINT(1) NOT NULL DEFAULT 0,
//    url_image VARCHAR(255),
//    available TINYINT(1) NOT NULL DEFAULT 1,
//    date_creation TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

}
