package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.ProductSizePrice;
import com.mx.loloscafe.backend_server.model.ProductSizePriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductSizePriceRepository extends JpaRepository<ProductSizePrice, ProductSizePriceId> {
    Optional<ProductSizePrice> findByProduct_IdAndSize_Id(Integer productId, Integer sizeId);
}
