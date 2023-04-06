package org.lessons.java.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.lessons.java.springlamiapizzeriacrud.model.Ingredient;
import org.lessons.java.springlamiapizzeriacrud.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pizzas/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("list", ingredientService.getAll());
        model.addAttribute("ingredientObj", new Ingredient());
        return "ingredients/index";
    }

    @PostMapping("/save")
    public String doSave(@Valid @ModelAttribute(name = "ingredientObj") Ingredient ingredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("list", ingredientService.getAll());
            return "ingredients/index";
        }
        ingredientService.create(ingredient);
        return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Ingredient ingredientToEdit = ingredientService.getById(id);
        model.addAttribute("ingredient", ingredientToEdit);
        model.addAttribute("ingredients", ingredientService.getAll());
        return "ingredients/index";
    }

    @PostMapping("/edit/{id}")
    public String doUpdate(@Valid @ModelAttribute Ingredient ingredient, BindingResult bindingResult, Model model, @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientService.getAll());
            return "ingredients/index";
        }
        ingredientService.create(ingredient);
        model.addAttribute("ingredients", ingredientService.getAll());
        return "redirect:/ingredients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        ingredientService.deleteById(id);
        return "redirect:/ingredients";
    }
}
