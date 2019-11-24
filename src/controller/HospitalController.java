package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import excepciones.NotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Hospital;


public class HospitalController implements Initializable {
	private ObservableList<Hospital> hospitales;

	private Stage stage;
	@FXML
	private Label lblOf;

	@FXML
	private AnchorPane panel;

	@FXML
	private VBox vbox;

	@FXML
	private Button delete;

	@FXML
	private MenuItem sortAgent;


	@FXML
	private Button ver;

	private TableView<Hospital> listIPS;

	@FXML
	void goBackAdministratorMenu(ActionEvent event) {
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

	void refresh() {
		listIPS = new TableView<Hospital>();
		listIPS.setPrefWidth(583);
		listIPS.setPrefHeight(360);

		hospitales = FXCollections.observableArrayList(Main.getEPS().obtenerHospitalesParaMostrar());
		listIPS.setItems(hospitales);

		TableColumn<Hospital, Integer> colNit = new TableColumn<>("Nit");
		colNit.setCellValueFactory(new PropertyValueFactory<Hospital, Integer>("nit"));
		colNit.setMinWidth(100);

		TableColumn<Hospital, String> colName = new TableColumn<>("Nombre");
		colName.setCellValueFactory(new PropertyValueFactory<Hospital, String>("nombre"));
		colName.setMinWidth(100);

		TableColumn<Hospital, String> colDir = new TableColumn<>("Direccion");
		colDir.setCellValueFactory(new PropertyValueFactory<Hospital, String>("direccion"));
		colDir.setMinWidth(100);

		TableColumn<Hospital, String> colTipo = new TableColumn<>("Tipo");
		colTipo.setCellValueFactory(new PropertyValueFactory<Hospital, String>("tipo"));
		colTipo.setMinWidth(100);

		TableColumn<Hospital, String> colNivel = new TableColumn<>("Nivel");
		colNivel.setCellValueFactory(new PropertyValueFactory<Hospital, String>("nivel"));
		colNivel.setMinWidth(100);

		TableColumn<Hospital, String> colRepre = new TableColumn<>("Representante");
		colRepre.setCellValueFactory(new PropertyValueFactory<Hospital, String>("representante"));
		colRepre.setMinWidth(120);
		
		TableColumn<Hospital, String> colUni = new TableColumn<>("Docencia");
		colUni.setCellValueFactory(new PropertyValueFactory<Hospital, String>("universitario"));
		colUni.setMinWidth(200);
		
		TableColumn<Hospital, String> colAcre = new TableColumn<>("Acreditacion");
		colAcre.setCellValueFactory(new PropertyValueFactory<Hospital, String>("acreditado"));
		colAcre.setMinWidth(200);

		listIPS.getColumns().addAll(colNit, colName, colDir, colTipo, colNivel, colRepre,colUni,colAcre);
		vbox.getChildren().add(listIPS);

		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				if (listIPS.getSelectionModel().isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Seleccione un elemento de la tabla");
					alert.showAndWait();
				} else {
					double nit = hospitales.get(listIPS.getSelectionModel().getSelectedIndex()).getNit();
					hospitales.remove(listIPS.getSelectionModel().getSelectedItem());
					Main.getEPS().eliminarIPSNIT(nit);
				}

			}
		});

		sortAgent.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				hospitales = FXCollections
						.observableArrayList(Main.getEPS().ordenarHospitalesSeleccion_Repre());
				listIPS.setItems(hospitales);

			}
		});
		
		
		ver.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				hospitales = FXCollections
						.observableArrayList(Main.getEPS().obtenerHospitalesParaMostrar());
				listIPS.setItems(hospitales);

			}
		});

	}

	@FXML
	void register(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/registroHospital.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@FXML
	void searchByNit(ActionEvent event) {
		Stage genericStage = new Stage();
    	genericStage.initModality(Modality.APPLICATION_MODAL);
    	genericStage.initOwner(stage);
    	VBox generic = new VBox(20);
    	Scene scene = new Scene(generic, 300, 200);
    	Label sing = new Label();
    	sing.setText("Introduzca el nombre de la clinica que desea buscar");
    	generic.getChildren().add(sing);
    	generic.setAlignment(Pos.CENTER);
    	javafx.scene.control.TextField genericText = new javafx.scene.control.TextField();
    	genericText.setMaxWidth(130);
    	generic.getChildren().add(genericText);
    	Button ok = new Button();
    	ok.setText("OK");
    	generic.getChildren().add(ok);
    	genericStage.setScene(scene);
    	genericStage.show();
    	ok.setOnAction(new EventHandler<ActionEvent>(){	
    		@Override
    		public void handle(ActionEvent arg0){
				try {
					
	    			hospitales = FXCollections.observableArrayList(Main.getEPS().buscarHospital_Nombre(genericText.getText()));
	    			listIPS.setItems(hospitales);
	    			genericStage.close();
				} catch (NotFoundException e) {
					Alert alert = new Alert(AlertType.ERROR);
		    		alert.setTitle("Error");
		    		alert.setHeaderText(null);
					alert.setContentText("el nombre no esta en la lista");
					alert.showAndWait();
				}
   			
    		}
    	
    	});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		refresh();

	}
}
