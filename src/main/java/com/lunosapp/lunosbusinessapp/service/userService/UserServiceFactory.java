package com.lunosapp.lunosbusinessapp.service.userService;

public enum UserServiceFactory {
    USER_SERVICE_FACTORY(new UserService());

    private UserServiceLocal userServiceLocal;

    UserServiceFactory(UserServiceLocal userServiceLocal){
        this.userServiceLocal = userServiceLocal;
    }

    public UserServiceLocal getUserServiceLocal(){
        return userServiceLocal;
    }
}
