package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControllerSignUp implements Initializable {

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
	private Label lbl3;
	@FXML
	private ImageView img4;
	@FXML
	private Label lbl4;
	@FXML
	private ImageView img5;
	@FXML
	private Button signUpButton;
	@FXML
	private Text txt1;
	@FXML
	private Text txt2;
	@FXML
	private Text txt3;
	@FXML
	private Text txt4;
	@FXML
	private Text txt5;
	@FXML
	private Label label;
	@FXML
	private Label close;
	@FXML
	private CheckBox termsOfUse;

	private RotateTransition rotateTransition1, rotateTransition2, rotateTransition3, rotateTransition4,
			rotateTransition5;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	double xOffset = 0, yOffset = 0;
	
	@FXML
	public void handleClose(MouseEvent event) throws IOException {
		Parent signInPage = FXMLLoader.load(getClass().getResource("sample.fxml"));
		Scene signInScene = new Scene(signInPage);
		Stage logIn = (Stage)((Node)event.getSource()).getScene().getWindow();
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
	public void onMouseEntered(MouseEvent event) {
		close.setTextFill(Color.color(0.465, 0, 0));
	}
	
	@FXML
	public void onMouseExited(MouseEvent event) {
		close.setTextFill(Color.color(0.565, 0.565,0.565));
	}

	@FXML
	private void signUpButton(ActionEvent event) {
		img1.setImage(new Image("picture/syn.png"));
		txt1.setText("Checking first name");
		rotateTransition1 = new RotateTransition(Duration.seconds(2), img1);
		rotateTransition2 = new RotateTransition(Duration.seconds(2), img2);
		rotateTransition3 = new RotateTransition(Duration.seconds(2), img3);
		rotateTransition4 = new RotateTransition(Duration.seconds(2), img4);
		rotateTransition5 = new RotateTransition(Duration.seconds(2), img5);
		RotateTransition transition[] = { rotateTransition1, rotateTransition2, rotateTransition3, rotateTransition4,
				rotateTransition5 };
		for (RotateTransition rTransition : transition) {
			rTransition.setCycleCount(1);
			rTransition.setAutoReverse(false);
			rTransition.setFromAngle(720);
			rTransition.setToAngle(0);
		}
		rotateTransition1.play();
		rotateTransition1.setOnFinished((e) -> {
			img1.setImage(new Image("picture/ok.png"));
			lbl1.setStyle("-fx-background-color:#45A563");
			img2.setImage(new Image("picture/syn.png"));
			txt2.setText("Checking last name");
			// function for checking if valid
			rotateTransition2.play();
		});

		rotateTransition2.setOnFinished((e) -> {
			img2.setImage(new Image("picture/ok.png"));
			lbl2.setStyle("-fx-background-color:#45A563");
			img3.setImage(new Image("picture/syn.png"));
			txt3.setText("Valid email address");
			// function for checking if valid
			rotateTransition3.play();
		});
		rotateTransition3.setOnFinished((e) -> {
			img3.setImage(new Image("picture/ok.png"));
			lbl3.setStyle("-fx-background-color:#45A563");
			img4.setImage(new Image("picture/syn.png"));
			txt4.setText("Verifying registration code");
			// function for checking if valid
			rotateTransition4.play();
		});
		rotateTransition4.setOnFinished((e) -> {
			img4.setImage(new Image("picture/ok.png"));
			lbl4.setStyle("-fx-background-color:#45A563");
			img5.setImage(new Image("picture/syn.png"));
			txt5.setText("Finalizing the registration");
			// function for checking if valid
			rotateTransition5.play();
		});
		rotateTransition5.setOnFinished((e) -> {
			img5.setImage(new Image("picture/ok.png"));
			label.setVisible(true);
			Parent signInPage = null;
			try {
				signInPage = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Scene signInScene = new Scene(signInPage);
			Stage logIn = (Stage)((Node)event.getSource()).getScene().getWindow();
			signInScene.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					xOffset = event.getSceneX();
					yOffset = event.getSceneY();
				}
			});

			// move around here
			signInScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
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

}
