package org.lessons.java.springlamiapizzeriacrud.service;

import org.lessons.java.springlamiapizzeriacrud.exceptions.OfferNotFoundException;
import org.lessons.java.springlamiapizzeriacrud.model.Offer;
import org.lessons.java.springlamiapizzeriacrud.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public Offer create(Offer formOffer) {

        return offerRepository.save(formOffer);
    }

    public Offer update(Offer formOffer) throws OfferNotFoundException {

        return offerRepository.save(formOffer);
    }

    public Offer getById(Integer id) throws OfferNotFoundException {
        Optional<Offer> result = offerRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new OfferNotFoundException(Integer.toString(id));
        }
    }
}
