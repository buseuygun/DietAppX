package com.bsc.gri.dietappx;

import java.util.ArrayList;

/**
 * Created by gri on 08.04.2017.
 */

public class UserData {

    private String userName;
    private String cinsiyet;
    private int boy;
    private int kilo;
    private int yas;
    private int hesaplananKalori;
    private boolean isDiyabet;
    private boolean isTansiyon;
    private boolean isVegan;
    private boolean isVejeteryan;
    private boolean isReflu;
    private boolean isKolesterol;
    private boolean isColyak;
    private boolean isGastrit;
    private ArrayList<String> alerjilerList = new ArrayList<>();
    private ArrayList<String> eatenFoodNames = new ArrayList<>();
    private Food toplamFood = new Food();
    private Food gunlukToplamFood = new Food();

    public Food getGunlukToplamFood() {
        return gunlukToplamFood;
    }

    public void setGunlukToplamFood(Food gunlukToplamFood) {
        this.gunlukToplamFood = gunlukToplamFood;
    }

    public int getHesaplananKalori() {
        return hesaplananKalori;
    }

    public void setHesaplananKalori(int hesaplananKalori) {
        this.hesaplananKalori = hesaplananKalori;
    }


    public boolean isGastrit() {
        return isGastrit;
    }

    public void setGastrit(boolean gastrit) {
        isGastrit = gastrit;
    }

    public boolean isReflu() {
        return isReflu;
    }

    public void setReflu(boolean reflu) {
        isReflu = reflu;
    }

    public boolean isKolesterol() {
        return isKolesterol;
    }

    public void setKolesterol(boolean kolesterol) {
        isKolesterol = kolesterol;
    }


    public boolean isColyak() {
        return isColyak;
    }

    public void setColyak(boolean colyak) {
        isColyak = colyak;
    }

    public ArrayList<String> getEatenFoodNames() {
        return eatenFoodNames;
    }

    public void setEatenFoodNames(ArrayList<String> eatenFoodNames) {
        this.eatenFoodNames = eatenFoodNames;
    }



    public Food getToplamFood() {
        return toplamFood;
    }

    public void setToplamFood(Food toplamFood) {
        this.toplamFood = toplamFood;
    }





    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public int getBoy() {
        return boy;
    }

    public void setBoy(int boy) {
        this.boy = boy;
    }

    public int getKilo() {
        return kilo;
    }

    public void setKilo(int kilo) {
        this.kilo = kilo;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }

    public boolean isDiyabet() {
        return isDiyabet;
    }

    public void setDiyabet(boolean diyabet) {
        isDiyabet = diyabet;
    }

    public boolean isTansiyon() {
        return isTansiyon;
    }

    public void setTansiyon(boolean tansiyon) {
        isTansiyon = tansiyon;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public boolean isVejeteryan() {
        return isVejeteryan;
    }

    public void setVejeteryan(boolean vejeteryan) {
        isVejeteryan = vejeteryan;
    }

    public ArrayList<String> getAlerjilerList() {
        return alerjilerList;
    }

    public void setAlerjilerList(ArrayList<String> alerjilerList) {
        this.alerjilerList = alerjilerList;
    }
    public UserData CreateTestData(){
        UserData userData = new UserData();
        ArrayList<String> sList = new ArrayList<>();
        sList.add("elma");
        userData.setCinsiyet("kadın");
        userData.setVejeteryan(true);
        userData.setUserName("buse");
        userData.setBoy(175);
        userData.setYas(21);
        userData.setKilo(60);
        userData.getToplamFood().setCalories(100);
        userData.getToplamFood().setKarbonhidrat(10);
        userData.getToplamFood().setProtein(15);
        userData.getToplamFood().setLif(20);
        userData.getToplamFood().setKolesterol(5);
        userData.getToplamFood().setSodyum(2);
        userData.getToplamFood().setPotasyum(55);
        userData.getToplamFood().setDemir(1);
        userData.getGunlukToplamFood().setCalories(99);
        userData.getGunlukToplamFood().setKarbonhidrat(10);
        userData.getGunlukToplamFood().setProtein(15);
        userData.getGunlukToplamFood().setLif(20);
        userData.getGunlukToplamFood().setKolesterol(5);
        userData.getGunlukToplamFood().setSodyum(2);
        userData.getGunlukToplamFood().setPotasyum(55);
        userData.getGunlukToplamFood().setDemir(1);

        return userData;
    }
}
