package com.mx.loloscafe.backend_server.controller;

import com.mx.loloscafe.backend_server.exceptions.OfferNotFoundException;
import com.mx.loloscafe.backend_server.exceptions.ErrorResponse;
import com.mx.loloscafe.backend_server.model.Offer;
import com.mx.loloscafe.backend_server.service.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offers")
public class OfferController {

    @ExceptionHandler(OfferNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOfferNotFound(OfferNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage(), 404));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst().orElse("Datos inválidos");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(msg, 400));
    }

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    // List all offers.
    @GetMapping
    public List<Offer> getAllOffers() {
        return offerService.getOffers();
    }

    // Create a new offer.
    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Offer> saveOffer(@RequestBody @Valid Offer newOffer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(offerService.createOffer(newOffer));
    }

    // Fetch a single offer by id.
    @GetMapping("/{id}")
    public ResponseEntity<Offer> findOfferById(@PathVariable Integer id) {
        return ResponseEntity.ok(offerService.findById(id));
    }

    // Delete an offer by id.
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Void> deleteOfferById(@PathVariable Integer id) {
        offerService.deleteOfferById(id);
        return ResponseEntity.noContent().build();
    }

    // Update an existing offer.
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Offer> updateOffer(@RequestBody @Valid Offer offer, @PathVariable Integer id) {
        return ResponseEntity.ok(offerService.updateOfferById(offer, id));
    }
}
