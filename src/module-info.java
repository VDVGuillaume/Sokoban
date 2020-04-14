module sokoban88 {
	exports persistentie;
	exports ui;
	exports util;
	exports gui;
	exports main;
	exports domein;
	exports exceptions;

	requires java.sql;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	requires java.desktop;
	requires javafx.swing;
	
	opens gui to javafx.graphics, javafx.fxml;
}