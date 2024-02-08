package frontend.shared;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateDialogForm {
	
	private static Stage dialogStage;
	
	public static void createDialogFormGroupProductList(String pathStage, Stage parentStage) {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(CreateDialogForm.class.getResource(pathStage));
			
			Pane pane = loader.load();
		
			dialogStage = new Stage();
			dialogStage.setTitle("");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException ex) {
			Alerts.showAlert("IO Exception", "Error loading view", ex.getMessage(), AlertType.ERROR);
			System.out.println(ex.getLocalizedMessage());
		}
	}

}
