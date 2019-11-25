package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import excepciones.EmptyFieldException;
import excepciones.FieldTypedIncorrectly;
import excepciones.NotFoundException;
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
import model.MedicoEspecialista;

public class RegistrarEspHosController implements Initializable{
	ObservableList<String> listA = FXCollections.observableArrayList();
	
	@FXML
	private TextField nit;
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField lastName;
	
	@FXML
	private TextField id;

	@FXML
	private TextField number;
	
	@FXML
	private ChoiceBox<String> especiality;
	
	
	@FXML
	public void addMedical(ActionEvent event) {
		try {
			if (validateMedicData()) {
				dataMed();
				Parent root = FXMLLoader.load(getClass().getResource("/application/medicosHospitalE.fxml"));
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
		} catch (NumberFormatException e) {
			
		} catch (NotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("No existe un hospital con el nit ingresado");
			alert.showAndWait();
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("/application/medicosHospitalE.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.centerOnScreen();
				stage.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	
	public void dataMed() throws NumberFormatException, NotFoundException {
		String nombre = name.getText() +" " + lastName.getText();
		String ide = id.getText();
		int no = Integer.parseInt(number.getText());
		String espe = especiality.getValue();
		MedicoEspecialista m = new MedicoEspecialista(nombre, ide, no, espe);
		Main.getEPS().añadirMedicoHospital(m, Integer.parseInt(nit.getText()));
	}
	
	public boolean validateMedicData() throws EmptyFieldException, FieldTypedIncorrectly {
		boolean isValid = true;
		// 
		if (number.getText().isEmpty()) {
			isValid = false;
			throw new EmptyFieldException("Licencia");
		}else if (checkIfHaveCaracters(number.getText())) {
			isValid = false;
			throw new FieldTypedIncorrectly("Licencia", "La Licencia debe contener solamente numeros.");
		}
		
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
		if (lastName.getText().isEmpty()) {
			isValid = false;
			throw new EmptyFieldException("Apellido");
		} 

		// 
		if (id.getText().isEmpty()) {
			isValid = false;
			throw new EmptyFieldException("id");
		} 
		
		if (especiality.getValue() == null) {
			isValid = false;
			throw new EmptyFieldException("especialidad");
		} 
		
		
		

		return isValid;
	}
	
	public void createBoxEspe() {
		listA.remove(listA);
		listA.addAll("Pediatria","Urologia","Nutricion","Osteometria","Nefrologia","Traumatologia","Ginecologia");
		especiality.getItems().addAll(listA);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		createBoxEspe();
		
	}

}
