package org.lessons.java.springlamiapizzeriacrud.api;

import jakarta.validation.Valid;
import org.lessons.java.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.lessons.java.springlamiapizzeriacrud.model.Offer;
import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzaRestController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> list(@RequestParam(name = "q") Optional<String> search) {
        if (search.isPresent()) {
            return pizzaService.getFilteredPizzas(search.get());
        }
        return pizzaService.getAllPizzas();
    }

    @GetMapping("/{id}")
    public Pizza getById(@PathVariable Integer id) {
        try {
            return pizzaService.getById(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
        return pizzaService.createPizza(pizza);
    }

    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @Valid @RequestBody Pizza pizza) {
        try {
            return pizzaService.updatePizza(pizza, id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            boolean success = pizzaService.deleteById(id);
            if (!success) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Unable to delete pizza with active offer.");
            }
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/offers")
    public List<Offer> getPizzaOffers(@PathVariable("id") Integer pizzaId) {
        Pizza pizza = null;
        try {
            pizza = pizzaService.getById(pizzaId);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return pizza.getOffers();
    }
}
