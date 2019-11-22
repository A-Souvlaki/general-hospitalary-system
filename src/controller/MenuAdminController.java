package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import threads.CircleThread;
import javafx.scene.Node;

public class MenuAdminController {
	
	private ChoiceIPSController cpc;
	
	@FXML
	private Circle blueCircle;
	
	@FXML
	private AnchorPane circleAnchorPane;
	
	private CircleThread ct;
	
	private int speed;
	
	public MenuAdminController() {
		ct = new CircleThread(this);
		blueCircle = new Circle();
		circleAnchorPane = new AnchorPane();
    	ct.start();
    	speed = 3;
	}

    @FXML
    void exit(ActionEvent event) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/application/login.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
    }

    @FXML
    void openIPSWindow(ActionEvent event) {
    	try{
    		Parent root = FXMLLoader.load(getClass().getResource("/application/choiceIps.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
    	
		}catch(Exception e){
			e.printStackTrace();
		}
    }

    @FXML
    void openMedicinesWindow(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(("Distribuidores.fxml")));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
    }

    @FXML
    void openAfiliatesWindow(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(("Inventory.fxml")));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
		}catch(Exception e){
			e.printStackTrace();
		}
    }
    
    public void moveBlueCircle() {
    	blueCircle.setLayoutX(blueCircle.getLayoutX()+speed);
    	if(blueCircle.getLayoutX() >= 410) {
    		speed = speed*-1;
    	}else if(blueCircle.getLayoutX() <= 0) {
    		speed = speed*-1;
    	}
    }

}

