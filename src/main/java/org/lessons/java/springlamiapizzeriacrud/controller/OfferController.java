package org.lessons.java.springlamiapizzeriacrud.controller;

import org.lessons.java.springlamiapizzeriacrud.model.Offer;
import org.lessons.java.springlamiapizzeriacrud.repository.OfferRepository;
import org.lessons.java.springlamiapizzeriacrud.service.OfferService;
import org.lessons.java.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    private String create(@RequestParam(name = "pizzaId") Optional<Integer> id, Model model) {
        Offer offer = new Offer();

        return "/offers/create";
    }
}
