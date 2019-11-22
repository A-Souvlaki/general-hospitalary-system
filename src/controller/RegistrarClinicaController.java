package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import excepciones.EmptyFieldException;
import excepciones.FieldTypedIncorrectly;
import excepciones.IncorrectPassWordException;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.IPS;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RegistrarClinicaController implements Initializable{
	
	ObservableList<String> listA = FXCollections.observableArrayList();
	ObservableList<String> listB = FXCollections.observableArrayList();
	
	@FXML
	private TextField nit;
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField adress;
	
	@FXML
	private ChoiceBox<String> tipe;
	
	@FXML
	private ChoiceBox<String> level;
	
	@FXML
	private CheckBox ep;
	
	@FXML
	public void addClinic(ActionEvent event) {
		try {
			if (validateClinicData()) {
				dataClinic();
				Parent root = FXMLLoader.load(getClass().getResource("/application/clinica.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.centerOnScreen();
				stage.show();
			}
		} catch (EmptyFieldException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} catch (FieldTypedIncorrectly e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void dataClinic() {
		String nitA = nit.getText();
		double n = Double.parseDouble(nitA);
		String nameA = name.getText();
		String adrss = adress.getText();
		String typ = tipe.getValue();
		String lvl = level.getValue();
		int l = Integer.parseInt(lvl);
		boolean especialidad;
		if (ep.isSelected()) {
			especialidad = true;
		}else {
			especialidad = false;
		}
		Main.getEPS().agregarIPS(n, nameA, adrss, typ,l,especialidad);
	}
	
	public boolean validateClinicData() throws EmptyFieldException, FieldTypedIncorrectly {
		boolean isValid = true;
		// 
		if (nit.getText().isEmpty()) {
			isValid = false;
			throw new EmptyFieldException("Nit");
		}else if (checkIfHaveCaracters(nit.getText())) {
			isValid = false;
			throw new FieldTypedIncorrectly("Nit", "El Nit debe contener solamente numeros.");
		}

		// 
		if (name.getText().isEmpty()) {
			isValid = false;
			throw new EmptyFieldException("Nombre");
		}

		// 
		if (adress.getText().isEmpty()) {
			isValid = false;
			throw new EmptyFieldException("Direccion");
		} 

		// 
		if (tipe.getValue() == null) {
			isValid = false;
			throw new EmptyFieldException("Contraseña");
		} 
		
		if (level.getValue() == null) {
			isValid = false;
			throw new EmptyFieldException("Contraseña");
		} 
		
		

		return isValid;
	}
	
	public boolean checkIfHaveCaracters(String id) {
		boolean valid = false;
		for (int i = 0; i < id.length(); i++) {
			if (Character.isAlphabetic(id.charAt(i)) || Character.isWhitespace(id.charAt(i))) {
				valid = true;
			}
		}
		return valid;
	}
	
	public void createBoxType() {
		listA.remove(listA);
		listA.addAll("Privada","Mixta");
		tipe.getItems().addAll(listA);

	}
	
	public void createBoxLevel() {
		listB.remove(listB);
		listB.addAll("1","2","3");
		level.getItems().addAll(listB);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createBoxType();
		createBoxLevel();
		
	}
	
	
}
