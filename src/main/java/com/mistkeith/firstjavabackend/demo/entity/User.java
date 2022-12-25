package com.mistkeith.firstjavabackend.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements IUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String bio;

    @Override
    public Boolean isEmpty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setBio(String bio) {

    }

}
