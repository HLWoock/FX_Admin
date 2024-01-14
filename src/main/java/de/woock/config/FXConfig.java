package de.woock.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import de.woock.FXApplication.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class FXConfig {

	@Value("classpath:chart.fxml")
	private Resource chartResource;
	
	@Autowired
	private ApplicationContext ctx;
	
	@EventListener
	public void onApplicationEvent(StageReadyEvent event) {
		Stage stage = event.getStage();
		stage.setOnCloseRequest(e -> {
			e.consume();
			Boolean answer = true;
			if (answer) {
				log.debug("--> and ciao");
				stage.close();
			}
		});
		Parent parent;
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(chartResource.getURL());
			fxmlLoader.setControllerFactory(aClass -> ctx.getBean(aClass));
			parent = fxmlLoader.load();
			
//			FXStattAutoController controller = fxmlLoader.getController();
			
			Scene scene = new Scene(parent, 1500, 1000);
			scene.getStylesheets().add("/stattauto.css");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
