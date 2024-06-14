package com.my.foodsafe.services;

import com.my.foodsafe.pojo.Food;
import com.my.foodsafe.pojo.Restaurant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRestaurantService {
    Restaurant saveRestaurant(Restaurant restaurant, String photoReference);

//    String getText(MultipartFile file);

    List<Restaurant> searchRestaurants(String searchString);

    List<Food> getRestaurantFood(Restaurant restaurant);
}
