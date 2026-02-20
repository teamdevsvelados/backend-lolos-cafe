package com.mx.loloscafe.backend_server.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "product_size_price")
public class ProductSizePrice {

    @EmbeddedId //due to composite key
    private ProductSizePriceId id;

    /// ////////////////////////
    ///      RELATIONS        //
    ////////////////////////////

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("sizeId")
    @JoinColumn(name = "size_id")
    private Size size;

    /////// - Attribute
    @Column(nullable = false)
    private BigDecimal price;

    // Constructor
    public ProductSizePrice(Product product, Size size, BigDecimal price) {
        this.id = new ProductSizePriceId(product.getId(), size.getId());
        this.product = product;
        this.size = size;
        this.price = price;
    }

    //Empty constructor
    public ProductSizePrice() {}

    public ProductSizePriceId getId() {
        return id;
    }

    public void setId(ProductSizePriceId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductSizePrice{" +
                "id=" + id +
                ", product=" + product +
                ", size=" + size +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductSizePrice that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(product, that.product) && Objects.equals(size, that.size) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, size, price);
    }
}
