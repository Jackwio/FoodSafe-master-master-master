package com.my.foodsafe.repositories;

import com.my.foodsafe.pojo.Food;
import com.my.foodsafe.pojo.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFoodRepository extends IBaseRepository<Food, String> {
    List<Food> findAllByRestaurant(Restaurant restaurant);
}
