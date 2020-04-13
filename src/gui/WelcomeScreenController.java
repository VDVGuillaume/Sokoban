package gui;

import java.io.IOException;

import domein.DomainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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

	@FXML
	private Button btnLogin;
	@FXML
	private Label lblUsernameRegister;
	@FXML
	private Label lblPasswordRegister;
	@FXML
	private Label lblFirstName;
	@FXML
	private Label lblName;
	@FXML
	private TextField txtUsernameRegister;
	@FXML
	private TextField txtPasswordRegister;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtName;

	public WelcomeScreenController(DomainController domainController) {
		this.domainController = domainController;

		languages = FXCollections.observableArrayList();
		languages.addAll("English", "Nederlands", "Français");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("WelcomeScreen.fxml"));
		loader.setController(this);
		loader.setRoot(this);

		try {
			loader.load();
			loadLanguageChoices();
			loadData();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@FXML
	private void loadData() {
		// set translations
		lblUsername.setText(domainController.translate("Username"));
		lblPassword.setText(domainController.translate("Password"));
		lblLogin.setText(domainController.translate("Login"));
		btnLogin.setText(domainController.translate("Login"));

		lblUsernameRegister.setText(domainController.translate("Username"));
		lblPasswordRegister.setText(domainController.translate("Password"));
		lblFirstName.setText(domainController.translate("FirstName"));
		lblName.setText(domainController.translate("Name"));
		btnRegister.setText(domainController.translate("Register"));

	}

	private void loggedIn() {
		Stage stage = (Stage) btnRegister.getScene().getWindow();
		MenuScreenController root = new MenuScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	}

	@FXML
	private void btnRegisterOnAction(ActionEvent event) {

		var username = txtUsernameRegister.getText();
		var password = txtPasswordRegister.getText();
		var name = txtName.getText();
		var firstName = txtFirstName.getText();

		try {
			domainController.register(name, firstName, username, password);
			loggedIn();
		} catch (Exception e) { // TODO Auto-generated catch block
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText(e.getMessage());
			//errorAlert.setContentText(e.getMessage());
			errorAlert.showAndWait();
		}
	}
	
	@FXML
	private void btnLoginOnAction(ActionEvent event) 
	{
		var username = txtUsername.getText();
		var password = txtPassword.getText();
		
		try {
			domainController.login(username, password);
			loggedIn();
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
			Alert errorAlert = new Alert(AlertType.ERROR);
			errorAlert.setHeaderText(e.getMessage());
			//errorAlert.setContentText(e.getMessage());
			errorAlert.showAndWait();
			}
	}
	
	@FXML
	private void loadLanguageChoices() {
		int domainLanguage = domainController.getLanguage() - 1;
		comboBoxLanguage.getItems().clear();
		comboBoxLanguage.getItems().addAll(languages);
		comboBoxLanguage.setValue(languages.get(domainLanguage).toString());
	}

	@FXML
	private void languageChoiceChanged(ActionEvent event) {
		int domainLanguage = domainController.getLanguage();
		int selectedLanguage = languages.indexOf(comboBoxLanguage.getValue()) + 1;

		if (domainLanguage != selectedLanguage) {
			domainController.setLanguage(selectedLanguage);
			loadData();
			event.consume();
		}
	}
}