package gui;

import java.io.IOException;

import domein.DomainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class WelcomeScreenController extends GridPane {
	private DomainController domainController;
	private ObservableList languages;
	
	@FXML
	private Label lblLogin;
	@FXML
	private Label lblUsername;
	@FXML
	private Label lblPassword;
	@FXML
	private Button btnRegister;
	
	@FXML
	private ComboBox<String> comboBoxLanguage;
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;
	
	public WelcomeScreenController(DomainController domainController) {
		this.domainController = domainController;
		languages = FXCollections.observableArrayList();
		languages.addAll("English", "Français", "Nederlands");

		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeScreen.fxml"));
		loader.setController(this);
		loader.setRoot(this);

		
		try {
			loader.load();
			loadData();

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@FXML
	private void loadData() 
	{
		//clear data
		comboBoxLanguage.getItems().clear();
		
		
		//fill data
		comboBoxLanguage.getItems().addAll(languages);
		lblUsername.setText(domainController.translate("Username"));
		lblPassword.setText(domainController.translate("Password"));
		lblLogin.setText(domainController.translate("Login"));
		btnRegister.setText(domainController.translate("Register"));
	}
	
	@FXML
	private void btnCalculateOnAction(ActionEvent event) 
	{
		var username = txtUsername.getText();
		var password = txtPassword.getText();
	}
}
