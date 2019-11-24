package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.EPS;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application {
	
	private static EPS eps;
	
	private static FileManager file;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//init();
			Parent root;
			if(eps.getAdmin() == null) {
				root = FXMLLoader.load(getClass().getResource("/application/pSalud.fxml"));
			}else {
				root = FXMLLoader.load(getClass().getResource("login.fxml"));
			}
			
			primaryStage.setTitle("EPS system");

			primaryStage.setFullScreen(false);
			// Group root = new Group();
			
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);

			// primaryStage.setVisible(true);

			primaryStage.show();
			//primaryStage.setOnCloseRequest(e -> closeProgram());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static EPS getEPS() {
		return eps;
	}
	
	public static void setEPS(EPS e) {
		eps = e;
	}
	
	public void init(){
		File market = new File("files\\eps.dat");
		if(market.exists()){
			System.out.println("Existo");
			Main.setEPS(file.loadMarketData("files\\eps.dat"));
		}
	}
	
	public void closeProgram(){
		try {
			file.saveMarketData("files\\eps.dat", Main.getEPS());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		eps = new EPS("",0);
		launch(args);
	}
}
