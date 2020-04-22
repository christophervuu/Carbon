package com.example.carbon.Activities.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carbon.HttpRequest.UserApi;
import com.example.carbon.Model.ModelAddDevice;
import com.example.carbon.R;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Console;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddDeviceFragment extends Fragment implements View.OnClickListener{

    // Declare Firebase
    private FirebaseAuth mAuth;

    private EditText mFragmentAddDeviceEditTextDevEUI, mFragmentAddDeviceEditTextName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_device, container, false);

        mAuth = FirebaseAuth.getInstance();

        mFragmentAddDeviceEditTextDevEUI = view.findViewById(R.id.FragmentAddDeviceEditTextDevEUI);
        mFragmentAddDeviceEditTextName = view.findViewById(R.id.FragmentAddDeviceEditTextName);

        view.findViewById(R.id.FragmentAddDeviceButtonAdd).setOnClickListener(this);

        return view;
    }

    private void UpdateDeviceInDatabase(String devEUI, String name) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rnozi7c90e.execute-api.us-east-2.amazonaws.com/Prod/app/device/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();

        UserApi userApi = retrofit.create(UserApi.class);

        ModelAddDevice modelAddDevice = new ModelAddDevice(mAuth.getCurrentUser().getUid(), devEUI, name);
        Call<ModelAddDevice> call = userApi.addDevice(modelAddDevice);

        call.enqueue(new Callback<ModelAddDevice>() {
            @Override
            public void onResponse(Call<ModelAddDevice> call, Response<ModelAddDevice> response) {
                Toast.makeText(getActivity(), "Device has been added to the account!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ModelAddDevice> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong... Please try again later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.FragmentAddDeviceButtonAdd) {
            UpdateDeviceInDatabase(mFragmentAddDeviceEditTextDevEUI.getText().toString(), mFragmentAddDeviceEditTextName.getText().toString());
        }
    }
}
