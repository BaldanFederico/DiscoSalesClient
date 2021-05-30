/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import java.util.logging.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 *
 * @author DiscoSales
 */
public class Login {
    private Scene login, activation, saveAccount, upload, main;
//    private StackPane middle;
    private HBox root;
    private Stage stage;
    private boolean active;

    public Login(Stage stage) {
        this.stage = stage;
        start();
        login = new Scene(root);
        active = false;
//        upload = new Scene(middle);
    }
    
    
    private void start() {
        root = new HBox(2);
        root.setPrefSize(800, 600);
        /**
         * 
         */
        StackPane s1 = new StackPane();
        s1.setPrefSize(200,600);
        s1.setStyle("-fx-background-color: #393E46");
        ImageView image = new ImageView("file:/DSLogo.png");
        image.setFitHeight(348.0);
        image.setFitWidth(373.0);
        image.setPreserveRatio(true);
        s1.getChildren().add(image);
        
        /**
         * 
         */
        StackPane s2 = new StackPane();
        s2.setPrefSize(600,600);
        VBox dates = new VBox();
        Label welcome = new Label("DiscoSales welcomes you back!");
        welcome.setFont(new Font("System Bold", 20));
        VBox.setMargin(welcome, new Insets(0,0,0,0));
        Label errUserData = new Label("WRONG Username AND/OR Password");
        errUserData.setTextFill(Color.TRANSPARENT);
        TextField userFld = new TextField();
        userFld.setAlignment(Pos.CENTER);
        userFld.setPromptText("Username");
        userFld.setPrefSize(200, 30);
        userFld.setStyle("-fx-background-color: transparent; -fx-border-color: #00ADB5; -fx-border-width: 0px 0px 2px 0px;");
        VBox.setMargin(userFld, new Insets(0,81,0,81));
        PasswordField passFld = new PasswordField();
        passFld.setPromptText("Password");
        passFld.setAlignment(Pos.CENTER);
        passFld.setPrefSize(200, 30);
        passFld.setStyle("-fx-background-color: transparent; -fx-border-color: #00ADB5; -fx-border-width: 0px 0px 2px 0px;");
        VBox.setMargin(passFld, new Insets(0,81,0,81));
        Button signinBtn = new Button("Sign in");
        signinBtn.setStyle("-fx-background-color: #00ADB5;");
        signinBtn.setTextFill(Color.WHITE);
        signinBtn.setAlignment(Pos.CENTER);
        signinBtn.setFont(new Font(14));
        signinBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                if (userFld.getText().equals("lucaagos") && passFld.getText().equals("1234")) {
                    errUserData.setTextFill(Color.TRANSPARENT);
                    try {
                        stage.setScene(upload);
                        stage.show();
                        Thread.sleep(5000);
                        stage.setScene(main);
                        stage.show();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (userFld.getText().equals("ManuPR77") && passFld.getText().equals("9593")) {
                    if (!active) {
                        stage.setScene(activation);
                        stage.show();
                    } else {;
                        try {
                            stage.setScene(upload);
                            stage.show();
                            Thread.sleep(5000);
                            stage.setScene(main);
                            stage.show();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    errUserData.setTextFill(Color.RED);
                }
            }
        });
        Label saveLbl = new Label("Forgot Your Password?");
        saveLbl.setTextFill(Color.web("#259AAE"));
        saveLbl.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                stage.setScene(saveAccount);
                stage.show();
            }
        });
        Label registerLbl = new Label("Not registered yet?");
        registerLbl.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                System.out.println("Register");
            }
        });
        
        dates.getChildren().addAll(welcome,errUserData,userFld,passFld,signinBtn,saveLbl,registerLbl);
        HBox.setHgrow(s2, Priority.ALWAYS);
        dates.setAlignment(Pos.CENTER);
        dates.setSpacing(40);
        
        s2.getChildren().add(dates);
        HBox.setHgrow(s1, Priority.ALWAYS);
        HBox.setHgrow(s2, Priority.NEVER);
        
        root.getChildren().addAll(s1,s2);
        
        /**
         * middle = new StackPane();
        middle.setMaxSize(-Infinity, -Infinity);
        middle.setMinSize(-Infinity, -Infinity);
        middle.setPrefSize(600, 400);
        
        VBox progress = new VBox(2);
        StackPane.setAlignment(middle, Pos.CENTER);
        progress.setAlignment(Pos.CENTER);
        ProgressIndicator indy = new ProgressIndicator();
        indy.setLayoutX(274);
        indy.setLayoutY(147);
        Label pr = new Label("Checking for updates...");
        pr.setAlignment(Pos.CENTER);
        pr.setLayoutX(220);
        pr.setLayoutY(214);
        pr.setPrefSize(156, 27);
        pr.setTextAlignment(TextAlignment.CENTER);
        pr.setWrapText(true);
        pr.setFont(new Font("System Bold Italic", 14));
        
        progress.getChildren().addAll(indy,pr);
        middle.getChildren().addAll(progress);
         * 
         * 
         */
        
        
        stage.setScene(login);
        stage.setTitle("Login");
    }
    
    public void setScenes(Scene activation, Scene saveAccount, Scene upload, Scene main){
        this.activation = activation;
        this.saveAccount = saveAccount;
        this.upload = upload;
        this.main = main;
    }
    
    public Scene getScene(){
        return login;
    }
}
