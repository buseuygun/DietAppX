package com.bsc.gri.dietappx;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddFoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        init();

    }

    public ArrayList<String> addedFood = new ArrayList<>();

    private void init(){

        foodNames.add("ilk");
        foodNames.add("ikinci");
        foodNames.add("üçüncü");
        foodNames.add("dördüncü");
        foodNames.add("beşinci");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, foodNames);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.auto_complete_text_view_foods);
        textView.setAdapter(adapter);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                //Toast.makeText(AddFoodActivity.this, "Done", Toast.LENGTH_LONG).show();
                AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.auto_complete_text_view_foods);
                TextView textViewSelectedFood = (TextView) findViewById(R.id.text_view_selected_food);
                textViewSelectedFood.append(textView.getText());
                addedFood.add(textViewSelectedFood.getText().toString());
                textView.setText("");
                //other logic
            }
        });

    }

    public void Ekle_Click(View view){
        //deney();
//TODO seçilen yemekleri userdata ya al ve xml e kaydet
        Date todaysDate = Calendar.getInstance().getTime();
        NewSaveUserDataAsXML();
        NewReadUserDataFromXML();
    }

    public void deney(){

        ReadFoodDataFromXML();
        Food f1 =new Food();
        TextView textViewFoodEaten = (TextView) findViewById(R.id.text_view_food_eaten);
        textViewFoodEaten.setText(f1.GetFoodListString(foodList2));
    }

    public static List<Food> foodList2 = new ArrayList<>();
    public static UserData userData = new UserData();
    public static ArrayList<String> foodNames = new ArrayList<>();
    final String xmlFile = "user_data";

    public void SaveUserDataAsXML() {

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
    public void NewSaveUserDataAsXML() {


        userData=userData.CreateTestData();
        try {
            FileOutputStream fileos = openFileOutput(xmlFile, Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "userData");

            xmlSerializer.startTag(null, "userName");
            xmlSerializer.text(userData.getUserName());
            xmlSerializer.endTag(null, "userName");
            xmlSerializer.startTag(null, "cinsiyet");
            xmlSerializer.text(userData.getCinsiyet());
            xmlSerializer.endTag(null, "cinsiyet");
            xmlSerializer.startTag(null, "boy");
            xmlSerializer.text(Integer.toString(userData.getBoy()));
            xmlSerializer.endTag(null, "boy");
            xmlSerializer.startTag(null, "kilo");
            xmlSerializer.text(Integer.toString(userData.getKilo()));
            xmlSerializer.endTag(null, "kilo");
            xmlSerializer.startTag(null, "yas");
            xmlSerializer.text(Integer.toString(userData.getYas()));
            xmlSerializer.endTag(null, "yas");

            xmlSerializer.startTag(null, "isDiyabet");
            xmlSerializer.text(Boolean.toString(userData.isDiyabet()));
            xmlSerializer.endTag(null, "isDiyabet");
            xmlSerializer.startTag(null, "isTansiyon");
            xmlSerializer.text(Boolean.toString(userData.isTansiyon()));
            xmlSerializer.endTag(null, "isTansiyon");
            xmlSerializer.startTag(null, "isVegan");
            xmlSerializer.text(Boolean.toString(userData.isVegan()));
            xmlSerializer.endTag(null, "isVegan");
            xmlSerializer.startTag(null, "isVejeteryan");
            xmlSerializer.text(Boolean.toString(userData.isVejeteryan()));
            xmlSerializer.endTag(null, "isVejeteryan");
            xmlSerializer.startTag(null, "isReflu");
            xmlSerializer.text(Boolean.toString(userData.isReflu()));
            xmlSerializer.endTag(null, "isReflu");
            xmlSerializer.startTag(null, "isKolesterol");
            xmlSerializer.text(Boolean.toString(userData.isKolesterol()));
            xmlSerializer.endTag(null, "isKolesterol");
            xmlSerializer.startTag(null, "isColyak");
            xmlSerializer.text(Boolean.toString(userData.isColyak()));
            xmlSerializer.endTag(null, "isColyak");
            xmlSerializer.startTag(null, "isGastrit");
            xmlSerializer.text(Boolean.toString(userData.isGastrit()));
            xmlSerializer.endTag(null, "isGastrit");

            xmlSerializer.startTag(null, "alerjiler");
            for (String alerji: userData.getAlerjilerList()) {
                xmlSerializer.startTag(null, "alerji");
                xmlSerializer.text(alerji);
                xmlSerializer.endTag(null, "alerji");
            }
            xmlSerializer.endTag(null, "alerjiler");

            xmlSerializer.startTag(null, "toplamFood");
            xmlSerializer.startTag(null, "calories");
            xmlSerializer.text(Integer.toString(userData.getGunlukToplamFood().getCalories()));
            xmlSerializer.endTag(null, "calories");
            xmlSerializer.startTag(null, "besinDegerleri");
            xmlSerializer.startTag(null, "karbonhidrat");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getKarbonhidrat()));
            xmlSerializer.endTag(null, "karbonhidrat");
            xmlSerializer.startTag(null, "protein");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getProtein()));
            xmlSerializer.endTag(null, "protein");
            xmlSerializer.startTag(null, "yag");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getYag()));
            xmlSerializer.endTag(null, "yag");
            xmlSerializer.startTag(null, "lif");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getLif()));
            xmlSerializer.endTag(null, "lif");
            xmlSerializer.startTag(null, "kolesterol");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getKolesterol()));
            xmlSerializer.endTag(null, "kolesterol");
            xmlSerializer.startTag(null, "sodyum");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getSodyum()));
            xmlSerializer.endTag(null, "sodyum");
            xmlSerializer.startTag(null, "potasyum");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getPotasyum()));
            xmlSerializer.endTag(null, "potasyum");
            xmlSerializer.startTag(null, "demir");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getDemir()));
            xmlSerializer.endTag(null, "demir");
            xmlSerializer.endTag(null, "besinDegerleri");
            xmlSerializer.endTag(null, "toplamFood");

            xmlSerializer.startTag(null, "gunlukToplamFood");
            xmlSerializer.startTag(null, "calories");
            xmlSerializer.text(Integer.toString(userData.getGunlukToplamFood().getCalories()));
            xmlSerializer.endTag(null, "calories");
            xmlSerializer.startTag(null, "besinDegerleri");
            xmlSerializer.startTag(null, "karbonhidrat");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getKarbonhidrat()));
            xmlSerializer.endTag(null, "karbonhidrat");
            xmlSerializer.startTag(null, "protein");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getProtein()));
            xmlSerializer.endTag(null, "protein");
            xmlSerializer.startTag(null, "yag");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getYag()));
            xmlSerializer.endTag(null, "yag");
            xmlSerializer.startTag(null, "lif");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getLif()));
            xmlSerializer.endTag(null, "lif");
            xmlSerializer.startTag(null, "kolesterol");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getKolesterol()));
            xmlSerializer.endTag(null, "kolesterol");
            xmlSerializer.startTag(null, "sodyum");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getSodyum()));
            xmlSerializer.endTag(null, "sodyum");
            xmlSerializer.startTag(null, "potasyum");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getPotasyum()));
            xmlSerializer.endTag(null, "potasyum");
            xmlSerializer.startTag(null, "demir");
            xmlSerializer.text(Float.toString(userData.getGunlukToplamFood().getDemir()));
            xmlSerializer.endTag(null, "demir");
            xmlSerializer.endTag(null, "besinDegerleri");
            xmlSerializer.endTag(null, "gunlukToplamFood");

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
    public static UserData testUserData = new UserData();

    public void NewReadUserDataFromXML() {

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
                        testUserData.setUserName(xpp.getText());
                    } else if (xpp.getName().equals("cinsiyet")) {
                        xpp.next();
                        testUserData.setCinsiyet(xpp.getText());
                    } else if (xpp.getName().equals("boy")) {
                        xpp.next();
                        testUserData.setBoy(Integer.parseInt(xpp.getText()));
                    } else if (xpp.getName().equals("kilo")) {
                        xpp.next();
                        testUserData.setKilo(Integer.parseInt(xpp.getText()));
                    } else if (xpp.getName().equals("yas")) {
                        xpp.next();
                        testUserData.setYas(Integer.parseInt(xpp.getText()));
                    } else if (xpp.getName().equals("isDiyabet")) {
                        xpp.next();
                        testUserData.setDiyabet(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("isTansiyon")) {
                        xpp.next();
                        testUserData.setTansiyon(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("isVegan")) {
                        xpp.next();
                        testUserData.setVegan(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("isVejeteryan")) {
                        xpp.next();
                        testUserData.setVejeteryan(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("isReflu")) {
                        xpp.next();
                        testUserData.setTansiyon(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("isKolesterol")) {
                        xpp.next();
                        testUserData.setTansiyon(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("isColyak")) {
                        xpp.next();
                        testUserData.setTansiyon(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("isGastrit")) {
                        xpp.next();
                        testUserData.setTansiyon(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("toplamFood")) {
                        while (!(xpp.getName().equals("toplamFood")) && xpp.getEventType() != XmlPullParser.END_TAG) {
                            if (xpp.getEventType() == XmlPullParser.START_TAG) {
                                if(xpp.getName().equals("calories")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("karbonhidrat")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("protein")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("yag")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("lif")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("kolesterol")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("sodyum")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("potasyum")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("demir")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }
                            }
                            xpp.next();
                        }
                    } else if (xpp.getName().equals("gunlukToplamFood")) {
                        while (!(xpp.getName().equals("gunlukToplamFood")) && xpp.getEventType() != XmlPullParser.END_TAG) {
                            if (xpp.getEventType() == XmlPullParser.START_TAG) {
                                if(xpp.getName().equals("calories")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("karbonhidrat")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("protein")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("yag")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("lif")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("kolesterol")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("sodyum")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("potasyum")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("demir")){
                                    xpp.next();
                                    testUserData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }
                            }
                            xpp.next();
                        }
                    } else if (xpp.getName().equals("alerjiler")) {
                        while (!(xpp.getName().equals("alerjiler")) && xpp.getEventType() != XmlPullParser.END_TAG) {
                            if (xpp.getEventType() == XmlPullParser.START_TAG) {
                                if (xpp.getName().equals("alerji"))
                                xpp.next();
                                localAlerjiList.add(xpp.getText());
                            }
                            xpp.next();
                        }
                    }
                }
                xpp.next();
            }
            testUserData.setAlerjilerList(localAlerjiList);
        } catch (Exception e) {
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
