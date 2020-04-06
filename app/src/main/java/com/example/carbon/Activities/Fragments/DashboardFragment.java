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

import com.example.carbon.Activities.MainActivity;
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

public class DashboardFragment extends Fragment implements DeviceAdapter.OnNoteListener {

    private DeviceAdapter adapter;
    private RecyclerView recyclerView;

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Retrofit retrofit = new Retrofit.Builder()
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
        });

        return view;
    }

    private void generateDeviceList(ArrayList<Info> deviceDataList, View view) {
        recyclerView = view.findViewById(R.id.RecyclerViewDeviceList);

        adapter = new DeviceAdapter(this, deviceDataList);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}
