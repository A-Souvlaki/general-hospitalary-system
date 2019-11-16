package application;
	
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
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root;
			if(eps.getAdmin() == null) {
				root = FXMLLoader.load(getClass().getResource("register.fxml"));
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
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static EPS getEPS() {
		return eps;
	}
	
	public static void main(String[] args) {
		eps = new EPS("",0);
		launch(args);
	}
}
