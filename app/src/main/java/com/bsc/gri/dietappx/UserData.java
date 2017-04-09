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
    private boolean isDiyabet;
    private boolean isTansiyon;
    private boolean isVegan;
    private boolean isVejeteryan;
    private ArrayList<String> alerjilerList = new ArrayList<>();

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
}
