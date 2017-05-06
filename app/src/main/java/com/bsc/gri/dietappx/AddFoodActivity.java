package com.bsc.gri.dietappx;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

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
    public ArrayList<Food> addedFoodAsFoodClass = new ArrayList<>();

    protected void init() {


        Food fProvider = new Food();
        ArrayList<String> foodNamesList = fProvider.GetAllFoodNamesList(RealMainActivity.foodList2);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, foodNamesList);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.auto_complete_text_view_foods);
        textView.setAdapter(adapter);

        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.auto_complete_text_view_foods);
                TextView textViewSelectedFood = (TextView) findViewById(R.id.text_view_selected_food);
                Food selectedFood = GetThatFood(textView.getText().toString());
                textViewSelectedFood.append(selectedFood.WriteFood() + "\n");
                //addedFood.add(textViewSelectedFood.getText().toString());
                addedFoodAsFoodClass.add(selectedFood);
                textView.setText("");
            }
        });

    }

    public Food GetThatFood(String thatFood) {

        for (Food food : RealMainActivity.foodList2
                ) {
            if (food.getName().equals(thatFood))
                return food;
        }

        return null;
    }

    public void Ekle_Click(View view) {

        if (!addedFoodAsFoodClass.isEmpty()) {

            //TODO bu kısmı test et ve profilIleKarşılaştır metodunu geliştir. onun içinde tavsiye sistemi olsun.
            //TODO kalori limiti uyarısı yap.
            Date todaysDate = Calendar.getInstance().getTime();

            Food gunlukToplamFood = new Food();

            for (Food ff:addedFoodAsFoodClass
                 ) {
                ff.setYenmeTarihi(todaysDate);

                if(!(gunlukToplamFood.getName().equals("gunlukToplam"))) {
                    gunlukToplamFood = ff;
                    gunlukToplamFood.setName("gunlukToplam");
                }
                else{
                    gunlukToplamFood.setCalories(gunlukToplamFood.getCalories()+ff.getCalories());
                    gunlukToplamFood.setKarbonhidrat(gunlukToplamFood.getKarbonhidrat()+ff.getKarbonhidrat());
                    gunlukToplamFood.setProtein(gunlukToplamFood.getProtein()+ff.getProtein());
                    gunlukToplamFood.setYag(gunlukToplamFood.getYag()+ff.getYag());
                    gunlukToplamFood.setLif(gunlukToplamFood.getLif()+ff.getLif());
                    gunlukToplamFood.setKolesterol(gunlukToplamFood.getKolesterol()+ff.getKolesterol());
                    gunlukToplamFood.setSodyum(gunlukToplamFood.getSodyum()+ff.getSodyum());
                    gunlukToplamFood.setPotasyum(gunlukToplamFood.getPotasyum()+ff.getPotasyum());
                    gunlukToplamFood.setDemir(gunlukToplamFood.getDemir()+ff.getDemir());

                }
            }

            Date oldDate = RealMainActivity.userData.getGunlukToplamFood().getYenmeTarihi()!=null ? RealMainActivity.userData.getGunlukToplamFood().getYenmeTarihi(): null;

            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

            boolean sameDay =true;

            if(oldDate !=null) {
                 sameDay = fmt.format(todaysDate).equals(fmt.format(oldDate));
            }



            if(!sameDay)
            RealMainActivity.userData.setGunlukToplamFood(gunlukToplamFood);
            else
            {
                RealMainActivity.userData.getGunlukToplamFood().setCalories(gunlukToplamFood.getCalories()+RealMainActivity.userData.getGunlukToplamFood().getCalories());
                RealMainActivity.userData.getGunlukToplamFood().setKarbonhidrat(gunlukToplamFood.getKarbonhidrat()+RealMainActivity.userData.getGunlukToplamFood().getKarbonhidrat());
                RealMainActivity.userData.getGunlukToplamFood().setProtein(gunlukToplamFood.getProtein()+RealMainActivity.userData.getGunlukToplamFood().getProtein());
                RealMainActivity.userData.getGunlukToplamFood().setYag(gunlukToplamFood.getYag()+RealMainActivity.userData.getGunlukToplamFood().getYag());
                RealMainActivity.userData.getGunlukToplamFood().setLif(gunlukToplamFood.getLif()+RealMainActivity.userData.getGunlukToplamFood().getLif());
                RealMainActivity.userData.getGunlukToplamFood().setKolesterol(gunlukToplamFood.getKolesterol()+RealMainActivity.userData.getGunlukToplamFood().getKolesterol());
                RealMainActivity.userData.getGunlukToplamFood().setSodyum(gunlukToplamFood.getSodyum()+RealMainActivity.userData.getGunlukToplamFood().getSodyum());
                RealMainActivity.userData.getGunlukToplamFood().setPotasyum(gunlukToplamFood.getPotasyum()+RealMainActivity.userData.getGunlukToplamFood().getPotasyum());
                RealMainActivity.userData.getGunlukToplamFood().setDemir(gunlukToplamFood.getDemir()+RealMainActivity.userData.getGunlukToplamFood().getDemir());

            }

            if(!(RealMainActivity.userData.getToplamFood()==null)){

                RealMainActivity.userData.setToplamFood(gunlukToplamFood);
            }else{
                RealMainActivity.userData.getToplamFood().setCalories(gunlukToplamFood.getCalories()+RealMainActivity.userData.getToplamFood().getCalories());
                RealMainActivity.userData.getToplamFood().setKarbonhidrat(gunlukToplamFood.getKarbonhidrat()+RealMainActivity.userData.getToplamFood().getKarbonhidrat());
                RealMainActivity.userData.getToplamFood().setProtein(gunlukToplamFood.getProtein()+RealMainActivity.userData.getToplamFood().getProtein());
                RealMainActivity.userData.getToplamFood().setYag(gunlukToplamFood.getYag()+RealMainActivity.userData.getToplamFood().getYag());
                RealMainActivity.userData.getToplamFood().setLif(gunlukToplamFood.getLif()+RealMainActivity.userData.getToplamFood().getLif());
                RealMainActivity.userData.getToplamFood().setKolesterol(gunlukToplamFood.getKolesterol()+RealMainActivity.userData.getToplamFood().getKolesterol());
                RealMainActivity.userData.getToplamFood().setSodyum(gunlukToplamFood.getSodyum()+RealMainActivity.userData.getToplamFood().getSodyum());
                RealMainActivity.userData.getToplamFood().setPotasyum(gunlukToplamFood.getPotasyum()+RealMainActivity.userData.getToplamFood().getPotasyum());
                RealMainActivity.userData.getToplamFood().setDemir(gunlukToplamFood.getDemir()+RealMainActivity.userData.getToplamFood().getDemir());

            }


            TextView textViewSelectedFood = (TextView) findViewById(R.id.text_view_selected_food);
            textViewSelectedFood.setText("");

            TextView textViewFoodEaten = (TextView) findViewById(R.id.text_view_food_eaten);
            textViewFoodEaten.setText(AddedFoodsString());

            Toast.makeText(AddFoodActivity.this, "Yemekler kaydedildi", Toast.LENGTH_LONG).show();

            addedFoodAsFoodClass = new ArrayList<>();
        } else {
            Toast.makeText(AddFoodActivity.this, "Hiç yemek seçmediniz!", Toast.LENGTH_LONG).show();
        }
    }

    public void GeriAl_Click(View view) {

        if (!addedFoodAsFoodClass.isEmpty()) {
            addedFoodAsFoodClass.remove(addedFoodAsFoodClass.size() - 1);
            TextView textViewSelectedFood = (TextView) findViewById(R.id.text_view_selected_food);

            textViewSelectedFood.setText(AddedFoodsString());
        } else {
            Toast.makeText(AddFoodActivity.this, "Hiç yemek seçmediniz!", Toast.LENGTH_LONG).show();
        }
    }

    protected String AddedFoodsString() {

        StringBuilder stringBuilder = new StringBuilder();

        for (Food aFood : addedFoodAsFoodClass
                ) {
            stringBuilder.append(aFood.WriteFood() + "\n");
        }

        return stringBuilder.toString();
    }


    protected void StartUserProfile(View view) {


        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }
}
