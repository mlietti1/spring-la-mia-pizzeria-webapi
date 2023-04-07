package org.lessons.java.springlamiapizzeriacrud.service;

import org.lessons.java.springlamiapizzeriacrud.model.Ingredient;
import org.lessons.java.springlamiapizzeriacrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAll() {
        return ingredientRepository.findAll(Sort.by("name"));
    }

    public Ingredient create(Ingredient formIngredient) {

        return ingredientRepository.save(formIngredient);
    }

    public Ingredient update(Ingredient formIngredient) {
        return ingredientRepository.save(formIngredient);
    }

    public Ingredient getById(Integer id) {
        return ingredientRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void deleteById(Integer id) {
        ingredientRepository.deleteById(id);
    }
}
