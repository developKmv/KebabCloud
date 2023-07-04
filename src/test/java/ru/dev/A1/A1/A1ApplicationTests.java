package ru.dev.A1.A1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.dev.A1.A1.*;
import ru.dev.A1.A1.models.Ingredient;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@SpringBootTest
class A1ApplicationTests {
	RestTemplate rest = new RestTemplate();
	@Test
	void contextLoads() {
	}
	@Test
	public void getIngredientById() {
		String ingredientId = "1";
		Map<String, String> urlVariables = new HashMap<>();
		urlVariables.put("id", ingredientId);
		URI url = UriComponentsBuilder
				.fromHttpUrl("http://localhost:8080/data-api/ingredients/{id}")
				.build(urlVariables);

		Ingredient rs = rest.getForObject(url, Ingredient.class);
		log.info(rs.toString());
	}
}
