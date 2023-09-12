package com.example.spaceplace;

import com.example.spaceplace.View.StartFront;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;

public class Front extends Application {
    public static Pane pane;
    public static Group root;
    public static Scene scene;
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        root = new Group();
        scene = new Scene(root, 1200, 800);
        stage.initStyle(StageStyle.DECORATED);
        pane = StartFront.getStartFront();
        root.getChildren().add(pane);
        stage.setTitle("Project");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch();
    }
}

