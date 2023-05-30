package ru.dev.A1.A1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Kebab {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "created_at")
    private Date createDate = new Date();

    @NotNull
    @Size(min=5,message = "Name must be at least 5 character long")
    private String name;

    @NotNull
    @Size(min=1,message = "You must choose at least 1 ingredient")
    @ManyToMany
    private List<Ingredient> ingredients;

    public void addIngedient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }
}
