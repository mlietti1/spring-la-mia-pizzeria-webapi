package org.lessons.java.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.java.springlamiapizzeriacrud.model.Ingredient;
import org.lessons.java.springlamiapizzeriacrud.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/pizzas/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String index(@RequestParam("id") Optional<Integer> idParam, Model model) {
        model.addAttribute("list", ingredientService.getAll());
        if (idParam.isPresent()) {
            model.addAttribute("ingredientObj", ingredientService.getById(idParam.get()));
        } else {
            model.addAttribute("ingredientObj", new Ingredient());
        }

        return "ingredients/index";
    }

    @PostMapping("/save")
    public String doSave(@Valid @ModelAttribute(name = "ingredientObj") Ingredient ingredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("list", ingredientService.getAll());
            return "ingredients/index";
        }
        if (ingredient.getId() != null) {
            ingredientService.update(ingredient);
        } else {
            ingredientService.create(ingredient);
        }
        ingredientService.create(ingredient);
        return "redirect:/pizzas/ingredients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        ingredientService.deleteById(id);

        return "redirect:/pizzas/ingredients";
    }
}
