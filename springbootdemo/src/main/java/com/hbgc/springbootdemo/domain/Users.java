package com.hbgc.springbootdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class Users extends BaseEntity implements Serializable, Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String gender;
    private String birthday;
    private String username;
    private String password;
    private String mobile;
    private String email;
    private String validateCode;
    private String token; //登录成功之后的token值。用来做身份验证。
    private  String avatar;
}
