package com.mx.loloscafe.backend_server.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/** Entity to explain extra option of our products
* Example: Tipo de leche, shots extra, etc.
*/
@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mapping enum "OptionType"
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of")
    private OptionType type;

    @Column(name = "name_of", length = 50)
    private String name;

    @Column(name = "extra_price")
    private BigDecimal extraPrice;

    // JPA Constructor
    public Option() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OptionType getType() {
        return type;
    }

    public void setType(OptionType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(BigDecimal extraPrice) {
        this.extraPrice = extraPrice;
    }
}