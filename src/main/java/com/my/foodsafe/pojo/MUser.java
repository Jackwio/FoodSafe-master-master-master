package com.my.foodsafe.pojo;


import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "MUser")
public class MUser implements Serializable {
    @Id
    @GeneratedValue
    private String id;
    private String name;
    private String identity;
    private String password;
    private String email;
}
