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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.PacienteContributario;
import model.PacienteSubsidiado;

public class PacientesController implements Initializable{
	
	ObservableList<String> listA = FXCollections.observableArrayList();
	
	private ObservableList<PacienteContributario> paC;
	
	private ObservableList<PacienteSubsidiado> paS;
	
	@FXML
	private BorderPane panel;
	
	@FXML
	private Label hojas;
	
	@FXML
	private Label peso;
	
	@FXML
	private Button delete;
	
	@FXML
	private ChoiceBox<String> pacientes;
	
	
	private TableView<PacienteContributario> contributarios;
	

	private TableView<PacienteSubsidiado> subsidiados;
	
	public void createBoxMed() {
		listA.remove(listA);
		listA.addAll("Subsidiados","Contributivos");
		pacientes.getItems().addAll(listA);
	}
	
	@FXML
	public void displayValue(ActionEvent event) {
		String value = pacientes.getValue();
		try {
			if ( value.equals( "Subsidiados" ) ) {
				
				subsidiados = new TableView<PacienteSubsidiado>();
				subsidiados.setMaxWidth(540);
				subsidiados.setMaxHeight(360);

				paS = FXCollections.observableArrayList(Main.getEPS().darListaSubsiado());
				subsidiados.setItems(paS);

				TableColumn<PacienteSubsidiado, String> colName = new TableColumn<>("Nombres");
				colName.setCellValueFactory(new PropertyValueFactory<PacienteSubsidiado, String>("nombre_p"));
				colName.setMinWidth(subsidiados.getMaxWidth()/4);
				
				TableColumn<PacienteSubsidiado, String> colId = new TableColumn<>("Apellidos");
				colId.setCellValueFactory(new PropertyValueFactory<PacienteSubsidiado, String>("apellido_p"));
				colId.setMinWidth(subsidiados.getMaxWidth()/4);
				
				TableColumn<PacienteSubsidiado, String> colEspe = new TableColumn<>("ID");
				colEspe.setCellValueFactory(new PropertyValueFactory<PacienteSubsidiado, String>("id_p"));
				colEspe.setMinWidth(subsidiados.getMaxWidth()/4);
				
				TableColumn<PacienteSubsidiado, String> colDo = new TableColumn<>("Sisben");
				colDo.setCellValueFactory(new PropertyValueFactory<PacienteSubsidiado, String>("sisben_p"));
				colDo.setMinWidth(subsidiados.getMaxWidth()/4);

				
				subsidiados.getColumns().addAll(colName,colId,colEspe,colDo);
				panel.setCenter(subsidiados);
				
				hojas.setText(""+Main.getEPS().contarHojasSubsidiado());
				
				peso.setText(""+Main.getEPS().darPesoSubsidiado());
				
				delete.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent t) {

						if (subsidiados.getSelectionModel().isEmpty()) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText(null);
							alert.setContentText("Seleccione un elemento de la tabla");
							alert.showAndWait();
						} else {
							String nombre = paS.get(subsidiados.getSelectionModel().getSelectedIndex()).getId_p();
							paS.remove(subsidiados.getSelectionModel().getSelectedItem());
							Main.getEPS().eliminarSubsidiado(nombre);;
							
						}

					}
				});
				
				
			} else {
				
				contributarios = new TableView<PacienteContributario>();
				contributarios.setMaxWidth(540);
				contributarios.setMaxHeight(360);

				paC = FXCollections.observableArrayList(Main.getEPS().darListaContributivo());
				contributarios.setItems(paC);

				TableColumn<PacienteContributario, String> colName = new TableColumn<>("Nombres");
				colName.setCellValueFactory(new PropertyValueFactory<PacienteContributario, String>("nombre_p"));
				colName.setMinWidth(contributarios.getMaxWidth()/4);
				
				TableColumn<PacienteContributario, String> colId = new TableColumn<>("Apellidos");
				colId.setCellValueFactory(new PropertyValueFactory<PacienteContributario, String>("apellido_p"));
				colId.setMinWidth(contributarios.getMaxWidth()/4);
				
				TableColumn<PacienteContributario, String> colEspe = new TableColumn<>("ID");
				colEspe.setCellValueFactory(new PropertyValueFactory<PacienteContributario, String>("id_p"));
				colEspe.setMinWidth(contributarios.getMaxWidth()/4);
				
				TableColumn<PacienteContributario, String> colP = new TableColumn<>("Cuota");
				colP.setCellValueFactory(new PropertyValueFactory<PacienteContributario, String>("cuota"));
				colP.setMinWidth(contributarios.getMaxWidth()/4);
				
				contributarios.getColumns().addAll(colName,colId,colEspe,colP);
				panel.setCenter(contributarios);
				
				hojas.setText(""+Main.getEPS().contarHojasContributivo());
				
				peso.setText(""+Main.getEPS().darPesoContributivo());
				
				delete.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent t) {

						if (contributarios.getSelectionModel().isEmpty()) {
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("Error");
							alert.setHeaderText(null);
							alert.setContentText("Seleccione un elemento de la tabla");
							alert.showAndWait();
						} else {
							String nombre = paC.get(contributarios.getSelectionModel().getSelectedIndex()).getId_p();
							paC.remove(contributarios.getSelectionModel().getSelectedItem());
							Main.getEPS().eliminarContributivo(nombre);;
							
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
