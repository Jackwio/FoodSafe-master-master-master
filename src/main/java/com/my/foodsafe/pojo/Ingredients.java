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
    private String ingredientId;
    private String ingredientChiName;
    private String ingredientEngName;
    private String ingredientDescription;
    private String foodLimit;
    private String ingredientType;
}
