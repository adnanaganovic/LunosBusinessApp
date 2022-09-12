package com.lunosapp.lunosbusinessapp.service.userService;

import com.lunosapp.lunosbusinessapp.entity.User;

import java.util.List;

//JAVNO DOSTUPAN INTERFACE i u njemu je samo nabrojano šta će raditi,
// a klasa koja ga implementira mora imati njegovu metodu (User login(String name, String password))

public interface UserServiceLocal {


    User login(String username, String password);

    public List<User> findAll();

    //DODAVANJE USERA U BAZU 2
    void create(User user);

    void removeById(Integer id);

    void edit(User user);

    void remove(User user);

    User find(Integer id);

    String hash (String password);


}
