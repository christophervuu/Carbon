package com.example.carbon.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carbon.Activities.AddUserEmailActivity;
import com.example.carbon.HttpRequest.JsonPlaceHolderApi;
import com.example.carbon.HttpRequest.UserApi;
import com.example.carbon.Model.ModelGetAccountUsers;
import com.example.carbon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountFragment extends Fragment  {
    private static final String TAG = "AccountFragment";

    private UserApi userApi;

    // Declare Firebase
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_account, container, false);

        final Intent intent = new Intent(getActivity(), AddUserEmailActivity.class);
        Button mButton = view.findViewById(R.id.FragmentAccountButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rnozi7c90e.execute-api.us-east-2.amazonaws.com/Prod/app/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

         userApi = retrofit.create(UserApi.class);

        ModelGetAccountUsers modelGetAccountUsers = new ModelGetAccountUsers(mAuth.getCurrentUser().getUid());
        Call<ModelGetAccountUsers> call = userApi.getAccountUsers(modelGetAccountUsers);

        call.enqueue(new Callback<ModelGetAccountUsers>() {
            @Override
            public void onResponse(Call<ModelGetAccountUsers> call, Response<ModelGetAccountUsers> response) {
                Log.w("2.0 getFeed > Full json res wrapped in gson => ", new Gson().toJson(response));

                if (!response.isSuccessful()) {
                    Log.d(TAG, "-----isFalse----");
                } else {
                    Log.d(TAG, "-----isSuccess----");
                }
            }

            @Override
            public void onFailure(Call<ModelGetAccountUsers> call, Throwable t) {
                Log.d(TAG, "----onFailure------");
                Log.e(TAG, t.getMessage());
                Log.d(TAG, "----onFailure------");
            }
        });

        return view;
    }
}
