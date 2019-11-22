package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ChoiceIPSController implements Initializable {

	public final static String CLINICA = "Clinica";
	public final static String HOSPITAL = "Hospital";
	public final static String CENTRO_DE_SALUD = "Centro de salud";

	ObservableList<String> list = FXCollections.observableArrayList();

	@FXML
	private ChoiceBox<String> choiceBox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createBox();
	}

	public void createBox() {
		list.remove(list);
		list.addAll(CLINICA, HOSPITAL, CENTRO_DE_SALUD);
		choiceBox.getItems().addAll(list);

	}
	
	@FXML
	private void displayValue(ActionEvent event) {
		String nivel = choiceBox.getValue();
		try {
			if (nivel.equals(CLINICA)) {
				Parent root = FXMLLoader.load(getClass().getResource("/application/clinica.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.centerOnScreen();
				stage.show();
			} else if (nivel.equals(HOSPITAL)) {
				Parent root = FXMLLoader.load(getClass().getResource("/application/hospital.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.centerOnScreen();
				stage.show();
			} else if (nivel.equals(CENTRO_DE_SALUD)) {
				Parent root = FXMLLoader.load(getClass().getResource("/application/pSalud.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.centerOnScreen();
				stage.show();
			}
		} catch (NullPointerException exception) {
			Alert error = new Alert(AlertType.INFORMATION);
			error.setHeaderText("¡Selecciona una opcion!");
			error.show();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	@FXML
    void exit(ActionEvent event) {
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
}
