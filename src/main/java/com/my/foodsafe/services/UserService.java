package com.my.foodsafe.services;

import com.my.foodsafe.pojo.User;
import com.my.foodsafe.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


//@Service
//public class UserService implements IUserService {
//
//    @Autowired
//    private IUserRepository userRepository;
//
//    @Transactional
//    @Override
//    public void saveUser(User user) {
//        userRepository.save(user);
//    }
////
////    @Override
////    public MUser getUser(String name) {
////        return userRepository.findByNamedParam(name);
////    }
//
//    @Override
//    public String loginUser(User user) {
//
//    }
//}
