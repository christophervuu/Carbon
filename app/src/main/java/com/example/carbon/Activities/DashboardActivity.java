package com.example.carbon.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.carbon.Adapters.DeviceAdapter;
import com.example.carbon.HttpRequest.JsonPlaceHolderApi;
import com.example.carbon.Model.DeviceResponse;
import com.example.carbon.Model.Info;
import com.example.carbon.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity {

    private DeviceAdapter adapter;
    private RecyclerView recyclerView;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/christophervuu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<DeviceResponse> call = jsonPlaceHolderApi.getDeviceResponse();

        call.enqueue(new Callback<DeviceResponse>() {
            @Override
            public void onResponse(Call<DeviceResponse> call, Response<DeviceResponse> response) {

                generateDeviceList(response.body().getInfo());
            }

            @Override
            public void onFailure(Call<DeviceResponse> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Something went wrong... Please try again later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDeviceList(ArrayList<Info> deviceDataList) {
        recyclerView = findViewById(R.id.RecyclerViewDeviceList);

        adapter = new DeviceAdapter(deviceDataList);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        recyclerView.setAdapter(adapter);
    }
}
