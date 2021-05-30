/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 *
 * @author DiscoSales
 */
public class Upload {
    private Stage stage;
    private Scene upload;
    private StackPane middle;

    public Upload(Stage stage) {
        this.stage = stage;
        start();
        upload = new Scene(middle);
    }
    
    private void start() {
        middle = new StackPane();
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
        
        
        stage.setTitle("DiscoSales - Updating...");
        stage.setScene(upload);
    }
    
    public Scene getScene(){
        return upload;
    }
}
