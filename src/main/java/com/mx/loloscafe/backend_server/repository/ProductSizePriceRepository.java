package com.mx.loloscafe.backend_server.repository;

import java.util.Optional;

public interface ProductSizePriceRepository {
    Optional<Object> findByProduct_IdAndSize_Id(Integer productId, Integer sizeId);
}
