package com.techbra.foodcrud.repository;

import com.techbra.foodcrud.model.Food;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FoodRepository extends ReactiveCrudRepository<Food, Integer> {

    @Query("select * from foods")
    public Flux<Food> myPersonalFindAllQuery();


}
