package com.lunosapp.lunosbusinessapp.views.AdminViews;

import com.lunosapp.lunosbusinessapp.entity.Project;
import com.lunosapp.lunosbusinessapp.entity.ProjectOrder;
import com.lunosapp.lunosbusinessapp.service.projectOrderService.ProjectOrderService;
import com.lunosapp.lunosbusinessapp.service.projectOrderService.ProjectOrderServiceFactory;
import com.lunosapp.lunosbusinessapp.service.projectService.ProjectService;
import com.lunosapp.lunosbusinessapp.service.projectService.ProjectServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

public class ProjectOrderPanel extends VBox {

    private Label titleLabel = new Label("Narudžbe");

    private ObservableList<ProjectOrder> projectOrderObservableList;
    private TableView<ProjectOrder> projectOrderTableView = new TableView<>();


    private TextField idClientTextField = new TextField();
    private TextField idProjectTextField = new TextField();
    private TextField installationDateTextField = new TextField();
    private TextField statusTextField = new TextField();


    private Button addOrderButton = new Button("Dodaj narudžbu");
    private Button deleteOrderButton = new Button("Obriši");

    public ProjectOrderPanel() {
        titleLabel.setFont(new Font("Arial", 20));
        setSpacing(5);
        setPadding(new Insets(10, 10, 10, 10));

        //PROMJENA BOJE U JAVAFX APP
        StackPane stackPane = new StackPane();
        BackgroundFill background_fill = new BackgroundFill(Color.GREEN,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        setBackground(background);
        titleLabel.setTextFill(Color.WHITE);




//        TableColumn<ProjectOrder, Integer> idClient = new TableColumn<>("Id klijenta");
//        idClient.setCellValueFactory(new PropertyValueFactory<ProjectOrder, Integer>("id")); //MORA BITI: ("username") isti naziv kao u entitetu npr. "private String username" (@Column(name = "username") private String username);
//
//        TableColumn<ProjectOrder, Integer> idProject = new TableColumn<>("Id projekta");
//        idProject.setCellValueFactory(new PropertyValueFactory<ProjectOrder, Integer>("id"));

//        TableColumn<ProjectOrder, DateFormat> installDate = new TableColumn<>("Datum instalacije");
//        installDate.setCellValueFactory(new PropertyValueFactory<ProjectOrder, DateFormat>("installation_date"));
////
//        TableColumn<ProjectOrder, Integer> status = new TableColumn<>("Status");
//        status.setCellValueFactory(new PropertyValueFactory<ProjectOrder, Integer>("status"));


//        ProjectOrderService projectOrderService = ProjectOrderServiceFactory.PROJECT_ORDER_SERVICE_FACTORY.getProjectOrderService();
//        projectOrderObservableList = FXCollections.observableList(projectOrderService.findAll());
//        projectOrderTableView.setItems(projectOrderObservableList);
//        projectOrderTableView.getColumns().addAll( idClient, idProject, installDate, status);

        getChildren().addAll(titleLabel, projectOrderTableView, getForm());
    }


    private HBox getForm(){
        HBox form = new HBox();
        form.setSpacing(3);


        idClientTextField.setPromptText("ID klijenta");
        idProjectTextField.setPromptText("ID projekta");
        installationDateTextField.setPromptText("Datum instalacije");
        statusTextField.setPromptText("Status projekta");

        addOrderButton.setOnAction(this::addProjectOrder);
        deleteOrderButton.setOnAction(this::removeProjectOrder);

        form.getChildren().addAll(idClientTextField, idProjectTextField, installationDateTextField, statusTextField, addOrderButton, deleteOrderButton);
        return form;
    }


    private void addProjectOrder(ActionEvent actionEvent){
        if (validate()) {
            ProjectOrder projectOrder = new ProjectOrder();
//            projectOrder.setIdClient(Integer.parseInt(idClientTextField.getText()));
//            projectOrder.setIdProject(Integer.parseInt(idProjectTextField.getText()));
//            projectOrder.setInstallationDate(new Date(installationDateTextField.getText()));
//            projectOrder.setStatus(Integer.parseInt(statusTextField.getText()));

            //NAKON OVOG KREIRAMO observabilnu listu i clearamo inpute
//            ProjectOrderService projectOrderService = ProjectOrderServiceFactory.PROJECT_ORDER_SERVICE_FACTORY.getProjectOrderService();
//            projectOrderService.create(projectOrder);
//            projectOrderObservableList.add(projectOrder);
//            idClientTextField.setText("");  //DRUGA OPCIJA CLEAR INPUT je da napravimo metodu sa ove 3 linije koda, i onda samo poyovemo clearInput();
//            idProjectTextField.clear();
//            installationDateTextField.setText("");
//            statusTextField.setText("");
        }else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sva polja moraju biti unesena!");
            a.show();
        }
    }

    private void removeProjectOrder(ActionEvent actionEvent) {
//        ProjectOrder selectedProject = projectOrderTableView.getSelectionModel().getSelectedItem();
//        ProjectOrderService projectOrderService = ProjectOrderServiceFactory.PROJECT_ORDER_SERVICE_FACTORY.getProjectOrderService();
        //DB
//        projectService(selectedProject.getId());
//        projectOrderService.removeById(selectedProject.getId());
        //Table View
//        projectOrderObservableList.remove(selectedProject);
    }

    private boolean validate() {                           // objasnjeno u SE 1/3
        return !idClientTextField.getText().isBlank()
                && !idProjectTextField.getText().isBlank()
                && !installationDateTextField.getText().isBlank()
                && !statusTextField.getText().isBlank();
    }

}
