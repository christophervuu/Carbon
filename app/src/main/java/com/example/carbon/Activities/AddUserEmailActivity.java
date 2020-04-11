package com.example.carbon.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carbon.HttpRequest.UserApi;
import com.example.carbon.Model.ModelAddUser;
import com.example.carbon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;

import java.time.Instant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddUserEmailActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AddUserEmail";

    // Declare Firebase
    private FirebaseAuth mAuth;

    UserApi userApi;

    private EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_email);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.AddUserEmailEditTextEmail);

        findViewById(R.id.AddUserEmailButtonConfirm).setOnClickListener(this);
    }

    public void AddUser(String email) {
        FirebaseUser currentUser = mAuth.getCurrentUser();

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


        ModelAddUser modelAddUser = new ModelAddUser(email, currentUser.getUid(), Instant.now().toString());
        Call<ModelAddUser> call = userApi.addUser(modelAddUser);

        call.enqueue(new Callback<ModelAddUser>() {
            @Override
            public void onResponse(Call<ModelAddUser> call, Response<ModelAddUser> response) {
                Log.w("2.0 getFeed > Full json res wrapped in gson => ", new Gson().toJson(response));

                if (!response.isSuccessful()) {
                    Log.d(TAG, "-----isFalse----");
                } else {
                    Log.d(TAG, "-----isSuccess----");
                }
            }

            @Override
            public void onFailure(Call<ModelAddUser> call, Throwable t) {
                Log.d(TAG, "----onFailure------");
                Log.e(TAG, t.getMessage());
                Log.d(TAG, "----onFailure------");
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.AddUserEmailButtonConfirm) {
            AddUser(mEmail.getText().toString());
            finish();
        }
    }
}
