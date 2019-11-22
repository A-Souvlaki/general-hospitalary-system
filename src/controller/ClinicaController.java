package controller;

import java.io.IOException;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import model.IPS;

public class ClinicaController {
	
	private Stage stage;
    @FXML
    private Label lblOf;

    @FXML
    private TableView<IPS> listIPS;

    @FXML
    void goBackAdministratorMenu(ActionEvent event) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/application/menuAdmin.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
    }

    @FXML
    void refresh(ActionEvent event) {
    	listIPS = new TableView<IPS>();
    	
//		
    	TableColumn<IPS, String> nitColumn = new TableColumn<>("Nit");
    	nitColumn.setMinWidth(100);
    	nitColumn.setCellValueFactory(new PropertyValueFactory<IPS, String>("nit"));
//   	
    	TableColumn<IPS, String> nameColumn = new TableColumn<>("Nombre");
    	nameColumn.setMinWidth(100);
    	nameColumn.setCellValueFactory(new PropertyValueFactory<IPS, String>("cost"));  	
    	listIPS.getColumns().addAll(nitColumn,nameColumn);
    	try {
    		ObservableList<IPS> x = FXCollections.observableArrayList(Main.getEPS().obtenerClinicasParaMostrar());
        	listIPS.setItems(x);
    	} catch (NullPointerException e) {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("El registro esta vacio");
			alert.showAndWait();
			
		}
    	
    }
    
    @FXML
    void register(ActionEvent event) {
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/registroClinica.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }

 

}
