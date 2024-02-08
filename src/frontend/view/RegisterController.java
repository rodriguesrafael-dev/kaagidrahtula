package frontend.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class RegisterController implements Initializable {

	@FXML
    private Button btnRegisterProduct;

    @FXML
    private Pane pageContent;
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {	
	}
	
	@FXML
	public void onClickRegisterProductView() throws IOException  {		
		FXMLLoader loader = new FXMLLoader(getClass()
				.getResource("/frontend/view/RegisterProductView.fxml"));
		
		Parent parent = loader.load();
		
		pageContent.getChildren().removeAll();
		pageContent.getChildren().setAll(parent);
	}
	
}
