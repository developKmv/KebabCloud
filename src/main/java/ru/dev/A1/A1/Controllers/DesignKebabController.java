package ru.dev.A1.A1.Controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.dev.A1.A1.data.IngredientRepository;
import ru.dev.A1.A1.models.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("kebabOrder")
public class DesignKebabController {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignKebabController(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }
    @ModelAttribute
    public void addIngredientsModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepository.findAll();

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredients.forEach(ingredientList::add);

        Ingredient.Type[] types = Ingredient.Type.values();

        log.info("Types: {}", Arrays.toString(types));
        log.info("List :{}",ingredientList.toString());

        for (Ingredient.Type t : types) {
            model.addAttribute(t.toString().toLowerCase(),
                    filterByType(ingredientList, t));
        }

    }

    @ModelAttribute(name = "kebabOrder")
    public KebabOrder kebabOrder() {
        return new KebabOrder();
    }

    @ModelAttribute(name = "kebab")
    public Kebab Kebab() {
        return new Kebab();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }
    @PostMapping
    public String processKebab(@Valid Kebab kebab, Errors errors, @ModelAttribute KebabOrder kebabOrder){

        if(errors.hasErrors()) return "design";

        kebabOrder.addKebab(kebab);
        log.info("Processing taco: {}",kebab);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream().filter(x -> x.getType()
                .equals(type)).collect(Collectors.toList());
    }
}