package com.my.foodsafe.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Menu {
    @Id
    @GeneratedValue
    private String id;
    private String name;
    private String type;
    private String image;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    private Restaurant restaurant;
}
