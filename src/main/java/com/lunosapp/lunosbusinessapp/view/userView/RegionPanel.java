package com.lunosapp.lunosbusinessapp.view.userView;

import com.lunosapp.lunosbusinessapp.entity.Privilege;
import com.lunosapp.lunosbusinessapp.entity.Region;
import com.lunosapp.lunosbusinessapp.service.privilegeService.PrivilegeServiceFactory;
import com.lunosapp.lunosbusinessapp.service.privilegeService.PrivilegeServiceLocal;
import com.lunosapp.lunosbusinessapp.service.regionService.RegionServiceFactory;
import com.lunosapp.lunosbusinessapp.service.regionService.RegionServiceLocal;
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

public class RegionPanel extends VBox {
    private Label titleLabel = new Label("Regije");
    private ObservableList<com.lunosapp.lunosbusinessapp.entity.Region> regionObservableList;
    private TableView<com.lunosapp.lunosbusinessapp.entity.Region> regionTableView = new TableView<>();
    private final TextField regionNameTextField = new TextField();

//    private final ChoiceBox<Address> regionChoiceBox = new ChoiceBox<>();

    private Button addRegionButton = new Button("Dodaj regiju");
    private Button deleteRegionButton = new Button("Obriši");


    public RegionPanel() {
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

        TableColumn<com.lunosapp.lunosbusinessapp.entity.Region, Integer> idColumn = new TableColumn<>("ID regije");
        idColumn.setCellValueFactory(new PropertyValueFactory<com.lunosapp.lunosbusinessapp.entity.Region, Integer>("id"));

        TableColumn<com.lunosapp.lunosbusinessapp.entity.Region, String> nameColumn = new TableColumn<>("Regija");
        nameColumn.setCellValueFactory(new PropertyValueFactory<com.lunosapp.lunosbusinessapp.entity.Region, String>("name")); //MORA BITI: ("username") isti naziv kao u entitetu npr. "private String username" (@Column(name = "username") private String username);

        List<com.lunosapp.lunosbusinessapp.entity.Region> regionList = RegionServiceFactory.REGION_SERVICE_FACTORY.getRegionServiceLocal().findAll();
        regionObservableList = FXCollections.observableList(regionList);
        regionTableView.setItems(regionObservableList);
        regionTableView.getColumns().addAll(idColumn,nameColumn);
//
        getChildren().addAll(titleLabel, regionTableView, getForm(), addRegionButton, deleteRegionButton);
    }

    private HBox getForm(){
        HBox form = new HBox();
        form.setSpacing(3);

//ovdje unosimo PrompText za fielde za unos username, password...
        regionNameTextField.setPromptText("Regija");
        //povezujemo ADDBUTTON sa actionEvent 1
        addRegionButton.setOnAction(this::addRegion);  //DODAVANJE USERA U BAZU 6 (zadnji korak)
        deleteRegionButton.setOnAction(this::removeRegion);

        form.getChildren().addAll(
                regionNameTextField);
        return form;
    }

    private void removeRegion (ActionEvent actionEvent) {
        Region selectedRegion = regionTableView.getSelectionModel().getSelectedItem();
        RegionServiceLocal regionServiceLocal = RegionServiceFactory.REGION_SERVICE_FACTORY.getRegionServiceLocal();
        //DB
        regionServiceLocal.removeById(selectedRegion.getId());
        //Table View
        regionObservableList.remove(selectedRegion);
    }

    //povezujemo ADDBUTTON sa actionEvent 2
    private void addRegion(ActionEvent event) {
        if (validate()) {                                  // objasnjeno u SE 1/3
//            //TRANZIJENTAN
            com.lunosapp.lunosbusinessapp.entity.Region region = new com.lunosapp.lunosbusinessapp.entity.Region();
            region.setName(regionNameTextField.getText());

//            DODAVANJE USERA U BAZU 1 (pa idemo dodati metodu u UserServiceLocal)
            RegionServiceLocal regionServiceLocal = RegionServiceFactory.REGION_SERVICE_FACTORY.getRegionServiceLocal();
//            //TRANZIJENTNOG U PERZISTENTNO STANJE (DODAVANJE USERA 3)
            regionServiceLocal.create(region);             //Autocommit mode: false prilikom dodavanja korisnika u bazu
            regionObservableList.add(region);
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
        return !regionNameTextField.getText().isBlank();
    }
    //
//            (DODAVANJE USERA 4)
    private void clearInput(){
        regionNameTextField.setText("");
    }
}
