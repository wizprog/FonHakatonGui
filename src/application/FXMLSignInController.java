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
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class FXMLSignInController implements EventHandler<ActionEvent> {

	@FXML
	private Pane pane;
	
	private AnchorPane homePane;
	
	@FXML
	private Button homeBtn;
	
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
	}
	
	public void createPage(AnchorPane home, String loc) throws IOException{
		home = FXMLLoader.load(getClass().getResource(loc));
		setNode(home);
	}
	
	
	// sledeci kod se ponavlja za svaku scenu ponaosob, na osnovu button-a
	@FXML
	public void sceneActionHome(ActionEvent event) throws IOException {
		this.createPage(homePane, "/application/home.fxml"); // samo se stavi naziv scena
	}
	

}