package org.lessons.java.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.java.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.lessons.java.springlamiapizzeriacrud.model.AlertMessage;
import org.lessons.java.springlamiapizzeriacrud.model.AlertMessage.AlertMessageType;
import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.service.IngredientService;
import org.lessons.java.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private IngredientService ingredientService;

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
        model.addAttribute("ingredientList", ingredientService.getAll());
        return "pizzas/edit";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredientList", ingredientService.getAll());
            return "pizzas/edit";
        }
        pizzaService.createPizza(formPizza);
        return "redirect:/pizzas";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Pizza pizza = pizzaService.getById(id);
            model.addAttribute("pizza", pizza);
            model.addAttribute("ingredientList", ingredientService.getAll());
            return "pizzas/edit";
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found.");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredientList", ingredientService.getAll());
            return "pizzas/edit";
        }
        try {
            Pizza updatedPizza = pizzaService.updatePizza(formPizza, id);
            return "redirect:/pizzas/" + Integer.toString(updatedPizza.getId());
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found.");
        }

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            boolean success = pizzaService.deleteById(id);
            if (success) {
                redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.SUCCESS, "Pizza with id " + id + " deleted."));

            } else {
                // throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to delete pizza with id " + id + ".");
                redirectAttributes.addFlashAttribute("message", new AlertMessage(AlertMessageType.ERROR, "Unable to delete pizza with id " + id + "."));
            }
        } catch (PizzaNotFoundException e) {
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            redirectAttributes.addAttribute("message", new AlertMessage(AlertMessageType.ERROR, "Pizza with id " + id + " not found."));
        }
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
