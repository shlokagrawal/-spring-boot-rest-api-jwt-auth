package com.shlok.todoapplication.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "users")
public class User extends Basemodel{
    private String username;
    private String password;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Todo> todos = new ArrayList<>();
}
