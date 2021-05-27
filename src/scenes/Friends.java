/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scenes;

import java.util.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.Global.Infinity;

/**
 *
 * @author manue
 */
public class Friends {
    private Stage stage;
    private HashMap<String, Scene> friendChats;
    private HashMap<String, Scene> chatRooms;
    private Scene main;
    private BorderPane root;
    
    
    public Friends(Stage stage){
        this.stage = stage;
        start();
        main = new Scene(root);
    }
    
    private void start() {
        root = new BorderPane();
        root.setPrefSize(600.0,400.0);
        root.setSnapToPixel(true);
        root.setPrefSize(600, 400);
        root.setMinSize(-Infinity, -Infinity);
        root.setMinSize(-Infinity, -Infinity);
        
        /**
         * The left-hand side of BorderPane is dedicated for the "Main menu" item.
         * 
         */
        ScrollPane scrollBar = new ScrollPane();
        scrollBar.setPrefSize(81,400);
        scrollBar.setMaxWidth(81);
        scrollBar.setPrefSize(81,400);
        scrollBar.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollBar.setFitToHeight(true);
        scrollBar.setFitToWidth(true);
        scrollBar.setCursor(Cursor.V_RESIZE);
        scrollBar.setStyle("-fx-background-color: transparent;");
        BorderPane.setAlignment(scrollBar, Pos.CENTER);
        
        ToolBar tools = new ToolBar();
        tools.setOrientation(Orientation.VERTICAL);
        tools.setPrefSize(82,400);
        tools.setStyle("-fx-background-color: transparent;");
        
        VBox items = new VBox();
        items.setAlignment(Pos.TOP_CENTER);
        items.setPrefWidth(60);
        items.setSpacing(6);
        items.setStyle("-fx-background-color: transparent;");
        items.setPadding(new Insets(7,0,7,0));
        //Content of items
        //Contains the menu tools of the interface
        Rectangle home = new Rectangle(40,40);
        home.setFill(Color.TRANSPARENT);
        home.setArcHeight(17);
        home.setArcWidth(17);
        home.setStroke(Color.BLACK);
        home.setStrokeWidth(1);
        home.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                System.out.print("Home");
            }
        });
        items.getChildren().add(home);
        Separator s = new Separator(Orientation.HORIZONTAL);
        s.setOpacity(0.42);
        s.setPrefWidth(20);
        items.getChildren().add(s);
        int mainTools = 0;
        ArrayList<Rectangle> chats = new ArrayList(mainTools);
        for(int i = 0; i < mainTools; i++){
                Rectangle chat = new Rectangle(40,40);
                chat.setFill(Color.TRANSPARENT);
                chat.setArcHeight(17);
                chat.setArcWidth(17);
                chat.setStroke(Color.BLACK);
                chat.setStrokeWidth(1);
                chat.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event){
                        
                    }
                });
                chats.add(chat);
                items.getChildren().add(chat);
        }
        Circle search = new Circle(20);
        search.setFill(Color.web("#1f93ff00"));
        search.setStroke(Color.BLACK);
        search.setStrokeWidth(1);
        items.getChildren().add(search);
        
        Circle newRoom = new Circle(20);
        newRoom.setFill(Color.web("#1f93ff00"));
        newRoom.setStroke(Color.BLACK);
        newRoom.setStrokeWidth(1);
        items.getChildren().add(newRoom);
        
        tools.getItems().add(items);
        scrollBar.setContent(tools);
        root.setLeft(scrollBar);
        
        /*
         * Part of the center of the interface
         * It gets
        */
        
        HBox center = new HBox();
        center.setPrefSize(171,400);
        center.setMaxWidth(700);
        BorderPane.setAlignment(center, Pos.CENTER);
        //
        VBox directs = new VBox();
        directs.setMaxWidth(700);
        directs.setPrefSize(171,400);
        //
        AnchorPane friends = new AnchorPane();
        friends.setPrefSize(200, 60);
        friends.setMaxSize(300, 60);
        friends.setMinSize(171, 60);
        Circle im = new Circle(20);
        im.setLayoutX(34);
        im.setLayoutY(30);
        im.setFill(Color.TRANSPARENT);
        im.setStroke(Color.BLACK);
        Label frLbl = new Label("Friends");
        frLbl.setFont(new Font("System Bold Italic", 16));
        Label nexts = new Label("Direct chats");
        nexts.setFont(new Font("System Bold",13));
        nexts.setPadding(new Insets(3,0,3,7));
        friends.getChildren().addAll(im,frLbl);
        ArrayList<AnchorPane> chatDs = new ArrayList(0); 
        int directchats = 0;
        for (int i = 0; i < directchats; i++) {
            AnchorPane chatD = new AnchorPane();
            chatD.setLayoutX(10);
            chatD.setLayoutY(10);
            chatD.setMaxSize(300, 60);
            chatD.setMinSize(171, 60);
            chatD.setPrefSize(200, 60);
            chatDs.add(chatD);
        }
        
        center.getChildren().add(directs);
        root.setCenter(center);
        
        /**
         * 
         */
        
        ToolBar settings = new ToolBar();
        settings.setMaxSize(85, 85);
        settings.setPrefSize(85, 40);
        BorderPane.setAlignment(settings, Pos.CENTER);
        settings.setOrientation(Orientation.VERTICAL);
        
        VBox set = new VBox(2);
        set.setPadding(new Insets(7,0,7,0));
        Circle setProfile = new Circle(20);
        setProfile.setFill(Color.web("#1f93ff00"));
        setProfile.setStroke(Color.BLACK);
        setProfile.setOnMouseClicked(null);
        Circle setRoom = new Circle(20);
        setRoom.setFill(Color.web("#1f93ff00"));
        setRoom.setLayoutX(32);
        setRoom.setLayoutY(61);
        set.getChildren().addAll(setProfile,setRoom);
        root.setRight(settings);
        
        stage.setTitle("Discord");
        stage.setScene(main);
    }
    
    public void setScenes(HashMap<String,Scene> friendChats, HashMap<String,Scene> chatRooms){
        this.friendChats = friendChats;
        this.chatRooms = chatRooms;
    }
    
    public Scene getScene(){
        return main;
    }
}
