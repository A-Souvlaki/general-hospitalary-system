package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import excepciones.EmptyFieldException;
import excepciones.IncorrectPassWordException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import threads.ClockThread;
import javafx.scene.Node;

public class LoginController implements Initializable {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private TextField textField;

	@FXML
	private PasswordField pw;

	@FXML
	private Button logIn;

	@FXML
	private Button signIn;
	
	@FXML
	private Label labelTime;
	
	 
	private ClockThread t;

	@FXML
	void ingresarInformacionAdmin(ActionEvent event) {
		try {
			if (validarInformacionAdmin()) {
				try {
					Parent root = FXMLLoader.load(getClass().getResource("/application/menuAdmin.fxml"));
					Scene scene = new Scene(root);
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.centerOnScreen();
					stage.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (EmptyFieldException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("User name incorrect, please try again");
			alert.showAndWait();
		} catch (IncorrectPassWordException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Password Incorrect, please try again");
			alert.showAndWait();
		}catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Incorrect, please try again");
			alert.showAndWait();
			
		} 
	}



	public boolean validarInformacionAdmin() throws EmptyFieldException, Exception, IncorrectPassWordException {
		boolean valid = true;
		// Chequeo si el ID ingresado es valido
		if (textField.getText().isEmpty()) {
			valid = false;
			throw new EmptyFieldException("El usuario es el ID del manager");
		} else if (!(textField.getText().equals(String.valueOf(Main.getEPS().getAdmin().getName())))) {
			valid = false;
			throw new Exception("error");
		}
		// Chequeo si la contraseña ingresada es valida
		if (pw.getText().isEmpty()) {
			valid = false;
			throw new EmptyFieldException("Contraseña");
		} else if (!(pw.getText().equals(Main.getEPS().getAdmin().getPassword()))) {
			valid = false;
			throw new IncorrectPassWordException(IncorrectPassWordException.INCORRECT_INPUT);
		}
		return valid;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startClock();

	}
	
	public void updateTime(String time) {
    	labelTime.setText(time);
    }
	
	 public void startClock() {
	    	t = new ClockThread(this);
	    	t.start();
	    }

}
