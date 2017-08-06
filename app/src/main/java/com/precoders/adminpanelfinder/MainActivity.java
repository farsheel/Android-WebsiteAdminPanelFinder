package com.precoders.adminpanelfinder;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Farsheel on 6/8/17.
 */

public class MainActivity extends AppCompatActivity {

    Button btnSubmit;
    EditText etUrl;
    RadioGroup rgFileTypeGroup;
    int rbFileTypeId;
    RadioButton rbFileType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit=(Button) findViewById(R.id.btnSubmit);
        etUrl=(EditText) findViewById(R.id.etUrl);
        rgFileTypeGroup=(RadioGroup) findViewById(R.id.radioFile);
        rbFileTypeId=rgFileTypeGroup.getCheckedRadioButtonId();
        rbFileType=(RadioButton) findViewById(rbFileTypeId);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findAdmin();
            }
        });

    }

    private void findAdmin() {

        if(etUrl.getText().toString().isEmpty() || !URLUtil.isValidUrl(etUrl.getText().toString()))
        {
            Toast.makeText(getApplicationContext(),"Please enter the url correctly",Toast.LENGTH_SHORT).show();
        }

        else
        {
            String url=etUrl.getText().toString();
            rbFileTypeId=rgFileTypeGroup.getCheckedRadioButtonId();
            rbFileType=(RadioButton) findViewById(rbFileTypeId);

            Intent in=new Intent(getApplicationContext(),AdminPanel.class);
            in.putExtra("url",url);
            in.putExtra("file",rbFileType.getText());

            startActivity(in);


        }
    }



}
