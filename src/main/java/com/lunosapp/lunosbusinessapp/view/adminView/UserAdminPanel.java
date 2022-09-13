package com.lunosapp.lunosbusinessapp.view.adminView;

import com.lunosapp.lunosbusinessapp.entity.Privilege;
import com.lunosapp.lunosbusinessapp.entity.User;
import com.lunosapp.lunosbusinessapp.service.privilegeService.PrivilegeServiceFactory;
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

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserAdminPanel extends VBox {

//    PRIKAZIVANJE PODATAKA IZ BAZE
    private Label titleLabel = new Label("Administracija uposlenih");

    private ObservableList<User> userObservableList;   //1.ovo je na kraju dodato i objasnjeno JWS 2/5
    private TableView<User> userTableView = new TableView<>();

    private String password;
//
//    OVO SU POLJA OD getForm(){}
    private final TextField usernameTextField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final TextField nameTextField = new TextField();
    private final TextField surnameTextField = new TextField();
    private final ChoiceBox<Privilege> privilegeChoiceBox = new ChoiceBox<>();
    //POLJA OD getForm(){}

    private Button addUserButton = new Button("Dodaj");
    private Button deleteUserButton = new Button("Obriši");
//KRAJ POLJA OD getForm(){}

//    NASTAVAK UserAdminPanel
    public UserAdminPanel() {
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(5);
        setPadding(new Insets(10, 10, 10, 10));

        //PROMJENA BOJE U JAVAFX APP
        BackgroundFill background_fill = new BackgroundFill(Color.BLUE,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        setBackground(background);
        titleLabel.setTextFill(Color.WHITE);
//
        TableColumn<User, String> usernameColumn = new TableColumn<>("Korisničko ime");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username")); //MORA BITI: ("username") isti naziv kao u entitetu npr. "private String username" (@Column(name = "username") private String username);
//
        TableColumn<User, String> nameColumn = new TableColumn<>("Ime");
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
//
        TableColumn<User, String> surnameColumn = new TableColumn<>("Prezime");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
//
        TableColumn<User, String> privilegeColumn = new TableColumn<>("Privilegija");
        privilegeColumn.setCellValueFactory(new PropertyValueFactory<>("idPrivilege"));

        List<User> userList = UserServiceFactory.USER_SERVICE_FACTORY.getUserServiceLocal().findAll();

        userObservableList = FXCollections.observableList(userList);   //2.ovo je na kraju dodato i objasnjeno JWS 2/5
        userTableView.setItems(userObservableList);
//        userTableView.setItems(FXCollections.observableList(userList)); //DRUGA VARIJANTA
        userTableView.getColumns().addAll(usernameColumn, nameColumn, surnameColumn, privilegeColumn);
//
        getChildren().addAll(titleLabel, userTableView, getForm());
    }

//IZRADA FORME ZA POPUNJAVANJE U UserAdmiadnannPanelu

      private HBox getForm(){
            HBox form = new HBox();
            form.setSpacing(3);
////
            List<Privilege> privileges = PrivilegeServiceFactory.PRIVILEGE_SERVICE_FACTORY.getPrivilegeServiceLocal().findAll();
            privilegeChoiceBox.setItems(FXCollections.observableList(privileges));
            privilegeChoiceBox.getSelectionModel().select(0);


//ovdje unosimo PrompText za fielde za unos username, password...
        usernameTextField.setPromptText("Username..");
        passwordField.setPromptText("Password..");
        nameTextField.setPromptText("Ime..");
        surnameTextField.setPromptText("Prezime..");

        //povezujemo ADDBUTTON sa actionEvent 1
        addUserButton.setOnAction(this::addUser);  //DODAVANJE USERA U BAZU 6 (zadnji korak)

        deleteUserButton.setOnAction(this::removeUser);

            form.getChildren().addAll(
                    usernameTextField,
                    passwordField,
                    nameTextField,
                    surnameTextField,
                    privilegeChoiceBox,
                    addUserButton,
                    deleteUserButton);
            return form;
        }
//
    //OVO SA UKLANJANJEM NIJE OBJAŠNJENO (odnosno negdje je naknadno spomenuto)
    private void removeUser(ActionEvent actionEvent) {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        UserServiceLocal userServiceLocal = UserServiceFactory.USER_SERVICE_FACTORY.getUserServiceLocal();
        //DB
//        userServiceLocal.find(selectedUser.getId());
        userServiceLocal.removeById(selectedUser.getId());
        //Table View
        userObservableList.remove(selectedUser);
    }

    //povezujemo ADDBUTTON sa actionEvent 2
    private void addUser(ActionEvent event) {
        if (validate()) {                                  // objasnjeno u SE 1/3
//            //TRANZIJENTAN
            User user = new User();
//            user.setUsername(usernameTextField.getText());
            String username = usernameTextField.getText();
            user.setUsername(username);
               // ovdje se uzima password
//            user.setPassword(passwordField.getText());   //SAMO OVA LINIJA JE ZA UZIMANJE PASSWORDA
//            passwordField.getText();
//            user.setPassword(passwordField.getText());
            String password = passwordField.getText();
            String hashedPassword = doHashing(password);
            user.setPassword(hashedPassword);



            user.setName(nameTextField.getText());
            user.setSurname(surnameTextField.getText());
            user.setIdPrivilege(privilegeChoiceBox.getValue());

//            DODAVANJE USERA U BAZU 1 (pa idemo dodati metodu u UserServiceLocal)
            UserServiceLocal userServiceLocal = UserServiceFactory.USER_SERVICE_FACTORY.getUserServiceLocal();
//            //TRANZIJENTNOG U PERZISTENTNO STANJE (DODAVANJE USERA 3)
            userServiceLocal.create(user);             //Autocommit mode: false prilikom dodavanja korisnika u bazu
            userObservableList.add(user);
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
        return !usernameTextField.getText().isBlank()
                && !passwordField.getText().isBlank()
                && !nameTextField.getText().isBlank()
                && !surnameTextField.getText().isBlank();
    }
//
//            (DODAVANJE USERA 4)
            private void clearInput(){
                usernameTextField.setText("");
                passwordField.setText("");
                nameTextField.clear();
                surnameTextField.clear();
//
            }

    public String doHashing (String input) {
        try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger bigInt = new BigInteger(1,messageDigest);
        return bigInt.toString(16);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
    }return "";
    }
//        try {
//        MessageDigest messageDigest = java.security.MessageDigest.getInstance("MD5");
//        messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
//        messageDigest.digest();
//        byte[] resultByteArray = messageDigest.digest();
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (byte b : resultByteArray){
//            stringBuilder.append(String.format("%02x", b));
//        }
//        return stringBuilder.toString();
//        }catch (NoSuchAlgorithmException e){
//            e.printStackTrace();
//        }
//        return "";
//    }
        }
