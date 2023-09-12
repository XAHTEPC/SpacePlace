package com.example.spaceplace.View;

import com.example.spaceplace.Front;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StartFront {
    static Button start;
    public static Pane getStartFront() throws FileNotFoundException {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        FileInputStream Url;
        Url = new FileInputStream("png/start.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        pane.getChildren().add(front);

        start = new Button();
        start.setBackground(null);
        start.setLayoutX(311);
        start.setLayoutY(343);
        start.setPrefSize(579,172);
        start.setOnAction(t->{
            Front.root.getChildren().remove(Front.pane);
            try {
                Front.pane = DateFront.getPane();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Front.root.getChildren().add(Front.pane);
        });
        pane.getChildren().add(start);
        return pane;
    }
}