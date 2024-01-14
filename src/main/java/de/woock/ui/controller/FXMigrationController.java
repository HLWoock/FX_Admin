package de.woock.ui.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polygon;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FXMigrationController {
	
	final ToggleGroup group = new ToggleGroup();

	@FXML   Button           bnMigrieren;
	@FXML   Button           bnChecken;
	@FXML   Polygon          arLeft;
	@FXML   Polygon          arRight;
	@FXML   RadioButton      rbMitglieder_Von;
	@FXML   RadioButton      rbFuhrpark_Von;
	@FXML   RadioButton      rbVersicherung_Von;
	@FXML   RadioButton      rbCallcenter_Von;
	@FXML   RadioButton      rbWerkstatt_Von;
	@FXML   RadioButton      rbBackoffice_Von;
	@FXML   RadioButton      rbMitglieder_Nach;
	@FXML   RadioButton      rbFuhrpark_Nach;
	@FXML   RadioButton      rbCallcenter_Nach;
	@FXML   RadioButton      rbVersicherung_Nach;
	@FXML   RadioButton      rbWerkstatt_Nach;
	@FXML   RadioButton      rbBackoffice_Nach;
	@FXML   ImageView        imgAnimation;
	
	@FXML
	public void initialize() {
		arLeft.setVisible(false);
		arRight.setVisible(false);
		imgAnimation.setVisible(false);
		
		createToggleGroup();
	}

	private void createToggleGroup() {
		rbMitglieder_Von   .setToggleGroup(group);
		rbFuhrpark_Von     .setToggleGroup(group);
		rbCallcenter_Von   .setToggleGroup(group);
		rbVersicherung_Von .setToggleGroup(group);
		rbWerkstatt_Von    .setToggleGroup(group);
		rbBackoffice_Von   .setToggleGroup(group);
		rbMitglieder_Nach  .setToggleGroup(group);
		rbFuhrpark_Nach    .setToggleGroup(group);
		rbCallcenter_Nach  .setToggleGroup(group);
		rbVersicherung_Nach.setToggleGroup(group);
		rbWerkstatt_Nach   .setToggleGroup(group);
		rbBackoffice_Nach  .setToggleGroup(group);
		
		group.selectedToggleProperty().addListener((ChangeListener<Toggle>) (ob, alt, neu) -> {
		    RadioButton rb = (RadioButton)group.getSelectedToggle();
  
		    if (rb != null) {
	        	arRight.setVisible(rb.getId().endsWith("Von"));
	        	arLeft.setVisible(rb.getId().endsWith("Nach"));
		    }
		});		
	}
	
	@SuppressWarnings("unused")
	@FXML 
	public void handleBnMigrieren() throws IOException {
		String text = ((RadioButton)(group.getSelectedToggle())).getId();
		System.out.println("Button migrieren" + text);
		
		Process proc = Runtime.getRuntime().exec("java -jar A.jar");
		// Then retreive the process output
		InputStream in  = proc.getInputStream();
		InputStream err = proc.getErrorStream();
		
		imgAnimation.setVisible(true);
	}
	
	@FXML
	public void handleBnChecken() {
		System.out.println("Button checken");
		imgAnimation.setVisible(false);
	}
	
	
}
