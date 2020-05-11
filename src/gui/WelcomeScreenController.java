package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import domein.DomainController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WelcomeScreenController extends BaseScreenController {
	@FXML
	private Button btnNederlands;
	@FXML
	private Button btnEngels;
	@FXML
	private Button btnFrans;
	@FXML
	private ImageView background;
	@FXML
	private Label lblUsername;
	@FXML
	private TextField txtUsername;
	@FXML
	private Label lblPassword;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Label lblLoginException;
	@FXML
	private Button btnLogin;
	

	private static String imageFilePathKey = "src\\resources\\images\\key.png";
	
	public WelcomeScreenController(DomainController domainController) {
		super(domainController, "WelcomeScreen.fxml");
		
		
		languages = FXCollections.observableArrayList();
		languages.addAll("English", "Nederlands", "Fran�ais");
		background.fitWidthProperty().bind(this.widthProperty());
		background.fitHeightProperty().bind(this.heightProperty());		
		
	}
	
	

	@Override
	protected void loadData() throws FileNotFoundException {
		FileInputStream isKey = new FileInputStream(imageFilePathKey);
		
		Image key = new Image(isKey,15,15,false, false);
		lblUsername.setText(domainController.translate("Username"));
		lblPassword.setText(domainController.translate("Password"));
		btnLogin.setText(domainController.translate("Login"));
		btnLogin.setGraphic(new ImageView(key));
		
	}
		

	@FXML
	private void btnLoginOnAction(ActionEvent event) {
		var username = txtUsername.getText();
		var password = txtPassword.getText();
		
		try {
		this.emptyFieldCheck(username);
		this.emptyFieldCheck(password);	
		domainController.login(username, password);
		loggedIn(this.getScene());
		
		} catch (Exception e) { // TODO Auto-generated catch block			
			lblLoginException.setText(e.getMessage());
		}
	}
	
	
}
