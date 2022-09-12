package com.lunosapp.lunosbusinessapp.view.adminView;

import com.lunosapp.lunosbusinessapp.entity.Client;
import com.lunosapp.lunosbusinessapp.entity.Privilege;
import com.lunosapp.lunosbusinessapp.entity.User;
import com.lunosapp.lunosbusinessapp.service.clientService.ClientServiceFactory;
import com.lunosapp.lunosbusinessapp.service.privilegeService.PrivilegeServiceFactory;
import com.lunosapp.lunosbusinessapp.service.privilegeService.PrivilegeServiceLocal;
import com.lunosapp.lunosbusinessapp.service.userService.UserServiceFactory;
import com.lunosapp.lunosbusinessapp.service.userService.UserServiceLocal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;

public class PrivilegePanel extends VBox {
    private Label titleLabel = new Label("Privilegije");
    private ObservableList<Privilege> privilegeObservableList;
    private TableView<Privilege> privilegeTableView = new TableView<>();

    private final TextField privilegeNameTextField = new TextField();

//    private final ChoiceBox<Address> idAddressChoiceBox = new ChoiceBox<>();

    private Button addPrivilegeButton = new Button("Dodaj privilegiju");
    private Button deletePrivilegeButton = new Button("Obriši");


    public PrivilegePanel() {
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(5);
        setPadding(new Insets(10, 10, 10, 10));

        //PROMJENA BOJE U JAVAFX APP
        BackgroundFill background_fill = new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        setBackground(background);
        titleLabel.setTextFill(Color.WHITE);

        //SETOVANJE VRIJEDNOSTI ĆELIJA I POVEZNICE SA ENTITY-EM // setCellValueFactory(new PropertyValueFactory<User,String>

        TableColumn<Privilege, Integer> idColumn = new TableColumn<>("ID privilegije");
        idColumn.setCellValueFactory(new PropertyValueFactory<Privilege, Integer>("id"));

        TableColumn<Privilege, String> nameColumn = new TableColumn<>("Privilegija");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Privilege, String>("name")); //MORA BITI: ("username") isti naziv kao u entitetu npr. "private String username" (@Column(name = "username") private String username);

        List<Privilege> privilegeList = PrivilegeServiceFactory.PRIVILEGE_SERVICE_FACTORY.getPrivilegeServiceLocal().findAll();
        privilegeObservableList = FXCollections.observableList(privilegeList);   //2.ovo je na kraju dodato i objasnjeno JWS 2/5
        privilegeTableView.setItems(privilegeObservableList);
        privilegeTableView.getColumns().addAll(idColumn,nameColumn);
//
        getChildren().addAll(titleLabel, privilegeTableView, getForm(), addPrivilegeButton, deletePrivilegeButton);
    }

    private HBox getForm(){
        HBox form = new HBox();
        form.setSpacing(3);

//ovdje unosimo PrompText za fielde za unos username, password...
        privilegeNameTextField.setPromptText("Privilegija");
        //povezujemo ADDBUTTON sa actionEvent 1
        addPrivilegeButton.setOnAction(this::addPrivilege);  //DODAVANJE USERA U BAZU 6 (zadnji korak)
        deletePrivilegeButton.setOnAction(this::removePrivilege);

        form.getChildren().addAll(
                privilegeNameTextField);
        return form;
    }

    private void removePrivilege (ActionEvent actionEvent) {
        Privilege selectedPrivilege = privilegeTableView.getSelectionModel().getSelectedItem();
        PrivilegeServiceLocal privilegeServiceLocal = PrivilegeServiceFactory.PRIVILEGE_SERVICE_FACTORY.getPrivilegeServiceLocal();
        //DB
        privilegeServiceLocal.removeById(selectedPrivilege.getId());
        //Table View
        privilegeObservableList.remove(selectedPrivilege);
    }

    //povezujemo ADDBUTTON sa actionEvent 2
    private void addPrivilege(ActionEvent event) {
        if (validate()) {                                  // objasnjeno u SE 1/3
//            //TRANZIJENTAN
            Privilege privilege = new Privilege();
            privilege.setName(privilegeNameTextField.getText());

//            DODAVANJE USERA U BAZU 1 (pa idemo dodati metodu u UserServiceLocal)
            PrivilegeServiceLocal privilegeServiceLocal = PrivilegeServiceFactory.PRIVILEGE_SERVICE_FACTORY.getPrivilegeServiceLocal();
//            //TRANZIJENTNOG U PERZISTENTNO STANJE (DODAVANJE USERA 3)
            privilegeServiceLocal.create(privilege);             //Autocommit mode: false prilikom dodavanja korisnika u bazu
            privilegeObservableList.add(privilege);
            clearInput();  //DODAVANJE USERA U BAZU 5
        }else{                                             // objasnjeno u SE 1/3
//            //prikažite smislenu poruku korisniku
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sva polja moraju biti unesena!");
            a.show();

        }
    }
    //
    private boolean validate() {                           // objasnjeno u SE 1/3
        return !privilegeNameTextField.getText().isBlank();
    }
    //
//            (DODAVANJE USERA 4)
    private void clearInput(){
      privilegeNameTextField.setText("");
    }
}