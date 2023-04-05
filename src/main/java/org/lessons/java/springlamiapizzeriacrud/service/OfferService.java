package org.lessons.java.springlamiapizzeriacrud.service;

import org.lessons.java.springlamiapizzeriacrud.model.Offer;
import org.lessons.java.springlamiapizzeriacrud.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public Offer create(Offer formOffer) {

        return offerRepository.save(formOffer);
    }
}
