package com.mx.loloscafe.backend_server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductSizePriceId implements Serializable {

    @Column(name = "id_product", nullable = false)
    private Integer productId;

    @Column(name = "id_size", nullable = false)
    private Integer sizeId;

    public ProductSizePriceId() {}

    public ProductSizePriceId(Integer productId, Integer sizeId) {
        this.productId = productId;
        this.sizeId = sizeId;
    }

    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    public Integer getSizeId() { return sizeId; }
    public void setSizeId(Integer sizeId) { this.sizeId = sizeId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductSizePriceId that)) return false;
        return Objects.equals(productId, that.productId) &&
                Objects.equals(sizeId, that.sizeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, sizeId);
    }
}
