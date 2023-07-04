package ru.dev.A1.A1;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.dev.A1.A1.dao.JpaDAOHibernate;
import ru.dev.A1.A1.data.UserRepository;
import ru.dev.A1.A1.models.Ingredient;
import ru.dev.A1.A1.models.Kebab;
import ru.dev.A1.A1.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class A1Application implements ApplicationContextAware {
	@Autowired
	private static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;
	}
	public static void main(String[] args) {
		SpringApplication.run(A1Application.class, args);
	}
	@Bean
	public CommandLineRunner dataLoader(JpaDAOHibernate repo, PasswordEncoder encoder){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repo.insertUser(new User(null,"user",encoder.encode("1"),"test",
						"","","","",""));

				Ingredient FLTO = new Ingredient(null,"FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
				Ingredient COTO = new Ingredient(null,"COTO", "Corn Tortilla", Ingredient.Type.WRAP);
				Ingredient GRBF = new Ingredient(null,"GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
				Ingredient CARN = new Ingredient(null,"CARN", "Carnitas", Ingredient.Type.PROTEIN);
				Ingredient TMTO = new Ingredient(null,"TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
				Ingredient LETC = new Ingredient(null,"LETC", "Lettuce", Ingredient.Type.VEGGIES);
				Ingredient CHED = new Ingredient(null,"CHED", "Cheddar", Ingredient.Type.CHEESE);
				Ingredient JACK = new Ingredient(null,"JACK", "Monterrey Jack", Ingredient.Type.VEGGIES);
				Ingredient SLSA = new Ingredient(null,"SLSA", "Salsa", Ingredient.Type.SAUCE);
				Ingredient SRCR = new Ingredient(null,"SRCR", "Sour Cream", Ingredient.Type.SAUCE);
				repo.insertIngredient(FLTO);
				repo.insertIngredient(COTO);
				repo.insertIngredient(GRBF);
				repo.insertIngredient(CARN);
				repo.insertIngredient(TMTO);
				repo.insertIngredient(LETC);
				repo.insertIngredient(CHED);
				repo.insertIngredient(JACK);
				repo.insertIngredient(SLSA);
				repo.insertIngredient(SRCR);


				List<Ingredient> allIngredient = repo.getAllIngredients();

				Kebab k1 = new Kebab();
				k1.setName("k1aaa");
				k1.setCreateDate(new Date());
				Ingredient[] k1Arr = {allIngredient.get(0),allIngredient.get(1),allIngredient.get(2)};
				k1.setIngredients(Arrays.stream(k1Arr).toList());

				repo.insertKebab(k1);

				Kebab k2 = new Kebab();
				k2.setName("k2aaa");
				k2.setCreateDate(new Date());
				Ingredient[] k2Arr = {allIngredient.get(3),allIngredient.get(4),allIngredient.get(5)};
				k2.setIngredients(Arrays.stream(k2Arr).toList());

				repo.insertKebab(k2);
			}
		};
	}


}