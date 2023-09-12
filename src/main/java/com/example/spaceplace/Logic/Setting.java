package com.example.spaceplace.Logic;

import com.example.spaceplace.parseLoft.Parse;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Date;

public class Setting {
    public static Date startDate =null;
    public static String stDate = null;
    public static Date endDate = null;
    public static String enDate = null;
    public static String startTime = null;
    public static String endTime = null;
    public static boolean[] type = null;
    public static String[] textType ={"208","7023","211","27025","210","1336","218",
            "19978","9570","214","379","217","216","222","209","19649"};
    public static String a = null;
    public static String b = null;
    public static String priceStart = null;
    public static String priceEnd = null;
    public static void setState(boolean[] state) {
        Setting.type = state;
    }
    public static String makeURL(){
        String res="https://www.loft2rent.ru/loft/?city=65322&text=";
        String prStart ="&price_start=" + priceStart;
        String prEnd = "&price_end=" + priceEnd;
        String peopStart = "&people_start=" + a;
        String peopEnd = "&people_end=" + b;
        String t = "&area_start=&area_end=&";
        String y = getType();
        String getDate = getPeriod();
        String a = res+y+prStart+prEnd+peopStart+peopEnd+t+getDate;
        System.out.println(res+y+prStart+prEnd+peopStart+peopEnd+t+getDate);
        return a;
    }
    public static String getPeriod(){
        char[] st = stDate.toCharArray();
        String start="party_date=";
        start=start+st[6]+""+st[7]+""+st[8]+""+st[9]+""+st[2]+""+st[3]+""+st[4]+""+st[2]+""+st[0]+""+st[1];
        char[] sta = startTime.toCharArray();
        start = start +"+"+ sta[0]+sta[1]+"%3A00%3A00%2C";
        st = enDate.toCharArray();
        String end ="";
        end = st[6]+""+st[7]+""+st[8]+""+st[9]+""+st[2]+""+st[3]+""+st[4]+""+st[2]+""+st[0]+""+st[1];
        char[] ends = endTime.toCharArray();
        end = end+"+"+ends[0]+ends[1]+"%3A00%3A00";
        return start+end;
    }
    public static String getType(){
        String res="";
        String ex ="&areatype%5B%5D=";
        for(int i=0;i<16;i++){
            if(type[i]){
                res=res+ex+textType[i];
            }
        }
        return res;
    }
    public static void startParse() throws IOException {
        String url = makeURL();
        Parse parse = new Parse(url);
        Pane pane = Parse.getParse();
    }
}
