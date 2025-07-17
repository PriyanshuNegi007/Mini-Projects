package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// Stage stae = new Stage();
		Group root = new Group();        // root node
		Scene scene = new Scene(root,Color.BLACK);   // Adding root node to scene
		
		Image image = new Image("Tick.png");
		stage.getIcons().add(image);                // Add image to stage top left
		stage.setTitle("Stage Demo Example");
		stage.setWidth(420);
		stage.setHeight(420);
		stage.setResizable(false);
		//stage.setX(50);
		//stage.setY(50);
		stage.setFullScreen(true);
		stage.setFullScreenExitHint("Press Q to exit");
		stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
		
		stage.setScene(scene);           // Add scene to stage
		stage.show();
		
		
	}
}
