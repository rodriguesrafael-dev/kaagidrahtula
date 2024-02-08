package frontend.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import backend.accessdb.DbException;
import backend.model.entity.GroupEntity;
import backend.model.exception.ValidationException;
import backend.model.service.GroupService;
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

public class RegisterGroupProductAddController implements Initializable {

	private GroupEntity groupEntity = new GroupEntity();
	private GroupService groupService = new GroupService();
	
	@FXML
	private TextField textField_id;
	
	@FXML
	private TextField textField_groupname;
	
	@FXML
	private Button buttonSave;
	
	@FXML
	private Button buttonCancel;
	
	@FXML
    private Pane pageContent;
	
	public void setGroupEntity(GroupEntity groupEntity) {
		this.groupEntity = groupEntity;
	}
	
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	@FXML
	public void onClickButtonSave() {
		if (groupEntity == null) {
			throw new IllegalStateException("GroupEntity was null!");
		}
		if (groupService == null) {
			throw new IllegalStateException("GroupService was null!");
		}
		
		try {
			groupEntity = getFormData();
			groupService.saveOrUpdate(groupEntity);
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
	
	private GroupEntity getFormData() {
		GroupEntity groupEntity = new GroupEntity();
		
		ValidationException exception = new ValidationException("Validation error");
		
		groupEntity.setId(ParseFormat.tryParseToLong(textField_id.getText()));
		
		if (textField_groupname.getText() == null || 
				textField_groupname.getText().trim().equals("")) {
			exception.addError("groupname", "Field can't be empty!");
		}
		groupEntity.setGroupname(textField_groupname.getText());
		
		if (exception.getErrors().size() > 0) {
			throw exception;
		}	
		return groupEntity;
	}
	
}
