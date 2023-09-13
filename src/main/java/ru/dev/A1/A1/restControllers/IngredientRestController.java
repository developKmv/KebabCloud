package ru.dev.A1.A1.restControllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.dev.A1.A1.dao.JpaDAOHibernate;
import ru.dev.A1.A1.data.IngredientRepository;
import ru.dev.A1.A1.models.Ingredient;

@Slf4j
@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
public class IngredientRestController {
    private IngredientRepository ingredientRepository;
    private JpaDAOHibernate jpaDAOHibernate;
    @Autowired
    public IngredientRestController(IngredientRepository ingredientRepository,JpaDAOHibernate jpaDAOHibernate){
        this.ingredientRepository = ingredientRepository;
        this.jpaDAOHibernate = jpaDAOHibernate;
    }
    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return ingredientRepository.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {

        Ingredient newIngredient = new Ingredient();
        newIngredient.setIngredient_id(ingredient.getIngredient_id());
        newIngredient.setName(ingredient.getName());
        newIngredient.setType(ingredient.getType());

        log.info(newIngredient.toString());

        return ingredientRepository.save(ingredient);
        //return jpaDAOHibernate.insertIngredient(ingredient);
    }
    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("id") String ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }
}
