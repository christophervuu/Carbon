package com.example.carbon.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carbon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpAccount extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "EmailPassword";

    private FirebaseAuth mAuth;

    private EditText mFirstName, mLastName, mBirthDate, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_name);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mFirstName = findViewById(R.id.SignUpNameEditTextFirstName);
        mLastName = findViewById(R.id.SignUpAccountEditTextLastName);

        findViewById(R.id.SignUpNameButtonSignUpAccept).setOnClickListener(this);
        findViewById(R.id.SignUpNameImageViewBack).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.SignUpNameButtonSignUpAccept) {
            continueSignUpBirthDate();
        } else if (i == R.id.SignUpNameImageViewBack ) {
            this.finish();
        }
    }

    private void continueSignUpBirthDate() {
        String FirstName = mFirstName.getText().toString();
        String LastName = mLastName.getText().toString();

        if (!validateForm(FirstName, LastName)) {
            return;
        }



    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);

        //showProgressBar();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpAccount.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // [START_EXCLUDE]
                        //hideProgressBar();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    public boolean validateForm(String FirstName, String LastName) {
        boolean valid = true;

        if (TextUtils.isEmpty(FirstName)) {
            mFirstName.setError("Required.");
            valid = false;
        } else {
            mFirstName.setError(null);
        }

        if (TextUtils.isEmpty(LastName)) {
            mLastName.setError("Required.");
            valid = false;
        } else {
            mLastName.setError(null);
        }

        if (TextUtils.isEmpty(LastName)) {
            mLastName.setError("Required.");
            valid = false;
        } else {
            mLastName.setError(null);
        }

        if (TextUtils.isEmpty(LastName)) {
            mLastName.setError("Required.");
            valid = false;
        } else {
            mLastName.setError(null);
        }

        return valid;
    }
}
