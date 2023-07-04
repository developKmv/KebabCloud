package ru.dev.A1.A1.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.dev.A1.A1.models.Ingredient;
import ru.dev.A1.A1.models.Kebab;
import ru.dev.A1.A1.models.KebabOrder;
import ru.dev.A1.A1.models.User;

import java.util.List;

@Slf4j
@Repository
@Data
@NoArgsConstructor
public class JpaDAOHibernate {

    @PersistenceContext
    private EntityManager entityManager;

    public JpaDAOHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User getUserByUsername(String username) {
        User user = null;

        try {
            user = entityManager.createQuery("from USER_KEBAB_CLOUD where username = :paramName", User.class)
                    .setParameter("paramName", username).getResultList().get(0);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return user;
    }

    public List<Ingredient> getAllIngredients() {
        return entityManager.createQuery("from Ingredient", Ingredient.class).getResultList();
    }

    public List<Kebab> getAllKebab() {
        return entityManager.createQuery("from Kebab", Kebab.class).getResultList();
    }

    public Kebab getKebabById(Long id) {
        return entityManager.find(Kebab.class, id);
    }

    @Transactional
    public KebabOrder insertKebabOrder(KebabOrder order) {
        entityManager.persist(order);
        return order;
    }

    @Transactional
    public User insertUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public Kebab insertKebab(Kebab kebab) {

        if (entityManager.contains(kebab)) {
            entityManager.persist(kebab);
        }else{
            entityManager.merge(kebab);
        }

        return kebab;
    }

    @Transactional
    public Ingredient insertIngredient(Ingredient ingredient) {
        if(entityManager.contains(ingredient)){
            entityManager.persist(ingredient);
        }else {
            entityManager.merge(ingredient);
        }
        return ingredient;
    }

}
