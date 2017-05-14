package com.bsc.gri.dietappx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DiyetimActivity extends AppCompatActivity {
    public Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyetim);
        final Context context = this;
       // init();
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, DiyetSecimActivity.class);
                startActivity(intent);
            }
        });
        init();
    }

    protected void init(){
        RealMainActivity realMainActivity = new RealMainActivity();


        if (RealMainActivity.userData!=null) {
            try {
              //TODO read and write the user data to text views

                TextView textViewUyguladigimDiyetler = (TextView) findViewById(R.id.text_view_uyguladigim_diyetler);
                TextView textViewYasakliBesinler = (TextView) findViewById(R.id.text_view_yasakli_besinler);
                TextView textViewHedefKalori = (TextView) findViewById(R.id.hedefkalori);

                StringBuilder stringBuilder = new StringBuilder();

                if(RealMainActivity.userData.isVejeteryan())
                    stringBuilder.append("Vejeteryan\n");

                if(RealMainActivity.userData.isVegan())
                    stringBuilder.append("Vegan\n");

                if(RealMainActivity.userData.isColyak())
                    stringBuilder.append("Çölyak\n");

                if(RealMainActivity.userData.isDiyabet())
                    stringBuilder.append("Diyabet\n");

                if(RealMainActivity.userData.isGastrit())
                    stringBuilder.append("Gastrit\n");

                if(RealMainActivity.userData.isKolesterol())
                    stringBuilder.append("Kolesterol\n");

                if(RealMainActivity.userData.isReflu())
                    stringBuilder.append("Reflü\n");

                if(RealMainActivity.userData.isTansiyon())
                    stringBuilder.append("Tansiyon\n");

                textViewUyguladigimDiyetler.setText(stringBuilder.toString());

                stringBuilder = new StringBuilder();

                try {
                    for (String ybesin : RealMainActivity.userData.getAlerjilerList()
                            ) {
                        String toAppend = ybesin + "\n";
                        stringBuilder.append(toAppend);
                    }

                    textViewYasakliBesinler.setText(stringBuilder.toString());
                }catch (Exception e) {}


                textViewHedefKalori.setText(Integer.toString(RealMainActivity.userData.getHesaplananKalori()));

            } catch (Exception e) {
                Toast.makeText(DiyetimActivity.this, "Kişisel veriniz bulunmamaktadır. Değiştir tuşundan ekleyebilir ya da güncelleyebilirsiniz.", Toast.LENGTH_LONG).show();
            }
        }else {

            //TODO ask user for new data
            //Toast.makeText(DiyetimActivity.this, "Kişisel veriniz bulunmamaktadır. Değiştir tuşundan ekleyebilir ya da güncelleyebilirsiniz.", Toast.LENGTH_LONG).show();
        }
    }




}
