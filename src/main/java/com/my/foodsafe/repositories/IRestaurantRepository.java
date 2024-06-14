package com.my.foodsafe.repositories;

import com.my.foodsafe.pojo.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface IRestaurantRepository extends IBaseRepository<Restaurant, String> {
    Restaurant findByRestaurantName(String restaurantName);

    @Query("select r from Restaurant r where r.restaurantName like %?1%")
    List<Restaurant> searchRestaurant(String keyword);
}
