package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;



@Data
public class Userlogin {
    private  String usercharacter;
    private String username;
    private String pwd;
}
