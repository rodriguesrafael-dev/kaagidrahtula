package frontend.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import backend.model.entity.GroupEntity;
import backend.model.service.GroupService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegisterGroupProductListController extends RegisterProductAddController implements Initializable {

	private GroupEntity groupEntity = new GroupEntity();
	private GroupService groupService = new GroupService();
	
	@FXML
    private TableView<GroupEntity> tableViewGroupEntity;

    @FXML
    private TableColumn<GroupEntity, Long> tableColumnId;
    
    @FXML
    private TableColumn<GroupEntity, String> tableColumnGroupname;
    
    @FXML
    private Button buttonSearchProductGroup;
    
    @FXML
    private TextField textFieldSearchGroup;
    
    @FXML
    private Pane pageContent; 
    
    public void setGroupEntity(GroupEntity groupEntity) {
    	this.groupEntity = groupEntity;
    }
    
    public void setGroupService(GroupService groupService) {
    	this.groupService = groupService;	
    }
    
    private ObservableList<GroupEntity> observableList;

    @FXML
    public void onClickButtonSearchProductGroup() {   	
    } 
    
    @FXML
    public void onMouseClickedSelectItem() {  	
    	tableViewGroupEntity.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {		
				
				int rowtable = tableViewGroupEntity.getSelectionModel().getSelectedIndex();
				 
				if (event.getClickCount() == 2) {				
					groupEntity = (GroupEntity) tableViewGroupEntity.getItems().get(rowtable);				
										
					static_textField_groupcode.setText(groupEntity.getId().toString());				
					static_textField_groupname.setText(groupEntity.getGroupname());
				
					createForm("/frontend/view/RegisterProductAddView.fxml");	
					
					Stage stage = (Stage) tableViewGroupEntity.getScene().getWindow();
					stage.close();						
				}				
			}  				
		});   	
    }
    
    private void createForm(String absoluteName) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(pane));			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {	
		initializeNodes();
		updateTableView();
		onMouseClickedSelectItem();	     
	}
	
	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnGroupname.setCellValueFactory(new PropertyValueFactory<>("groupname"));	
	}
	
	public void updateTableView() {
		if (groupService == null) {
			throw new IllegalStateException("GroupService was null!");
		}
		List<GroupEntity> list = groupService.findAll();
		observableList = FXCollections.observableArrayList(list);
		tableViewGroupEntity.setItems(observableList);
	}
	
}
