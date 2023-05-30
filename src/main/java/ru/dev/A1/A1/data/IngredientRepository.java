package ru.dev.A1.A1.data;

import org.springframework.data.repository.CrudRepository;
import ru.dev.A1.A1.models.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient,String> {
}
