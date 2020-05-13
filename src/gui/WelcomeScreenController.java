package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import domein.DomainController;
import exceptions.FieldException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class WelcomeScreenController extends BaseScreenController {
	
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
	@FXML
	private Hyperlink linkRegister;
	

	private static String imageFilePathKey = "src\\resources\\images\\key.png";
	
	public WelcomeScreenController(DomainController domainController) {
		super(domainController, "WelcomeScreen.fxml");
		
		
		languages = FXCollections.observableArrayList();
		languages.addAll("English", "Nederlands", "Français");
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
		linkRegister.setText(domainController.translate("RegisterLink"));
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
		
		} catch (FieldException e ) { // TODO Auto-generated catch block			
			lblLoginException.setText(e.getMessage());
		}
		  catch (Exception e) {
			  e.printStackTrace();
		  }
	}
	
	@FXML
	private void hyperlinkRegister(ActionEvent event) {
		Stage stage = (Stage) this.getScene().getWindow();
		RegisterScreenController root = new RegisterScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	}
	
	
}
