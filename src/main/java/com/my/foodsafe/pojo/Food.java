package com.my.foodsafe.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tbl_foods")
public class Food implements Serializable {
    @Id
    private String foodId;
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
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @JsonIgnore
    @OneToOne(mappedBy = "food", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private History history;
}
