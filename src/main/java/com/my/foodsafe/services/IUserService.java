package com.my.foodsafe.services;

import com.my.foodsafe.pojo.User;

public interface IUserService {
    void saveUser(User user);
//    MUser getUser(String name);
    void updateUser(User user);

    String loginUser(User user);

    void logoutUser();

    void RegisterUser(User user);
}
