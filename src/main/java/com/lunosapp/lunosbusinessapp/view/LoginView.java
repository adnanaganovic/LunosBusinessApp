package com.lunosapp.lunosbusinessapp.view;

import com.lunosapp.lunosbusinessapp.Controller;
import com.lunosapp.lunosbusinessapp.service.Encryptor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class LoginView extends GridPane {
    private final Label usernameLabel = new Label("Korisničko ime: ");
    private final Label passwordLabel = new Label("Lozinka: ");
    private final TextField usernameTextField = new TextField();
    private final PasswordField passwordField = new PasswordField();
//    private final PasswordField hiddenPasswordField = new PasswordField();  //Hash

    private final Button loginButton = new Button("Prijava");
    private final Button cancelButton = new Button("Odustani");
    private final Label messageLabel = new Label();//ovdje ne piše ništa..sadržaj ćemo možda dinamički popuniti


    public LoginView() {
        setHgap(10);
        setVgap(15);
        setPadding(new Insets(25, 25, 25, 25));
        setAlignment(Pos.TOP_CENTER);

//        PROMJENA BOJE U JAVAFX APP
        BackgroundFill background_fill = new BackgroundFill(Color.ROYALBLUE,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        setBackground(background);
        usernameLabel.setTextFill(Color.WHITESMOKE);
        passwordLabel.setTextFill(Color.WHITE);
        messageLabel.setTextFill(Color.WHITE);

        //DODAVANJE LOGA
        Image image = new Image("white logo.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        setAlignment(Pos.BOTTOM_CENTER);

        VBox hBox = new VBox();
        hBox.getChildren().add(imageView);
        add(hBox, 1,4);

//        imageView.setX(50);
//        imageView.setY(25);
//        imageView.setFitHeight(100);
//        imageView.setFitWidth(100);



        //username
        add(usernameLabel, 0, 0);
        add(usernameTextField, 1, 0);
        //password
        add(passwordLabel, 0, 1);
        add(passwordField, 1, 1);
        //FlowPane
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER_RIGHT);
        loginButton.setOnAction(Controller.instance().getEventBus().getLoginEvent());
        cancelButton.setOnAction(Controller.instance().getEventBus().getCancelEvent());
        flowPane.getChildren().addAll(loginButton, cancelButton);
        add(flowPane, 1, 2);
        //message
        add(messageLabel, 1, 3);
    }

    public String getUsername() {
        return usernameTextField.getText();
    }

//    public String getPassword() {
//        return passwordField.getText();                  //BEZ HASH
//    }
    public String getPassword() {
        return passwordField.getText();
//        String password = passwordField.getText();
//        String hashedPassword = doHashing(password);
//        return hashedPassword;
    }



    public void setLoginMessage(String message){
        messageLabel.setText(message);
    }

//    public String doHashing (String password) {
//        try {
//            MessageDigest messageDigest = java.security.MessageDigest.getInstance("MD5");
//            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
//            messageDigest.digest();
//            byte[] resultByteArray = messageDigest.digest();
//
//            StringBuilder stringBuilder = new StringBuilder();
//
//            for (byte b : resultByteArray){
//                stringBuilder.append(String.format("%02x", b));
//            }
//            return stringBuilder.toString();
//        }catch (NoSuchAlgorithmException e){
//            e.printStackTrace();
//        }
//        return "";
//    }
}

