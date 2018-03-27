package application;

import java.util.concurrent.TimeUnit;

import javafx.animation.FadeTransition;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.*;

public class Controller implements EventHandler<ActionEvent>{

	@FXML
	private TextField username;
	
	@FXML
	private Label label;

	@FXML
	public void handleClose(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	public void onMouseEntered(MouseEvent event) {
		label.setTextFill(Color.RED);
	}
	
	@FXML
	public void onMouseExited(MouseEvent event) {
		label.setTextFill(Color.color(0.565, 0.565,0.565));
	}
	
	@FXML
	private void handleButtonAction(ActionEvent event) throws Exception {
		Parent signInPage = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
		Scene signInScene = new Scene(signInPage);
		Stage logIn = (Stage)((Node)event.getSource()).getScene().getWindow();
		logIn.setScene(signInScene);
		logIn.show();
	}

	@Override
	public void handle(ActionEvent arg0) {

	}

}
