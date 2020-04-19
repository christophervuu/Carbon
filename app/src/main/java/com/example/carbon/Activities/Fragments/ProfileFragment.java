package com.example.carbon.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carbon.Activities.ForgotPassword;
import com.example.carbon.Activities.SignUpAccount;
import com.example.carbon.HttpRequest.JsonPlaceHolderApi;
import com.example.carbon.HttpRequest.UserApi;
import com.example.carbon.Model.ModelCreateUserAccount;
import com.example.carbon.Model.ModelResponseUser;
import com.example.carbon.Model.ModelUpdateUser;
import com.example.carbon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import java.time.Instant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ProfileFragment";

    private TextView mName;

    private EditText mFirstName, mLastName;
    private EditText mEmail, mPhone;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();

        mName = view.findViewById(R.id.FragmentProfileName);

        mFirstName = view.findViewById(R.id.FragmentProfileEditTextChangeFirstName);
        mLastName = view.findViewById(R.id.FragmentProfileEditTextChangeLastName);
        mEmail = view.findViewById(R.id.FragmentProfileEditTextEmail);
        mPhone = view.findViewById(R.id.FragmentProfileEditTextPhone);

        view.findViewById(R.id.FragmentProfileButton).setOnClickListener(this);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rnozi7c90e.execute-api.us-east-2.amazonaws.com/Prod/app/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();

        UserApi userApi = retrofit.create(UserApi.class);

        ModelResponseUser modelResponseUser = new ModelResponseUser(null, null, null, null, mAuth.getCurrentUser().getUid());
        Call<ModelResponseUser> call = userApi.getProfile(modelResponseUser);

        call.enqueue(new Callback<ModelResponseUser>() {
            @Override
            public void onResponse(Call<ModelResponseUser> call, Response<ModelResponseUser> response) {
                Log.w("2.0 getFeed > Full json res wrapped in gson => ", new Gson().toJson(response.body().getFirstName()));

                String name = response.body().getFirstName() + " " + response.body().getLastName();
                mName.setText(name);
                mFirstName.setText(response.body().getFirstName());
                mLastName.setText(response.body().getLastName());
                mPhone.setText(response.body().getPhone());
                mEmail.setText(response.body().getEmail());


                if (!response.isSuccessful()) {
                    Log.d(TAG, "-----isFalse----");
                } else {
                    Log.d(TAG, "-----isSuccess----");
                }
            }

            @Override
            public void onFailure(Call<ModelResponseUser> call, Throwable t) {
                Log.d(TAG, "----onFailure------");
                Log.e(TAG, t.getMessage());
                Log.d(TAG, "----onFailure------");
            }
        });

        return view;
    }

    public void UpdateUser(String FirstName, String LastName, String Email, String Phone) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rnozi7c90e.execute-api.us-east-2.amazonaws.com/Prod/app/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserApi userApi = retrofit.create(UserApi.class);

        ModelUpdateUser modelUpdateUser = new ModelUpdateUser(FirstName, LastName, Email, Phone, mAuth.getCurrentUser().getUid(), Instant.now().toString());
        Call<ModelUpdateUser> call = userApi.updateUser(modelUpdateUser);

        call.enqueue(new Callback<ModelUpdateUser>() {
            @Override
            public void onResponse(Call<ModelUpdateUser> call, Response<ModelUpdateUser> response) {

                if (!response.isSuccessful()) {
                    Log.d(TAG, "-----isFalse----");
                } else {
                    Log.d(TAG, "-----isSuccess----");
                    Toast.makeText(getActivity(), "Your profile has been updated.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ModelUpdateUser> call, Throwable t) {
                Log.d(TAG, "----onFailure------");
                Log.e(TAG, t.getMessage());
                Log.d(TAG, "----onFailure------");
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.FragmentProfileButton) {
            UpdateUser(mFirstName.getText().toString(), mLastName.getText().toString(), mEmail.getText().toString(), mPhone.getText().toString());
        }
    }
}
