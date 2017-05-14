package com.bsc.gri.dietappx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.RadioButton;

public class KaloriHesaplamaIlkSayfa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalori_hesaplama_ilk_sayfa);
        final Context context = this;
        //this.finish();
        final RadioButton kilover =(RadioButton) findViewById(R.id.kiloVerRadioButton);
        final RadioButton kilokoru =(RadioButton) findViewById(R.id.kiloKoruRadioButton);
        final Button devamEt=(Button) findViewById(R.id.devamEtButton);
        devamEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kilokoru.isChecked()) {
                    Intent Intents= new Intent(KaloriHesaplamaIlkSayfa.this, KaloriHesaplamaActivity.class); // <----- START "BEACHES" ACTIVITY
                    startActivity(Intents);
                    setContentView(R.layout.activity_kalori_hesaplama);
                }
                else if (kilover.isChecked()) {
                    Intent Intentm = new Intent(getApplicationContext(), KiloVermeAlgoritmasi.class); // <----- START "HIll STATIONS" ACTIVITY
                    startActivityForResult(Intentm, 0);
                }

            }
        });

    }
}
