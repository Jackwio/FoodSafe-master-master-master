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
    private String userId;
    private String userName;
    private String userIdentity;
    private String userPassword;
    private String userEmail;
}
