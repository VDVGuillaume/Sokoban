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

	@FXML
	private void btnRegisterOnAction(ActionEvent event) {
		var username = txtUsername.getText();
		var password = txtPassword.getText();
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