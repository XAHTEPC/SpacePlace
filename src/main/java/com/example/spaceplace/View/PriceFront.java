package com.example.spaceplace.View;

import com.example.spaceplace.Front;
import com.example.spaceplace.Logic.Setting;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PriceFront {
    public static Pane getPane() throws FileNotFoundException {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        FileInputStream Url = new FileInputStream("png/price.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        pane.getChildren().add(front);

        Button price1 = new Button();
        price1.setBackground(null);
        price1.setLayoutY(100);
        price1.setLayoutX(375);
        price1.setPrefSize(450, 90);
        pane.getChildren().add(price1);

        Button price2 = new Button();
        price2.setBackground(null);
        price2.setLayoutY(201);
        price2.setLayoutX(375);
        price2.setPrefSize(450, 90);
        pane.getChildren().add(price2);

        Button price3 = new Button();
        price3.setBackground(null);
        price3.setLayoutY(302);
        price3.setLayoutX(375);
        price3.setPrefSize(450, 90);
        pane.getChildren().add(price3);

        Button price4 = new Button();
        price4.setBackground(null);
        price4.setLayoutY(403);
        price4.setLayoutX(375);
        price4.setPrefSize(450, 90);
        pane.getChildren().add(price4);

        Button price5 = new Button();
        price5.setBackground(null);
        price5.setLayoutY(504);
        price5.setLayoutX(375);
        price5.setPrefSize(450, 90);
        pane.getChildren().add(price5);

        Button prev = new Button();
        prev.setBackground(null);
        prev.setLayoutY(750);
        prev.setLayoutX(50);
        prev.setPrefSize(130, 25);
        pane.getChildren().add(prev);

        prev.setOnAction(t1 -> {
            Front.root.getChildren().remove(Front.pane);
            try {
                Front.pane = PeopleFront.getPane();
                Setting.priceStart=null;
                Setting.priceEnd=null;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Front.root.getChildren().add(Front.pane);
        });

        price1.setOnAction(t1 -> {
                Front.root.getChildren().remove(Front.pane);
                Setting.priceStart = "0";
                Setting.priceEnd = "500";
            try {
                Setting.startParse();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //Front.pane = PriceFront.getPane();
        });
        price2.setOnAction(t1 -> {
            Front.root.getChildren().remove(Front.pane);
            Setting.priceStart = "500";
            Setting.priceEnd = "1000";
            try {
                Setting.startParse();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //Front.pane = PriceFront.getPane();
        });
        price3.setOnAction(t1 -> {
            Front.root.getChildren().remove(Front.pane);
            Setting.priceStart = "1000";
            Setting.priceEnd = "2500";
            try {
                Setting.startParse();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //Front.pane = PriceFront.getPane();
        });
        price4.setOnAction(t1 -> {
            Front.root.getChildren().remove(Front.pane);
            Setting.priceStart = "2500";
            Setting.priceEnd = "10000";
            try {
                Setting.startParse();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //Front.pane = PriceFront.getPane();
        });
        price5.setOnAction(t1 -> {
            Front.root.getChildren().remove(Front.pane);
            Setting.priceStart = "10000";
            try {
                Setting.startParse();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //Front.pane = PriceFront.getPane();
        });

        return pane;
    }

}
