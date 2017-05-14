package com.bsc.gri.dietappx;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static List<Food> foodList = new ArrayList<>();
    public static List<Food> foodList2 = new ArrayList<>();
    public static UserData userData = new UserData();
    public static ArrayList<String> foodNames = new ArrayList<>();
    public static ArrayList<Food> selectedFoods = new ArrayList<>();
    final String xmlFile = "userData";

    public void SaveUserDataAsXML() {

        //XmlPullParser xpp = getResources().getXml(R.xml.food_db);


        String userNAme = "username";
        String password = "password";
        try {
            FileOutputStream fileos = openFileOutput(xmlFile, Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "userData");
            xmlSerializer.startTag(null, "userName");
            xmlSerializer.text("isim");
            xmlSerializer.endTag(null, "userName");
            xmlSerializer.startTag(null, "cinsiyet");
            xmlSerializer.text("kadın");
            xmlSerializer.endTag(null, "cinsiyet");
            xmlSerializer.startTag(null, "boy");
            xmlSerializer.text("190");
            xmlSerializer.endTag(null, "boy");
            xmlSerializer.startTag(null, "kilo");
            xmlSerializer.text("70");
            xmlSerializer.endTag(null, "kilo");
            xmlSerializer.startTag(null, "yas");
            xmlSerializer.text("30");
            xmlSerializer.endTag(null, "yas");
            xmlSerializer.startTag(null, "hastaliklar");
            xmlSerializer.startTag(null, "isDiyabet");
            xmlSerializer.text("1");
            xmlSerializer.endTag(null, "isDiyabet");
            xmlSerializer.startTag(null, "isTansiyon");
            xmlSerializer.text("0");
            xmlSerializer.endTag(null, "isTansiyon");
            xmlSerializer.endTag(null, "hastaliklar");
         /*   xmlSerializer.startTag(null, "alerjiler");
            xmlSerializer.startTag(null, "fistik");
            xmlSerializer.text("1");
            xmlSerializer.endTag(null, "fıstık");
            xmlSerializer.endTag(null, "alerjiler");*/
            xmlSerializer.startTag(null, "diyetler");
            xmlSerializer.startTag(null, "vejeteryan");
            xmlSerializer.text("1");
            xmlSerializer.endTag(null, "vejeteryan");
            xmlSerializer.endTag(null, "diyetler");
            xmlSerializer.endTag(null, "userData");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();
            fileos.write(dataWrite.getBytes());
            fileos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void ReadUserDataFromXML() {

        //TextView textView = (TextView) findViewById(R.id.text_view_dailyTotal);
        ArrayList<String> localAlerjiList = new ArrayList<>();

        try {
            FileInputStream fis = getApplicationContext().openFileInput(xmlFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);

            String receiveString;
            StringBuilder stringBuilder = new StringBuilder();

            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString);
            }

            fis.close();

            String ret = stringBuilder.toString();
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            //textView.append(ret + "\n");

            xpp.setInput(new StringReader(ret));
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("userName")) {
                        xpp.next();
                        userData.setUserName(xpp.getText());
                    } else if (xpp.getName().equals("cinsiyet")) {
                        xpp.next();
                        userData.setCinsiyet(xpp.getText());
                    } else if (xpp.getName().equals("boy")) {
                        xpp.next();
                        userData.setBoy(Integer.parseInt(xpp.getText()));
                    } else if (xpp.getName().equals("kilo")) {
                        xpp.next();
                        userData.setKilo(Integer.parseInt(xpp.getText()));
                    } else if (xpp.getName().equals("yas")) {
                        xpp.next();
                        userData.setYas(Integer.parseInt(xpp.getText()));
                    } else if (xpp.getName().equals("isDiyabet")) {
                        xpp.next();
                        userData.setDiyabet(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("isTansiyon")) {
                        xpp.next();
                        userData.setTansiyon(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("isVegan")) {
                        xpp.next();
                        userData.setVegan(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("isVejeteryan")) {
                        xpp.next();
                        userData.setVejeteryan(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("alerjiler")) {
                        while (!(xpp.getName().equals("alerjiler")) && xpp.getEventType() != XmlPullParser.END_TAG) {
                            if (xpp.getEventType() == XmlPullParser.START_TAG) {
                                localAlerjiList.add(xpp.getName());
                            }
                            xpp.next();
                        }
                    }
                }
                xpp.next();
            }
            userData.setAlerjilerList(localAlerjiList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ReadFoodDataFromXML() {
        XmlPullParser xpp = getResources().getXml(R.xml.food_db);
        //TextView textView = (TextView) findViewById(R.id.text_view_dailyTotal);
        Food food = new Food();
        ArrayList<String> ingredients = new ArrayList<>();
        ArrayList<String> tagsList = new ArrayList<>();
        //String gname;


        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("name")) {
                        xpp.next();
                        food.setName(xpp.getText());
                    } else if (xpp.getName().equals("calories")) {
                        xpp.next();
                        food.setCalories(Integer.parseInt(xpp.getText()));
                    } else if (xpp.getName().equals("karbonhidrat")) {
                        xpp.next();
                        food.setKarbonhidrat(Float.parseFloat(xpp.getText()));
                    } else if (xpp.getName().equals("protein")) {
                        xpp.next();
                        food.setProtein(Float.parseFloat(xpp.getText()));
                    } else if (xpp.getName().equals("yag")) {
                        xpp.next();
                        food.setYag(Float.parseFloat(xpp.getText()));
                    } else if (xpp.getName().equals("lif")) {
                        xpp.next();
                        food.setLif(Float.parseFloat(xpp.getText()));
                    } else if (xpp.getName().equals("kolesterol")) {
                        xpp.next();
                        food.setKolesterol(Float.parseFloat(xpp.getText()));
                    } else if (xpp.getName().equals("sodyum")) {
                        xpp.next();
                        food.setSodyum(Float.parseFloat(xpp.getText()));
                    } else if (xpp.getName().equals("potasyum")) {
                        xpp.next();
                        food.setPotasyum(Float.parseFloat(xpp.getText()));
                    } else if (xpp.getName().equals("demir")) {
                        xpp.next();
                        food.setDemir(Float.parseFloat(xpp.getText()));
                    } else if (xpp.getName().equals("isVegan")) {
                        xpp.next();
                        food.setVegan(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("ingredients")) {
                        xpp.next();
                        while (!(xpp.getName().equals("ingredients"))) {
                            if (xpp.getEventType() == XmlPullParser.START_TAG) {
                                if (xpp.getName().equals("ingredient")) {
                                    xpp.next();
                                    ingredients.add(xpp.getText());
                                }
                            }
                            xpp.next();
                        }
                        food.setIngredients(ingredients);
                        ingredients = new ArrayList<>();
                    } else if (xpp.getName().equals("tags")) {
                        xpp.next();
                        while (!(xpp.getName().equals("tags"))) {
                            if (xpp.getEventType() == XmlPullParser.START_TAG) {
                                if (xpp.getName().equals("tag")) {
                                    xpp.next();
                                    tagsList.add(xpp.getText());
                                }
                            }
                            xpp.next();
                        }
                        food.setTags(tagsList);
                        tagsList = new ArrayList<>();
                    }
                }
                xpp.next();
                if (xpp.getEventType() == XmlPullParser.END_TAG) {
                    if (xpp.getName().equals("food")) {
                        foodList2.add(food);
                        food = new Food();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void StartUserProfile(View view){

        SaveUserDataAsXML();
        ReadFoodDataFromXML();
        ReadUserDataFromXML();
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }
}
