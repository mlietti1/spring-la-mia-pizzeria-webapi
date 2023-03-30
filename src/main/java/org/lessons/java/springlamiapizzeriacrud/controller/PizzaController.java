package org.lessons.java.springlamiapizzeriacrud.controller;

import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {
        List<Pizza> pizzas;
        if (keyword.isEmpty()) {
            pizzas = pizzaRepository.findAll(Sort.by("name"));

        } else {
            pizzas = pizzaRepository.findByNameContainingIgnoreCaseOrderByName(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("list", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("pizza", result.get());
            return "pizzas/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found.");
        }
        // con lambda:
        // Pizza p = pizzaRepository.findById(id).orElseThrow()->new ResponseStatusException(HttpStatus.NOT_FOUND);
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
