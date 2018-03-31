package application;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class FXMLSignInController implements EventHandler<ActionEvent> {

	@FXML
	private Pane pane;
	@FXML
	private Label label;
	@FXML
	private WebView browser;
	
	private AnchorPane homePane;
	
	@FXML
	private Button homeBtn;
	@FXML
	private ImageView img1;
	
	private RotateTransition rotateTransition1;
	public Boolean ready = false;
	int i=0;
	
	@Override
	public void handle(ActionEvent arg0) {

	}
	
	@FXML
	private void handleClose(MouseEvent event) {
		System.exit(0);
	}
	
	@FXML
	private void minimizeApp(MouseEvent event) {
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}
	
	 public void setNode(Node node) {
		pane.getChildren().clear();
		pane.getChildren().add((Node)node);
		
		ft(node);
	}
	
	public void createPage(AnchorPane home, String loc) throws IOException{
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
	private void sceneActionPromotions(ActionEvent event) throws IOException{
		this.createPage(homePane, "/application/loading.fxml");
		// salje se serveru poruka
		
		//this.createPage(homePane, "/application/promotions.fxml");
	}
	
	@FXML
	private void sceneActionGo(ActionEvent event) throws IOException {
		this.createPage(homePane, "/application/go.fxml");
		browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine.load("http://google.com");
	}
	
	// videcemo kako ce ovo da se salje
	
	private void check() {
		if(Integer.parseInt(label.getText())>0 && label.getText()!=null) {
			label.setVisible(true);
		}
	}
	

}