package de.woock.ui.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import de.woock.ui.view.MigrationUI;
import de.woock.ui.view.StammdatenUI;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class FXStattAutoController {
	
	ApplicationContext ctx;
	
	@FXML private TreeView<String> menuTree;
	@FXML private AnchorPane       contentPane;
	@FXML private ImageView        imgBackground;
	@FXML private Label            lbSubtitel;
	
	public FXStattAutoController(ApplicationContext ctx) {
		this.ctx = ctx;
	}
	
	@FXML
	public void initialize() {
		log.debug("here we go --->  ");
		
		TreeItem<String> rootItem = initTree();
		
		menuTree.setRoot(rootItem);
		menuTree.getSelectionModel()
		        .selectedItemProperty()
		        .addListener((item, oldValue, newValue) -> selectContentView(newValue));
	}

	private TreeItem<String> initTree() {
		TreeItem<String> rootItem = new TreeItem<String> ("StattAuto", loadImage("Firma"));
        rootItem.setExpanded(true);

        
        TreeItem<String> debug = new TreeItem<>("Wartung", loadImage("Debug"));
        debug.getChildren().add(new TreeItem<String> ("Stammdaten"));
        debug.getChildren().add(new TreeItem<String> ("Migration"));
        
        
        
        rootItem.getChildren().add(debug);
        
		return rootItem;
	}

	private void selectContentView(TreeItem<String> treeItem) {
		TreeItem<String> selectedItem = (TreeItem<String>) treeItem;
		if(selectedItem.getParent() != null) {
			String viewName = String.format("%s:%s", selectedItem.getParent().getValue(), selectedItem.getValue());
			log.debug("selected page: {}", viewName);
	        switch (viewName) {
	       	case "Wartung:Stammdaten":
	       		setBackgroundImage("stammdaten", 1.6);
	       		lbSubtitel.setText("Debug");
	       		switchContent(new StammdatenUI().getNode(ctx));
	       		break;
	       	case "Wartung:Migration":
	       		setBackgroundImage("migration", 2.4);
	       		lbSubtitel.setText("Migration");
	       		switchContent(new MigrationUI().getNode(ctx));
	       		break;
	       }
		}
	}

	private void setBackgroundImage(String filename, double scale) {
		try {
			imgBackground.setImage(new Image(new FileInputStream(new File(String.format("./src/main/resources/images/backgrounds/%s.jpg", filename)).getAbsolutePath())));
			imgBackground.setScaleX(scale);
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}

	private boolean switchContent(Node node) {
		return contentPane.getChildren().setAll(node);
	}

	private ImageView loadImage(String name) {
		return new ImageView(new Image(getClass().getResourceAsStream(String.format("/images/%s.png", name))));
	}
}
