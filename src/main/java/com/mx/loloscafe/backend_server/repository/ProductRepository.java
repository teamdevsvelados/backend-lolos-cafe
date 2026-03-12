package com.mx.loloscafe.backend_server.repository;

import com.mx.loloscafe.backend_server.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByAvailableTrue();

    // List <Product> findByCategoryIdAvailable(Integer categoryId);

    boolean existsByNameOf(String nameOf);
}
