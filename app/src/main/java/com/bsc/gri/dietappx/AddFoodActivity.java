package com.bsc.gri.dietappx;


import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
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
    public int kalanKalori= RealMainActivity.userData.getHesaplananKalori();
    public ArrayList<Food> addedFoodAsFoodClass = new ArrayList<>();
    public ArrayList<Food> savedFoodAsFoodClass = new ArrayList<>();

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
                CheckThatFood(selectedFood);
                addedFoodAsFoodClass.add(selectedFood);
                textViewSelectedFood.append(selectedFood.WriteFood() + "\n");
                textView.setText("");

            }
        });

    }

    protected void PromptUser(String mesaj) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Emin Misiniz?");

        builder.setMessage(mesaj);

        builder.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Geri al", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GeriAl_Click(null);
                dialog.cancel();
            }
        });

        builder.show();
    }

    protected void CheckThatFood(Food gFood) {

        //PromptUser(".... içeriğine alerjiniz var, eklemek istediğinizden emin misiniz?");
        String sistemOnerisi ="";

        ArrayList<String> alerjiler = RealMainActivity.userData.getAlerjilerList();
        TagConstants tagConstants = new TagConstants();


        if (kalanKalori > 0){
            kalanKalori = kalanKalori - gFood.getCalories();
            if (kalanKalori < 0){
                PromptUser(gFood.getName()+ " yemeğini ekleyerek günlük kalori limitinizi aşıyorsunuz.\n\nYine de ekelemek ister misiniz?");
            }
        }

        if (alerjiler != null) {
            for (String alerji : alerjiler
                    ) {
                for (String ingredient : gFood.getIngredients()
                        ) {
                    if (alerji.equals(ingredient))
                        PromptUser(ingredient + " içeriğine alerjiniz var, eklemek istediğinizden emin misiniz?");
                }
            }
        }
        if (RealMainActivity.userData.isVegan()) {

                if (!gFood.isVegan()) {
                     PromptUser(gFood.getName() + "Vegan diyetinize uygun değil \n\nYine de eklemek ister misiniz?");
                }

        }
        if (RealMainActivity.userData.isVejeteryan()) {
            for (String tag : gFood.getTags()
                    ) {
                if (tag.equals(tagConstants.Et) || tag.equals(tagConstants.Balik)
                        || tag.equals(tagConstants.IslenmisEt)) {
                    PromptUser(gFood.getName() + " yiyeceği "+tag +" kategorisinde. Vejeteryan diyetinize uygun değil." + sistemOnerisi + "\n\nYine de eklemek ister misiniz?");
                }
            }
        }
        if (RealMainActivity.userData.isTansiyon()) {
            for (String tag : gFood.getTags()
                    ) {
                if (tag.equals(tagConstants.Kahve) || tag.equals(tagConstants.Ekmek) || tag.equals(tagConstants.IslenmisEt )
                        || tag.equals(tagConstants.Kizartma) || tag.equals(tagConstants.AlkolluIcecek)
                        || tag.equals(tagConstants.AsitliIcecek) || tag.equals(tagConstants.Tatli)) {
                    if (tag.equals(tagConstants.Kizartma)){sistemOnerisi="\n(Kızartma yerine daha sağlıklı bir pişirme yöntemini tercih edebilirsiz)";}
                    if (tag.equals(tagConstants.IslenmisEt)){sistemOnerisi="\n(İşlenmiş et yerine yağsız kırmızı et ya da beyaz et yemeyi tercih edebilirsiz)";}
                    if (tag.equals(tagConstants.Tatli)){sistemOnerisi="\n(Tatlı yerine meyve yemeyi tercih edebilirsiz)";}
                    PromptUser(gFood.getName() + " yiyeceği "+tag +" kategorisinde. Tansiyon diyetinize uygun değil " + sistemOnerisi + "\n\nYine de eklemek ister misiniz?");
                    sistemOnerisi="";
                }
            }
        }
        if (RealMainActivity.userData.isReflu()) {
            for (String tag : gFood.getTags()
                    ) {
                if (tag.equals(tagConstants.Kizartma) || tag.equals(tagConstants.Yagli) || tag.equals(tagConstants.AlkolluIcecek) ||tag.equals(tagConstants.AsitliIcecek) ) {
                    if (tag.equals(tagConstants.Kizartma)){sistemOnerisi="\n(Kızartma yerine daha sağlıklı bir pişirme yöntemini tercih edebilirsiz)";}
                    PromptUser(gFood.getName() + " yiyeceği "+tag +" kategorisinde. Reflü diyetinize uygun değil. " + sistemOnerisi + "\n\nYine de eklemek ister misiniz?");
                    sistemOnerisi="";
                }
            }
        }
        if (RealMainActivity.userData.isKolesterol()) {
            for (String tag : gFood.getTags()
                    ) {
                if (tag.equals(tagConstants.Yagli) || tag.equals(tagConstants.Kizartma) || tag.equals(tagConstants.Tatli)
                        || tag.equals(tagConstants.KatiYag) || tag.equals(tagConstants.IslenmisEt)) {
                    if (tag.equals(tagConstants.Kizartma)){sistemOnerisi="\n(Kızartma yerine daha sağlıklı bir pişirme yöntemini tercih edebilirsiz)";}
                    if (tag.equals(tagConstants.IslenmisEt)){sistemOnerisi="\n(İşlenmiş et yerine yağsız beyaz et yemeyi tercih edebilirsiz)";}
                    if (tag.equals(tagConstants.Tatli)){sistemOnerisi="\n(Tatlı yerine meyve yemeyi tercih edebilirsiz)";}
                    if(tag.equals(tagConstants.KatiYag)){sistemOnerisi="\n(Katı yağ içeren besinler yerine zeytin yağı içeren yemeyi tercih edebilirsiz)";}
                    PromptUser(gFood.getName() + " yiyeceği "+tag +" kategorisinde. Kolesterol diyetinize uygun değil. " + sistemOnerisi + "\n\nYine de eklemek ister misiniz?");
                    sistemOnerisi="";
                }
            }
        }
        if (RealMainActivity.userData.isGastrit()) {
            for (String tag : gFood.getTags()
                    ) {
                if (tag.equals(tagConstants.Yagli) || tag.equals(tagConstants.Kizartma) || tag.equals(tagConstants.Tatli )
                        || tag.equals(tagConstants.AlkolluIcecek)|| tag.equals(tagConstants.Kahve) ||tag.equals(tagConstants.AsitliIcecek)) {
                    if(tag.equals(tagConstants.Kizartma)){sistemOnerisi="\n(Kızartma yerine daha sağlıklı bir pişirme yöntemini tercih edebilirsiz)";}
                    if(tag.equals(tagConstants.Tatli)){sistemOnerisi="\n(Tatlı yerine meyve yemeyi tercih edebilirsiz)";}
                    PromptUser(gFood.getName() + " yiyeceği "+tag +" kategorisinde. Gastrit diyetinize uygun değil. " + sistemOnerisi + "\n\nYine de eklemek ister misiniz?");
                    sistemOnerisi="";
                }
            }
        }
        if (RealMainActivity.userData.isDiyabet()) {
            for (String tag : gFood.getTags()
                    ) {
                if (tag.equals(tagConstants.Unlu) || tag.equals(tagConstants.Ekmek) || tag.equals(tagConstants.AsitliIcecek)
                        || tag.equals(tagConstants.KatiYag) || tag.equals(tagConstants.Kizartma)) {
                    if (tag.equals(tagConstants.Kizartma)){sistemOnerisi="\n(Kızartma yerine daha sağlıklı bir pişirme yöntemini tercih edebilirsiz)";}
                    if(tag.equals(tagConstants.KatiYag)){sistemOnerisi="\n(Katı yağ içeren besinler yerine zeytin yağı içeren besinleri tercih edebilirsiz)";}
                    if(tag.equals(tagConstants.Tatli)){sistemOnerisi="\n(Tatlı yerine tatlandırıcı kullanılmış besinleri yemeyi tercih edebilirsiz)";}
                    PromptUser(gFood.getName() + " yiyeceği "+tag +" kategorisinde. Diyabet diyetinize uygun değil. " + sistemOnerisi + "\n\nYine de eklemek ister misiniz?");
                    sistemOnerisi="";
                }
            }
        }
        if (RealMainActivity.userData.isColyak()) {
            for (String tag : gFood.getTags()
                    ) {
                if (tag.equals(tagConstants.Ekmek) || tag.equals(tagConstants.DiyetEkmek) || tag.equals(tagConstants.Unlu)) {
                    if(tag.equals(tagConstants.Ekmek)){sistemOnerisi="\n(Ekmek yerine pilav yemeyi tercih edebilirsiz)";}
                    if(tag.equals(tagConstants.Unlu)){sistemOnerisi="\n(Un içeren mamüller yerine pirinç unlu besinleri tercih edebilirsiz)";}
                    PromptUser(gFood.getName() + " yiyeceği "+tag +" kategorisinde. Çölyak diyetinize uygun değil. " + sistemOnerisi + "\n\nYine de eklemek ister misiniz?");
                    sistemOnerisi="";
                }
            }
        }

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
            Date todaysDate = Calendar.getInstance().getTime();

            Food gunlukToplamFood = new Food();

            for (Food ff : addedFoodAsFoodClass
                    ) {
                ff.setYenmeTarihi(todaysDate);

               /* if(gunlukToplamFood==null) {
                    gunlukToplamFood = ff;
                    gunlukToplamFood.setName("gunlukToplam");
                }
                else{*/
                gunlukToplamFood.setCalories(gunlukToplamFood.getCalories() + ff.getCalories());
                gunlukToplamFood.setKarbonhidrat(gunlukToplamFood.getKarbonhidrat() + ff.getKarbonhidrat());
                gunlukToplamFood.setProtein(gunlukToplamFood.getProtein() + ff.getProtein());
                gunlukToplamFood.setYag(gunlukToplamFood.getYag() + ff.getYag());
                gunlukToplamFood.setLif(gunlukToplamFood.getLif() + ff.getLif());
                gunlukToplamFood.setKolesterol(gunlukToplamFood.getKolesterol() + ff.getKolesterol());
                //gunlukToplamFood.setSodyum(gunlukToplamFood.getSodyum()+ff.getSodyum());
                //gunlukToplamFood.setPotasyum(gunlukToplamFood.getPotasyum()+ff.getPotasyum());
                //gunlukToplamFood.setDemir(gunlukToplamFood.getDemir()+ff.getDemir());


                //}
                savedFoodAsFoodClass.add(ff);
            }

            Date oldDate = RealMainActivity.userData.getGunlukToplamFood().getYenmeTarihi() != null ? RealMainActivity.userData.getGunlukToplamFood().getYenmeTarihi() : null;

            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

            boolean sameDay = true;

            if (oldDate != null) {
                sameDay = fmt.format(todaysDate).equals(fmt.format(oldDate));
            }


            if (!sameDay)
                RealMainActivity.userData.setGunlukToplamFood(gunlukToplamFood);
            else {
                RealMainActivity.userData.getGunlukToplamFood().setCalories(gunlukToplamFood.getCalories() + RealMainActivity.userData.getGunlukToplamFood().getCalories());
                RealMainActivity.userData.getGunlukToplamFood().setKarbonhidrat(gunlukToplamFood.getKarbonhidrat() + RealMainActivity.userData.getGunlukToplamFood().getKarbonhidrat());
                RealMainActivity.userData.getGunlukToplamFood().setProtein(gunlukToplamFood.getProtein() + RealMainActivity.userData.getGunlukToplamFood().getProtein());
                RealMainActivity.userData.getGunlukToplamFood().setYag(gunlukToplamFood.getYag() + RealMainActivity.userData.getGunlukToplamFood().getYag());
                RealMainActivity.userData.getGunlukToplamFood().setLif(gunlukToplamFood.getLif() + RealMainActivity.userData.getGunlukToplamFood().getLif());
                RealMainActivity.userData.getGunlukToplamFood().setKolesterol(gunlukToplamFood.getKolesterol() + RealMainActivity.userData.getGunlukToplamFood().getKolesterol());
                //RealMainActivity.userData.getGunlukToplamFood().setSodyum(gunlukToplamFood.getSodyum()+RealMainActivity.userData.getGunlukToplamFood().getSodyum());
                //RealMainActivity.userData.getGunlukToplamFood().setPotasyum(gunlukToplamFood.getPotasyum()+RealMainActivity.userData.getGunlukToplamFood().getPotasyum());
                //RealMainActivity.userData.getGunlukToplamFood().setDemir(gunlukToplamFood.getDemir()+RealMainActivity.userData.getGunlukToplamFood().getDemir());

            }

            if (!(RealMainActivity.userData.getToplamFood() == null)) {

                RealMainActivity.userData.setToplamFood(gunlukToplamFood);
            } else {
                RealMainActivity.userData.getToplamFood().setCalories(gunlukToplamFood.getCalories() + RealMainActivity.userData.getToplamFood().getCalories());
                RealMainActivity.userData.getToplamFood().setKarbonhidrat(gunlukToplamFood.getKarbonhidrat() + RealMainActivity.userData.getToplamFood().getKarbonhidrat());
                RealMainActivity.userData.getToplamFood().setProtein(gunlukToplamFood.getProtein() + RealMainActivity.userData.getToplamFood().getProtein());
                RealMainActivity.userData.getToplamFood().setYag(gunlukToplamFood.getYag() + RealMainActivity.userData.getToplamFood().getYag());
                RealMainActivity.userData.getToplamFood().setLif(gunlukToplamFood.getLif() + RealMainActivity.userData.getToplamFood().getLif());
                RealMainActivity.userData.getToplamFood().setKolesterol(gunlukToplamFood.getKolesterol() + RealMainActivity.userData.getToplamFood().getKolesterol());
                //RealMainActivity.userData.getToplamFood().setSodyum(gunlukToplamFood.getSodyum()+RealMainActivity.userData.getToplamFood().getSodyum());
                //RealMainActivity.userData.getToplamFood().setPotasyum(gunlukToplamFood.getPotasyum()+RealMainActivity.userData.getToplamFood().getPotasyum());
                //RealMainActivity.userData.getToplamFood().setDemir(gunlukToplamFood.getDemir()+RealMainActivity.userData.getToplamFood().getDemir());

            }


            TextView textViewSelectedFood = (TextView) findViewById(R.id.text_view_selected_food);
            textViewSelectedFood.setText("");

            TextView textViewFoodEaten = (TextView) findViewById(R.id.text_view_food_eaten);
            textViewFoodEaten.setText(AddedFoodsString(savedFoodAsFoodClass));

            Toast.makeText(AddFoodActivity.this, "Yemekler kaydedildi", Toast.LENGTH_LONG).show();
            //TODO kaydedilenler listesinden yazdır
            addedFoodAsFoodClass = new ArrayList<>();
        } else {
            Toast.makeText(AddFoodActivity.this, "Hiç yemek seçmediniz!", Toast.LENGTH_LONG).show();
        }
    }

    public void GeriAl_Click(View view) {

        if (!addedFoodAsFoodClass.isEmpty()) {
            addedFoodAsFoodClass.remove(addedFoodAsFoodClass.size() - 1);
            TextView textViewSelectedFood = (TextView) findViewById(R.id.text_view_selected_food);

            textViewSelectedFood.setText(AddedFoodsString(addedFoodAsFoodClass));
        } else {
            Toast.makeText(AddFoodActivity.this, "Hiç yemek seçmediniz!", Toast.LENGTH_LONG).show();
        }
    }

    protected String AddedFoodsString(ArrayList<Food> foodList) {

        StringBuilder stringBuilder = new StringBuilder();

        for (Food aFood : foodList
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
