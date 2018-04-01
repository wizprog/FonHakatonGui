package application;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import sqlConnection.*;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.control.*;

public class Controller implements EventHandler<ActionEvent> {

	@FXML
	private ImageView img1;
	@FXML
	private Label lbl1;
	@FXML
	private ImageView img2;
	@FXML
	private Label lbl2;
	@FXML
	private ImageView img3;
	@FXML
	private Text txt1;
	@FXML
	private Text txt2;
	@FXML
	private Text txt3;

	private RotateTransition rotateTransition1, rotateTransition2, rotateTransition3;

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
		label.setTextFill(Color.color(0.565, 0.565, 0.565));
	}

	double xOffset = 0, yOffset = 0;

	@FXML
	private void handleButtonActionLogIn(ActionEvent event) throws Exception {
		// Database dat = new Database();

		// User current = dat.queryLog(username.getText(), password.getText());

		img1.setImage(new Image("picture/syn.png"));
		txt1.setText("Checking username");
		rotateTransition1 = new RotateTransition(Duration.seconds(0.1), img1);
		rotateTransition2 = new RotateTransition(Duration.seconds(0.1), img2);
		rotateTransition3 = new RotateTransition(Duration.seconds(0.1), img3);
		RotateTransition transition[] = { rotateTransition1, rotateTransition2, rotateTransition3 };
		for (RotateTransition rTransition : transition) {
			rTransition.setCycleCount(1);
			rTransition.setAutoReverse(false);
			rTransition.setFromAngle(0);
			rTransition.setToAngle(720);
		}
		rotateTransition1.play();
		rotateTransition1.setOnFinished((e) -> {
			img1.setImage(new Image("picture/ok.png"));
			lbl1.setStyle("-fx-background-color:#45A563");
			img2.setImage(new Image("picture/syn.png"));
			txt2.setText("Checking password");
			// function for checking if valid
			rotateTransition2.play();
		});

		rotateTransition2.setOnFinished((e) -> {
			img2.setImage(new Image("picture/ok.png"));
			lbl2.setStyle("-fx-background-color:#45A563");
			img3.setImage(new Image("picture/syn.png"));
			txt3.setText("Accessing");
			// function for checking if valid
			rotateTransition3.play();
		});
		rotateTransition3.setOnFinished((e) -> {
			img3.setImage(new Image("picture/ok.png"));
			Parent signInPage = null;
			try {
				signInPage = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Scene signInScene = new Scene(signInPage);
			Stage logIn = (Stage) ((Node) event.getSource()).getScene().getWindow();
			logIn.setScene(signInScene);
			// if (current != null) {
			logIn.show();
			// }

			// dat.closeConnection();

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
		});
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
