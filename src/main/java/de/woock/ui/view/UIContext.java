package de.woock.ui.view;

import org.springframework.context.ApplicationContext;

import javafx.scene.Node;

public interface UIContext {

	Node getNode(ApplicationContext ctx);

}