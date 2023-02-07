package com.techbra.foodcrud;

import com.techbra.foodcrud.model.Food;
import com.techbra.foodcrud.service.FoodService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;


@WebFluxTest
@RunWith(SpringRunner.class)
class FoodcrudApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@MockBean
	private FoodService foodService;

	@Test
	public void getAllFoods(){
		Food food1 = new Food(1, "eba");
		Food food2 = new Food(2, "pizza");
		Food food3 = new Food(3, "rice");
		Flux<Food> foodFlux = Flux.just(food1, food2, food3);
		when(foodService.findAllFoods()).thenReturn(foodFlux);
		Flux<Food> resultResponse = webTestClient.get().uri("/")
				.exchange()
				.expectStatus()	.isOk()
				.returnResult(Food.class)
				.getResponseBody();
		StepVerifier.create(resultResponse)
				.expectSubscription()
				.expectNextMatches(f -> f.getName().equals("eba"))
				.expectNextMatches(f -> f.getName().equals("pizza"))
				.expectNextMatches(f ->f.getName().equals("rice"))
				.verifyComplete();
	}
}
