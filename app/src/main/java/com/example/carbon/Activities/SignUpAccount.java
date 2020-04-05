package com.example.carbon.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carbon.HttpRequest.UserApi;
import com.example.carbon.Model.UserProfileTest;
import com.example.carbon.Model.UserProfilev2;
import com.example.carbon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpAccount extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "EmailPassword";

    private FirebaseAuth mAuth;

    private EditText mFirstName, mLastName, mBirthDate;
    private EditText mEmail, mPassword;

    UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_account);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mFirstName = findViewById(R.id.SignUpAccountEditTextFirstName);
        mLastName = findViewById(R.id.SignUpAccountEditTextLastName);
        mEmail = findViewById(R.id.SignUpAccountEditTextEmail);
        mPassword = findViewById(R.id.SignUpAccountEditTextPassword);

        findViewById(R.id.SignUpAccountButtonSignUpAccept).setOnClickListener(this);
        findViewById(R.id.SignUpAccountImageViewBack).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.SignUpAccountButtonSignUpAccept) {
            continueSignUpBirthDate();
        } else if (i == R.id.SignUpAccountImageViewBack) {
            this.finish();
        }
    }

    private void continueSignUpBirthDate() {
        String FirstName = mFirstName.getText().toString();
        String LastName = mLastName.getText().toString();
        String Email = mEmail.getText().toString();
        String Password = mPassword.getText().toString();

        if (!validateForm(FirstName, LastName, Email, Password)) {
            return;
        }

        createAccount(Email, Password);
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

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("https://rnozi7c90e.execute-api.us-east-2.amazonaws.com/Viaanix/app/user/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            userApi = retrofit.create(UserApi.class);

                            UserProfileTest userProfileTest = new UserProfileTest("1234", null, "firstname", "lastname", "2000-12-31", "email", "phone");
                            Call<UserProfileTest> call = userApi.createUser(userProfileTest);

                            call.enqueue(new Callback<UserProfileTest>() {
                                @Override
                                public void onResponse(Call<UserProfileTest> call, Response<UserProfileTest> response) {
                                    Log.w("2.0 getFeed > Full json res wrapped in gson => ", new Gson().toJson(response));

                                    if (!response.isSuccessful()) {
                                        Log.d(TAG, "-----isFalse----");
                                    } else {
                                        Log.d(TAG, "-----isSuccess----");
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserProfileTest> call, Throwable t) {
                                    Log.d(TAG, "----onFailure------");
                                    Log.e(TAG, t.getMessage());
                                    Log.d(TAG, "----onFailure------");
                                }
                            });
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

    public boolean validateForm(String FirstName, String LastName, String Email, String Password) {
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

        if (TextUtils.isEmpty(Email)) {
            mEmail.setError("Required.");
            valid = false;
        } else {
            mEmail.setError(null);
        }

        if (TextUtils.isEmpty(Password)) {
            mPassword.setError("Required.");
            valid = false;
        } else {
            mPassword.setError(null);
        }

        return valid;
    }
}
