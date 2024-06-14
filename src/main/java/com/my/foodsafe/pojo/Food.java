package com.my.foodsafe.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Food {
    @Id
    @GeneratedValue
    private int foodId;
    private String foodName;
    private double foodWeight;
    private double foodProtein;
    private double foodCarbon;
    private double foodCalories;
    private double foodFat;
    private double foodSugar;
    private double foodNa;
    private double foodCholesterol;
}
