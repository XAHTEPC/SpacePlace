package com.example.spaceplace.parseLoft;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.example.spaceplace.Front;
import com.example.spaceplace.View.ResFront;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parse {
    static String url;
    static Document Doc;
    public Parse(String url) throws IOException {
        this.url = url;
        this.Doc = Jsoup.connect(url).get();
        System.out.println("url: "+url);
        //System.out.println(Doc);
    }
    public static Pane getParse() throws IOException {
        Front.root.getChildren().remove(Front.pane);
        Front.pane = ResFront.getPane();
        Front.root.getChildren().add(Front.pane);

        Pane res = new Pane();
        Elements all = Doc.getElementsByAttributeValue("class", "col-12 col-sm-6");
        int u = 30;
        for (Element el : all) {
            Elements title = el.getElementsByAttributeValue("class", "card-title");
            Elements info = el.getElementsByAttributeValue("class", "card-data d-column");
            Elements metro = el.getElementsByAttributeValue("class","text-blue");
            Elements path = el.getElementsByAttributeValue("class", "card-body");
            Elements png = el.getElementsByAttributeValue("class","carousel-item active");
            String absoluteUrl;
            Element imageElement = png.select("img").first();
            if(imageElement==null) {
               continue;
            }
            absoluteUrl = imageElement.absUrl("data-src");
            String moreInf;
            Element rent = path.select("a").first();
            moreInf = rent.absUrl("href");
            System.out.println(moreInf);
            System.out.println("title: " + title.text());
            System.out.println("info: " + info.text());
            System.out.println("metro: " + metro.text());
            System.out.println("path: " + path.text());
            System.out.println("png:"+absoluteUrl);

            Text title_text = new Text();
            title_text.setText(title.text());
            title_text.setLayoutY(u);
            title_text.setLayoutX(50);
            title_text.setFont(Font.font("Jost", 20));
            title_text.setFill(Color.rgb(127,95,184));
            title_text.setStyle("-fx-font-weight: bold;");
            res.getChildren().add(title_text);

            FileInputStream Url1 = new FileInputStream("png/rent.png");
            Image url1 = new Image(Url1);
            ImageView pen = new ImageView(url1);

            Button get = new Button();
            get.setGraphic(pen);
            get.setStyle("-fx-background-color: transparent;");
            get.setLayoutY(u+324);
            get.setLayoutX(681);
            res.getChildren().add(get);
            get.setOnAction(value ->{
                try {
                    //URL url = new URL(moreInf);
                    openWebpage(moreInf);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            InputStream is = null;
            ImageView photo = new ImageView();
            String fileUrl = absoluteUrl;
            try {
                URL url = new URL(fileUrl);
                URLConnection connection = url.openConnection();
                is = connection.getInputStream();
                photo = new ImageView(new Image(is));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //photo.resize(300,200);
            photo.setLayoutX(50);
            photo.setLayoutY(u+30);
            res.getChildren().add(photo);

            Text inf = new Text();
            inf.setText("Информация: "+ info.text());
            inf.setLayoutX(700);
            inf.setLayoutY(u+250);
            inf.setFont(Font.font("Jost", 20));
            inf.setFill(Color.rgb(127,95,184));
            inf.setStyle("-fx-background-color: transparent;");
            res.getChildren().add(inf);

            Text metr = new Text();
            metr.setText("Метро: "+ metro.text());
            metr.setLayoutX(700);
            metr.setLayoutY(u+175);
            metr.setFill(Color.rgb(127,95,184));
            metr.setFont(Font.font("Jost", 20));

            res.getChildren().add(metr);
            //res.setStyle("-fx-background-color: transparent;");
            ResFront.scrollPane.setContent(res);
            u+=500;

        }
        return res;
    }

    public static void openWebpage(String urlString) throws IOException {
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(urlString));
    }

}
