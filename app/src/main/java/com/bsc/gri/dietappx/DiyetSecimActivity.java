package com.bsc.gri.dietappx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.view.View;

public class DiyetSecimActivity extends AppCompatActivity {
public Button kaydetDiyetSecim;
    public Button hesapla;
    @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_diyet_secim);
                final Context context = this;
                kaydetDiyetSecim=(Button) findViewById(R.id.kaydetDiyetSecim);
                hesapla=(Button) findViewById(R.id.button5);
                kaydetDiyetSecim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        Intent intent = new Intent(context, DiyetimActivity.class);
                        startActivity(intent);
                    }
                });
                hesapla.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        Intent intent = new Intent(context, KaloriHesaplamaIlkSayfa.class);
                        startActivity(intent);
            }
        });

    }
}
