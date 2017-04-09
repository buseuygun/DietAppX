package com.bsc.gri.dietappx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfilEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_edit);
        init();
    }

    private Button button2;
    public static String NAME = "name";
    private EditText etName = null;
    private String strName= null;

    private void init()
    {

        etName = (EditText) findViewById(R.id.editText);


        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                clickedSubmit();
            }
        });
    }
    private void clickedSubmit() {

        strName = etName.getText().toString().trim();


        try {

            Bundle extras = new Bundle();
            extras.putString(NAME, strName);



            Intent intent = new Intent();


            intent.putExtras(extras);


            intent.setClass(getApplicationContext(), ProfilimActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
