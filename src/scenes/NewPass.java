/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 *
 * @author DiscoSales
 */
public class NewPass {
    private Stage stage;
    private Scene newPass, saveAccount, login;
    private HBox root;

    public NewPass(Stage stage) {
        this.stage = stage;
        start();
        newPass = new Scene(root);
    }
    
    public void start() {
        root = new HBox();
        
        /**
         * 
         */
        StackPane s1 = new StackPane();
        s1.setMaxSize(Infinity, Infinity);
        s1.setStyle("-fx-background-color: #393E46;");
        ImageView image = new ImageView("file:/DSLogo.png");
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
        Label arrow = new Label("<- I don't want to change password");
        arrow.setFont(new Font("System Bold", 16));
        VBox.setMargin(arrow, new Insets(-120,360,0,0));
        arrow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                stage.setScene(saveAccount);
                stage.show();
            }
        });
        Label d1 = new Label("Insert your new password");
        d1.setTextAlignment(TextAlignment.CENTER);
        d1.setTextFill(Color.web("#393E46"));
        d1.setWrapText(true);
        d1.setAlignment(Pos.CENTER);
        d1.setMaxSize(400, 300);
        d1.setPrefSize(400, 77);
        d1.setFont(new Font("System Italic", 21));
        VBox.setMargin(d1, new Insets(90,0,0,0));
        Label errPass = new Label("These passwords don't correspond");
        errPass.setTextFill(Color.TRANSPARENT);
        HBox text1 = new HBox(2);
        text1.setAlignment(Pos.CENTER);
        text1.setPrefSize(600, 57);
        text1.setSpacing(15);
        Label u = new Label("Username : ");
        u.setTextFill(Color.web("#393E46"));
        u.setFont(new Font("Verdana", 14));
        PasswordField passFld = new PasswordField();
        passFld.setPromptText("username");
        passFld.setMaxWidth(300);
        passFld.setPrefSize(172, 25);
        passFld.setFont(new Font("System",14));
        passFld.setStyle("-fx-background-color: transparent; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #00ADB5;");
        text1.getChildren().addAll(u,passFld);
        VBox.setMargin(text1, new Insets(0,0,0,0));
        HBox text = new HBox(2);
        text.setAlignment(Pos.CENTER);
        text.setPrefSize(600, 57);
        text.setSpacing(15);
        Label e = new Label("Email : ");
        e.setTextFill(Color.web("#393E46"));
        e.setFont(new Font("Verdana", 14));
        PasswordField confFld = new PasswordField();
        confFld.setPromptText("user@example.com");
        confFld.setMaxWidth(300);
        confFld.setPrefSize(172, 25);
        confFld.setFont(new Font("System",14));
        confFld.setStyle("-fx-background-color: transparent; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #00ADB5;");
        text.getChildren().addAll(e,confFld);
        VBox.setMargin(text, new Insets(0,0,30,0));
        Button saveBtn = new Button("Change password");
        saveBtn.setTextFill(Color.WHITE);
        saveBtn.setStyle("-fx-background-color: #00ADB5;");
        saveBtn.setPrefSize(107,29);
        saveBtn.setFont(new Font("System", 13));
        VBox.setMargin(confFld, new Insets(13,0,0,0));
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                errPass.setTextFill(Color.TRANSPARENT);
                errPass.setText("These passwords don't correspond");
                if (passFld.getText().equals(confFld.getText())) {
                    errPass.setText("You must not use a password already used");
                    if (true) {
                        stage.setScene(login);
                        stage.show();
                    } else {
                        errPass.setTextFill(Color.RED);
                    }
                } else {
                    errPass.setTextFill(Color.RED);
                }
            }
        });
        datas.getChildren().addAll(arrow,d1,errPass,text1,text,saveBtn);
        datas.setPadding(new Insets(0,36,0,36));
        s2.getChildren().add(datas);
        root.getChildren().addAll(s1,s2);
        
        stage.setScene(saveAccount);
        stage.setTitle("Save account");
        stage.show();
    }

    
    public void setScenes(Scene login, Scene saveAccount) {
        this.login = login;
        this.saveAccount = saveAccount;
    }
    
    public Scene getScene(){
        return newPass;
    }
    
}
