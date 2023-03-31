package org.lessons.java.springlamiapizzeriacrud.repository;

import org.lessons.java.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    public List<Pizza> findByNameContainingIgnoreCase(String name);

    public List<Pizza> findByNameContainingIgnoreCaseOrderByName(String name);

    @Query(value = "select name from pizzas", nativeQuery = true)
    public List<String> getNames();
}
