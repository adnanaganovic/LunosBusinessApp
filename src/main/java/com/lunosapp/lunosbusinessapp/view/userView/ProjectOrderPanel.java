package com.lunosapp.lunosbusinessapp.view.userView;

import com.lunosapp.lunosbusinessapp.entity.*;
import com.lunosapp.lunosbusinessapp.service.addressService.AddressServiceFactory;
import com.lunosapp.lunosbusinessapp.service.clientService.ClientServiceFactory;
import com.lunosapp.lunosbusinessapp.service.projectOrderService.ProjectOrderService;
import com.lunosapp.lunosbusinessapp.service.projectOrderService.ProjectOrderServiceFactory;
import com.lunosapp.lunosbusinessapp.service.projectService.ProjectServiceFactory;
import com.lunosapp.lunosbusinessapp.service.statusService.StatusServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class ProjectOrderPanel extends VBox {

    private Label titleLabel = new Label("Narudžbe");

    private ObservableList<ProjectOrder> projectOrderObservableList;
    private TableView<ProjectOrder> projectOrderTableView = new TableView<>();


    private ChoiceBox<Client> idClientTextField = new ChoiceBox<>();
    private ChoiceBox<Project> idProjectTextField = new ChoiceBox<>();
    private TextField installationDateTextField = new TextField();
//    private ChoiceBox<Status> statusTextField = new ChoiceBox<>();
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



        TableColumn<ProjectOrder, Integer> idProjectOrder = new TableColumn<>("Id narudžbe");
        idProjectOrder.setCellValueFactory(new PropertyValueFactory<ProjectOrder, Integer>("id"));

        TableColumn<ProjectOrder, Client> idClient = new TableColumn<>("Klijent/ID/Opština");
        idClient.setCellValueFactory(new PropertyValueFactory<>("idClient")); //???"idProject" je ispravan -> MORA BITI: ("username") isti naziv kao u entitetu npr. "private String username" (@Column(name = "username") private String username);

        TableColumn<ProjectOrder, Project> idProject = new TableColumn<>("Projekat/ID/Cijena");
        idProject.setCellValueFactory(new PropertyValueFactory<>("idProject"));  //"idProject" je ispravno ali izbacuje -StackOverFlowException-

        TableColumn<ProjectOrder, Integer> installDate = new TableColumn<>("Datum instalacije");
        installDate.setCellValueFactory(new PropertyValueFactory<>("installationDate"));
//
        TableColumn<ProjectOrder, Status> status = new TableColumn<>("Status");
        status.setCellValueFactory(new PropertyValueFactory<>("status"));


        ProjectOrderService projectOrderService = ProjectOrderServiceFactory.PROJECT_ORDER_SERVICE_FACTORY.getProjectOrderService();
        projectOrderObservableList = FXCollections.observableList(projectOrderService.findAll());
        projectOrderTableView.setItems(projectOrderObservableList);
        projectOrderTableView.getColumns().addAll( idProjectOrder, idClient, idProject, installDate, status);

        getChildren().addAll(titleLabel, projectOrderTableView, getForm());
    }


    private HBox getForm(){
        HBox form = new HBox();
        form.setSpacing(3);

        List<Client> clients = ClientServiceFactory.CLIENT_SERVICE_FACTORY.getClientServiceLocal().findAll();   //StackOverflowError
        idClientTextField.setItems(FXCollections.observableList(clients));
        idClientTextField.getSelectionModel().select(0);
        List<Project> projects = ProjectServiceFactory.PROJECT_SERVICE_FACTORY.getProjectService().findAll();   //StackOverflowError
        idProjectTextField.setItems(FXCollections.observableList(projects));
        idProjectTextField.getSelectionModel().select(0);
//        List<Status> statuses = StatusServiceFactory.STATUS_SERVICE_FACTORY.getStatusServiceLocal().findAll();
//        statusTextField.setItems(FXCollections.observableList(statuses));
//        statusTextField.getSelectionModel().select(0);

//        idProjectOrderTextField.setPromptText("ID narudžbe");
//        idClientTextField.setPromptText("ID klijenta");
//        idProjectTextField.setPromptText("ID projekta");
        installationDateTextField.setPromptText("Datum: npr.[24112022]");
        statusTextField.setPromptText("Status projekta: npr.[1]");

        addOrderButton.setOnAction(this::addProjectOrder);
        deleteOrderButton.setOnAction(this::removeProjectOrder);

        form.getChildren().addAll( idClientTextField, idProjectTextField, installationDateTextField, statusTextField, addOrderButton, deleteOrderButton);
        return form;
    }


    private void addProjectOrder(ActionEvent actionEvent){
        if (validate()) {
            ProjectOrder projectOrder = new ProjectOrder();
            projectOrder.setClientId(idClientTextField.getValue());
            projectOrder.setIdProject(idProjectTextField.getValue());
            projectOrder.setInstallationDate(Integer.parseInt(installationDateTextField.getText()));
//            projectOrder.setInstallationDate(Date.from(Instant.parse(installationDateTextField.getText())));
//            projectOrder.setStatus(statusTextField.getValue());
            projectOrder.setStatus(Integer.parseInt(statusTextField.getText()));

            //NAKON OVOG KREIRAMO observabilnu listu i clearamo inpute
            ProjectOrderService projectOrderService = ProjectOrderServiceFactory.PROJECT_ORDER_SERVICE_FACTORY.getProjectOrderService();
            projectOrderService.create(projectOrder);
            projectOrderObservableList.add(projectOrder);

//            idClientTextField.setText("");  //DRUGA OPCIJA CLEAR INPUT je da napravimo metodu sa ove 3 linije koda, i onda samo poyovemo clearInput();
//            idProjectTextField.clear();
//            idProjectOrderTextField.clear();
//            installationDateTextField.setText("");
//            statusTextField.setText("");
            clearInput();
        }else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sva polja moraju biti unesena!");
            a.show();
        }
    }

    private void removeProjectOrder(ActionEvent actionEvent) {
        ProjectOrder selectedProjectOrder = projectOrderTableView.getSelectionModel().getSelectedItem();
        ProjectOrderService projectOrderService = ProjectOrderServiceFactory.PROJECT_ORDER_SERVICE_FACTORY.getProjectOrderService();
                              // DB
        projectOrderService.removeById(selectedProjectOrder.getId());
                           // Table View
        projectOrderObservableList.remove(selectedProjectOrder);
    }

    private boolean validate() {                           // objasnjeno u SE 1/3
        return
//                !idProjectTextField.getText().isBlank()
//                && !idClientTextField.getText().isBlank()
                 !installationDateTextField.getText().isBlank()
                 && !statusTextField.getText().isBlank();
    }
    private void clearInput(){
        installationDateTextField.setText("");
        statusTextField.setText("");
    }

}
