package gui;

import java.io.FileNotFoundException;
import java.io.IOException;

import domein.DomainController;
import exceptions.FieldException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class BaseScreenController extends GridPane {

	protected ObservableList languages;
	protected DomainController domainController;

	@FXML
	protected ComboBox comboBoxLanguage;

	protected BaseScreenController(DomainController domainController, String resource)  {	
		
		this.domainController = domainController;
		
		languages = FXCollections.observableArrayList();
		languages.addAll("English", "Nederlands", "Français");
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
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

	// method to override with child implementation of this class
	protected abstract void loadData() throws FileNotFoundException;
	
	// Event Listener on ComboBox[#comboBoxLanguage].onAction
	@FXML
	protected void languageChoiceChanged(ActionEvent event) {
		int domainLanguage = domainController.getLanguage();
		int selectedLanguage = languages.indexOf(comboBoxLanguage.getValue()) + 1;

		if (domainLanguage != selectedLanguage) {
			domainController.setLanguage(selectedLanguage);
			try {
				loadData();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			event.consume();
		}
	}

	@FXML
	private void loadLanguageChoices() {
		int domainLanguage = domainController.getLanguage() - 1;		
		
		comboBoxLanguage.getItems().clear();
		comboBoxLanguage.getItems().addAll(languages);
		comboBoxLanguage.setValue(languages.get(domainLanguage).toString());
	}


	protected <T>  void emptyFieldCheck(T field) {
		if( field == null || field.toString().isEmpty()) 
			throw new FieldException(domainController.translate("EmptyField"));	
	}
	
	protected void setMenuScreen(Scene currentScene) {
		Stage stage = (Stage) currentScene.getWindow();
		MenuScreenController root = new MenuScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
	}
	
	protected void setWelcomeScreen(Scene currentScene) {
		Stage stage = (Stage) currentScene.getWindow();
		WelcomeScreenController root = new WelcomeScreenController(domainController);
		Scene scene = new Scene(root, 1000, 500);
		stage.setScene(scene);
		
	}
	
	
	
	
	
}