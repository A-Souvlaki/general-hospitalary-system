package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.AltoNivel;
import model.Comun;


public class MedicamentosController implements Initializable {
	
	ObservableList<String> listA = FXCollections.observableArrayList();
	
	private ObservableList<AltoNivel> medAN;
	
	private ObservableList<Comun> medCO;
	
	@FXML
	private BorderPane panel;
	
	@FXML
	private Label hojas;
	
	@FXML
	private Label peso;
	
	@FXML
	private Button delete;
	
	@FXML
	private ChoiceBox<String> medicamentos;
	
	
	private TableView<AltoNivel> altoNivel;;
	

	private TableView<Comun> comun;
	
	public void createBoxMed() {
		listA.remove(listA);
		listA.addAll("Alto Nivel","Comunes(POS)");
		medicamentos.getItems().addAll(listA);
	}
	
	@FXML
	public void displayValue(ActionEvent event) {
		String value = medicamentos.getValue();
		try {
			if ( value.equals( "Alto Nivel" ) ) {
				
				altoNivel = new TableView<AltoNivel>();
				altoNivel.setMaxWidth(545);
				altoNivel.setMaxHeight(360);

				medAN = FXCollections.observableArrayList(Main.getEPS().darListaAltoNivel());
				altoNivel.setItems(medAN);

				TableColumn<AltoNivel, String> colName = new TableColumn<>("ID");
				colName.setCellValueFactory(new PropertyValueFactory<AltoNivel, String>("id"));
				colName.setMinWidth(altoNivel.getMaxWidth()/5);
				
				TableColumn<AltoNivel, String> colId = new TableColumn<>("Codigo ATC");
				colId.setCellValueFactory(new PropertyValueFactory<AltoNivel, String>("codigo_atc"));
				colId.setMinWidth(altoNivel.getMaxWidth()/5);
				
				TableColumn<AltoNivel, String> colEspe = new TableColumn<>("Nombre");
				colEspe.setCellValueFactory(new PropertyValueFactory<AltoNivel, String>("nombre"));
				colEspe.setMinWidth(altoNivel.getMaxWidth()/5);
				
				TableColumn<AltoNivel, Double> colDo = new TableColumn<>("Costo");
				colDo.setCellValueFactory(new PropertyValueFactory<AltoNivel, Double>("costo"));
				colDo.setMinWidth(altoNivel.getMaxWidth()/5);
				
				TableColumn<AltoNivel, Integer> colLi = new TableColumn<>("Dosis");
				colLi.setCellValueFactory(new PropertyValueFactory<AltoNivel, Integer>("dosis_mg"));
				colLi.setMinWidth(altoNivel.getMaxWidth()/5);
				
				
				
				altoNivel.getColumns().addAll(colName,colId,colEspe,colDo,colLi);
				panel.setCenter(altoNivel);
				
				hojas.setText(""+Main.getEPS().contarHojasAltoNivel());
				
				peso.setText(""+Main.getEPS().darPesoAltoNivel());
				
				delete.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent t) {

						if (altoNivel.getSelectionModel().isEmpty()) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText(null);
							alert.setContentText("Seleccione un elemento de la tabla");
							alert.showAndWait();
						} else {
							String nombre = medAN.get(altoNivel.getSelectionModel().getSelectedIndex()).getNombre();
							medAN.remove(altoNivel.getSelectionModel().getSelectedItem());
							Main.getEPS().eliminarAltoNivel(nombre);
							
						}

					}
				});
				
				
			} else {
				
				comun = new TableView<Comun>();
				comun.setMaxWidth(540);
				comun.setMaxHeight(360);

				medCO = FXCollections.observableArrayList(Main.getEPS().darListaComun());
				comun.setItems(medCO);

				TableColumn<Comun, String> colName = new TableColumn<>("ID");
				colName.setCellValueFactory(new PropertyValueFactory<Comun, String>("id"));
				colName.setMinWidth(altoNivel.getMaxWidth()/4);
				
				TableColumn<Comun, String> colId = new TableColumn<>("Codigo ATC");
				colId.setCellValueFactory(new PropertyValueFactory<Comun, String>("codigo_atc"));
				colId.setMinWidth(altoNivel.getMaxWidth()/4);
				
				TableColumn<Comun, String> colEspe = new TableColumn<>("Nombre");
				colEspe.setCellValueFactory(new PropertyValueFactory<Comun, String>("nombre"));
				colEspe.setMinWidth(altoNivel.getMaxWidth()/4);
				
				TableColumn<Comun, String> colP = new TableColumn<>("Principio");
				colP.setCellValueFactory(new PropertyValueFactory<Comun, String>("principio"));
				colP.setMinWidth(altoNivel.getMaxWidth()/4);
				
				comun.getColumns().addAll(colName,colId,colEspe,colP);
				panel.setCenter(comun);
				
				hojas.setText(""+Main.getEPS().contarComun());
				
				peso.setText(""+Main.getEPS().darPesoComun());
				
				delete.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent t) {

						if (comun.getSelectionModel().isEmpty()) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText(null);
							alert.setContentText("Seleccione un elemento de la tabla");
							alert.showAndWait();
						} else {
							String nombre = medCO.get(comun.getSelectionModel().getSelectedIndex()).getNombre();
							medCO.remove(comun.getSelectionModel().getSelectedItem());
							Main.getEPS().eliminarComun(nombre);
							
						}

					}
				});
				
			}
		}catch ( NullPointerException e) {
			Alert error = new Alert(AlertType.INFORMATION);
			error.setHeaderText("¡Selecciona una opicon!");
			error.show();
		}
	}
	
	public void back(ActionEvent e) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/menuAdmin.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createBoxMed();
	}
	
	
	
	
	
}
