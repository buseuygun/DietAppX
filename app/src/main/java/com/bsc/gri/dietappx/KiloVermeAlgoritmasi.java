package com.bsc.gri.dietappx;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class KiloVermeAlgoritmasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kilo_verme_algoritmasi);
        init();
        addListenerOnButton();
    }

    public List<String> list;
    Spinner spinner;
    Spinner spinner2;
    Context context = this;

    private RadioGroup radioGroup;
    private RadioGroup kilover_radioGroup;
    private RadioButton radioButton;
    private RadioButton kilover_radioButton;
    private EditText evYas;
    private EditText evBoy;
    private EditText evKilo;
    private Button btnDisplay;
    private String size;
    int cinsiyetValue = 1;

    double deger = 0;
    double negatif = 0;
    ArrayAdapter<String> adapter;


    public void addListenerOnButton() {

        radioGroup = (RadioGroup) findViewById(R.id.rg_kilover_cinsiyet);
        kilover_radioGroup = (RadioGroup) findViewById(R.id.kilover_rg);
        btnDisplay = (Button) findViewById(R.id.kilover_button);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                spinner2 = (Spinner) findViewById(R.id.kilover_spinner);
                evBoy = (EditText) findViewById(R.id.kilover_et_boy);
                evYas = (EditText) findViewById(R.id.kilover_et_yas);
                evKilo = (EditText) findViewById(R.id.kilover_et_kilo);
                boolean isRadioButtonsChecked = true;
                int selectedId = -1;
                int selectedIdkilover = -1;
                // get selected radio button from radioGroup
                try {
                    selectedId = radioGroup.getCheckedRadioButtonId();
                    selectedIdkilover = kilover_radioGroup.getCheckedRadioButtonId();
                } catch (Exception e) {
                    //do nothing
                    isRadioButtonsChecked = false;
                }


                try{
                    double aktiviteDegeri = 1;

                    // find the radiobutton by returned id
                    radioButton = (RadioButton) findViewById(selectedId);
                    kilover_radioButton = (RadioButton) findViewById(selectedIdkilover);


                    String Sboy = evBoy.getText().toString();
                    int boy = Integer.parseInt(Sboy);


                    String Syas = evYas.getText().toString();
                    int yas = Integer.parseInt(Syas);


                    String Skilo = evKilo.getText().toString();
                    int kilo = Integer.parseInt(Skilo);


                    size = spinner2.getSelectedItem().toString();
                    String cinsiyet = radioButton.getText().toString();
                    String haftalikKilovermeDegeri = kilover_radioButton.getText().toString();

                    if (size.equals(getString(R.string.Egzersiz1))) {
                        aktiviteDegeri = 1.2;
                    } else if (size.equals(getString(R.string.Egzersiz2))) {
                        aktiviteDegeri = 1.375;
                    } else if (size.equals(getString(R.string.Egzersiz3))) {
                        aktiviteDegeri = 1.55;
                    } else if (size.equals(getString(R.string.Egzersiz4))) {
                        aktiviteDegeri = 1.725;
                    } else if (size.equals(getString(R.string.Egzersiz5))) {
                        aktiviteDegeri = 1.9;
                    }
                    if (cinsiyet.equals(getString(R.string.woman))) {
                        cinsiyetValue = 665;
                        deger = aktiviteDegeri * (cinsiyetValue + (9.6 * kilo) + (1.7 * boy) - (4.7 * yas));
                    }
                    if (cinsiyet.equals(getString(R.string.man))) {
                        cinsiyetValue = 66;
                        deger = (cinsiyetValue + (13.75 * kilo) + (5 * boy) - (6.8 * yas)) * aktiviteDegeri;
                    }
                    if (haftalikKilovermeDegeri.equals("Haftada 0.25 kg vermek")) {
                        negatif = -250;
                        deger = deger + negatif;
                    }
                    if (haftalikKilovermeDegeri.equals("Haftada 0.50 kg vermek")) {
                        negatif = -500;
                        deger = deger + negatif;
                    }

                    TextView myAwesomeTextView = (TextView) findViewById(R.id.kilover_hesaplanan_kalori);
//in your OnCreate() method
                    myAwesomeTextView.setText(Double.toString(deger));
                    try {
                        int i = (int)deger;
                        RealMainActivity.userData.setHesaplananKalori(i);
                        //RealMainActivity realMainActivity = new RealMainActivity();
                        //realMainActivity.SaveUserDataAsXML(RealMainActivity.userData);
                    }catch (Exception e){}
                    Intent i = new Intent(KiloVermeAlgoritmasi.this, DiyetSecimActivity.class);
                    //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);

                }
                catch(Exception e){
                    Toast.makeText(KiloVermeAlgoritmasi.this, "eksik ve ya yanlış girdiniz!", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    public void init() {

        list = new ArrayList<>();
        list.add(getString(R.string.Egzersiz1));
        list.add(getString(R.string.Egzersiz2));
        list.add(getString(R.string.Egzersiz3));
        list.add(getString(R.string.Egzersiz4));
        list.add(getString(R.string.Egzersiz5));

        spinner = (Spinner) findViewById(R.id.kilover_spinner);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);


        new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3) {


            }

            @Override
            public void onNothingSelected(final AdapterView<?> arg0) {

            }
        };
    }
}
