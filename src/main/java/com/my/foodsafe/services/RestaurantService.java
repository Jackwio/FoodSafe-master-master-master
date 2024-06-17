package com.my.foodsafe.services;

import com.my.foodsafe.pojo.Food;
import com.my.foodsafe.pojo.History;
import com.my.foodsafe.pojo.Restaurant;
import com.my.foodsafe.repositories.IFoodRepository;
import com.my.foodsafe.repositories.IHistoryRepository;
import com.my.foodsafe.repositories.IRestaurantRepository;
import com.my.foodsafe.utilities.IUUIDGenerator;
import com.my.foodsafe.utilities.TestUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RestaurantService implements IRestaurantService {

    @Autowired
    private IRestaurantRepository restaurantRepository;
    @Autowired
    private IUUIDGenerator iuuidGenerator;
    @Autowired
    private TestUpload testUpload;
    @Autowired
    private IFoodRepository foodRepository;
    @Autowired
    private IHistoryRepository historyRepository;

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant, String photoReference) {
        Restaurant tempRest = restaurantRepository.findByRestaurantName(restaurant.getRestaurantName());
        if(tempRest != null){
            return tempRest;
        }
        else{
            testUpload.savePhotoLocally(photoReference);
            restaurant.setRestaurantId(iuuidGenerator.getUUID());
            restaurant.setRestaurantImage(photoReference);
            restaurantRepository.save(restaurant);
        }
        return restaurant;
    }

    //搜尋餐廳(名字)
    public List<Restaurant> searchRestaurants(String searchString){
        return (restaurantRepository.searchRestaurant(searchString));
    }

    //找那間餐廳有的食物
    public List<Food> getRestaurantFood (Restaurant restaurant){
        return (foodRepository.findAllByRestaurant(restaurant));
    }

    public List<History> getAllHistory (){
        List<History> historyList = new ArrayList<>();
            historyList = historyRepository.findAll();
        return historyList;
    }

}
