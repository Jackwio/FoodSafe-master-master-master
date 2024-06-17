package com.my.foodsafe.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "tbl_users")
public class User implements Serializable {
    @Id
    private String userId;
    private String userName;
    private int userIdentity;
    private String userPwd;
    private String userEmail;
    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    private List<History> histories;
}
