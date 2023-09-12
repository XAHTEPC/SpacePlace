package com.example.spaceplace.View;

import com.example.spaceplace.Front;
import com.example.spaceplace.Logic.Setting;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TypeFront {
    static boolean[] state = new boolean[16];
    static Button[] tick = new Button[16];

    public static Pane getPane() throws FileNotFoundException {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        FileInputStream Url = new FileInputStream("png/type.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        pane.getChildren().add(front);
        int u = 171;
        if(Setting.type==null) {
            for (int i = 0; i < 16; i++)
                state[i] = false;
        }
        else
            state = Setting.type;
        for (int i = 0; i < 16; i++) {
            tick[i] = new Button();
            tick[i].setBackground(null);
            if(state[i])
                tick[i].setStyle("-fx-background-color: rgb(127, 95, 184);");
            else
                tick[i].setStyle("-fx-background-color: transparent;");

            if (i % 2 == 1) {
                tick[i].setLayoutY(u);
                tick[i].setLayoutX(649);
                u += 50;
            } else {
                tick[i].setLayoutY(u);
                tick[i].setLayoutX(149);
            }
            tick[i].setPrefSize(25, 25);
            pane.getChildren().add(tick[i]);
            final int T = i;
            tick[i].setOnAction(t1 -> {
                if (!state[T]) {
                    tick[T].setStyle("-fx-background-color: rgb(127, 95, 184);");
                    state[T] = true;
                } else {
                    tick[T].setStyle("-fx-background-color: transparent;");
                    state[T] = false;
                }
            });
        }
        Button prev = new Button();
        prev.setBackground(null);
        prev.setLayoutY(750);
        prev.setLayoutX(50);
        prev.setPrefSize(130, 25);
        pane.getChildren().add(prev);

        Button next = new Button();
        next.setBackground(null);
        next.setLayoutY(750);
        next.setLayoutX(1027);
        next.setPrefSize(123, 25);
        pane.getChildren().add(next);

        prev.setOnAction(t1 -> {
            Front.root.getChildren().remove(Front.pane);
            try {
                Front.pane = DateFront.getPane();
                Setting.type = null;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Front.root.getChildren().add(Front.pane);
        });

        next.setOnAction(t1 -> {
            Setting.setState(state);
            Front.root.getChildren().remove(Front.pane);
            try {
                Front.pane = PeopleFront.getPane();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Front.root.getChildren().add(Front.pane);
        });
        return pane;
    }
}

