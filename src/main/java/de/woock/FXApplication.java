package de.woock;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

@EnableScheduling
@SpringBootApplication
public class FXApplication extends Application {
	
	private ConfigurableApplicationContext ctx;
	
	public static Stage stage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXApplication.stage = primaryStage;
		ctx.publishEvent(new StageReadyEvent(primaryStage));
	}
	
	@Override
	public void init() throws Exception {
		ctx = new SpringApplicationBuilder(FXApplication.class).run();
	}
	
	@Override
	public void stop() throws Exception {
		ctx.close();
		Platform.exit();
	}

	public static class StageReadyEvent {
		Stage stage;
		public StageReadyEvent(Stage stage) {
			this.stage = stage;
		}

		public Stage getStage() {
			return stage;
		}
	}

}
