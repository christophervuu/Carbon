package com.example.carbon.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carbon.HttpRequest.UserApi;
import com.example.carbon.Model.DeviceProfile;
import com.example.carbon.R;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarbonMonoxideActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mCarbonMonoxideTextViewDeviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_monoxide);

        mCarbonMonoxideTextViewDeviceId = findViewById(R.id.CarbonMonoxideTextViewDeviceId);

        findViewById(R.id.CarbonMonoxideImageViewBack).setOnClickListener(this);

        String DeviceId = getIntent().getStringExtra("DeviceId");

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

        DeviceProfile deviceProfile = new DeviceProfile(DeviceId);
        Call<DeviceProfile> call = userApi.getDeviceProfile(deviceProfile);

        call.enqueue(new Callback<DeviceProfile>() {
            @Override
            public void onResponse(Call<DeviceProfile> call, Response<DeviceProfile> response) {
                Log.i("Response, DeviceId: ", response.body().getType());
            }

            @Override
            public void onFailure(Call<DeviceProfile> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.CarbonMonoxideImageViewBack) {
            this.finish();
        }
    }
}
