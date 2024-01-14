package de.woock.ui.view;

import java.io.IOException;

import org.springframework.context.ApplicationContext;

import de.woock.FXApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class MigrationUI implements UIContext {
	
	@Override
	public Node getNode(ApplicationContext ctx) {
		
		Node node = null;

		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXApplication.class.getResource("/migration.fxml"));
            loader.setControllerFactory(bean -> ctx.getBean(bean));
            node = (AnchorPane) loader.load();         
//          FXMigrationController controller =  loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return node;
	}

}
