package com.techbra.foodcrud.service;

import com.techbra.foodcrud.model.Food;
import com.techbra.foodcrud.repository.FoodRepository;
import io.r2dbc.spi.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class FoodService {

    @Autowired
    FoodRepository foodRepository;

    public Flux<Food> findAllFoods() {
        return foodRepository.findAll();
    }

    public Mono<Food> findSingleFood(Integer id) {
        return foodRepository.findById(id);
    }

    public Mono<Food> saveSingleFood(Food food) {
        return foodRepository.save(food);
    }

    public Mono<Food> editSingleFood(Food food, Integer id) {
        return foodRepository.findById(id)
                .map(f -> {
                    f.setName(food.getName());
                    return f;
                }).flatMap(f -> foodRepository.save(f));
    }

    public Mono<Void> deleteSigleFood(Integer id) {
        return foodRepository.deleteById(id);
    }

    public Flux<Food> myPersonalFindAllQuery(){
        return foodRepository.myPersonalFindAllQuery();
    }


}
