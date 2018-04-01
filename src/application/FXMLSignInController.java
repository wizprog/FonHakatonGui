package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
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
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.net.Socket;
import java.net.URL;
import java.util.*;

public class FXMLSignInController implements EventHandler<ActionEvent> {

	@FXML
	private Pane pane;
	@FXML
	private Label label;
	@FXML
	private WebView browser;
	@FXML
	private Label close;
	@FXML
	private Label time;


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
	private Button hand;
	@FXML
	private ImageView cir;
	
	
	@FXML
	private Label plab1;
	@FXML
	private Label plab2;
	@FXML
	private Label plab3;
	
	public Boolean ready = false;
	int i = 0;

	@Override
	public void handle(ActionEvent arg0) {

	}
	
	/*@FXML
	public void initialize() {

	    Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {            
	        Calendar cal = Calendar.getInstance();
	        int second = cal.get(Calendar.SECOND);
	        int minute = cal.get(Calendar.MINUTE);
	        int hour = cal.get(Calendar.HOUR);
	        //System.out.println(hour + ":" + (minute) + ":" + second);
	        lbl1.setText(hour + ":" + (minute) + ":" + second);
	    }),
	         new KeyFrame(Duration.seconds(1))
	    );
	    clock.setCycleCount(Animation.INDEFINITE);
	    clock.play();
	}  */
	
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
	    boolean loading = true;
	    this.createPage(homePane, "/application/loading.fxml");

		Platform.runLater(new Runnable() {
			
            @Override public void run() {
            	//this.createPage(homePane, "/application/loading.fxml");
           // 	try {
				/*	homePane = FXMLLoader.load(getClass().getResource("/application/loading.fxml"));
					pane.getChildren().clear();
					pane.getChildren().add((Node) homePane);	
					pane.setDisable(true);*/
            		lbl1.setText(String.valueOf( System.nanoTime()));
            		
			/*	} catch (IOException e) {
					e.printStackTrace();
				}*/
            }
        });  
		try (Socket socket = new Socket("localhost", 4002);
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());) {	
			out.writeObject("\"BS11490\"");
			out.flush();
			int array[]=new int[6];
			for(int i=0;i<6;i++) {
				array[i]=(int)in.readObject();
				System.out.println(array[i]);   // 3 call  4 sms  5 internet 
			}
			
			int sorted[] = new int[3];
			int pom;
			
			HashMap<String, Integer> map = new HashMap<String,Integer>();
			map.put("1", array[3]);
			map.put("2", array[4]);
			map.put("3", array[5]);
			
			
			int data[] = new int[3];
			int dataIndex[] = new int[3];
			
			data[0] = array[3];
			data[1] = array[4];
			data[2] = array[5];
			
			dataIndex[0] = 1;
			dataIndex[1] = 2;
			dataIndex[2] = 3;
			
			int pom1, pom2 ;
			
			for (int i=0; i<3 ; i++) 
				for (int j=i; j<3 ; j++) {
					if (data[i]>data[j]) {
						pom1 = data[i];
						data[i] = data[j];
						data[j] = pom1;
						
						pom2 = dataIndex[i];
						dataIndex[i] = dataIndex[j];
						dataIndex[j] = pom2;
					}
				}
			String bonus = "";
			String bonus1 = "";
			
			switch( dataIndex[2]) {
			case 1: bonus += " CALL BONUS";
				break;
			case 2: bonus += " SMS BONUS";
				break;
			case 3: bonus += " INTERNET BONUS";				
			}
			
		//	plab1.setText(bonus);
			
			switch( dataIndex[1]) {
			case 1: bonus1 += " CALL BONUS";
				break;
			case 2: bonus1 += " SMS BONUS";
				break;
			case 3: bonus1 += " INTERNET BONUS";				
			}
			
		//	plab2.setText(bonus1);
			
		//	plab3.setText(bonus + bonus1);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		loading = false;
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
		TranslateTransition t = new TranslateTransition(Duration.seconds(3), cir);
		t.setFromX(0); t.setToX(-150);
		t.setFromY(0); t.setToY(15);
		t.setAutoReverse(false);
		t.setCycleCount(1);/*
		KeyValue k1 = new KeyValue(cir.translateXProperty(), 0.0);
		KeyValue k2 = new KeyValue(cir.translateXProperty(), -150.0, Interpolator.EASE_BOTH);
		KeyValue k3 = new KeyValue(cir.translateYProperty(), 0.0);
		KeyValue k4 = new KeyValue(cir.translateYProperty(), 15.0, Interpolator.EASE_BOTH);
		
		KeyFrame kf1 = new KeyFrame(Duration.ZERO, k1, k3);
		KeyFrame kf2 = new KeyFrame(Duration.seconds(3), k2, k4);
		
		Timeline t = new Timeline(kf1, kf2);
		*/
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

}