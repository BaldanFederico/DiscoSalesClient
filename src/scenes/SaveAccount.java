/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;
import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 *
 * @author manue
 */
public class SaveAccount {
    private HBox root;
    private Stage stage;
    private Scene saveAccount, login;

    public SaveAccount(Stage stage) {
        this.stage = stage;
        start();
        saveAccount = new Scene(root);
    }
    
    
    private void start() {
        root = new HBox();
        
        /**
         * 
         */
        StackPane s1 = new StackPane();
        s1.setMaxSize(Infinity, Infinity);
        s1.setStyle("-fx-background-color: #393E46;");
        ImageView image = new ImageView("file:/NetBeansProjects/JavaFXApplication11/DSLogo.png");
        image.setFitHeight(348);
        image.setFitWidth(373);
        image.setPreserveRatio(true);
        HBox.setHgrow(image, Priority.ALWAYS);
        s1.getChildren().add(image);
        /**
         * 
         */
        StackPane s2 = new StackPane();
        HBox.setHgrow(s2, Priority.ALWAYS);
        VBox datas = new VBox();
        datas.setAlignment(Pos.CENTER);
        StackPane.setAlignment(datas, Pos.CENTER);
        Label arrow = new Label("<- Login");
        arrow.setFont(new Font("System Bold", 16));
        VBox.setMargin(arrow, new Insets(-120,360,0,0));
        arrow.setOnMouseClicked(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent event){
                 System.out.println("Login");
             }
        });
        Label d1 = new Label("Insert your e-mail address in order to save your account");
        d1.setTextAlignment(TextAlignment.CENTER);
        d1.setTextFill(Color.web("#393E46"));
        d1.setWrapText(true);
        d1.setAlignment(Pos.CENTER);
        d1.setMaxSize(400, 300);
        d1.setPrefSize(400, 77);
        d1.setFont(new Font("System Italic", 21));
        VBox.setMargin(d1, new Insets(90,0,0,0));
        Label d2 = new Label("You will be sent an e-mail. If you are not registered a DiscoSales acount, the e-mail will not be sent");
        d2.setTextAlignment(TextAlignment.CENTER);
        d2.setTextFill(Color.web("#393E46"));
        d2.setWrapText(true);
        d2.setAlignment(Pos.CENTER);
        d2.setMaxSize(400, 300);
        d2.setPrefSize(390, 66);
        d2.setFont(new Font("System Italic", 12));
        Label errEmail = new Label("WRONG Username AND/OR Password");
        errEmail.setTextFill(Color.RED);
        HBox text = new HBox(2);
        text.setAlignment(Pos.CENTER);
        text.setPrefSize(600, 57);
        text.setSpacing(15);
        Label e = new Label("Email : ");
        e.setTextFill(Color.web("#393E46"));
        e.setFont(new Font("Verdana", 14));
        TextField emailFld = new TextField();
        emailFld.setPromptText("user@example.com");
        emailFld.setMaxWidth(300);
        emailFld.setPrefSize(172, 25);
        emailFld.setFont(new Font("System",14));
        emailFld.setStyle("-fx-background-color: transparent; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #00ADB5;");
        text.getChildren().addAll(e,emailFld);
        VBox.setMargin(text, new Insets(0,0,30,0));
        Button saveBtn = new Button("Send e-mail");
        saveBtn.setTextFill(Color.WHITE);
        saveBtn.setStyle("-fx-background-color: #00ADB5;");
        saveBtn.setPrefSize(107,29);
        saveBtn.setFont(new Font("System", 13));
        VBox.setMargin(emailFld, new Insets(13,0,0,0));
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                
            }
        });
        datas.getChildren().addAll(arrow,d1,d2,errEmail,text,saveBtn);
        datas.setPadding(new Insets(0,36,0,36));
        s2.getChildren().add(datas);
        root.getChildren().addAll(s1,s2);
        
        stage.setScene(saveAccount);
        stage.setTitle("Save account");
    }
    
    public void setScene(Scene login){
        this.login = login;
    }
    
    public Scene getScene(){
        return saveAccount;
    }
}
