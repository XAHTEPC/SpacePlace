package com.example.spaceplace.View;

import com.example.spaceplace.Front;
import com.example.spaceplace.Logic.Setting;
import com.example.spaceplace.Logic.Toast;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFront {
    public static Date StartDate;
    public static Date EndDate;
    public static String startTime;
    public static String endTime;
    static Button next;
    static Button prev;
    static String[] choise = {
            "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00",
            "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00",
            "22:00", "23:00", "00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00"};

    public static Pane getPane() throws FileNotFoundException {
        Pane pane = new Pane();
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        FileInputStream Url = new FileInputStream("png/date.png");
        Image url = new Image(Url);
        ImageView front = new ImageView(url);
        front.setX(0);
        front.setY(0);
        pane.getChildren().add(front);

        TextField startDate = new TextField();
        startDate.setBackground(null);
        startDate.setLayoutX(320);
        if(Setting.stDate==null)
            startDate.setPromptText("30-01-2020");
        else
            startDate.setText(Setting.stDate);
        startDate.setLayoutY(230);
        startDate.setMaxHeight(60);
        startDate.setMaxWidth(275);
        startDate.setFont(Font.font("Jost", 25));
        pane.getChildren().add(startDate);

        TextField endDate = new TextField();
        endDate.setBackground(null);
        if(Setting.enDate==null)
            endDate.setPromptText("04-02-2020");
        else
            endDate.setText(Setting.enDate);
        endDate.setLayoutX(907);
        endDate.setLayoutY(230);
        endDate.setMaxHeight(60);
        endDate.setMaxWidth(275);
        endDate.setFont(Font.font("Jost", 25));
        pane.getChildren().add(endDate);

        ObservableList<String> type = FXCollections.observableArrayList(choise);
        ComboBox<String> comboBoxStart = new ComboBox<String>(type);
        comboBoxStart.setMaxWidth(275);
        if(Setting.startTime==null)
            comboBoxStart.setValue(choise[19]);
        else
            comboBoxStart.setValue(Setting.startTime);
        comboBoxStart.setMinWidth(275);
        comboBoxStart.setBackground(null);
        comboBoxStart.setLayoutX(310);
        comboBoxStart.setLayoutY(426);
        comboBoxStart.setStyle("-fx-font: 30px \"Serif\";");
        pane.getChildren().add(comboBoxStart);

        ComboBox<String> comboBoxEnd = new ComboBox<String>(type);
        comboBoxEnd.setMaxWidth(275);
        if(Setting.endTime==null)
            comboBoxEnd.setValue(choise[27]);
        else
            comboBoxEnd.setValue(Setting.endTime);
        comboBoxEnd.setMinWidth(275);
        comboBoxEnd.setBackground(null);
        comboBoxEnd.setLayoutX(902);
        comboBoxEnd.setLayoutY(431);
        comboBoxEnd.setStyle("-fx-font: 30px \"Serif\";");
        pane.getChildren().add(comboBoxEnd);

        prev = new Button();
        prev.setBackground(null);
        prev.setLayoutY(750);
        prev.setLayoutX(50);
        prev.setPrefSize(130, 25);
        pane.getChildren().add(prev);

        next = new Button();
        next.setBackground(null);
        next.setLayoutY(750);
        next.setLayoutX(1027);
        next.setPrefSize(123, 25);
        pane.getChildren().add(next);

        prev.setOnAction(t1 -> {
            Front.root.getChildren().remove(Front.pane);
            try {
                Front.pane = StartFront.getStartFront();
                Setting.startDate = null;
                Setting.stDate = null;
                Setting.enDate = null;
                Setting.endTime = null;
                Setting.endDate = null;
                Setting.startTime = null;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Front.root.getChildren().add(Front.pane);
        });

        next.setOnAction(t1 -> {
            String date1 = startDate.getText();
            String date2 = endDate.getText();
            String time1 = comboBoxStart.getSelectionModel().getSelectedItem();
            String time2 = comboBoxEnd.getSelectionModel().getSelectedItem();
            Format formatter = new SimpleDateFormat("dd-MM-yyyy");
            if (!date1.isEmpty() && !date2.isEmpty() && check(date1) && check(date2)) {
                try {
                    StartDate = (Date) formatter.parseObject(date1);
                    EndDate = (Date) formatter.parseObject(date2);
                    Setting.endDate = EndDate;
                    Setting.enDate = date2;
                    Setting.startDate = StartDate;
                    Setting.stDate = date1;
                    Setting.startTime = time1;
                    Setting.endTime = time2;
                    Front.root.getChildren().remove(Front.pane);
                    Front.pane = TypeFront.getPane();
                    Front.root.getChildren().add(Front.pane);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                Toast.show("Неверно введета дата", Front.pane, 200, 500);
            }
        });
        return pane;
    }

    public static boolean check(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        boolean fl = true;
        char[] mas = date.toCharArray();
        if(date.length()!=10)
            return false;
        fl = mas[0]>='0'&&mas[0]<='9'&&mas[1]>='0'&&mas[1]<='9'&&mas[2]=='-'&&
                mas[3]>='0'&&mas[3]<='9'&&mas[4]>='0'&&mas[4]<='9'&&mas[5]=='-'&&
                mas[6]=='2'&&mas[7]=='0'&&mas[8]>='0'&&mas[8]<='9'&&mas[9]>='0'&&mas[9]<='9';
        if(fl) {
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date.trim());
            } catch (ParseException pe) {
                return false;
            }
            System.out.println("ok");
            return true;
        }
        else
            return false;
    }


}
