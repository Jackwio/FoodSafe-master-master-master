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
@Table (name = "tbl_menu")
public class Menu {
    @Id
    @GeneratedValue
    private String menuId;
    private String menuName;
    private String menuType;
    private String menuImage;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurantId")
    private Restaurant restaurant;
}
