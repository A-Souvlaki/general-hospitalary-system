package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import excepciones.EmptyFieldException;
import excepciones.FieldTypedIncorrectly;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RegistrarPuestoDeSaludController implements Initializable {
	
	ObservableList<String> listA = FXCollections.observableArrayList();
	ObservableList<String> listB = FXCollections.observableArrayList();
	ObservableList<String> listC = FXCollections.observableArrayList();
	ObservableList<String> listD = FXCollections.observableArrayList();
	
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
	private TextField nurse;
	
	@FXML
	private TextField laboratory;
	
	@FXML
	private ChoiceBox<String> vacum;
	
	@FXML
	private ChoiceBox<String> pharmacy;
	

	
	@FXML
	public void addClinic(ActionEvent event) {
		try {
			if (validateClinicData()) {
				dataPSalud();
				Parent root = FXMLLoader.load(getClass().getResource("/application/pSalud.fxml"));
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
	
	
	public void dataPSalud() {
		String nitA = nit.getText();
		int n = Integer.parseInt(nitA);
		String nameA = name.getText();
		String adrss = adress.getText();
		String typ = tipe.getValue();
		String lvl = level.getValue();
		int l = Integer.parseInt(lvl);
		String jefe_E = nurse.getText();
		String jefe_L = laboratory.getText();
		String vac = vacum.getValue();
		String pharm = pharmacy.getValue();
		Main.getEPS().agregarIPS(n, nameA, adrss, typ,l,jefe_E,jefe_L,vac,pharm);
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
		
		if (nurse.getText().isEmpty()) {
			isValid = false;
			throw new EmptyFieldException("Direccion");
		} 
		
		if (laboratory.getText().isEmpty()) {
			isValid = false;
			throw new EmptyFieldException("Direccion");
		} 

		// 
		if (tipe.getValue() == null) {
			isValid = false;
			throw new EmptyFieldException("tipo");
		} 
		
		if (level.getValue() == null) {
			isValid = false;
			throw new EmptyFieldException("nivel");
		} 
		
		if (vacum.getValue() == null) {
			isValid = false;
			throw new EmptyFieldException("especialidad");
		} 
		
		if (pharmacy.getValue() == null) {
			isValid = false;
			throw new EmptyFieldException("especialidad");
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
	
	public void createBoxVacum() {
		listC.remove(listC);
		listC.addAll("Si","No");
		vacum.getItems().addAll(listC);

	}
	
	public void createBoxPharmacy() {
		listD.remove(listD);
		listD.addAll("Si","No");
		pharmacy.getItems().addAll(listC);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createBoxType();
		createBoxLevel();
		createBoxVacum();
		createBoxPharmacy();
		
	}
}
