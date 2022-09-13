package com.lunosapp.lunosbusinessapp;

import com.lunosapp.lunosbusinessapp.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginView loginView = new LoginView();
        Controller.instance().setStage(stage);
        Controller.instance().setLoginView(loginView);
        Scene scene = new Scene(loginView, 750, 400);
        stage.setTitle("LUNOS poslovna aplikacija");

        stage.getIcons().add(new Image("logoIcon.jpg"));    //setovanje ikonice

        stage.setScene(scene);
        stage.show();


//        DEFAULTNI CODE ŠTO DOĐE SA KREIRANJEM FX APPLIKACIJE
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();





////        OVAKO POZIVAMO METODU IZ ENKAPSULIRANOG SERVICA (Također ovako i provjeravamo vezu sa bazom)
//        UserServiceLocal userServiceLocal= UserServiceFactory.USER_SERVICE_FACTORY.getUserServiceLocal();
//        User user = userServiceLocal.login("Emina","emina123");
//        System.out.println(user);


//        EntityManager entityManager = Persistence.createEntityManagerFactory("lunosPU").createEntityManager();
//        Query query = entityManager.createNamedQuery("User.findAll");
//        query.getResultList().forEach(System.out::println);

    }
}