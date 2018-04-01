package application;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.net.URL;

public class FXMLSignInController implements EventHandler<ActionEvent> {

	public long tm = System.currentTimeMillis();
	public long tmCur;

	@FXML
	private Pane pane;
	@FXML
	private Label label;
	@FXML
	private WebView browser;
	@FXML
	private Label close;
	@FXML
	public Label time;

	private AnchorPane homePane;

	@FXML
	private Button homeBtn;
	@FXML
	private ImageView img1;
	@FXML
	private ImageView img;
	@FXML
	private Label lbl1;
	@FXML
	private Label lbl2;
	@FXML
	private Label lbl3;
	@FXML
	private ImageView hand;
	@FXML
	private ImageView cir;
	@FXML
	private Polygon pol;
	@FXML
	private Button acc;
	@FXML
	private TextField code;

	public Boolean ready = false;
	int i = 0, j = 0;
	
	@FXML
	private Label plab1;
	@FXML
	private Label plab2;
	@FXML
	private Label plab3;
	@FXML
	private Label plab12;
	@FXML
	private Label plab22;
	@FXML
	private Label plab32;

	@Override
	public void handle(ActionEvent arg0) {

	}

	@FXML
	private void handleClose(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	private void minimizeApp(MouseEvent event) {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}

	public void setNode(Node node) {
		pane.getChildren().clear();
		pane.getChildren().add((Node) node);

		ft(node);
	}

	public void createPage(AnchorPane home, String loc) throws IOException {
		home = FXMLLoader.load(getClass().getResource(loc));
		setNode(home);
	}

	private void ft(Node node) {
		FadeTransition ft = new FadeTransition(Duration.seconds(1.5));
		ft.setNode(node);
		ft.setInterpolator(Interpolator.EASE_OUT);
		ft.setFromValue(0.1);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.play();
	}

	@FXML
	private void sceneActionHome(ActionEvent event) throws IOException {
		this.createPage(homePane, "/application/home.fxml");
	}

	@FXML
	private void sceneActionPay(ActionEvent event) throws IOException {
		this.createPage(homePane, "/application/onlinePaying.fxml");
	}

	@FXML
	private void sceneActionPacket(ActionEvent event) throws IOException {
		this.createPage(homePane, "/application/packet.fxml");
	}

	@FXML
	private void sceneActionPromotions(ActionEvent event) throws IOException {
		this.createPage(homePane, "/application/promotions.fxml");
	}

	@FXML
	private void sceneActionGo(ActionEvent event) throws IOException {

		this.createPage(homePane, "/application/go.fxml");

	}

	@FXML
	private void rotateMe(ActionEvent event) {
		RotateTransition rt = new RotateTransition(Duration.millis(10), hand);
		rt.setCycleCount(1);
		rt.setFromAngle(0);
		rt.setToAngle(180);
		rt.play();
	}

	@FXML
	private void move(ActionEvent event) {
		/*
		 * TranslateTransition t = new TranslateTransition(Duration.seconds(3), cir);
		 * t.setFromX(0); t.setToX(-150); t.setFromY(0); t.setToY(15);
		 * t.setAutoReverse(false); t.setCycleCount(1);/* KeyValue k1 = new
		 * KeyValue(cir.translateXProperty(), 0.0); KeyValue k2 = new
		 * KeyValue(cir.translateXProperty(), -150.0, Interpolator.EASE_BOTH); KeyValue
		 * k3 = new KeyValue(cir.translateYProperty(), 0.0); KeyValue k4 = new
		 * KeyValue(cir.translateYProperty(), 15.0, Interpolator.EASE_BOTH);
		 * 
		 * KeyFrame kf1 = new KeyFrame(Duration.ZERO, k1, k3); KeyFrame kf2 = new
		 * KeyFrame(Duration.seconds(3), k2, k4);
		 * 
		 * Timeline t = new Timeline(kf1, kf2);
		 */
		/////////////////////////////////////////////////////////////////////// 666,211,636,161,474,181,509,130
		PathTransition t = null;
		if (j++ == 0) {
			t = new PathTransition(Duration.seconds(5), new Polyline(0, 0, -30, -47, -184, -33, -155, -78), cir);
		} else {
			////////////////////////////////// 510, 132, 474,181,466,229,497,290,538, 275
			cir.setLayoutX(510);
			cir.setLayoutY(132);
			t = new PathTransition(Duration.seconds(5),
					new Polyline(0, 0, -36, 49, -44, 97, -13, 158, 28, 143, 43, 172), cir);
		}
		t.play();
	}

	// videcemo kako ce ovo da se salje

	private void check() {
		if (Integer.parseInt(label.getText()) > 0 && label.getText() != null) {
			label.setVisible(true);
		}
	}

	@FXML
	public void onMouseEntered(MouseEvent event) {
		close.setTextFill(Color.color(0.465, 0, 0));
	}

	@FXML
	public void onMouseExited(MouseEvent event) {
		close.setTextFill(Color.color(0.565, 0.565, 0.565));
	}

	AnimationTimer timer = new AnimationTimer() {

		private long startTime;

		@Override
		public void start() {
			startTime = System.currentTimeMillis();
			super.start();
		}

		@Override
		public void handle(long timestamp) {
			long now = System.currentTimeMillis();
			double t = (now - startTime) / 1000.0;
			time.setText(String.valueOf(t));
		}
	};

	@FXML
	public void notifyMe(ActionEvent event) {
		tmCur = System.currentTimeMillis();
		time.setText(String.valueOf(((tmCur - tm) / 1000)));
		if (i++ % 2 == 0) {
			pol.setVisible(true);
			time.setVisible(true);
			label.setText("");
			label.setStyle("-fx-background-color: transparent");
			pol.setFill(Color.color(0.357, 0.357, 0.357));
			pol.setStroke(Color.color(0.357, 0.357, 0.357));
		} else {
			pol.setVisible(false);
			time.setVisible(false);
			pol.setFill(Color.color(0.357, 0.357, 0.357, 0));
			pol.setStroke(Color.color(0.357, 0.357, 0.357, 0));
		}
	}

}
