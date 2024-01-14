package de.woock.ui.component;

import org.springframework.stereotype.Component;

import eu.hansolo.medusa.Gauge;
import eu.hansolo.medusa.Gauge.SkinType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

@Component
public class Gauges {
	public Gauge cpu    = new Gauge(SkinType.GAUGE);
	public Gauge ram    = new Gauge(SkinType.GAUGE);
	public Gauge thread = new Gauge(SkinType.GAUGE);
	
    public Label lbCPU    = new Label("CPU");
    public Label lbRAM    = new Label("RAM");
    public Label lbThread = new Label("Threads");
	
    public void labels() {
    	lbCPU   .setLayoutX(160);
    	lbCPU   .setLayoutY(500);
    	lbRAM   .setLayoutX(503);
    	lbRAM   .setLayoutY(500);
    	lbThread.setLayoutX(800);
    	lbThread.setLayoutY(500);
    	
    }
	public Gauges() {
		labels();
		cpu.setTitle("CPU");
		cpu.setTitleColor(Color.WHITESMOKE);
		cpu.setUnit("%");
		cpu.setUnitColor(Color.WHITESMOKE);
		cpu.setDecimals(0);
		cpu.setValue(0.0);
		cpu.setMaxValue(100);
		cpu.setAnimated(true);
		cpu.setAnimationDuration(500);
		cpu.setLayoutX( 50 - cpu.getLayoutBounds().getMinX());
		cpu.setLayoutY(300 - cpu.getLayoutBounds().getMinY());
		
		ram.setTitle("RAM");
		ram.setTitleColor(Color.WHITESMOKE);
		ram.setUnit("MB");
		ram.setUnitColor(Color.WHITESMOKE);
		ram.setDecimals(0);
		ram.setValue(0.0);
		ram.setMaxValue(Double.valueOf(100));
		ram.setAnimated(true);
		ram.setAnimationDuration(500);
		ram.setLayoutX(390 - ram.getLayoutBounds().getMinX());
		ram.setLayoutY(300 - ram.getLayoutBounds().getMinY());

		thread.setTitle("Threads");
		thread.setTitleColor(Color.WHITESMOKE);
		thread.setUnit("#");
		thread.setUnitColor(Color.WHITESMOKE);
		thread.setDecimals(0);
		thread.setValue(0);
		thread.setMaxValue(50);
		thread.setAnimated(true);
		thread.setAnimationDuration(500);
		thread.setLayoutX(700 - thread.getLayoutBounds().getMinX());
		thread.setLayoutY(300 - thread.getLayoutBounds().getMinY());
	}

}
