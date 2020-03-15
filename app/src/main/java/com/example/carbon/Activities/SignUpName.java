package com.example.carbon.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.carbon.R;

public class SignUpName extends AppCompatActivity implements View.OnClickListener {

    private EditText mFirstName, mLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_name);

        mFirstName = findViewById(R.id.SignUpNameEditTextFirstName);
        mLastName = findViewById(R.id.SignUpNameEditTextLastName);

        findViewById(R.id.SignUpNameButtonSignUpAccept).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.SignUpNameButtonSignUpAccept) {
            Intent intent = new Intent(getApplicationContext(), SignUpBirthDate.class);
            intent.putExtra("FirstName", FirstName);
            intent.putExtra("LastName", LastName);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }



}
