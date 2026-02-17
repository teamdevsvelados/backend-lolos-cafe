package com.mx.loloscafe.backend_server.service;

import com.mx.loloscafe.backend_server.model.OrderItems;
import com.mx.loloscafe.backend_server.repository.OrderItemsRepository;
import com.mx.loloscafe.backend_server.model.Option;
import com.mx.loloscafe.backend_server.repository.OptionRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;
    private final OptionRepository optionRepository;
    private final ProductSizePriceRepository productSizePriceRepository;

    @Autowired
    public OrderItemsService(OrderItemsRepository orderItemsRepository, OptionRepository optionRepository, ProductSizePriceRepository productSizePriceRepository) {
        this.orderItemsRepository = orderItemsRepository;
        this.optionRepository = optionRepository;
        this.productSizePriceRepository = productSizePriceRepository;
    }

    // Calculate baseprice
    private BigDecimal calculateBasePrice(OrderItems item) {
        // productsizeprice stuff goes here
    }

    // Calculate total item price (total extras and total line)
    private void recalculateTotals(OrderItems item) {

        // Validate that item quantity is at least 1
        if (item.getQuantity() == null || item.getQuantity() < 1) {
            throw new IllegalArgumentException("Quantity must be >= 1");
        }

        // Validate that base price has a value
        if (item.getBasePrice() == null) {
            throw new IllegalArgumentException("basePrice is required to calculate item total");
        }

        // Makes sure that even if there are no options, the empty set exists
        if (item.getOptions() == null) {
            item.setOptions(new HashSet<>());
        }

        // Sum extras(options) per unit
        BigDecimal extrasPerUnit = item.getOptions().stream()
                .filter(opt -> opt.getAvailable() == null || opt.getAvailable())
                .map(opt -> opt.getExtraPrice() == null ? BigDecimal.ZERO : opt.getExtraPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal qty = BigDecimal.valueOf(item.getQuantity());

        BigDecimal totalExtras = extrasPerUnit.multiply(qty);

        item.setTotalExtras(totalExtras);

        BigDecimal baseTotal = item.getBasePrice().multiply(qty);

        item.setTotalLine(baseTotal.add(totalExtras));
    }


    // Create item, no options yet.
    @Transactional
    public OrderItems addItem(OrderItems newItem){
        // Validate that item has an order
        if (newItem.getOrder() == null) {
            throw new IllegalArgumentException("Item requires an order");
        }

        // Validate that a product exists
        if (newItem.getProduct() == null) {
            throw new IllegalArgumentException("Item requires a product");
        }

        // Validate that size exists
        if (newItem.getSize() == null) {
            throw new IllegalArgumentException("Item requires a size");
        }

        newItem.setBasePrice(calculateBasePrice(newItem));

        recalculateTotals(newItem);

        return orderItemsRepository.save(newItem);
    }

    // addOptionToItem method
    @Transactional
    public OrderItems addOptionToItem(Integer itemId, Integer optionId) {
        // Validate that OrderItem exists
        OrderItems item = orderItemsRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("OrderItem with id " + itemId + " not found."));

        // Validate that option exists
        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new IllegalArgumentException("Option with id " + optionId + " not found."));

        // Validate that option is set as available (value not null and not false)
        if (option.getAvailable() != null && !option.getAvailable()) {
            throw new IllegalStateException("Option with id " + optionId + " is not available.");
        }

        // Ensure options set exists (avoid null and duplicates)
        if (item.getOptions() == null) {
            item.setOptions(new HashSet<>());
        }

        // Add option
        item.getOptions().add(option);

        // Recalculate total
        recalculateTotals(item);

        // Save modified item
        return orderItemsRepository.save(item);
    }

    // removeOptionFromItem
    @Transactional
    public OrderItems removeOptionFromItem(Integer itemId, Integer optionId){
        // Validate that OrderItem exists
        OrderItems item = orderItemsRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("OrderItem with id " + itemId + " not found."));

        // If no options, nothing to remove
        if (item.getOptions() == null || item.getOptions().isEmpty()) {
            return item;
        }

        boolean removed = item.getOptions()
                .removeIf(opt -> opt.getId() != null && opt.getId().equals(optionId));
        if (!removed) {
            throw new IllegalArgumentException("Option not attached to item: " + optionId);
        }

        recalculateTotals(item);

        return orderItemsRepository.save(item);
    }

    // updateItem
    @Transactional
    public OrderItems updateItem(Integer itemId, OrderItems updatedData) {

        // Validate that item exists
        OrderItems existing = orderItemsRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("OrderItem with id " + itemId + " not found."));

        // quantity
        if (updatedData.getQuantity() != null) {
            if (updatedData.getQuantity() < 1) {
                throw new IllegalArgumentException("Quantity must be equal or greater than 1");
            }
            existing.setQuantity(updatedData.getQuantity());
        }

        if (updatedData.getItemNotes() != null) {
            existing.setItemNotes(updatedData.getItemNotes());
        }

        boolean sizeChanged = updatedData.getSize() != null
                && updatedData.getSize().getId() != null
                && (existing.getSize() == null
                || !updatedData.getSize().getId().equals(existing.getSize().getId()));

        boolean productChanged = updatedData.getProduct() != null
                && updatedData.getProduct().getId() != null
                && (existing.getProduct() == null
                || !updatedData.getProduct().getId().equals(existing.getProduct().getId()));

        if (updatedData.getSize() != null) existing.setSize(updatedData.getSize());
        if (updatedData.getProduct() != null) existing.setProduct(updatedData.getProduct());

        if (sizeChanged || productChanged) {
            existing.setBasePrice(calculateBasePrice(existing));
        }

        recalculateTotals(existing);

        return orderItemsRepository.save(existing);
    }

    // Delete item
    @Transactional
    public OrderItems deleteItem(Integer itemId) {
        OrderItems existing = orderItemsRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("OrderItem with id " + itemId + " not found."));

        orderItemsRepository.delete(existing);

        return existing;
    }

    @Transactional
    public OrderItems getItemById(Integer itemId) {
        return orderItemsRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("OrderItem with id " + itemId + " not found."));
    }

    @Transactional
    public List<OrderItems> getItemsByOrderId(Integer orderId) {
        return orderItemsRepository.findByOrder_Id(orderId);
    }
}
