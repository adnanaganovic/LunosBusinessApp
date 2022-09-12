package com.lunosapp.lunosbusinessapp.view.userView;

import com.lunosapp.lunosbusinessapp.entity.Address;
import com.lunosapp.lunosbusinessapp.entity.Client;
import com.lunosapp.lunosbusinessapp.entity.Municipality;
import com.lunosapp.lunosbusinessapp.entity.Region;
import com.lunosapp.lunosbusinessapp.service.addressService.AddressServiceFactory;
import com.lunosapp.lunosbusinessapp.service.clientService.ClientServiceFactory;
import com.lunosapp.lunosbusinessapp.service.clientService.ClientServiceLocal;
import com.lunosapp.lunosbusinessapp.service.regionService.RegionServiceFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class ClientPanel extends VBox {

    private Label titleLabel = new Label("Klijenti");
    private ObservableList<Client> clientObservableList;
    private TableView<Client> clientTableView = new TableView<>();

    private final TextField nameTextField = new TextField();
    private final TextField surnameTextField = new TextField();
    private final TextField phoneTextField = new TextField();
    private final TextField emailTextField = new TextField();
    private final ChoiceBox<Address> idAddressChoiceBox = new ChoiceBox<>();

    private Button addClientButton = new Button("Dodaj klijenta");
    private Button deleteClientButton = new Button("Obriši");


    public ClientPanel() {
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(5);
        setPadding(new Insets(10, 10, 10, 10));

        //PROMJENA BOJE U JAVAFX APP
        BackgroundFill background_fill = new BackgroundFill(Color.GREEN,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        setBackground(background);
        titleLabel.setTextFill(Color.WHITE);

        //SETOVANJE VRIJEDNOSTI ĆELIJA I POVEZNICE SA ENTITY-EM // setCellValueFactory(new PropertyValueFactory<User,String>

        TableColumn<Client, Integer> idColumn = new TableColumn<>("Id klijenta");
        idColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));

        TableColumn<Client, String> nameColumn = new TableColumn<>("Ime");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("name")); //MORA BITI: ("username") isti naziv kao u entitetu npr. "private String username" (@Column(name = "username") private String username);
//
        TableColumn<Client, String> surnameColumn = new TableColumn<>("Prezime");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("surname"));
//
        TableColumn<Client, String> phoneColumn = new TableColumn<>("Telefon");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
//
        TableColumn<Client, String> emailColumn = new TableColumn<>("E-mail klijenta");
        emailColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));

        TableColumn<Client, Address> addressIdColumn = new TableColumn<>("Adresa/ID");
        addressIdColumn.setCellValueFactory(new PropertyValueFactory<>("idAddress"));

        List<Client> clientList = ClientServiceFactory.CLIENT_SERVICE_FACTORY.getClientServiceLocal().findAll();
        clientObservableList = FXCollections.observableList(clientList);   //2.ovo je na kraju dodato i objasnjeno JWS 2/5
        clientTableView.setItems(clientObservableList);
//        userTableView.setItems(FXCollections.observableList(userList)); //DRUGA VARIJANTA
        clientTableView.getColumns().addAll(idColumn, nameColumn, surnameColumn, phoneColumn, emailColumn, addressIdColumn);
//
        getChildren().addAll(titleLabel, clientTableView, getForm());
    }

    private HBox getForm(){
        HBox form = new HBox();
        form.setSpacing(3);

        List<Address> addresses = AddressServiceFactory.ADDRESS_SERVICE_FACTORY.getAddressServiceLocal().findAll();   //StackOverflowError
        idAddressChoiceBox.setItems(FXCollections.observableList(addresses));
        idAddressChoiceBox.getSelectionModel().select(0);

        phoneTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")){
                    phoneTextField.setText(newValue.replaceAll("[^\\d]", "" ));
                }
            }
        });


//ovdje unosimo PrompText za fielde za unos username, password...
        nameTextField.setPromptText("Ime..");
        surnameTextField.setPromptText("Prezime..");
        phoneTextField.setPromptText("Telefon..");
        emailTextField.setPromptText("E-mail..");

        //povezujemo ADDBUTTON sa actionEvent 1
        addClientButton.setOnAction(this::addClient);  //DODAVANJE USERA U BAZU 6 (zadnji korak)
        deleteClientButton.setOnAction(this::removeClient);

        form.getChildren().addAll(
               nameTextField,
                surnameTextField,
                phoneTextField,
                emailTextField,
                idAddressChoiceBox,
                addClientButton,
                deleteClientButton);
        return form;
    }

    private void removeClient(ActionEvent actionEvent) {
        Client selectedClient = clientTableView.getSelectionModel().getSelectedItem();
        ClientServiceLocal clientServiceLocal = ClientServiceFactory.CLIENT_SERVICE_FACTORY.getClientServiceLocal();
//        ClientServiceLocal clientServiceLocal = ClientServiceFactory.CLIENT_SERVICE_FACTORY.getClientServiceLocal().findAll();  //NE RADI !!!
        //DB
        clientServiceLocal.removeById(selectedClient.getId());
        //Table View
        clientObservableList.remove(selectedClient);
    }

    //povezujemo ADDBUTTON sa actionEvent 2
    private void addClient(ActionEvent event) {
        if (validate()) {                                  // objasnjeno u SE 1/3
//            //TRANZIJENTAN
            Client client = new Client();
            client.setName(nameTextField.getText());
            client.setSurname(surnameTextField.getText());
            client.setPhone(phoneTextField.getText());
            client.setEmail(emailTextField.getText());
            client.setIdAddress(idAddressChoiceBox.getValue());

//            DODAVANJE USERA U BAZU 1 (pa idemo dodati metodu u UserServiceLocal)
            ClientServiceLocal clientServiceLocal = ClientServiceFactory.CLIENT_SERVICE_FACTORY.getClientServiceLocal();  //NE RADI !!!
//            Exception in thread "JavaFX Application Thread" java.lang.ClassCastException: class java.util.ArrayList cannot be cast to
//            class com.lunosapp.lunosbusinessapp.service.clientService.ClientService (java.util.ArrayList is in module java.base of loader 'bootstrap';
//            com.lunosapp.lunosbusinessapp.service.clientService.ClientService is in module com.lunosapp.lunosbusinessapp of loader 'app')
//	at com.lunosapp.lunosbusinessapp/com.lunosapp.lunosbusinessapp.views.UserViews.ClientPanel.addClient(ClientPanel.java:123)

//            //TRANZIJENTNOG U PERZISTENTNO STANJE (DODAVANJE USERA 3)
            clientServiceLocal.create(client);             //Autocommit mode: false prilikom dodavanja korisnika u bazu
            clientObservableList.add(client);
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
        return !nameTextField.getText().isBlank()
                && !surnameTextField.getText().isBlank()
                && !phoneTextField.getText().isBlank();
//                && !emailTextField.getText().isBlank();
    }
    //
//            (DODAVANJE USERA 4)
    private void clearInput(){
        nameTextField.setText("");
        surnameTextField.setText("");
        phoneTextField.clear();
        emailTextField.clear();
    }
    }

