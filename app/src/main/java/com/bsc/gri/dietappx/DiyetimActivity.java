package com.bsc.gri.dietappx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DiyetimActivity extends AppCompatActivity {

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
    }

    public Button button4;
    public TextView tvName = null;
    public String strName = null;
    public Bundle extras = null;

    private void init() {

        tvName = (TextView) findViewById(R.id.textView2);

        extras = getIntent().getExtras();

        if (extras != null) {
            strName = extras.getString(ProfilEditActivity.NAME);
            tvName.setText(strName);
        }

    }


}
