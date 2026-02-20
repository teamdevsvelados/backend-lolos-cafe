package com.mx.loloscafe.backend_server.controller;

import com.mx.loloscafe.backend_server.model.Product;
import com.mx.loloscafe.backend_server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //debo obtener todos los productos que existen para admin y para cliente

    /// /////////////////////////////
    ///         CLIENTE            //
    /// /////////////////////////////

    // Cliente: ver productos DISPONIBLES
    @GetMapping
    public ResponseEntity<List<Product>> getAvailableProducts() { //A ResponseEntity whose body is a list of Products
        List<Product> products = productService.getAvailable();

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(products);
    }

    // Cliente: ver productos DISPONIBLES por categoría
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getAvailableByCategory(
            @PathVariable Integer categoryId) {

        List<Product> products =
                productService.getAvailableByCategory(categoryId);

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(products);
    }

    /// /////////////////////////////
    ///         ADMIN              //
    /// /////////////////////////////

    // Admin: ver TODOS los productos (activos + inactivos)
    @GetMapping("/admin")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> products = productService.getAll();

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(products);
    }

    // Admin: crear producto
    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> saveProduct(@RequestBody Product newProduct) {

        Product created = productService.create(newProduct);
        return new ResponseEntity<>(created, HttpStatus.CREATED); //201
    }

    // Admin: actualizar producto, admin quiere actualizar producto
    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Integer id,
            @RequestBody Product product) {

        Product updated = productService.update(id, product);

        return ResponseEntity.ok(updated);
    }

    // Admin: desactivar producto
    @PatchMapping("/{id}/disable")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> disableProduct(@PathVariable Integer id) {

        productService.disable(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}") //
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> DeleteProduct(@PathVariable Integer id) {
        productService.deleteIfUnused(id);
        return ResponseEntity.noContent().build();
    }

}
