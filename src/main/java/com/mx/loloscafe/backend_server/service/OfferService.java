package com.mx.loloscafe.backend_server.service;

import com.mx.loloscafe.backend_server.exceptions.OfferNotFoundException;
import com.mx.loloscafe.backend_server.model.Offer;
import com.mx.loloscafe.backend_server.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {
    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    // List all offers.
    public List<Offer> getOffers() {
        return offerRepository.findAll();
    }

        // List only active offers (available=true, current date between startDate/endDate or null)
        public List<Offer> getActiveOffers() {
            return offerRepository.findAll().stream()
                    .filter(offer -> Boolean.TRUE.equals(offer.getAvailable()))
                    .filter(offer -> {
                        var now = java.time.LocalDate.now();
                        return (offer.getStartDate() == null || !now.isBefore(offer.getStartDate())) &&
                               (offer.getEndDate() == null || !now.isAfter(offer.getEndDate()));
                    })
                    .toList();
        }

    // Create a new offer.
    public Offer createOffer(Offer newOffer) {
        return offerRepository.save(newOffer);
    }

    // Fetch a single offer or throw if missing.
    public Offer findById(Integer id) {
        return offerRepository.findById(id).orElseThrow(() -> new OfferNotFoundException(id));
    }

    // Delete by id if present.
    public void deleteOfferById(Integer id) {
        if (offerRepository.existsById(id)) {
            offerRepository.deleteById(id);
        } else {
            throw new OfferNotFoundException(id);
        }
    }

    // Update mutable fields on the existing offer.
    public Offer updateOfferById(Offer offer, Integer id) {
        return offerRepository.findById(id)
                .map(offerData -> {
                    offerData.setNameOf(offer.getNameOf());
                    offerData.setDescriptionOf(offer.getDescriptionOf());
                    offerData.setDiscountType(offer.getDiscountType());
                    offerData.setValueOf(offer.getValueOf());
                    offerData.setAvailable(offer.getAvailable());
                    offerData.setStartDate(offer.getStartDate());
                    offerData.setEndDate(offer.getEndDate());
                    return offerRepository.save(offerData);
                })
                .orElseThrow(() -> new OfferNotFoundException(id));
    }
}
