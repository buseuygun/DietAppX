package com.bsc.gri.dietappx;

import android.content.Context;
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

public class KaloriHesaplamaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalori_hesaplama);
        init();
    }

    public List<String> list;
    Spinner spinner;
    Spinner spinner2;
    Context context=this;


    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private EditText evYas;
    private EditText evBoy;
    private EditText evKilo;
    private Button btnDisplay;
    private String size;
    int cinsiyetValue=1;

    double deger=0;




    ArrayAdapter<String> adapter;

    public void addListenerOnButton() {

        radioGroup = (RadioGroup) findViewById(R.id.rg1);
        btnDisplay = (Button) findViewById(R.id.button7);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();
                double aktiviteDegeri=1;
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);
                evBoy=(EditText) findViewById(R.id.eT2) ; String Sboy=evBoy.getText().toString();
                int boy=Integer.parseInt(Sboy);
                evYas=(EditText) findViewById(R.id.eT1) ; String Syas=evYas.getText().toString();
                int yas=Integer.parseInt(Syas);
                evKilo=(EditText) findViewById(R.id.eT3) ; String Skilo=evKilo.getText().toString();
                int kilo=Integer.parseInt(Skilo);
                spinner2=(Spinner) findViewById(R.id.spinner1);
                size=spinner2.getSelectedItem().toString();
                String cinsiyet= radioButton.getText().toString() ;
                if(size.equals("Nerdeyse hiç egzersiz yapmıyorum.")){
                    aktiviteDegeri=1.2;
                }
                else if (size.equals("Haftada 1 ya da 2 kez hafif egzersizler yapıyorum")){
                    aktiviteDegeri=1.375;
                }
                else if (size.equals("Haftada 4-5 gün spor yapıyorum")){
                    aktiviteDegeri=1.55;
                }
                else if (size.equals("Neredeyse haftanın her günü ağır idmanlarım var")){
                    aktiviteDegeri=1.725;
                }
                else if (size.equals("Her gün çok çok ağır idmanlarım var")){
                    aktiviteDegeri=1.9;
                }
                if(cinsiyet.equals("bayan")){
                    cinsiyetValue=665;
                    deger=aktiviteDegeri*(cinsiyetValue+(9.6*kilo)+(1.7*boy)-(4.7*yas));
                }
                else if(cinsiyet.equals("erkek")){
                    cinsiyetValue=66;
                    deger=(cinsiyetValue+(13.75*kilo)+(5*boy)-(6.8*yas))*aktiviteDegeri;
                }

                TextView myAwesomeTextView = (TextView)findViewById(R.id.textView15);
//in your OnCreate() method
                myAwesomeTextView.setText(Double.toString(deger));

            }

        });

    }
    public void init(){
        list=new ArrayList<>();
        list.add("Nerdeyse hiç egzersiz yapmıyorum.");
        list.add("Haftada 1 ya da 2 kez hafif egzersizler yapıyorum");
        list.add("Haftada 4-5 gün spor yapıyorum");
        list.add("Neredeyse haftanın her günü ağır idmanlarım var");
        list.add("Her gün çok çok ağır idmanlarım var");

        spinner=(Spinner) findViewById(R.id.spinner1);
        adapter=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(adapter);



        new  OnItemSelectedListener () {
            @Override
            public void onItemSelected( AdapterView<?> parent,  View view,  int position, long arg3) {


            }

            @Override
            public void onNothingSelected(final AdapterView<?> arg0) {

            }
        };
    }

}













