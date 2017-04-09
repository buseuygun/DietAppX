package com.bsc.gri.dietappx;

import java.util.ArrayList;

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

    public String WriteFood(Food food) {

        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append(food.getName() + "\n" + "Kalori:" + food.getCalories() + "\n" +
                "Karbonhidrat:" + food.getKarbonhidrat() + "\n" + "Protein:" + food.getProtein() + "\n" +
                "Yağ: " + food.getYag() + "\n" + "Lif: " + food.getLif() + "\n" + "Kolesterol: " +
                food.getKolesterol() + "\n" + "Sodyum: " + food.getSodyum() + "\n" + "Potasyum: " +
                food.getPotasyum() + "\n" + "Demir: " + food.getDemir() + "\n");

        ArrayList<String> ing = food.getIngredients();
        if(ing != null) {
            for (String in : ing) {
                stringBuilder.append(in + "\n");
            }
        }
        if(food.isVegan())
            stringBuilder.append("Vegan");

        return stringBuilder.toString();
    }



}
