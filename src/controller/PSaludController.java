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
import model.PuestoDeSalud;

public class PSaludController implements Initializable{

	private ObservableList<PuestoDeSalud> puestos;

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
	private MenuItem sortNurse;

	@FXML
	private MenuItem sortLab;

	@FXML
	private Button ver;

	private TableView<PuestoDeSalud> listIPS;

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

	@SuppressWarnings("unchecked")
	void refresh() {
		listIPS = new TableView<PuestoDeSalud>();
		listIPS.setMaxWidth(900);
		listIPS.setMaxHeight(360);

		puestos = FXCollections.observableArrayList(Main.getEPS().obtenerPuestosParaMostrar());
		listIPS.setItems(puestos);

		TableColumn<PuestoDeSalud, Integer> colNit = new TableColumn<>("Nit");
		colNit.setCellValueFactory(new PropertyValueFactory<PuestoDeSalud, Integer>("nit"));
		colNit.setMinWidth(listIPS.getMaxWidth()/9);

		TableColumn<PuestoDeSalud, String> colName = new TableColumn<>("Nombre");
		colName.setCellValueFactory(new PropertyValueFactory<PuestoDeSalud, String>("nombre"));
		colName.setMinWidth(listIPS.getMaxWidth()/9);

		TableColumn<PuestoDeSalud, String> colDir = new TableColumn<>("Direccion");
		colDir.setCellValueFactory(new PropertyValueFactory<PuestoDeSalud, String>("direccion"));
		colDir.setMinWidth(listIPS.getMaxWidth()/9);

		TableColumn<PuestoDeSalud, String> colTipo = new TableColumn<>("Tipo");
		colTipo.setCellValueFactory(new PropertyValueFactory<PuestoDeSalud, String>("tipo"));
		colTipo.setMinWidth(listIPS.getMaxWidth()/9);

		TableColumn<PuestoDeSalud, String> colNivel = new TableColumn<>("Nivel");
		colNivel.setCellValueFactory(new PropertyValueFactory<PuestoDeSalud, String>("nivel"));
		colNivel.setMinWidth(listIPS.getMaxWidth()/9);

		TableColumn<PuestoDeSalud, String> colEnfe = new TableColumn<>("Jefe de Enfermeros");
		colEnfe.setCellValueFactory(new PropertyValueFactory<PuestoDeSalud, String>("jefe_enfermeros"));
		colEnfe.setMinWidth(listIPS.getMaxWidth()/9);

		TableColumn<PuestoDeSalud, String> colLab = new TableColumn<>("Jefe de Laboratorio");
		colLab.setCellValueFactory(new PropertyValueFactory<PuestoDeSalud, String>("jefe_laboratorio"));
		colLab.setMinWidth(listIPS.getMaxWidth()/9);

		TableColumn<PuestoDeSalud, String> colVac = new TableColumn<>("Vacunas");
		colVac.setCellValueFactory(new PropertyValueFactory<PuestoDeSalud, String>("vacunas"));
		colVac.setMinWidth(listIPS.getMaxWidth()/9);

		TableColumn<PuestoDeSalud, String> colFar = new TableColumn<>("Farmacia");
		colFar.setCellValueFactory(new PropertyValueFactory<PuestoDeSalud, String>("farmacia"));
		colFar.setMinWidth(listIPS.getMaxWidth()/9);

		listIPS.getColumns().addAll(colNit, colName, colDir, colTipo, colNivel, colEnfe, colLab, colVac, colFar);
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
					double nit = puestos.get(listIPS.getSelectionModel().getSelectedIndex()).getNit();
					puestos.remove(listIPS.getSelectionModel().getSelectedItem());
					Main.getEPS().eliminarIPSNIT(nit);
				}

			}
		});

		sortNurse.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				puestos = FXCollections.observableArrayList(Main.getEPS().ordenarInsercion_enfermeros());
				listIPS.setItems(puestos);

			}
		});

		sortLab.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				puestos = FXCollections.observableArrayList(Main.getEPS().ordenarInsercion_laboratorio());
				listIPS.setItems(puestos);

			}
		});
		ver.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {

				puestos = FXCollections.observableArrayList(Main.getEPS().obtenerPuestosParaMostrar());
				listIPS.setItems(puestos);

			}
		});

	}
	
	@FXML
	void medical(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/medicosPuesto.fxml"));
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
			root = FXMLLoader.load(getClass().getResource("/application/registroPSalud.fxml"));
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
		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {

					puestos = FXCollections.observableArrayList(
							Main.getEPS().buscarPuesto_nit(Integer.parseInt(genericText.getText())));
					listIPS.setItems(puestos);
					genericStage.close();
				} catch (NotFoundException e) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("el nit no esta en la lista");
					alert.showAndWait();
				} catch (NumberFormatException e) {
					Alert alert = new Alert(AlertType.ERROR);
		    		alert.setTitle("Error");
		    		alert.setHeaderText(null);
					alert.setContentText("Recuerda que el nit son solo numeros!!!");
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
