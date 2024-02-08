package frontend.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class AppMainDeskController implements Initializable {

	@FXML
    private Button btnAvaria;

    @FXML
    private Button btnConfiguracoes;

    @FXML
    private Button btnEstoque;

    @FXML
    private Button btnFinanceiro;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnNotaFiscal;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnVenda;

    @FXML
    private StackPane pageContent;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {				
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/frontend/view/HomeView.fxml"));
			pageContent.getChildren().removeAll();
			pageContent.getChildren().setAll(parent);
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}
	@FXML
	public void returnHomeView(javafx.event.ActionEvent actionEvent) throws IOException  {		
		Parent parent = FXMLLoader.load(getClass().getResource("/frontend/view/HomeView.fxml"));
		pageContent.getChildren().removeAll();
		pageContent.getChildren().setAll(parent);
		btnHome.setStyle(".sideBar-item");
	}
	
	@FXML
	public void startRegisterView() throws IOException  {
		Parent parent = FXMLLoader.load(getClass().getResource("/frontend/view/RegisterView.fxml"));
		pageContent.getChildren().removeAll();
		pageContent.getChildren().setAll(parent);		
	}
	
	@FXML
	public void startInvoiceView(javafx.event.ActionEvent actionEvent) throws IOException  {
		Parent parent = FXMLLoader.load(getClass().getResource("/frontend/view/InvoiceView.fxml"));
		pageContent.getChildren().removeAll();
		pageContent.getChildren().setAll(parent);
	}
	
	@FXML
	public void startSaleView(javafx.event.ActionEvent actionEvent) throws IOException  {
		Parent parent = FXMLLoader.load(getClass().getResource("/frontend/view/SaleView.fxml"));
		pageContent.getChildren().removeAll();
		pageContent.getChildren().setAll(parent);
	}
	
	@FXML
	public void startBreakdownView(javafx.event.ActionEvent actionEvent) throws IOException  {
		Parent parent = FXMLLoader.load(getClass().getResource("/frontend/view/BreakdownView.fxml"));
		pageContent.getChildren().removeAll();
		pageContent.getChildren().setAll(parent);
	}
	
	@FXML
	public void startInventoryView(javafx.event.ActionEvent actionEvent) throws IOException  {
		Parent parent = FXMLLoader.load(getClass().getResource("/frontend/view/InventoryView.fxml"));
		pageContent.getChildren().removeAll();
		pageContent.getChildren().setAll(parent);
	}
	
	@FXML
	public void startFinancialView(javafx.event.ActionEvent actionEvent) throws IOException  {
		Parent parent = FXMLLoader.load(getClass().getResource("/frontend/view/FinancialView.fxml"));
		pageContent.getChildren().removeAll();
		pageContent.getChildren().setAll(parent);
	}
	
	@FXML
	public void startSettingsView(javafx.event.ActionEvent actionEvent) throws IOException  {
		Parent parent = FXMLLoader.load(getClass().getResource("/frontend/view/SettingsView.fxml"));
		pageContent.getChildren().removeAll();
		pageContent.getChildren().setAll(parent);
	}

}
