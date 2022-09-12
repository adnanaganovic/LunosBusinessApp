package com.lunosapp.lunosbusinessapp.view.userView;

import com.lunosapp.lunosbusinessapp.entity.Municipality;
import com.lunosapp.lunosbusinessapp.entity.Region;
import com.lunosapp.lunosbusinessapp.service.municipalityService.MunicipalityServiceFactory;
import com.lunosapp.lunosbusinessapp.service.municipalityService.MunicipalityServiceLocal;
import com.lunosapp.lunosbusinessapp.service.regionService.RegionServiceFactory;
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

public class MunicipalityPanel extends VBox {
    private Label titleLabel = new Label("Opštine");
    private ObservableList<Municipality> municipalityObservableList;
    private TableView<Municipality> municipalityTableView = new TableView<>();

    private final TextField municipalityNameTextField = new TextField();
    private final ChoiceBox<Region> regionChoiceBox = new ChoiceBox<>();

    private Button addMunicipalityButton = new Button("Dodaj opštinu");
    private Button deleteMunicipalityButton = new Button("Obriši");


    public MunicipalityPanel() {
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

        TableColumn<Municipality, Integer> idColumn = new TableColumn<>("ID opštine");
        idColumn.setCellValueFactory(new PropertyValueFactory<Municipality, Integer>("id"));

        TableColumn<Municipality, String> nameColumn = new TableColumn<>("Opština");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Municipality, String>("name")); //MORA BITI: ("username") isti naziv kao u entitetu npr. "private String username" (@Column(name = "username") private String username);

        TableColumn<Municipality, Region> regionIdColumn = new TableColumn<>("Regija/ID");
        regionIdColumn.setCellValueFactory(new PropertyValueFactory<>("idRegion"));


        List<Municipality> municipalityList = MunicipalityServiceFactory.MUNICIPALITY_SERVICE_FACTORY.getMunicipalityServiceLocal().findAll();
        municipalityObservableList = FXCollections.observableList(municipalityList);
        municipalityTableView.setItems(municipalityObservableList);
        municipalityTableView.getColumns().addAll(idColumn,nameColumn, regionIdColumn);    //...addAll(idColumn,nameColumn, regionIdColumn) -> java.lang.StackOverflowError NE MOŽE PRIKAZATI TABELU
//
        getChildren().addAll(titleLabel, municipalityTableView, getForm());
    }

    private HBox getForm(){
        HBox form = new HBox();
        form.setSpacing(3);

        List<Region> regions = RegionServiceFactory.REGION_SERVICE_FACTORY.getRegionServiceLocal().findAll();   //StackOverflowError
        regionChoiceBox.setItems(FXCollections.observableList(regions));
        regionChoiceBox.getSelectionModel().select(0);

//ovdje unosimo PrompText za fielde za unos username, password...
        municipalityNameTextField.setPromptText("Opština");
        //povezujemo ADDBUTTON sa actionEvent 1
        addMunicipalityButton.setOnAction(this::addMunicipality);  //DODAVANJE USERA U BAZU 6 (zadnji korak)
        deleteMunicipalityButton.setOnAction(this::removeMunicipality);

        form.getChildren().addAll(
                municipalityNameTextField,
                regionChoiceBox,
                addMunicipalityButton,
                deleteMunicipalityButton);
        return form;
    }

    private void removeMunicipality (ActionEvent actionEvent) {
        Municipality selectedMunicipality = municipalityTableView.getSelectionModel().getSelectedItem();
        MunicipalityServiceLocal municipalityServiceLocal = MunicipalityServiceFactory.MUNICIPALITY_SERVICE_FACTORY.getMunicipalityServiceLocal();
        //DB
        municipalityServiceLocal.removeById(selectedMunicipality.getId());
        //Table View
        municipalityObservableList.remove(selectedMunicipality);
    }

    //povezujemo ADDBUTTON sa actionEvent 2
    private void addMunicipality(ActionEvent event) {
        if (validate()) {                                  // objasnjeno u SE 1/3
//            //TRANZIJENTAN
            Municipality municipality = new Municipality();
            municipality.setName(municipalityNameTextField.getText());
            municipality.setIdRegion(regionChoiceBox.getValue());

//            DODAVANJE USERA U BAZU 1 (pa idemo dodati metodu u UserServiceLocal)
            MunicipalityServiceLocal municipalityServiceLocal = MunicipalityServiceFactory.MUNICIPALITY_SERVICE_FACTORY.getMunicipalityServiceLocal();
//            //TRANZIJENTNOG U PERZISTENTNO STANJE (DODAVANJE USERA 3)
            municipalityServiceLocal.create(municipality);             //Autocommit mode: false prilikom dodavanja korisnika u bazu
            municipalityObservableList.add(municipality);
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
        return !municipalityNameTextField.getText().isBlank();
    }
    //
//            (DODAVANJE USERA 4)
    private void clearInput(){
        municipalityNameTextField.setText("");
    }
}

