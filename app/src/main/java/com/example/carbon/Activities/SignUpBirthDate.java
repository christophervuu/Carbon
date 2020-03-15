package com.example.carbon.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.example.carbon.R;

import java.util.Calendar;

public class SignUpBirthDate extends AppCompatActivity implements View.OnClickListener {

    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_birth_date);

        findViewById(R.id.SignUpBirthDateButtonContinue).setOnClickListener(this);
        findViewById(R.id.SignUpBirthDateImageViewBack).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.SignUpBirthDateButtonContinue) {
            continueSignUpContact();
        } else if (i == R.id.SignUpBirthDateImageViewBack) {
            this.finish();
        }
    }

    public void continueSignUpContact() {
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
