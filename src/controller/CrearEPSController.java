package controller;

import application.Main;
import excepciones.EmptyFieldException;
import excepciones.FieldTypedIncorrectly;
import excepciones.MinimunActivesException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class CrearEPSController {

	@FXML
	private TextField txtFldName;

	@FXML
	private TextField txtFldActivos;

	@FXML
	void createEPS(ActionEvent event) {
		try {
			if (validateMarketData()) {
				initializateEPS();
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/application/login.fxml"));
					Scene scene = new Scene(root);
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.centerOnScreen();
					stage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				cleanFields();

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
		} catch (MinimunActivesException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	public void initializateEPS() throws MinimunActivesException {
		String EPSname = txtFldName.getText();
		double activos = Integer.parseInt(txtFldActivos.getText());

		
		if (activos < 1000000000) {
			throw new MinimunActivesException(
					"El valor de activos no es valido para la iniciacion del sistema de una EPS");
		}else {
			Main.getEPS().setNombre(EPSname);
			Main.getEPS().setCaraterEconomico(activos);
		}
		

	}

	public boolean validateMarketData() throws EmptyFieldException, FieldTypedIncorrectly, EmptyFieldException {
		boolean isValid = true;
		// Chequeo si el nombre del Market ingresado es valido
		if (txtFldName.getText().isEmpty()) {
			isValid = false;
			throw new EmptyFieldException("Nombre");
		}

		// Chequeo si el nit ingresado es valido
		if (txtFldActivos.getText().isEmpty()) {
			isValid = false;
			throw new EmptyFieldException("Nit");
		} else if (checkIfIsFreeOfCaractersOrHaveSpaces(txtFldActivos.getText())) {
			isValid = false;
			throw new FieldTypedIncorrectly("Nit", "Este debe contener unicamente numeros sin espacios");
		}

		return isValid;
	}

	public boolean checkIfIsFreeOfCaractersOrHaveSpaces(String x) {
		boolean freeOfCaracters = false;
		for (int i = 0; i < x.length(); i++) {
			if (Character.isAlphabetic(x.charAt(i)) || Character.isWhitespace(x.charAt(i))) {
				freeOfCaracters = true;
			}
		}
		return freeOfCaracters;
	}

	public void cleanFields() {
		txtFldName.clear();
		txtFldActivos.clear();
	}
}
