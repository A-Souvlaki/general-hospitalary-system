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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Clinica;
import javafx.scene.Node;

public class ClinicaController implements Initializable {
	
	private ObservableList<Clinica> clinicas;

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
	private MenuItem sortNit;

	@FXML
	private MenuItem sortNombre;
	
	@FXML
	private MenuItem medicos;
	
	@FXML
	private Button ver;

	private TableView<Clinica> listIPS;

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
		listIPS = new TableView<Clinica>();
		listIPS.setMaxWidth(540);
		listIPS.setMaxHeight(360);

		clinicas = FXCollections
				.observableArrayList(Main.getEPS().obtenerClinicasParaMostrar());
		listIPS.setItems(clinicas);

		TableColumn<Clinica, Integer> colNit = new TableColumn<>("Nit");
		colNit.setCellValueFactory(new PropertyValueFactory<Clinica, Integer>("nit"));
		colNit.setMinWidth(listIPS.getMaxWidth() / 6);

		TableColumn<Clinica, String> colName = new TableColumn<>("Nombre");
		colName.setCellValueFactory(new PropertyValueFactory<Clinica, String>("nombre"));
		colName.setMinWidth(listIPS.getMaxWidth() / 6);

		TableColumn<Clinica, String> colDir = new TableColumn<>("Direccion");
		colDir.setCellValueFactory(new PropertyValueFactory<Clinica, String>("direccion"));
		colDir.setMinWidth(listIPS.getMaxWidth() / 6);

		TableColumn<Clinica, String> colTipo = new TableColumn<>("Tipo");
		colTipo.setCellValueFactory(new PropertyValueFactory<Clinica, String>("tipo"));
		colTipo.setMinWidth(listIPS.getMaxWidth() / 6);

		TableColumn<Clinica, String> colNivel = new TableColumn<>("Nivel");
		colNivel.setCellValueFactory(new PropertyValueFactory<Clinica, String>("nivel"));
		colNivel.setMinWidth(listIPS.getMaxWidth() / 6);

		TableColumn<Clinica, String> colEspe = new TableColumn<>("Especialidad");
		colEspe.setCellValueFactory(new PropertyValueFactory<Clinica, String>("especialidad"));
		colEspe.setMinWidth(listIPS.getMaxWidth() / 6);

		listIPS.getColumns().addAll(colNit, colName, colDir, colTipo, colNivel, colEspe);
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
					double nit = clinicas.get(listIPS.getSelectionModel().getSelectedIndex()).getNit();
					clinicas.remove(listIPS.getSelectionModel().getSelectedItem());
					Main.getEPS().eliminarIPSNIT(nit);
				}

			}
		});

		sortNit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				clinicas = FXCollections
						.observableArrayList(Main.getEPS().ordenarClinicasBurbuja_Nit());
				listIPS.setItems(clinicas);

			}
		});
		
		sortNombre.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				clinicas = FXCollections
						.observableArrayList(Main.getEPS().ordenarClinicasBurbuja_Nombre());
				listIPS.setItems(clinicas);

			}
		});
		
		ver.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				clinicas = FXCollections
						.observableArrayList(Main.getEPS().obtenerClinicasParaMostrar());
				listIPS.setItems(clinicas);

			}
		});
		
		

	}
	
	@FXML
	void medical(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/medicosClinica.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void register(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/registroClinica.fxml"));
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
    	sing.setText("Introduzca el nit de la clinica que desea buscar");
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
					
	    			clinicas = FXCollections.observableArrayList(Main.getEPS().buscarClinica_nit(Integer.parseInt(genericText.getText())));
	    			listIPS.setItems(clinicas);
	    			genericStage.close();
				} catch (NotFoundException e) {
					Alert alert = new Alert(AlertType.ERROR);
		    		alert.setTitle("Error");
		    		alert.setHeaderText(null);
					alert.setContentText("el nit no esta en la lista");
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
