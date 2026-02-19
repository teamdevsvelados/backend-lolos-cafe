package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.ProductSizePrice;
import com.mx.loloscafe.backend_server.model.ProductSizePriceId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductSizePriceRepository extends JpaRepository<ProductSizePrice, ProductSizePriceId> {

    List<ProductSizePrice> findByProduct_Id(Integer productId);

    Optional<ProductSizePrice> findByProduct_IdAndSize_Id(Integer productId, Integer sizeId);

    boolean existsByProduct_IdAndSize_Id(Integer productId, Integer sizeId);
}
