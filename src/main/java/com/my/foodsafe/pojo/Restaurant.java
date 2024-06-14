package com.my.foodsafe.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name= "tbl_restaurant")
public class Restaurant {
    @Id
    @GeneratedValue
    private String restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantImage;
    
}
