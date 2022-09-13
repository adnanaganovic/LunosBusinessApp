package com.lunosapp.lunosbusinessapp.event;

import com.lunosapp.lunosbusinessapp.Controller;
import com.lunosapp.lunosbusinessapp.view.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogoutEvent implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        Controller.instance().setLoggedUser(null);
        Controller.instance().getStage().setTitle("Login");

        LoginView loginView = new LoginView();
        Controller.instance().setLoginView(loginView);       //Ukoliko ne setujemo ovdje novi loginView, Controller (koji je singleton) će prikaziavti kešovani stari loginView
        Scene scene = new Scene(loginView, 750, 400);
        Stage stage = new Stage();
        Controller.instance().getStage().setScene(scene);

    }
}