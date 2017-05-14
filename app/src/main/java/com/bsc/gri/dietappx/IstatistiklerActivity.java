package com.bsc.gri.dietappx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class IstatistiklerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istatistikler);

        String stats = RealMainActivity.userData.getGunlukToplamFood().WriteFoodWitouthIng();

        TextView textView = (TextView) findViewById(R.id.text_view_stats);
        textView.append("\n"+stats);
    }
}
