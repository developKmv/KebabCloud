package ru.dev.A1.A1.data;

import ru.dev.A1.A1.models.Ingredient;

import java.util.Optional;

public interface IngredientRepository_jdbc {
    Iterable<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}
