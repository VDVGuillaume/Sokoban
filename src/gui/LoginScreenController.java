package gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import domein.DomainController;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

public class LoginScreenController extends BaseScreenController {
	@FXML
	private Label lblLogin;
	@FXML
	private Label lblUsername;
	@FXML
	private Label lblPassword;
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Button btnLogin;
	@FXML
	private Label lblLoginException;

	// Event Listener on Button[#btnLogin].onAction
	private static String imageFilePathKey = "src\\resources\\images\\key.png";
	
	public LoginScreenController(DomainController domainController) {
		super(domainController, "LoginScreen.fxml");
	}

	@Override
	protected void loadData() throws FileNotFoundException {
		// set translations
		FileInputStream isKey = new FileInputStream(imageFilePathKey);
		
		Image key = new Image(isKey,15,15,false, false);
		lblUsername.setText(domainController.translate("Username"));
		lblPassword.setText(domainController.translate("Password"));
		lblLogin.setText(domainController.translate("Login"));
		btnLogin.setText(domainController.translate("Login"));
		btnLogin.setGraphic(new ImageView(key));
		lblLoginException.setText("");
	
		
	}



	@FXML
	private void btnLoginOnAction(ActionEvent event) {
		var username = txtUsername.getText();
		var password = txtPassword.getText();
		
		try {
		this.emptyFieldCheck(username);
		this.emptyFieldCheck(password);	
		domainController.login(username, password);
		setMenuScreen(this.getScene());
		
		} catch (Exception e) { // TODO Auto-generated catch block			
			lblLoginException.setText(e.getMessage());
		}
	}
	
}
