package com.mx.loloscafe.backend_server.model;

import com.mx.loloscafe.backend_server.model.enums.OptionType;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** Entity to explain extra option of our products
* Example: Tipo de leche, shots extra, etc.
*/
@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /// ////////////////////////
    ///      RELATIONS        //
    ////////////////////////////

    @ManyToMany(mappedBy = "allowedOptions")
    //N:M Option ← Product
    private Set<Product> products = new HashSet<>();

    /////// - Attributes -

    // Mapping enum "OptionType"
    @Enumerated(EnumType.STRING)
    @Column(name = "typeOf", nullable = false)
    private OptionType type;

    @Column(name = "name_Of", nullable = false, length = 50)
    private String name;

    @Column(name = "extra_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal extraPrice;

    @Column(nullable = false)
    private Boolean available = true;

    // Constructor method - JPA Constructor
    public Option(Integer id, OptionType type, String name, BigDecimal extraPrice, Boolean available) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.extraPrice = extraPrice;
        this.available = available;
    }

    // Empty Constructure
    public Option() {
    }

    // Setters and Getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", extraPrice=" + extraPrice +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Option option)) return false;
        return Objects.equals(id, option.id) && type == option.type && Objects.equals(name, option.name) && Objects.equals(extraPrice, option.extraPrice) && Objects.equals(available, option.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, extraPrice, available);
    }
}