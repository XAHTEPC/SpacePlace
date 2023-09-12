package com.example.spaceplace.View;

import com.example.spaceplace.Front;
import com.example.spaceplace.Logic.Setting;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ResFront {

    public static ScrollPane scrollPane = new ScrollPane();
    public static Pane getPane() throws FileNotFoundException {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        FileInputStream Url = new FileInputStream("png/result.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        pane.getChildren().add(front);

        Button prev = new Button();
        prev.setBackground(null);
        prev.setLayoutY(750);
        prev.setLayoutX(50);
        prev.setPrefSize(130, 25);
        pane.getChildren().add(prev);

        Button start = new Button();
        start.setBackground(null);
        start.setLayoutY(708);
        start.setLayoutX(698);
        start.setPrefSize(450, 70);
        pane.getChildren().add(start);

        scrollPane.setLayoutX(50);
        scrollPane.setLayoutY(80);
        scrollPane.setMinHeight(0);
        scrollPane.setMinWidth(1100);
        scrollPane.setMaxHeight(600);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.getChildren().add(scrollPane);

        prev.setOnAction(t1 -> {
            Front.root.getChildren().remove(Front.pane);
            try {
                Front.pane = PriceFront.getPane();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Front.root.getChildren().add(Front.pane);
        });

        start.setOnAction(t1 -> {
            Front.root.getChildren().remove(Front.pane);
            try {
                Setting.priceEnd = null;
                Setting.priceStart = null;
                Setting.stDate = null;
                Setting.enDate = null;
                Setting.b = null;
                Setting.a = null;
                Setting.textType=null;
                Front.pane = StartFront.getStartFront();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Front.root.getChildren().add(Front.pane);
        });


        return pane;
    }
}
