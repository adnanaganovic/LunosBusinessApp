package com.lunosapp.lunosbusinessapp;

import com.lunosapp.lunosbusinessapp.entity.User;
import com.lunosapp.lunosbusinessapp.views.AdminViews.AdminView;
import com.lunosapp.lunosbusinessapp.views.LoginView;
import com.lunosapp.lunosbusinessapp.views.UserViews.UserView;
import com.lunosapp.lunosbusinessapp.events.EventBus;
import javafx.stage.Stage;

public class Controller {

    private static Controller INSTANCE = null;

//CONTROLLER pokazuje koji ce View biti prikazan i vodit ce računa o korisniku u sesiji (loggedUser)
    private LoginView loginView;
    private AdminView adminView;
    private UserView userView;
    private User loggedUser;
    private Stage stage;
    private EventBus eventBus = new EventBus();

    private Controller() {
    }


    public static Controller instance() {
        if(INSTANCE == null){
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public AdminView getAdminView() {
        return adminView;
    }

    public void setAdminView(AdminView adminView) {
        this.adminView = adminView;
    }

    public UserView getUserView() {
        return userView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }
}

