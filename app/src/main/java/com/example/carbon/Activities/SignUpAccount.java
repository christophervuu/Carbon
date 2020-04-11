package com.example.carbon.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carbon.HttpRequest.UserApi;
import com.example.carbon.Model.ModelCreateUserAccount;
import com.example.carbon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import java.time.Instant;
import java.util.Calendar;
import java.util.UUID;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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

    private DatePickerDialog.OnDateSetListener mDateSetListener;

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

        mBirthDate = findViewById(R.id.SignUpAccountEditTextBirthDate);

        mBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        SignUpAccount.this,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = month + "/" + dayOfMonth + "/" + year;
                mBirthDate.setText(date);
            }
        };

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

        createAccount(FirstName, LastName, Email, Password);
    }

    private void createAccount(final String firstName, final String lastName, final String email, final String password) {
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

                            CreateUserInDatabase(firstName, lastName, email, password, user);

                            Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                            startActivity(intent);
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

    public void CreateUserInDatabase(String firstName, String lastName, String email, String password, FirebaseUser user) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rnozi7c90e.execute-api.us-east-2.amazonaws.com/Prod/app/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();

        userApi = retrofit.create(UserApi.class);

        ModelCreateUserAccount modelCreateUserAccount = new ModelCreateUserAccount(UUID.randomUUID().toString(), UUID.randomUUID().toString(), firstName, lastName, "2000-12-31", email, null, false, false, false, true, user.getUid(), Instant.now().toString(), Instant.now().toString());
        Call<ModelCreateUserAccount> call = userApi.createUser(modelCreateUserAccount);

        call.enqueue(new Callback<ModelCreateUserAccount>() {
            @Override
            public void onResponse(Call<ModelCreateUserAccount> call, Response<ModelCreateUserAccount> response) {
                Log.w("2.0 getFeed > Full json res wrapped in gson => ", new Gson().toJson(response));

                if (!response.isSuccessful()) {
                    Log.d(TAG, "-----isFalse----");
                } else {
                    Log.d(TAG, "-----isSuccess----");
                }
            }

            @Override
            public void onFailure(Call<ModelCreateUserAccount> call, Throwable t) {
                Log.d(TAG, "----onFailure------");
                Log.e(TAG, t.getMessage());
                Log.d(TAG, "----onFailure------");
            }
        });
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
