package com.bsc.gri.dietappx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class DiyetSecimActivity extends AppCompatActivity {
public Button kaydetDiyetSecim;
    public Button hesapla;
    @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_diyet_secim);
                final Context context = this;

                init();

                kaydetDiyetSecim=(Button) findViewById(R.id.kaydetDiyetSecim);
                hesapla=(Button) findViewById(R.id.button5);
                kaydetDiyetSecim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        onKaydet();
                        //Intent intent = new Intent(context, DiyetimActivity.class);
                        //startActivity(intent);
                    }
                });
                hesapla.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        onKaydet();
                        Intent intent = new Intent(context, KiloVermeAlgoritmasi.class);
                        startActivity(intent);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, RealMainActivity.ingredientNames);


        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.yasakli_Besin);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                RealMainActivity.userData.getAlerjilerList().add(autoCompleteTextView.getText().toString());
                Toast.makeText(DiyetSecimActivity.this,autoCompleteTextView.getText().toString()+ " eklendi." , Toast.LENGTH_LONG).show();
                autoCompleteTextView.setText("");
                //other logic
            }
        });



    }

    public void init(){
        CheckBox checkBoxVegan = (CheckBox) findViewById(R.id.vegan);
        CheckBox checkBoxVejeteryan = (CheckBox) findViewById(R.id.vejetaryen);
        CheckBox checkBoxReflu = (CheckBox) findViewById(R.id.reflu);
        CheckBox checkBoxKolesterol = (CheckBox) findViewById(R.id.kolesterol);
        CheckBox checkBoxDiyabet = (CheckBox) findViewById(R.id.diyabet);
        CheckBox checkBoxGastrit = (CheckBox) findViewById(R.id.gastrit);
        CheckBox checkBoxTansiyon = (CheckBox) findViewById(R.id.tansiyon);
        CheckBox checkBoxColyak = (CheckBox) findViewById(R.id.colyak);
        EditText editTextKalori = (EditText) findViewById(R.id.editText2);

        checkBoxVegan.setChecked(RealMainActivity.userData.isVegan());
        checkBoxVejeteryan.setChecked(RealMainActivity.userData.isVejeteryan());
        checkBoxReflu.setChecked(RealMainActivity.userData.isReflu());
        checkBoxKolesterol.setChecked(RealMainActivity.userData.isKolesterol());
        checkBoxDiyabet.setChecked(RealMainActivity.userData.isDiyabet());
        checkBoxGastrit.setChecked(RealMainActivity.userData.isGastrit());
        checkBoxTansiyon.setChecked(RealMainActivity.userData.isTansiyon());
        checkBoxColyak.setChecked(RealMainActivity.userData.isColyak());
        editTextKalori.setText(Integer.toString(RealMainActivity.userData.getHesaplananKalori()));


    }
    public void onKaydet(){

        CheckBox checkBoxVegan = (CheckBox) findViewById(R.id.vegan);
        CheckBox checkBoxVejeteryan = (CheckBox) findViewById(R.id.vejetaryen);
        CheckBox checkBoxReflu = (CheckBox) findViewById(R.id.reflu);
        CheckBox checkBoxKolesterol = (CheckBox) findViewById(R.id.kolesterol);
        CheckBox checkBoxDiyabet = (CheckBox) findViewById(R.id.diyabet);
        CheckBox checkBoxGastrit = (CheckBox) findViewById(R.id.gastrit);
        CheckBox checkBoxTansiyon = (CheckBox) findViewById(R.id.tansiyon);
        CheckBox checkBoxColyak = (CheckBox) findViewById(R.id.colyak);
        EditText editTextKalori = (EditText) findViewById(R.id.editText2);

        RealMainActivity.userData.setVegan(checkBoxVegan.isChecked());
        RealMainActivity.userData.setVejeteryan(checkBoxVejeteryan.isChecked());
        RealMainActivity.userData.setReflu(checkBoxReflu.isChecked());
        RealMainActivity.userData.setKolesterol(checkBoxKolesterol.isChecked());
        RealMainActivity.userData.setGastrit(checkBoxGastrit.isChecked());
        RealMainActivity.userData.setTansiyon(checkBoxTansiyon.isChecked());
        RealMainActivity.userData.setColyak(checkBoxColyak.isChecked());
        RealMainActivity.userData.setDiyabet(checkBoxDiyabet.isChecked());
        RealMainActivity.userData.setHesaplananKalori(Integer.parseInt(editTextKalori.getText().toString()));

        RealMainActivity.userData.setUserName("degisti");
        Intent i = new Intent(DiyetSecimActivity.this, RealMainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        RealMainActivity.userData.setUserName("degisti");

        Intent intent = new Intent(DiyetSecimActivity.this, DiyetimActivity.class);
        startActivity(intent);

  /*      RealMainActivity realMainActivity = new RealMainActivity();
        try {
            //realMainActivity.SaveUserDataAsXML(RealMainActivity.userData);
        }catch (Exception e){
            Toast.makeText(DiyetSecimActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }*/

    }
}
