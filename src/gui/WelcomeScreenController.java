package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import domein.DomainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WelcomeScreenController extends BaseScreenController {

	@FXML
	private Label lblLogin;
	@FXML
	private Label lblUsername;
	@FXML
	private Label lblPassword;
	@FXML
	private Tooltip tooltip;
	@FXML
	private Button btnRegister;
	
	@FXML
	private Label lblLoginException;
	@FXML
	private Label lblRegisterException;
	

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
	
	private static String imageFilePathDisk = "src\\resources\\images\\disk.png";
	private static String imageFilePathKey = "src\\resources\\images\\key.png";
	
	public WelcomeScreenController(DomainController domainController) {
		super(domainController, "WelcomeScreen.fxml");
	}

	@Override
	protected void loadData() throws FileNotFoundException {
		// set translations
		FileInputStream isDisk = new FileInputStream(imageFilePathDisk);
		FileInputStream isKey = new FileInputStream(imageFilePathKey);
		Image disk = new Image(isDisk,15,15,false, false);
		Image key = new Image(isKey,15,15,false, false);
		lblUsername.setText(domainController.translate("Username"));
		lblPassword.setText(domainController.translate("Password"));
		lblLogin.setText(domainController.translate("Login"));
		btnLogin.setText(domainController.translate("Login"));
		btnLogin.setGraphic(new ImageView(key));
		
		lblUsernameRegister.setText(domainController.translate("Username"));
		lblPasswordRegister.setText(domainController.translate("Password"));
		Tooltip tooltip = new Tooltip();
		tooltip.setText(domainController.translate("TooltipPassword"));
		txtPasswordRegister.setTooltip(tooltip);
		lblFirstName.setText(domainController.translate("FirstName"));
		lblName.setText(domainController.translate("Name"));
		btnRegister.setText(domainController.translate("Register"));
		btnRegister.setGraphic(new ImageView(disk));
		
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
		this.emptyFieldCheck(username);
		this.emptyFieldCheck(password);
		this.emptyFieldCheck(name);
		this.emptyFieldCheck(firstName);		
		domainController.register(name, firstName, username, password);
		loggedIn();
		
		} catch (Exception e) {
			lblRegisterException.setText(e.getMessage());
		}
	}

	@FXML
	private void btnLoginOnAction(ActionEvent event) {
		var username = txtUsername.getText();
		var password = txtPassword.getText();
		
		try {
		this.emptyFieldCheck(username);
		this.emptyFieldCheck(password);	
		domainController.login(username, password);
		loggedIn();
		
		} catch (Exception e) { // TODO Auto-generated catch block			
			lblLoginException.setText(e.getMessage());
		}
	}
	
	
}