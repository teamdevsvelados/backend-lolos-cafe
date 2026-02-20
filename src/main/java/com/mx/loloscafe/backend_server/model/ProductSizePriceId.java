package com.mx.loloscafe.backend_server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductSizePriceId implements Serializable {

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "size_id")
    private Integer sizeId;

    protected ProductSizePriceId() {}

    public ProductSizePriceId(Integer productId, Integer sizeId) {
        this.productId = productId;
        this.sizeId = sizeId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    @Override
    public String toString() {
        return "ProductSizePriceId{" +
                "productId=" + productId +
                ", sizeId=" + sizeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductSizePriceId)) return false;
        ProductSizePriceId that = (ProductSizePriceId) o;
        return Objects.equals(productId, that.productId)
                && Objects.equals(sizeId, that.sizeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, sizeId);
    }
}
