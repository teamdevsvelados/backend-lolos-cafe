package com.mx.loloscafe.backend_server.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "product_size_price")
public class ProductSizePrice {

    @EmbeddedId
    private ProductSizePriceId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "id_product", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("sizeId")
    @JoinColumn(name = "id_size", nullable = false)
    private Size size;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    public ProductSizePrice() {}

    public ProductSizePrice(Product product, Size size, BigDecimal precio) {
        this.product = product;
        this.size = size;
        this.precio = precio;
        this.id = new ProductSizePriceId(
                product != null ? product.getId() : null,
                size != null ? size.getId() : null
        );
    }

    public ProductSizePriceId getId() { return id; }
    public void setId(ProductSizePriceId id) { this.id = id; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public Size getSize() { return size; }
    public void setSize(Size size) { this.size = size; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductSizePrice that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
