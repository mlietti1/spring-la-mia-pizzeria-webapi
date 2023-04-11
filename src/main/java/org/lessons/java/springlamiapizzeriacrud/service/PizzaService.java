package org.lessons.java.springlamiapizzeriacrud.service;


import org.lessons.java.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.lessons.java.springlamiapizzeriacrud.model.Ingredient;
import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.repository.IngredientRepository;
import org.lessons.java.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    public Pizza createPizza(Pizza formPizza) {
        Pizza pizzaToPersist = new Pizza();
        pizzaToPersist.setName(formPizza.getName());
        pizzaToPersist.setPrice(formPizza.getPrice());
        pizzaToPersist.setDescription(formPizza.getDescription());
        Set<Ingredient> formIngredients = getPizzaIngredients(formPizza);
        pizzaToPersist.setIngredients(formIngredients);
        pizzaToPersist.setCreatedAt(LocalDateTime.now());
        pizzaToPersist.setUpdatedAt(LocalDateTime.now());
        return pizzaRepository.save(pizzaToPersist);
    }

    public Pizza updatePizza(Pizza formPizza, Integer id) throws PizzaNotFoundException {
        Pizza pizzaToUpdate = getById(id);
        pizzaToUpdate.setName(formPizza.getName());
        pizzaToUpdate.setPrice(formPizza.getPrice());
        pizzaToUpdate.setDescription(formPizza.getDescription());
        Set<Ingredient> formIngredients = getPizzaIngredients(formPizza);
        pizzaToUpdate.setIngredients(formIngredients);
        pizzaToUpdate.setUpdatedAt(LocalDateTime.now());
        return pizzaRepository.save(pizzaToUpdate);
    }

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll(Sort.by("name"));
    }

    public List<Pizza> getFilteredPizzas(String keyword) {
        return pizzaRepository.findByNameContainingIgnoreCaseOrderByName(keyword);
    }

    public Pizza getById(Integer id) throws PizzaNotFoundException {
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PizzaNotFoundException(Integer.toString(id));
        }
    }

    public boolean deleteById(Integer id) throws PizzaNotFoundException {
        pizzaRepository.findById(id).orElseThrow(() -> new PizzaNotFoundException(Integer.toString(id)));
        try {
            pizzaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Set<Ingredient> getPizzaIngredients(Pizza formPizza) {
        Set<Ingredient> formIngredients = new HashSet<>();
        for (Ingredient i : formPizza.getIngredients()) {
            formIngredients.add(ingredientRepository.findById(i.getId()).orElseThrow());
        }
        return formIngredients;
    }
}
