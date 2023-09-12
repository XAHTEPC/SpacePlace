package com.example.spaceplace.View;

import com.example.spaceplace.Front;
import com.example.spaceplace.Logic.Setting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PeopleFront {
    public static Pane getPane() throws FileNotFoundException {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        FileInputStream Url = new FileInputStream("png/people.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        pane.getChildren().add(front);

        TextField from = new TextField();
        from.setMinWidth(275);
        from.setMaxWidth(275);
        from.setMinHeight(60);
        from.setMaxHeight(60);
        from.setLayoutX(688);
        from.setLayoutY(332);
        if(Setting.a==null)
            from.setPromptText("с");
        else
            from.setText(Setting.a);
        from.setFont(Font.font("Jost", 20));
        from.setBackground(null);
        pane.getChildren().add(from);

        TextField to = new TextField();
        to.setPrefSize(400,70);
        to.setBackground(null);
        if(Setting.b==null)
            to.setPromptText("до");
        else
            to.setText(Setting.b);
        to.setLayoutX(688);
        to.setLayoutY(419);
        to.setFont(Font.font("Jost", 20));
        pane.getChildren().add(to);

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
                Front.pane = TypeFront.getPane();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Front.root.getChildren().add(Front.pane);
        });

        next.setOnAction(t1 -> {
            String a = from.getText();
            String b = to.getText();
            if(!a.isEmpty()&&!b.isEmpty()&&check(a)&&check(b)) {
                Setting.a = a;
                Setting.b = b;
                Front.root.getChildren().remove(Front.pane);
                try {
                    Front.pane = PriceFront.getPane();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Front.root.getChildren().add(Front.pane);
            }
        });
        return pane;
    }

    public static boolean check(String kol){
        char[] mas = kol.toCharArray();
        boolean fl = true;
        for(int i=0;i<kol.length();i++){
            if(mas[i]>='0'&&mas[i]<='9')
                continue;
            else
                fl = false;
        }
        return fl;
    }
}
