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
import exceptions.FieldException;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

public class RegisterScreenController extends BaseScreenController {
	
	@FXML
	private Label lblUsernameRegister;
	@FXML
	private TextField txtUsernameRegister;
	@FXML
	private Label lblPasswordRegister;
	@FXML
	private PasswordField txtPasswordRegister;
	@FXML
	private Label lblFirstName;
	@FXML
	private Label lblName;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtName;
	@FXML
	private Button btnRegister;
	@FXML
	private Label lblRegisterException;
	@FXML
	private ImageView background;
	@FXML
	private Label lblRegister;

	private static String imageFilePathDisk = "src\\resources\\images\\disk.png";

	
	public RegisterScreenController(DomainController domainController) {
		super(domainController, "RegisterScreen.fxml");
		background.fitWidthProperty().bind(this.widthProperty());
		background.fitHeightProperty().bind(this.heightProperty());	
	}

	@Override
	protected void loadData() throws FileNotFoundException {
		// set translations
		FileInputStream isDisk = new FileInputStream(imageFilePathDisk);
		Image disk = new Image(isDisk,15,15,false, false);		
		
		lblUsernameRegister.setText(domainController.translate("Username"));
		lblPasswordRegister.setText(domainController.translate("Password"));
		Tooltip tooltip = new Tooltip();
		tooltip.setText(domainController.translate("TooltipPassword"));
		txtPasswordRegister.setTooltip(tooltip);
		lblFirstName.setText(domainController.translate("FirstName"));
		lblName.setText(domainController.translate("Name"));
		btnRegister.setText(domainController.translate("Register"));
		btnRegister.setGraphic(new ImageView(disk));
		lblRegisterException.setText("");
		lblRegister.setText(domainController.translate("Register"));
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
		domainController.register(name, firstName, username, password);
		setMenuScreen(this.getScene());
		
		} catch (FieldException e ) { // TODO Auto-generated catch block			
			lblRegisterException.setText(e.getMessage());
		}
		  catch (Exception e) {
			  e.printStackTrace();
		  }
	}
	
	@FXML
	private void btnReturnToWelcomeScreen(ActionEvent event) {		
		setWelcomeScreen(this.getScene());
	}

	
}
