/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author DiscoSales
 */
public class Activation {
    private Stage stage;
    private Scene activation, upload, login, main;
    private HBox root;

    public Activation(Stage stage) {
        this.stage = stage;
        start();
        activation = new Scene(root);
    }
    
    
    private void start() {
        root = new HBox(2);
        root.setPrefSize(800, 600);
        StackPane s1 = new StackPane();
        s1.setPrefSize(393.0, 600.0);
        s1.setStyle("-fx-background-color: #393E46");
        ImageView image = new ImageView("file:/DSLogo.png");
        image.setFitHeight(348.0);
        image.setFitWidth(373.0);
        image.setPreserveRatio(true);
        s1.getChildren().add(image);
        
        StackPane s2 = new StackPane();
        s2.setPrefSize(404.0,600.0);
        VBox dates = new VBox();
        
        Label arrow = new Label("<- Login");
        arrow.setFont(new Font("System Bold", 16));
        VBox.setMargin(arrow, new Insets(-80,0,40,-240));
        arrow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                stage.setScene(login);
                stage.show();
            }
        });
        
        Label d = new Label("Activate your account");
        d.setFont(new Font("System Bold", 20));
        VBox.setMargin(d, new Insets(0,0,0,0));
        Label errCode = new Label("Incorrect code. Retry");
        errCode.setTextFill(Color.TRANSPARENT);
        Label d2 = new Label("Activation code");
        d2.setStyle("-fx-border-width: 0px 0px 0px 0px;");
        VBox.setMargin(d2, new Insets(-12,0,-24,0));
        TextField codeFld = new TextField();
        codeFld.setAlignment(Pos.CENTER);
        codeFld.setPromptText("-- -- -- -- -- --");
        codeFld.setStyle("-fx-background-color: transparent; -fx-border-color: #00ADB5; -fx-border-width: 0px 0px 2px 0px;");
        codeFld.setFont(new Font(19));
        VBox.setMargin(codeFld, new Insets(0,81,23,81));
        Button activeBtn = new Button("Activate");
        activeBtn.setStyle("-fx-background-color: #00ADB5;");
        activeBtn.setTextFill(Color.WHITE);
        activeBtn.setAlignment(Pos.CENTER);
        activeBtn.setPrefSize(96,20);
        activeBtn.setFont(new Font(14));
        activeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                try {
                    stage.setScene(upload);
                    stage.show();
                    Thread.sleep(5000);
                    stage.setScene(main);
                    stage.show();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Activation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        dates.getChildren().addAll(arrow,d,d2,errCode,codeFld,activeBtn);
        HBox.setHgrow(s2, Priority.ALWAYS);
        dates.setAlignment(Pos.CENTER);
        dates.setSpacing(40);
        
        s2.getChildren().add(dates);
        HBox.setHgrow(s1, Priority.ALWAYS);
        HBox.setHgrow(s2, Priority.NEVER);
        
        root.getChildren().addAll(s1,s2);
        
        stage.setTitle("Activate!");
        stage.setScene(activation);
    }
    
    public void setScenes(Scene login, Scene upload, Scene main){
        this.login = login;
        this.upload = upload;
        this.main = main;
    }
    
    public Scene getScene(){
        return activation;
    }
}
