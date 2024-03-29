package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass()
					//.getResource("/frontend/view/AppLoginView.fxml"));
					.getResource("/frontend/view/AppMainDeskView.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("kaagi-drahtula");
			primaryStage.setMinHeight(750);
			primaryStage.setMinWidth(1270);
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
