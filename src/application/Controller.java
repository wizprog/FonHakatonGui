package application;

import java.util.concurrent.TimeUnit;
import sqlConnection.*;

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
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.control.*;

public class Controller implements EventHandler<ActionEvent>{

	@FXML
	private TextField username;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Label label;

	@FXML
	public void handleClose(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	public void onMouseEntered(MouseEvent event) {
		label.setTextFill(Color.color(0.465, 0, 0));
	}
	
	@FXML
	public void onMouseExited(MouseEvent event) {
		label.setTextFill(Color.color(0.565, 0.565,0.565));
	}
	
	double xOffset = 0, yOffset = 0;

	@FXML
	private void handleButtonActionLogIn(ActionEvent event) throws Exception {
		// Database dat = new Database();
	        
	     //   User current = dat.queryLog(username.getText(), password.getText());
			
	     
				Parent signInPage = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
				Scene signInScene = new Scene(signInPage);
				Stage logIn = (Stage)((Node)event.getSource()).getScene().getWindow();
				logIn.setScene(signInScene);
				 //  if (current != null) {
					   logIn.show();
				//   }

			//dat.closeConnection();
			
		signInPage.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});

		// move around here
		signInPage.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				logIn.setX(event.getScreenX() - xOffset);
				logIn.setY(event.getScreenY() - yOffset);
			}
		});
		logIn.setScene(signInScene);
		logIn.show();
	}
	
	@FXML
	private void handleButtonActionLogUp(ActionEvent event) throws Exception {
		Parent signUpPage = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		Scene signUpScene = new Scene(signUpPage);
		Stage logUp = (Stage)((Node)event.getSource()).getScene().getWindow();
		signUpPage.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});

		// move around here
		signUpPage.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				logUp.setX(event.getScreenX() - xOffset);
				logUp.setY(event.getScreenY() - yOffset);
			}
		});
		logUp.setScene(signUpScene);
		logUp.show();
	}

	@Override
	public void handle(ActionEvent arg0) {

	}

}
