package com.lunosapp.lunosbusinessapp.views.UserViews;

import com.lunosapp.lunosbusinessapp.views.AdminViews.ProjectAdminPanel;
import com.lunosapp.lunosbusinessapp.views.AdminViews.ProjectOrderPanel;
import com.lunosapp.lunosbusinessapp.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class UserView extends BorderPane {

    private final ToggleButton projectToggleButton = new ToggleButton("Projekti");

    private final ToggleButton orderToggleButton = new ToggleButton("Narudžbe");
    private final Button logoutButton = new Button("Odjava");
    //
    private ProjectOrderPanel projectOrderPanel = new ProjectOrderPanel();
    private ProjectAdminPanel projectAdminPanel = new ProjectAdminPanel();

    public UserView() {
        setCenter(projectOrderPanel);
        BackgroundFill background_fill = new BackgroundFill(Color.ROYALBLUE,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        setBackground(background);

        //OVAKO ĆEMO ISKLJUČITI SELEKTOVANI BUTTON (KADA SELEKTUJEMO userToggleButton ISKLJUČIT ĆEMO projectToggleButton i obratno)
        ToggleGroup menuToggleGroup = new ToggleGroup();
        projectToggleButton.setToggleGroup(menuToggleGroup);
        orderToggleButton.setToggleGroup(menuToggleGroup);
//
        projectToggleButton.setSelected(true);
//
        HBox mainMenu = new HBox();
        mainMenu.setSpacing(5);
        mainMenu.setPadding(new Insets(10, 10, 10, 10));
        logoutButton.setOnAction(Controller.instance().getEventBus().getLogoutEvent());
        logoutButton.setText("Odjava(" + Controller.instance().getLoggedUser().getName() + ")");

           //Ima i drugi način preko Override metode (ISPOD
        projectToggleButton.setOnAction(e -> setCenter(projectAdminPanel));
        orderToggleButton.setOnAction(e -> setCenter(projectOrderPanel));

//        userToggleButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                    setCenter(userAdminPanel);
//                }
//        });
//        projectToggleButton.setOnAction(new EventHandler<ActionEvent>(){
//        @Override
//        public void handle(ActionEvent event) {
//            setCenter(projectAdminPanel);
//        }

        mainMenu.getChildren().addAll(projectToggleButton, orderToggleButton);

//
        HBox logoutHBox = new HBox(logoutButton);
        logoutHBox.setAlignment(Pos.BASELINE_RIGHT);
        logoutHBox.setPadding(new Insets(10, 10, 10, 10));
//
        GridPane topPane = new GridPane();
        topPane.add(mainMenu, 0, 0);
        topPane.add(logoutHBox, 1, 0);
        setTop(topPane);
    }

}
