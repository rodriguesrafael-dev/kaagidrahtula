package frontend.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import backend.accessdb.DbException;
import backend.model.entity.ProductEntity;
import backend.model.exception.ValidationException;
import backend.model.service.ProductService;
import frontend.shared.Alerts;
import frontend.shared.CreateDialogForm;
import frontend.shared.ParseFormat;
import frontend.shared.StageAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegisterProductAddController implements Initializable {

	/*
	https://github.com/JavaCodeJunkie/javafx/blob/master/DialogDemo/src/application/Main.java
	https://github.com/JavaCodeJunkie/javafx/blob/master/DialogDemo/src/application/Person.java
	https://github.com/JavaCodeJunkie/javafx/blob/master/DialogDemo/src/application/PersonDialog.java
	https://github.com/sialcasa/mvvmFX/issues/574
	https://pt.stackoverflow.com/questions/244620/capturar-mudan%C3%A7a-textfield-do-javafx
	https://www.youtube.com/watch?v=ZWWvJgNLslk
	*/
	
	private ProductEntity productEntity = new ProductEntity();
	private ProductService productService = new ProductService();

	@FXML
	private TextField textField_id;
	
	@FXML
	private TextField textField_description;

	@FXML
	private TextField textField_groupcode;
	
	public static TextField static_textField_groupcode;

	@FXML
	private TextField textField_groupname;
	
	public static TextField static_textField_groupname;

	@FXML
	private TextField textField_subgroupcode;

	@FXML
	private TextField textField_subgroupname;

	@FXML
	private TextField textField_reference;

	@FXML
	private TextField textField_barcode;

	@FXML
	private Button buttonSearchGroupProduct;

	@FXML
	private Button buttonSearchSubgroupProduct;

	@FXML
	private Button buttonSave;

	@FXML
	private Button buttonCancel;

	@FXML
	private Pane pageContent;

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@FXML
	private void onClickButtonSearchGroupProduct(ActionEvent event) {
		Stage parentStage = StageAction.currentStage(event);
		CreateDialogForm.createDialogFormGroupProductList("/frontend/view/RegisterGroupProductListView.fxml", parentStage);		
	}

	@FXML
	public void onClickButtonSave() {
		if (productEntity == null) {
			throw new IllegalStateException("ProductEntity was null!");
		}
		
		if (productService == null) {
			throw new IllegalStateException("ProductService was null!");
		}

		try {
			productEntity = getFormData();
			productService.saveOrUpdate(productEntity);
		} catch (DbException ex) {
			Alerts.showAlert("Error saving object!", null, ex.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	public void onClickButtonCancel() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/frontend/view/RegisterProductView.fxml"));

		Parent parent = loader.load();

		pageContent.getChildren().removeAll();
		pageContent.getChildren().setAll(parent);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		static_textField_groupcode = textField_groupcode;			
		static_textField_groupname = textField_groupname;	
	}
	
	private ProductEntity getFormData() {
		ProductEntity productEntity = new ProductEntity();

		ValidationException exception = new ValidationException("Validation error");

		productEntity.setId(ParseFormat.tryParseToLong(textField_id.getText()));

		if (textField_description.getText() == null || textField_description.getText().trim().equals("")) {
			exception.addError("description", "Field can't be empty!");
		}
		productEntity.setDescription(textField_description.getText());
		productEntity.setReference(textField_reference.getText());
		productEntity.setBarcode(textField_barcode.getText());

		if (exception.getErrors().size() > 0) {
			throw exception;
		}
		return productEntity;
	}
	
}
