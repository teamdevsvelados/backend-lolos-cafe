package com.mx.loloscafe.backend_server.controller;

import com.mx.loloscafe.backend_server.model.ProductSizePrice;
import com.mx.loloscafe.backend_server.service.ProductSizePriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/product-size-price")
public class ProductSizePriceController {

    private final ProductSizePriceService service;

    public ProductSizePriceController(ProductSizePriceService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductSizePrice>> getAll() {
        List<ProductSizePrice> list = service.getAll();
        if (list.isEmpty()) return ResponseEntity.noContent().build(); // 204
        return ResponseEntity.ok(list);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductSizePrice>> getByProduct(@PathVariable Integer productId) {
        List<ProductSizePrice> list = service.getByProduct(productId);
        if (list.isEmpty()) return ResponseEntity.noContent().build(); // 204
        return ResponseEntity.ok(list);
    }

    @GetMapping("/product/{productId}/size/{sizeId}")
    public ResponseEntity<ProductSizePrice> getOne(@PathVariable Integer productId,
                                                   @PathVariable Integer sizeId) {
        return ResponseEntity.ok(service.getOne(productId, sizeId));
    }

    // endpoint "rápido" para obtener solo el precio
    @GetMapping("/product/{productId}/size/{sizeId}/precio")
    public ResponseEntity<BigDecimal> getPrecio(@PathVariable Integer productId,
                                                @PathVariable Integer sizeId) {
        return ResponseEntity.ok(service.getPrecio(productId, sizeId));
    }

    // POST y PUT sin DTO: lo más simple es usar @RequestParam
    @PostMapping
    public ResponseEntity<ProductSizePrice> create(@RequestParam Integer productId,
                                                   @RequestParam Integer sizeId,
                                                   @RequestParam BigDecimal precio) {
        return ResponseEntity.status(201).body(service.create(productId, sizeId, precio));
    }

    @PutMapping("/product/{productId}/size/{sizeId}")
    public ResponseEntity<ProductSizePrice> updatePrecio(@PathVariable Integer productId,
                                                         @PathVariable Integer sizeId,
                                                         @RequestParam BigDecimal precio) {
        return ResponseEntity.ok(service.updatePrecio(productId, sizeId, precio));
    }

    @DeleteMapping("/product/{productId}/size/{sizeId}")
    public ResponseEntity<Void> delete(@PathVariable Integer productId,
                                       @PathVariable Integer sizeId) {
        service.delete(productId, sizeId);
        return ResponseEntity.noContent().build(); // 204
    }
}
