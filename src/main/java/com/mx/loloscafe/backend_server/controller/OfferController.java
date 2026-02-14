package com.mx.loloscafe.backend_server.controller;

import com.mx.loloscafe.backend_server.exceptions.OfferNotFoundException;
import com.mx.loloscafe.backend_server.model.Offer;
import com.mx.loloscafe.backend_server.service.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offers")
public class OfferController {

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
    @PostMapping("/new-offer")
    public ResponseEntity<Offer> saveOffer(@RequestBody Offer newOffer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(offerService.createOffer(newOffer));
    }

    // Fetch a single offer by id.
    @GetMapping("/{id}")
    public ResponseEntity<Offer> findOfferById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(offerService.findById(id));
        } catch (OfferNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete an offer by id.
    @DeleteMapping("/delete-offer/{id}")
    public ResponseEntity<Offer> deleteOfferById(@PathVariable Integer id) {
        try {
            offerService.deleteOfferById(id);
            return ResponseEntity.noContent().build();
        } catch (OfferNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing offer.
    @PutMapping("/update-offer/{id}")
    public ResponseEntity<Offer> updateOffer(@RequestBody Offer offer, @PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(offerService.updateOfferById(offer, id));
        } catch (OfferNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
