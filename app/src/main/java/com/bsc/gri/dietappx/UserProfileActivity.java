package com.bsc.gri.dietappx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }


    public void FillThePage(View view){

        EditText editTextName = (EditText) findViewById(R.id.edit_name);
        EditText editTextAge = (EditText) findViewById(R.id.edit_age);
        EditText editTextHeight = (EditText) findViewById(R.id.edit_height);
        EditText editTextWeight = (EditText) findViewById(R.id.edit_weight);
        RadioButton radioButtonMan = (RadioButton) findViewById(R.id.radio_sex_man);
        RadioButton radioButtonWoman = (RadioButton) findViewById(R.id.radio_sex_woman);
        RadioButton radioButtonVejeteryan = (RadioButton) findViewById(R.id.radio_vej);

        editTextName.setText(MainActivity.userData.getUserName());
        editTextAge.setText(Integer.toString(MainActivity.userData.getYas()));
        editTextHeight.setText(Integer.toString(MainActivity.userData.getBoy()));
        editTextWeight.setText(Integer.toString(MainActivity.userData.getKilo()));

        if (MainActivity.userData.getCinsiyet().equals("kadın"))
            radioButtonWoman.setChecked(true);
        else
            radioButtonMan.setChecked(true);

        radioButtonVejeteryan.setChecked(MainActivity.userData.isVejeteryan());

    }
}
