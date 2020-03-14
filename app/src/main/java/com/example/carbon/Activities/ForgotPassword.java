package com.example.carbon.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.example.carbon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ForgotPassword";

    private EditText mEmailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mEmailField = findViewById(R.id.EditTextSendForgotEmail);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ButtonSendForgotEmail) {
            sendPasswordReset(mEmailField.getText().toString());
        }
    }

    public void sendPasswordReset(String emailAddress) {

        if (!validateForm()) {
            return;
        }

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                        }
                    }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        return valid;
    }
}
