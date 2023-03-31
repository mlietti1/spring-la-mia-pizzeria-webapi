package org.lessons.java.springlamiapizzeriacrud.service;


import org.lessons.java.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.lessons.java.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    public Pizza createPizza(Pizza formPizza) {
        Pizza pizzaToPersist = new Pizza();
        pizzaToPersist.setName(formPizza.getName());
        pizzaToPersist.setPrice(formPizza.getPrice());
        pizzaToPersist.setDescription(formPizza.getDescription());
        return pizzaRepository.save(pizzaToPersist);
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

    public boolean deleteById(Integer id) {
        pizzaRepository.findById(id).orElseThrow(() -> new PizzaNotFoundException(Integer.toString(id)));
        try {
            pizzaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
