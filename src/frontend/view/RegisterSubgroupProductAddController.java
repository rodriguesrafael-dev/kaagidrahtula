package frontend.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import backend.accessdb.DbException;
import backend.model.entity.SubgroupEntity;
import backend.model.exception.ValidationException;
import backend.model.service.SubgroupService;
import frontend.shared.Alerts;
import frontend.shared.ParseFormat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class RegisterSubgroupProductAddController implements Initializable {

	private SubgroupEntity subgroupEntity = new SubgroupEntity();
	private SubgroupService subgroupService = new SubgroupService();
	
	@FXML
	private TextField textField_id;
	
	@FXML
	private TextField textField_subgroupname;
	
	@FXML
	private Button buttonSave;
	
	@FXML
	private Button buttonCancel;
	
	@FXML
    private Pane pageContent;
	
	public void setSubgroupEntity(SubgroupEntity subgroupEntity) {
		this.subgroupEntity = subgroupEntity;
	}
	
	public void setSubgroupService(SubgroupService subgroupService) {
		this.subgroupService = subgroupService;
	}
	
	@FXML
	public void onClickButtonSave() {
		if (subgroupEntity == null) {
			throw new IllegalStateException("SubgroupEntity was null!");
		}
		if (subgroupService == null) {
			throw new IllegalStateException("SubgroupService was null!");
		}
		
		try {
			subgroupEntity = getFormData();
			subgroupService.saveOrUpdate(subgroupEntity);
		} catch (DbException ex) {
			Alerts
			.showAlert("Error saving object!", null, ex.getMessage(), 
					AlertType.ERROR);
		}
	}
	
	@FXML
	public void onClickButtonCancel() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass()
				.getResource("/frontend/view/RegisterProductView.fxml"));
		
		Parent parent = loader.load();
		
		pageContent.getChildren().removeAll();
		pageContent.getChildren().setAll(parent);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {	
	}
	
	private SubgroupEntity getFormData() {
		SubgroupEntity obj = new SubgroupEntity();
		
		ValidationException exception = new ValidationException("Validation error");
		
		obj.setId(ParseFormat.tryParseToLong(textField_id.getText()));
		
		if (textField_subgroupname.getText() == null || 
				textField_subgroupname.getText().trim().equals("")) {
			exception.addError("groupname", "Field can't be empty!");
		}
		obj.setSubgroupname(textField_subgroupname.getText());
		
		if (exception.getErrors().size() > 0) {
			throw exception;
		}	
		return obj;
	}
	
}
