package com.lunosapp.lunosbusinessapp.event;

import com.lunosapp.lunosbusinessapp.entity.Privilege;
import com.lunosapp.lunosbusinessapp.entity.User;
import com.lunosapp.lunosbusinessapp.service.userService.UserServiceFactory;
import com.lunosapp.lunosbusinessapp.view.adminView.AdminView;
import com.lunosapp.lunosbusinessapp.Controller;
import com.lunosapp.lunosbusinessapp.view.LoginView;
import com.lunosapp.lunosbusinessapp.view.userView.UserView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginEvent implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {

        LoginView loginView = Controller.instance().getLoginView();
        //prvo uzmemo podatke: username i password
        String username = loginView.getUsername();
        String password = Controller.instance().getLoginView().getPassword();

//        String hashedPassword = doHashing(password);


        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            loginView.setLoginMessage("Username ili password nije unesen!");
            return;
        }
//        password = UserServiceFactory.USER_SERVICE_FACTORY.getUserServiceLocal().hash(password);



        User user = UserServiceFactory.USER_SERVICE_FACTORY.getUserServiceLocal().login(username, password);
        if(user == null){
            loginView.setLoginMessage("Netačan username ili password!");
        }else{
            Controller.instance().setLoggedUser(user);
            Privilege privilege = user.getIdPrivilege();
            BorderPane mainPanel;
            if("director".equalsIgnoreCase(privilege.getName())){
                //ADMIN PANEL
                mainPanel = new AdminView();
                Controller.instance().setAdminView((AdminView) mainPanel);
                Controller.instance().getStage().setTitle("Admin panel: " + user.getName()+" " +user.getSurname());

                Controller.instance().getStage().getIcons().add(new Image("logo.png")); //SETOVANJE IKONICE

            }else {
                //EMPLOYEE PANEL
                mainPanel = new UserView();
                Controller.instance().setUserView((UserView) mainPanel);
                Controller.instance().getStage().setTitle("User panel: " + user.getName()+" " +user.getSurname());
            }

//NA KRAJU SE SAMO POSTAVI SCENE
            Scene scene = new Scene(mainPanel, 1100, 500);
            Controller.instance().getStage().setScene(scene);   //OVDJE NECE DA PRIMI getScene(scene) == SOLVE zato što treba: setScene(scene);
        }

    }
    public String doHashing (String password) {
        try {
            MessageDigest messageDigest = java.security.MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            messageDigest.digest();
            byte[] resultByteArray = messageDigest.digest();

            StringBuilder stringBuilder = new StringBuilder();

            for (byte b : resultByteArray){
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }

}
