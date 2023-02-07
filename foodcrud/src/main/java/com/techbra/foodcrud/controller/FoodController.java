package com.techbra.foodcrud.controller;

import com.techbra.foodcrud.model.Food;
import com.techbra.foodcrud.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class FoodController {

    @Autowired
    FoodService foodService;

    @GetMapping
    public Flux<Food> findAllFoods(){
        return foodService.findAllFoods();
    }

    @GetMapping("/{id}")
    public Mono<Food> getSIngleFood(@PathVariable Integer id){
        return foodService.findSingleFood(id);
    }
    @PostMapping
    public Mono<Food> saveSingleFood(@RequestBody Food food){
        return foodService.saveSingleFood(food);
    }
    @PutMapping("/{id}")
    public Mono<Food> editFood(@RequestBody Food food, @PathVariable Integer id){
        return foodService.editSingleFood(food, id);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> deleteSingleFood(@PathVariable Integer id){
        return foodService.deleteSigleFood(id);
    }
    @GetMapping("/personal")
    public Flux<Food> myPersonalFindAllQuery(){
        return foodService.myPersonalFindAllQuery();
    }
}
