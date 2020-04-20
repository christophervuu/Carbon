package com.example.carbon.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carbon.Activities.CarbonMonoxideActivity;
import com.example.carbon.Adapters.DeviceAdapter;
import com.example.carbon.HttpRequest.UserApi;
import com.example.carbon.Model.DeviceList;
import com.example.carbon.Model.DeviceRecords;
import com.example.carbon.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardFragment extends Fragment implements DeviceAdapter.OnNoteListener {

    private DeviceAdapter adapter;
    private RecyclerView recyclerView;

    // Declare Firebase
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/christophervuu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<DeviceResponse> call = jsonPlaceHolderApi.getDeviceResponse();

        call.enqueue(new Callback<DeviceResponse>() {
            @Override
            public void onResponse(Call<DeviceResponse> call, Response<DeviceResponse> response) {
                generateDeviceList(response.body().getInfo(), view);
            }

            @Override
            public void onFailure(Call<DeviceResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong... Please try again later!", Toast.LENGTH_SHORT).show();
            }
        });*/

        mAuth = FirebaseAuth.getInstance();

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

        DeviceRecords deviceRecords = new DeviceRecords(mAuth.getCurrentUser().getUid());
        Call<DeviceRecords> call = userApi.getDeviceList(deviceRecords);

        call.enqueue(new Callback<DeviceRecords>() {
            @Override
            public void onResponse(Call<DeviceRecords> call, Response<DeviceRecords> response) {
                generateDeviceList(response.body().getBody(), view);
            }

            @Override
            public void onFailure(Call<DeviceRecords> call, Throwable t) {
                Toast.makeText(getActivity(), "Something went wrong... Please try again later!", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    private void generateDeviceList(ArrayList<DeviceList> deviceDataList, View view) {
        recyclerView = view.findViewById(R.id.RecyclerViewDeviceList);

        adapter = new DeviceAdapter(this, deviceDataList);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNoteClick(int position, ArrayList<DeviceList> dataList) {
        Intent intent = new Intent(getActivity(), CarbonMonoxideActivity.class);
        intent.putExtra("DeviceId", dataList.get(position).getDeviceId());
        startActivity(intent);
    }
}
