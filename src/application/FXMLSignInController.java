package application;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.animation.FadeTransition;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class FXMLSignInController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent arg0) {

	}
	
	@FXML
	public void handleClose(MouseEvent event) {
		System.exit(0);
	}
}