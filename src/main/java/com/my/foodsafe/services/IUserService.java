package com.my.foodsafe.services;

import com.my.foodsafe.pojo.MUser;

public interface IUserService {
    void saveUser(MUser user);
//    MUser getUser(String name);
    void updateUser(MUser user);

    String loginUser(MUser user);

    void logoutUser();

    void RegisterUser(MUser user);
}
