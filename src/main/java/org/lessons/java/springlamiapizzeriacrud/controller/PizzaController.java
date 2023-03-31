package org.lessons.java.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.java.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {
        List<Pizza> pizzas;
        if (keyword.isEmpty()) {
            pizzas = pizzaService.getAllPizzas();
        } else {
            pizzas = pizzaService.getFilteredPizzas(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("list", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        try {
            Pizza pizza = pizzaService.getById(id);
            model.addAttribute("pizza", pizza);
            return "pizzas/show";
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found.");
        }

        /*Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("pizza", result.get());
            return "pizzas/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found.");
        }*/
        // con lambda:
        // Pizza p = pizzaRepository.findById(id).orElseThrow()->new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "pizzas/create";
        }
        pizzaService.createPizza(formPizza);
        return "redirect:/pizzas";
    }

    /*
    @GetMapping("/search")
    public String search(Model model, @RequestParam(name = "q") String keyword) {
      System.out.println(pizzaRepository.findByNameContainingIgnoreCaseOrderByCreatedAtDesc(keyword));
    List<Pizza> filteredPizzas = pizzaRepository.findByNameContainingIgnoreCase(keyword);
    model.addAttribute("list", filteredPizzas);
    return "/pizzas/index";
    }
    */


}
