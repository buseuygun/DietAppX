package com.bsc.gri.dietappx;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Xml;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.List;

public class RealMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(RealMainActivity.this, AddFoodActivity.class);
                startActivity(i);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        onStartOfApp();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.real_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_diyetim) {
            Intent i = new Intent(RealMainActivity.this, DiyetimActivity.class);
                startActivity(i);
        } else if (id == R.id.nav_add_food) {
            Intent i = new Intent(RealMainActivity.this, AddFoodActivity.class);
            startActivity(i);
        } else if(id== R.id.nav_istatistikler){
            //Intent i = new Intent(RealMainActivity.this, KaloriHesaplamaIlkSayfa.class);
            //startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static List<Food> foodList2 = new ArrayList<>();
    public static UserData userData = new UserData();
    public static ArrayList<String> ingredientNames = new ArrayList<>();
    final String xmlFile = "user_data";


    public void onStartOfApp(){
        ReadFoodDataFromXML();
        try{

            if(userData.getUserName().equals("degisti")) {
                userData.setUserName("saved");
                SaveUserDataAsXML();

            }
            else {
                ReadUserDataFromXML();
            }

        }catch(Exception e)
        {
            //do nothing
        }
        try{
           Food f = new Food();
            ingredientNames=f.GetAllIngredientsList(foodList2);
        }catch(Exception e)
        {
            //do nothing
        }
    }

    public void deney(){

        ReadFoodDataFromXML();
        Food f1 =new Food();
        TextView textViewFoodEaten = (TextView) findViewById(R.id.text_view_food_eaten);
        textViewFoodEaten.setText(f1.GetFoodListString(foodList2));
    }




    public void SaveUserDataAsXML() {

        //userData= new UserData();
        //userData=userData.CreateTestData();
        try {
            FileOutputStream fileos = openFileOutput(xmlFile, Context.MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "userData");

            xmlSerializer.startTag(null, "userName");
            String s2 = userData.getUserName();
            if(s2!=null)
            xmlSerializer.text(s2);
            xmlSerializer.endTag(null, "userName");

            xmlSerializer.startTag(null, "cinsiyet");
            String s1= userData.getCinsiyet();
            if(s1!=null)
            xmlSerializer.text(s1);
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

            //TODO gunluktoplam yerine toplam food olacak
            xmlSerializer.startTag(null, "toplamFood");
            xmlSerializer.startTag(null, "calories");
            xmlSerializer.text(Integer.toString(userData.getToplamFood().getCalories()));
            xmlSerializer.endTag(null, "calories");
            xmlSerializer.startTag(null, "besinDegerleri");
            xmlSerializer.startTag(null, "karbonhidrat");
            xmlSerializer.text(Float.toString(userData.getToplamFood().getKarbonhidrat()));
            xmlSerializer.endTag(null, "karbonhidrat");
            xmlSerializer.startTag(null, "protein");
            xmlSerializer.text(Float.toString(userData.getToplamFood().getProtein()));
            xmlSerializer.endTag(null, "protein");
            xmlSerializer.startTag(null, "yag");
            xmlSerializer.text(Float.toString(userData.getToplamFood().getYag()));
            xmlSerializer.endTag(null, "yag");
            xmlSerializer.startTag(null, "lif");
            xmlSerializer.text(Float.toString(userData.getToplamFood().getLif()));
            xmlSerializer.endTag(null, "lif");
            xmlSerializer.startTag(null, "kolesterol");
            xmlSerializer.text(Float.toString(userData.getToplamFood().getKolesterol()));
            xmlSerializer.endTag(null, "kolesterol");
            xmlSerializer.startTag(null, "sodyum");
            xmlSerializer.text(Float.toString(userData.getToplamFood().getSodyum()));
            xmlSerializer.endTag(null, "sodyum");
            xmlSerializer.startTag(null, "potasyum");
            xmlSerializer.text(Float.toString(userData.getToplamFood().getPotasyum()));
            xmlSerializer.endTag(null, "potasyum");
            xmlSerializer.startTag(null, "demir");
            xmlSerializer.text(Float.toString(userData.getToplamFood().getDemir()));
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
                    } else if (xpp.getName().equals("isReflu")) {
                        xpp.next();
                        userData.setTansiyon(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("isKolesterol")) {
                        xpp.next();
                        userData.setTansiyon(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("isColyak")) {
                        xpp.next();
                        userData.setTansiyon(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("isGastrit")) {
                        xpp.next();
                        userData.setTansiyon(Boolean.parseBoolean(xpp.getText()));
                    } else if (xpp.getName().equals("toplamFood")) {
                        while (!(xpp.getName().equals("toplamFood")) && xpp.getEventType() != XmlPullParser.END_TAG) {
                            if (xpp.getEventType() == XmlPullParser.START_TAG) {
                                if(xpp.getName().equals("calories")){
                                    xpp.next();
                                    userData.getToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("karbonhidrat")){
                                    xpp.next();
                                    userData.getToplamFood().setKarbonhidrat(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("protein")){
                                    xpp.next();
                                    userData.getToplamFood().setProtein(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("yag")){
                                    xpp.next();
                                    userData.getToplamFood().setYag(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("lif")){
                                    xpp.next();
                                    userData.getToplamFood().setLif(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("kolesterol")){
                                    xpp.next();
                                    userData.getToplamFood().setKolesterol(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("sodyum")){
                                    xpp.next();
                                    userData.getToplamFood().setSodyum(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("potasyum")){
                                    xpp.next();
                                    userData.getToplamFood().setPotasyum(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("demir")){
                                    xpp.next();
                                    userData.getToplamFood().setDemir(Integer.parseInt(xpp.getText()));
                                }
                            }
                            xpp.next();
                        }
                    } else if (xpp.getName().equals("gunlukToplamFood")) {
                        while (!(xpp.getName().equals("gunlukToplamFood")) && xpp.getEventType() != XmlPullParser.END_TAG) {
                            if (xpp.getEventType() == XmlPullParser.START_TAG) {
                                if(xpp.getName().equals("calories")){
                                    xpp.next();
                                    userData.getGunlukToplamFood().setCalories(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("karbonhidrat")){
                                    xpp.next();
                                    userData.getGunlukToplamFood().setKarbonhidrat(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("protein")){
                                    xpp.next();
                                    userData.getGunlukToplamFood().setProtein(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("yag")){
                                    xpp.next();
                                    userData.getGunlukToplamFood().setYag(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("lif")){
                                    xpp.next();
                                    userData.getGunlukToplamFood().setLif(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("kolesterol")){
                                    xpp.next();
                                    userData.getGunlukToplamFood().setKolesterol(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("sodyum")){
                                    xpp.next();
                                    userData.getGunlukToplamFood().setSodyum(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("potasyum")){
                                    xpp.next();
                                    userData.getGunlukToplamFood().setPotasyum(Integer.parseInt(xpp.getText()));
                                }else if(xpp.getName().equals("demir")){
                                    xpp.next();
                                    userData.getGunlukToplamFood().setDemir(Integer.parseInt(xpp.getText()));
                                }
                            }
                            xpp.next();
                        }
                    } else if (xpp.getName().equals("alerjiler")) {
                        xpp.next();
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


}

