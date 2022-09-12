package com.lunosapp.lunosbusinessapp.view.userView;

import com.lunosapp.lunosbusinessapp.entity.Project;
import com.lunosapp.lunosbusinessapp.service.projectService.ProjectService;
import com.lunosapp.lunosbusinessapp.service.projectService.ProjectServiceFactory;
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

import java.math.BigDecimal;

public class ProjectAdminPanel extends VBox {

    private Label titleLabel = new Label("Projekti");
    private ObservableList<Project> projectObservableList;
    private TableView<Project> projectTableView = new TableView<>();

    private TextField projectSystemTypeTextField = new TextField();
    private TextField numberOfElementsTextField = new TextField();
    private TextField projectPriceTextField = new TextField();


    private Button addProjectButton = new Button("Dodaj projekat");
    private Button deleteProjectButton = new Button("Obriši");

    public ProjectAdminPanel(){
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


        //PODEŠAVANJE DA U numberOfElements unose samo brojevi (OVAKO OVERRIDE-amo prvobitno ponašanje listenera
        numberOfElementsTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")){
                    numberOfElementsTextField.setText(newValue.replaceAll("[^\\d]", "" ));
                }
            }
        });
        projectPriceTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(!newValue.matches("\\d*")){
                    projectPriceTextField.setText(newValue.replaceAll("[^\\d]", "" ));
                }
            }
        });

        TableColumn<Project, Integer> idProjekta = new TableColumn<>("Id projekta");
        idProjekta.setCellValueFactory(new PropertyValueFactory<Project, Integer>("id"));

        TableColumn<Project, String> projectSystemType = new TableColumn<>("Tip sistema");
        projectSystemType.setCellValueFactory(new PropertyValueFactory<Project, String>("systemType")); //MORA BITI: ("username") isti naziv kao u entitetu npr. "private String username" (@Column(name = "username") private String username);
//
        TableColumn<Project, Integer> numberOfElements = new TableColumn<>("Broj elemenata sistema");
        numberOfElements.setCellValueFactory(new PropertyValueFactory<Project, Integer>("numberOfElements"));
//
        TableColumn<Project, BigDecimal> price = new TableColumn<>("Cijena sistema u KM");
        price.setCellValueFactory(new PropertyValueFactory<Project, BigDecimal>("price"));


        ProjectService projectService = ProjectServiceFactory.PROJECT_SERVICE_FACTORY.getProjectService();
        projectObservableList = FXCollections.observableList(projectService.findAll());
        projectTableView.setItems(projectObservableList);
        projectTableView.getColumns().addAll(idProjekta, projectSystemType, numberOfElements, price);

        getChildren().addAll(titleLabel, projectTableView, getForm());
    }

    private HBox getForm(){
        HBox form = new HBox();
        form.setSpacing(3);


        projectSystemTypeTextField.setPromptText("Tip sistema");
        numberOfElementsTextField.setPromptText("Broj elemenata sistema");
        projectPriceTextField.setPromptText("Cijena sistema");

        addProjectButton.setOnAction(this::addProject);
        deleteProjectButton.setOnAction(this::removeProject);

        form.getChildren().addAll(projectSystemTypeTextField, numberOfElementsTextField, projectPriceTextField, addProjectButton, deleteProjectButton);
        return form;
    }


    private void addProject(ActionEvent actionEvent){
        if (validate()) {
            Project project = new Project();
            project.setSystemType(projectSystemTypeTextField.getText());
            project.setNumberOfElements(Integer.parseInt(numberOfElementsTextField.getText()));
            project.setPrice(new BigDecimal(projectPriceTextField.getText()));
            //NAKON OVOG KREIRAMO observabilnu listu i clearamo inpute
            ProjectService projectService = ProjectServiceFactory.PROJECT_SERVICE_FACTORY.getProjectService();
            projectService.create(project);
            projectObservableList.add(project);
            projectSystemTypeTextField.setText("");  //DRUGA OPCIJA CLEAR INPUT je da napravimo metodu sa ove 3 linije koda, i onda samo poyovemo clearInput();
            numberOfElementsTextField.clear();
            projectPriceTextField.setText("");
        }else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Sva polja moraju biti unesena!");
            a.show();
        }
    }

    private void removeProject(ActionEvent actionEvent) {
        Project selectedProject = projectTableView.getSelectionModel().getSelectedItem();
        ProjectService projectService = ProjectServiceFactory.PROJECT_SERVICE_FACTORY.getProjectService();
        //DB
//        projectService(selectedProject.getId());
        projectService.removeById(selectedProject.getId());
        //Table View
        projectObservableList.remove(selectedProject);
    }

    private boolean validate() {                           // objasnjeno u SE 1/3
        return !projectSystemTypeTextField.getText().isBlank()
                && !numberOfElementsTextField.getText().isBlank()
                && !projectPriceTextField.getText().isBlank();
    }

}
