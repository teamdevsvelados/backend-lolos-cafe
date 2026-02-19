package com.mx.loloscafe.backend_server.controller;

import com.mx.loloscafe.backend_server.model.OrderItems;
import com.mx.loloscafe.backend_server.service.OrderItemsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemsController {
    private final OrderItemsService orderItemsService;

    public OrderItemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    // Gets all items with the same order id
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItems>> getItemsByOrderId(@PathVariable Integer orderId) {
        List<OrderItems> items = orderItemsService.getItemsByOrderId(orderId);
        return ResponseEntity.ok(items);
    }

    // Get one order item by its id
    @GetMapping("/{itemId}")
    public ResponseEntity<OrderItems> getItemById(@PathVariable Integer itemId) {
        OrderItems item = orderItemsService.getItemById(itemId);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<OrderItems> addItem(@Valid @RequestBody OrderItems newItem) {
        OrderItems created = orderItemsService.addItem(newItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<OrderItems> updateItem(@Valid @PathVariable Integer itemId, @RequestBody OrderItems updatedData) {
        OrderItems updated = orderItemsService.updateItem(itemId, updatedData);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<OrderItems> deleteItem(@PathVariable Integer itemId) {
        OrderItems deleted = orderItemsService.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }

    // Add an option to an item
    @PostMapping("/{itemId}/options/{optionId}")
    public ResponseEntity<OrderItems> addOptionToItem(@PathVariable Integer itemId, @PathVariable Integer optionId) {
        OrderItems updated = orderItemsService.addOptionToItem(itemId, optionId);
        return ResponseEntity.ok(updated);
    }

    // Remove an option from an item
    @DeleteMapping("/{itemId}/options/{optionId}")
    public ResponseEntity<OrderItems> removeOptionFromItem(@PathVariable Integer itemId, @PathVariable Integer optionId) {
        OrderItems updated = orderItemsService.removeOptionFromItem(itemId, optionId);
        return ResponseEntity.ok(updated);
    }
}
