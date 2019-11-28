 package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.EPS;
import threads.MusicThread;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application implements Serializable{
	
	private static EPS eps;
	
	private static FileManager file;
	
	private static MusicThread m;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			init();
			Parent root;
			if(eps.getAdmin() == null) {
				root = FXMLLoader.load(getClass().getResource("/application/register.fxml"));
				eps.cargarAltoNivel("files\\altoNivel.csv");
				eps.cargarComun("files\\comun.csv");
				eps.cargarSubsidiados("files\\subsi.csv");
				eps.cargarContributivos("files\\contri.csv");
			}else {
				root = FXMLLoader.load(getClass().getResource("/application/login.fxml"));
			}
			
			primaryStage.setTitle("EPS system");

			primaryStage.setFullScreen(false);
			// Group root = new Group();
			
			Scene scene = new Scene(root);

			primaryStage.setScene(scene);

			// primaryStage.setVisible(true);

			primaryStage.show();
			primaryStage.setOnCloseRequest(e -> closeProgram());
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
		File market = new File("files\\saves.dat");
		if(market.exists()){
			Main.setEPS(file.loadMarketData("files\\saves.dat"));
		}
		//m = new MusicThread("sounds\\halo.au",true);
		//m.start();
	}
	
	public void closeProgram(){
		try {
			file.saveMarketData("files\\saves.dat",Main.getEPS());
			//m.stopa();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		eps = new EPS("",0);
		file = new FileManager();
		
		launch(args);
	}
}
