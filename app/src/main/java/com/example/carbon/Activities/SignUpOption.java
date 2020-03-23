package com.example.carbon.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.carbon.R;

public class SignUpOption extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_option);

        findViewById(R.id.SignUpOptionButtonEmail).setOnClickListener(this);
        //findViewById(R.id.SignUpOptionButtonBack).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.SignUpOptionButtonEmail) {
            Intent intent = new Intent(getApplicationContext(), SignUpAccount.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}
