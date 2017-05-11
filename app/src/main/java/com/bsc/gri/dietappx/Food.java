package com.bsc.gri.dietappx;

import android.support.v7.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by gri on 08.04.2017.
 */

public class Food {
    private String name;
    private int calories;
    private float karbonhidrat;
    private float protein;
    private float yag;
    private float lif;
    private float kolesterol;
    private float sodyum;
    private float potasyum;
    private float demir;
    private boolean isVegan;
    private ArrayList<String> ingredients = new ArrayList<>();
    private ArrayList<String> tags = new ArrayList<>();
    private Date yenmeTarihi = new Date();

    public Date getYenmeTarihi() {
        return yenmeTarihi;
    }

    public void setYenmeTarihi(Date yenmeTarihi) {
        this.yenmeTarihi = yenmeTarihi;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public Food() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public float getKarbonhidrat() {
        return karbonhidrat;
    }

    public void setKarbonhidrat(float karbonhidrat) {
        this.karbonhidrat = karbonhidrat;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getYag() {
        return yag;
    }

    public void setYag(float yag) {
        this.yag = yag;
    }

    public float getLif() {
        return lif;
    }

    public void setLif(float lif) {
        this.lif = lif;
    }

    public float getKolesterol() {
        return kolesterol;
    }

    public void setKolesterol(float kolesterol) {
        this.kolesterol = kolesterol;
    }

    public float getSodyum() {
        return sodyum;
    }

    public void setSodyum(float sodyum) {
        this.sodyum = sodyum;
    }

    public float getPotasyum() {
        return potasyum;
    }

    public void setPotasyum(float potasyum) {
        this.potasyum = potasyum;
    }

    public float getDemir() {
        return demir;
    }

    public void setDemir(float demir) {
        this.demir = demir;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public String WriteFood() {

        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append(this.getName() + "\n" + "Kalori:" + this.getCalories() + "\n" +
                "Karbonhidrat:" + this.getKarbonhidrat() + "\n" + "Protein:" + this.getProtein() + "\n" +
                "Yağ: " + this.getYag() + "\n" + "Lif: " + this.getLif() + "\n" + "Kolesterol: " +
                this.getKolesterol() + "\n" /*+ "Sodyum: " + this.getSodyum() + "\n" + "Potasyum: " +
                this.getPotasyum() + "\n" + "Demir: " + this.getDemir() + "\n"*/);

       /* ArrayList<String> tgs = this.getTags();
        if (tgs != null) {
            for (String in : tgs) {
                stringBuilder.append(in + "\n");
            }
        }*/


        ArrayList<String> ing = this.getIngredients();
        if(ing != null) {
            stringBuilder.append("\nİçindekiler:" + "\n");
            for (String in : ing) {
                stringBuilder.append(in + "\n");
            }
        }
        /*if(this.isVegan())
            stringBuilder.append("Vegan\n");*/
        stringBuilder.append("__________________\n");

        return stringBuilder.toString();
    }

    public String GetFoodListString(List<Food> fds){

        StringBuilder stringBuilder = new StringBuilder();

        for (Food fd : fds) {
            stringBuilder.append(fd.WriteFood());
        }

        return stringBuilder.toString();
    }

    public ArrayList<String> GetAllIngredientsList(List<Food> fds){
        ArrayList<String> arrayList = new ArrayList<>();

        for (Food fd : fds) {
            for (String ingredient: fd.getIngredients()
                 ) {
                arrayList.add(ingredient);
            }
        }
        return arrayList;
    }

    public ArrayList<String> GetAllFoodNamesList(List<Food> fds){
        ArrayList<String> arrayList = new ArrayList<>();

        for (Food fd : fds) {

                arrayList.add(fd.getName());

        }
        return arrayList;
    }
    public String WriteFoodWitouthIng() {

        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append("Kalori:" + this.getCalories() + "\n" +
                "Karbonhidrat:" + this.getKarbonhidrat() + "\n" + "Protein:" + this.getProtein() + "\n" +
                "Yağ: " + this.getYag() + "\n" + "Lif: " + this.getLif() + "\n" + "Kolesterol: " +
                this.getKolesterol() + "\n" /*+ "Sodyum: " + this.getSodyum() + "\n" + "Potasyum: " +
                this.getPotasyum() + "\n" + "Demir: " + this.getDemir() + "\n"*/);

       /* ArrayList<String> tgs = this.getTags();
        if (tgs != null) {
            for (String in : tgs) {
                stringBuilder.append(in + "\n");
            }
        }*/


        return stringBuilder.toString();
    }


}
