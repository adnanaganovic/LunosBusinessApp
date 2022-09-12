package com.lunosapp.lunosbusinessapp.view.userView;

import com.lunosapp.lunosbusinessapp.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class UserView extends BorderPane {

    private final ToggleButton clientToggleButton = new ToggleButton("Klijenti");
    private final ToggleButton regionToggleButton = new ToggleButton("Regije");
    private final ToggleButton municipalityToggleButton = new ToggleButton("Opštine");
    private final ToggleButton addressToggleButton = new ToggleButton("Adrese");
    private final ToggleButton projectToggleButton = new ToggleButton("Projekti");

    private final ToggleButton orderToggleButton = new ToggleButton("Narudžbe");

    private final Button logoutButton = new Button("Odjava");
    //

    private ClientPanel clientPanel = new ClientPanel();
    private RegionPanel regionPanel = new RegionPanel();
    private MunicipalityPanel municipalityPanel = new MunicipalityPanel();
    private AddressPanel addressPanel = new AddressPanel();
    private ProjectAdminPanel projectAdminPanel = new ProjectAdminPanel();
    private ProjectOrderPanel projectOrderPanel = new ProjectOrderPanel();


    public UserView() {
        setCenter(projectOrderPanel);
        BackgroundFill background_fill = new BackgroundFill(Color.ROYALBLUE,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        setBackground(background);

        //OVAKO ĆEMO ISKLJUČITI SELEKTOVANI BUTTON (KADA SELEKTUJEMO userToggleButton ISKLJUČIT ĆEMO projectToggleButton i obratno)
        ToggleGroup menuToggleGroup = new ToggleGroup();
        clientToggleButton.setToggleGroup(menuToggleGroup);
        regionToggleButton.setToggleGroup(menuToggleGroup);
        municipalityToggleButton.setToggleGroup(menuToggleGroup);
        addressToggleButton.setToggleGroup(menuToggleGroup);
        projectToggleButton.setToggleGroup(menuToggleGroup);
        orderToggleButton.setToggleGroup(menuToggleGroup);
//
        clientToggleButton.setSelected(true);
//
        HBox mainMenu = new HBox();
        mainMenu.setSpacing(5);
        mainMenu.setPadding(new Insets(10, 10, 10, 10));
        logoutButton.setOnAction(Controller.instance().getEventBus().getLogoutEvent());
        logoutButton.setText("Odjava(" + Controller.instance().getLoggedUser().getName() + ")");

           //Ima i drugi način preko Override metode (ISPOD
        clientToggleButton.setOnAction(e -> setCenter(clientPanel));
        regionToggleButton.setOnAction(e -> setCenter(regionPanel));
        municipalityToggleButton.setOnAction(e -> setCenter(municipalityPanel));
        addressToggleButton.setOnAction(e -> setCenter(addressPanel));
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

        mainMenu.getChildren().addAll(clientToggleButton, regionToggleButton, municipalityToggleButton, addressToggleButton, projectToggleButton, orderToggleButton);

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
