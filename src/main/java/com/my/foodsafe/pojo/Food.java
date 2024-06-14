package com.my.foodsafe.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tbl_foods")
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
    private String foodType;
    private String foodImage;
    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurantId")
    private Restaurant restaurant;
}
