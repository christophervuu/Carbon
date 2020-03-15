package com.example.carbon.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.carbon.R;

public class SignUpBirthDate extends AppCompatActivity implements View.OnClickListener {

    DatePicker datePicker;
    TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_birth_date);

        datePicker = findViewById(R.id.SignUpBirthDateDatePickerBirthDate);

        findViewById(R.id.SignUpBirthDateButtonContinue).setOnClickListener(this);
        findViewById(R.id.SignUpBirthDateImageViewBack).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.SignUpBirthDateButtonContinue) {
            continueSignUpContact(datePicker);
        } else if (i == R.id.SignUpBirthDateImageViewBack) {
            this.finish();
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void continueSignUpContact(DatePicker datePicker) {
        String DateOfBirth = datePicker.getMonth() + "-" + datePicker.getDayOfMonth() + "-" + datePicker.getYear();

        Intent intentPrevious = getIntent();
        String FirstName = intentPrevious.getStringExtra("FirstName");
        String LastName = intentPrevious.getStringExtra("LastName");

        Bundle extras = new Bundle();
        extras.putString("FirstName", FirstName);
        extras.putString("LastName", LastName);
        extras.putString("DateOfBirth", DateOfBirth);

        Intent intent = new Intent(getApplicationContext(), SignUpEmail.class);
        intent.putExtras(extras);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
