package com.lunosapp.lunosbusinessapp.view.userView;

import com.lunosapp.lunosbusinessapp.entity.Address;
import com.lunosapp.lunosbusinessapp.entity.Municipality;
import com.lunosapp.lunosbusinessapp.entity.Region;
import com.lunosapp.lunosbusinessapp.service.addressService.AddressServiceFactory;
import com.lunosapp.lunosbusinessapp.service.addressService.AddressServiceLocal;
import com.lunosapp.lunosbusinessapp.service.municipalityService.MunicipalityServiceFactory;
import com.lunosapp.lunosbusinessapp.service.municipalityService.MunicipalityServiceLocal;
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

public class AddressPanel extends VBox {

    //Exception in thread "JavaFX Application Thread" java.lang.StackOverflowError
    //	at com.lunosapp.lunosbusinessapp/com.lunosapp.lunosbusinessapp.entity.Municipality.toString(Municipality.java:118)
    //	at java.base/java.lang.String.valueOf(String.java:2951)
    //	at com.lunosapp.lunosbusinessapp/com.lunosapp.lunosbusinessapp.entity.Address.toString(Address.java:124)
    //	at java.base/java.lang.String.valueOf(String.java:2951)
    //	at java.base/java.lang.StringBuilder.append(StringBuilder.java:172)
    //	at java.base/java.util.AbstractCollection.toString(AbstractCollection.java:473)
    //	at org.hibernate.orm.core@5.6.7.Final/org.hibernate.collection.internal.PersistentBag.toString(PersistentBag.java:622)
    //	at java.base/java.lang.String.valueOf(String.java:2951)
    //	at com.lunosapp.lunosbusinessapp/com.lunosapp.lunosbusinessapp.entity.Municipality.toString(Municipality.java:118)
    //	at java.base/java.lang.String.valueOf(String.java:2951)
    //	at com.lunosapp.lunosbusinessapp/com.lunosapp.lunosbusinessapp.entity.Address.toString(Address.java:124)
    private Label titleLabel = new Label("Adrese");
    private ObservableList<Address> addressObservableList;
    private TableView<Address> addressTableView = new TableView<>();
    private final TextField streetNameTextField = new TextField();
    private final TextField numberTextField = new TextField();
    private final TextField floorTextField = new TextField();

    private final ChoiceBox<Municipality> municipalityChoiceBox = new ChoiceBox<>();

    private Button addAddressButton = new Button("Dodaj adresu");
    private Button deleteAddressButton = new Button("Obriši");


    public AddressPanel() {
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(5);
        setPadding(new Insets(10, 10, 10, 10));

        //PROMJENA BOJE U JAVAFX APP
        BackgroundFill background_fill = new BackgroundFill(Color.DARKGREY,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        setBackground(background);
        titleLabel.setTextFill(Color.WHITE);

        //SETOVANJE VRIJEDNOSTI ĆELIJA I POVEZNICE SA ENTITY-EM // setCellValueFactory(new PropertyValueFactory<User,String>

        TableColumn<Address, Integer> idColumn = new TableColumn<>("ID adrese");
        idColumn.setCellValueFactory(new PropertyValueFactory<Address, Integer>("id"));

        TableColumn<Address, String> streetColumn = new TableColumn<>("Ulica");
        streetColumn.setCellValueFactory(new PropertyValueFactory<Address, String>("street")); //MORA BITI: ("username") isti naziv kao u entitetu npr. "private String username" (@Column(name = "username") private String username);

        TableColumn<Address, String> numberColumn = new TableColumn<>("Broj");
        numberColumn.setCellValueFactory(new PropertyValueFactory<Address, String>("number"));

        TableColumn<Address, String> floorColumn = new TableColumn<>("Sprat");
        floorColumn.setCellValueFactory(new PropertyValueFactory<Address, String>("floor"));

        TableColumn<Address, Municipality> municipalityIdColumn = new TableColumn<>("Opština/ID");
        municipalityIdColumn.setCellValueFactory(new PropertyValueFactory<>("idMunicipality"));


        List<Address> addressList = AddressServiceFactory.ADDRESS_SERVICE_FACTORY.getAddressServiceLocal().findAll();
        addressObservableList = FXCollections.observableList(addressList);
        addressTableView.setItems(addressObservableList);
        addressTableView.getColumns().addAll(idColumn, streetColumn, numberColumn, floorColumn, municipalityIdColumn);
//
        getChildren().addAll(titleLabel, addressTableView, getForm());
    }

    private HBox getForm(){
        HBox form = new HBox();
        form.setSpacing(3);

        numberTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")){
                    numberTextField.setText(newValue.replaceAll("[^\\d]", "" ));
                }
            }
        });
        floorTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")){
                    floorTextField.setText(newValue.replaceAll("[^\\d]", "" ));
                }
            }
        });

        List<Municipality> municipalityList = MunicipalityServiceFactory.MUNICIPALITY_SERVICE_FACTORY.getMunicipalityServiceLocal().findAll();
        municipalityChoiceBox.setItems(FXCollections.observableList(municipalityList));
        municipalityChoiceBox.getSelectionModel().select(0);

//ovdje unosimo PrompText za fielde za unos username, password...
        streetNameTextField.setPromptText("Ulica");
        numberTextField.setPromptText("Broj");
        floorTextField.setPromptText("Sprat");
        //povezujemo ADDBUTTON sa actionEvent 1
        addAddressButton.setOnAction(this::addAddress);  //DODAVANJE USERA U BAZU 6 (zadnji korak)
        deleteAddressButton.setOnAction(this::removeAddress);

        form.getChildren().addAll(
                streetNameTextField,
                numberTextField,
                floorTextField,
                municipalityChoiceBox,
                addAddressButton,
                deleteAddressButton);
        return form;
    }

    private void removeAddress (ActionEvent actionEvent) {
        Address selectedAddress = addressTableView.getSelectionModel().getSelectedItem();
        AddressServiceLocal addressServiceLocal = AddressServiceFactory.ADDRESS_SERVICE_FACTORY.getAddressServiceLocal();
        //DB
        addressServiceLocal.removeById(selectedAddress.getId());
        //Table View
        addressObservableList.remove(selectedAddress);
    }

    //povezujemo ADDBUTTON sa actionEvent 2
    private void addAddress(ActionEvent event) {
        if (validate()) {                                  // objasnjeno u SE 1/3
//            //TRANZIJENTAN
            Address address = new Address();
            address.setStreet(streetNameTextField.getText());
            address.setNumber(numberTextField.getText());
            address.setFloor(floorTextField.getText());
            address.setIdMunicipality(municipalityChoiceBox.getValue());

//            DODAVANJE USERA U BAZU 1 (pa idemo dodati metodu u UserServiceLocal)
            AddressServiceLocal addressServiceLocal = AddressServiceFactory.ADDRESS_SERVICE_FACTORY.getAddressServiceLocal();
//            //TRANZIJENTNOG U PERZISTENTNO STANJE (DODAVANJE USERA 3)
            addressServiceLocal.create(address);             //Autocommit mode: false prilikom dodavanja korisnika u bazu
            addressObservableList.add(address);
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
        return !streetNameTextField.getText().isBlank()
                && !numberTextField.getText().isBlank()
                && !floorTextField.getText().isBlank();
    }
    //
//            (DODAVANJE USERA 4)
    private void clearInput(){
        streetNameTextField.setText("");
        numberTextField.setText("");
        floorTextField.setText("");
    }
}
