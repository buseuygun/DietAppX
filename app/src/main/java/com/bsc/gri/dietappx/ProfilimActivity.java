package com.bsc.gri.dietappx;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfilimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilim);
        final Context context = this;
        ///

        init();
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(context, ProfilEditActivity.class);
                startActivity(intent);
            }
        });
    }

    public Button button1;
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
