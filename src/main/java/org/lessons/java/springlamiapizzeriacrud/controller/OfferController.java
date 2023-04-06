package org.lessons.java.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.java.springlamiapizzeriacrud.exceptions.OfferNotFoundException;
import org.lessons.java.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.lessons.java.springlamiapizzeriacrud.model.Offer;
import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.service.OfferService;
import org.lessons.java.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas/offers")
public class OfferController {
    @Autowired
    private OfferService offerService;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/create")
    public String create(@RequestParam(name = "pizzaId") Optional<Integer> id, Model model) {
        Offer offer = new Offer();
        offer.setStartDate(LocalDate.now());
        offer.setEndDate(LocalDate.now().plusMonths(1));
        if (id.isPresent()) {
            try {
                Pizza pizza = pizzaService.getById(id.get());
                offer.setPizza(pizza);
            } catch (PizzaNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        model.addAttribute("offer", offer);

        return "/offers/edit";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute Offer formOffer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/offers/edit";
        }
        Offer createdOffer = offerService.create(formOffer);
        return "redirect:/pizzas/" + Integer.toString(createdOffer.getPizza().getId());
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        try {
            Offer offer = offerService.getById(id);
            model.addAttribute("offer", offer);
            return "offers/edit";
        } catch (OfferNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offer with id " + id + " not found.");
        }
    }

    @PostMapping("edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "offers/edit";
        }
        Offer updatedOffer = offerService.update(formOffer);
        return "redirect:/pizzas/" + Integer.toString(updatedOffer.getPizza().getId());
    }
}
