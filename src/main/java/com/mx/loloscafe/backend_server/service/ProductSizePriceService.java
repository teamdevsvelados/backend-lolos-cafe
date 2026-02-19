package com.mx.loloscafe.backend_server.service;

import com.mx.loloscafe.backend_server.model.ProductSizePrice;
import com.mx.loloscafe.backend_server.model.ProductSizePriceId;
import com.mx.loloscafe.backend_server.model.Product;
import com.mx.loloscafe.backend_server.model.Size;
import com.mx.loloscafe.backend_server.repository.ProductRepository;
import com.mx.loloscafe.backend_server.repository.ProductSizePriceRepository;
import com.mx.loloscafe.backend_server.repository.SizeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductSizePriceService {

    private final ProductSizePriceRepository repo;
    private final ProductRepository productRepo;
    private final SizeRepository sizeRepo;

    public ProductSizePriceService(ProductSizePriceRepository repo,
                                   ProductRepository productRepo,
                                   SizeRepository sizeRepo) {
        this.repo = repo;
        this.productRepo = productRepo;
        this.sizeRepo = sizeRepo;
    }

    public List<ProductSizePrice> getAll() {
        return repo.findAll();
    }

    public List<ProductSizePrice> getByProduct(Integer productId) {
        return repo.findByProduct_Id(productId);
    }

    public ProductSizePrice getOne(Integer productId, Integer sizeId) {
        return repo.findByProduct_IdAndSize_Id(productId, sizeId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe product_size_price para id_product=" + productId + " y id_size=" + sizeId
                ));
    }

    public BigDecimal getPrecio(Integer productId, Integer sizeId) {
        return getOne(productId, sizeId).getPrecio();
    }

    public ProductSizePrice create(Integer productId, Integer sizeId, BigDecimal precio) {
        if (precio == null || precio.signum() < 0) {
            throw new IllegalArgumentException("precio must be >= 0");
        }
        if (repo.existsByProduct_IdAndSize_Id(productId, sizeId)) {
            throw new IllegalArgumentException("Ya existe un precio para ese producto y size");
        }

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + productId));

        Size size = sizeRepo.findById(sizeId)
                .orElseThrow(() -> new IllegalArgumentException("Size no encontrado: " + sizeId));

        ProductSizePrice psp = new ProductSizePrice();
        psp.setId(new ProductSizePriceId(productId, sizeId));
        psp.setProduct(product);
        psp.setSize(size);
        psp.setPrecio(precio);

        return repo.save(psp);
    }

    public ProductSizePrice updatePrecio(Integer productId, Integer sizeId, BigDecimal nuevoPrecio) {
        if (nuevoPrecio == null || nuevoPrecio.signum() < 0) {
            throw new IllegalArgumentException("precio must be >= 0");
        }

        ProductSizePrice existing = getOne(productId, sizeId);
        existing.setPrecio(nuevoPrecio);
        return repo.save(existing);
    }

    public void delete(Integer productId, Integer sizeId) {
        ProductSizePriceId id = new ProductSizePriceId(productId, sizeId);
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("No existe registro para borrar");
        }
        repo.deleteById(id);
    }
}
