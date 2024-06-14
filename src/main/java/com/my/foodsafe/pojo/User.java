package com.my.foodsafe.pojo;


import lombok.*;


import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "tbl_users")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private String userId;
    private String userName;
    private String userIdentity;
    private String userPassword;
    private String userEmail;
}
