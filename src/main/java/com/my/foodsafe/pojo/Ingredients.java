package com.my.foodsafe.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "tbl_ingredients")
public class Ingredients {
    @Id
    @GeneratedValue
    private int id;
    private String ingredientsChiName;
    private String ingredientsEngName;
    private String ingredientsDescription;
    private String ingredientsFoodLimit;
    private String ingredientsType;
}
