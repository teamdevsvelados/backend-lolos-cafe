package com.mx.loloscafe.backend_server.service;

import com.mx.loloscafe.backend_server.model.Product;
import com.mx.loloscafe.backend_server.repository.ProductRepository;
import com.mx.loloscafe.backend_server.repository.OrderItemRepository;
import com.mx.loloscafe.backend_server.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    /// /////////////////////////////
    ///         GETTERS            //
    /// /////////////////////////////

    // ADMIN: todos los productos (activos + inactivos)
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // CLIENTE: solo productos disponibles
    public List<Product> getAvailable() {
        return productRepository.findByAvailable();
    }

    // CLIENTE: productos disponibles por categoría
    public List<Product> getAvailableByCategory(Integer categoryId) {
        return productRepository.findByCategoryIdAvailable(categoryId);
    }

    // Buscar por ID
    public Product findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id)); //this is OrderItemException
    }

    /// /////////////////////////////
    ///         CREATE             //
    /// /////////////////////////////

    // ADMIN: crear producto
    public Product create(Product newProduct) {

        // Por seguridad, al crear siempre disponible
        newProduct.setAvailable(true);
        return productRepository.save(newProduct);
    }

    /// /////////////////////////////
    ///         UPDATE             //
    /// /////////////////////////////

    // ADMIN: actualizar producto
    public Product update(Integer id, Product product) {

        return productRepository.findById(id)
                .map(existing -> {
                    existing.setNameOf(product.getNameOf());
                    existing.setDescription(product.getDescription());
                    existing.setType(product.getType());
                    existing.setHaveCoffe(product.isHaveCoffe());
                    existing.setUrlImage(product.getUrlImage());
//                    existing.setCategory(product.getCategory());

                    return productRepository.save(existing);
                })
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    /// /////////////////////////////
    ///         DELETE             //
    /// /////////////////////////////

    // ADMIN: soft delete
    public void disable(Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setAvailable(false);
        productRepository.save(product);
    }


    // ADMIN: delete SOLO si no está referenciado
    public void deleteIfUnused(Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        boolean isReferenced = orderItemRepository.existsByProductId(id); //check

        if (isReferenced) {
            // if referenced → deactivated
            product.setAvailable(false);
            productRepository.save(product);
        } else {
            // not  referenced → delete
            productRepository.deleteById(id);
        }
    }

}
