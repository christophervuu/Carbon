package com.example.carbon.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.carbon.Activities.AddUserActivity;
import com.example.carbon.HttpRequest.UserApi;
import com.example.carbon.Model.UserList;
import com.example.carbon.Model.UserRecords;
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

public class AccountFragment extends Fragment  {
    private static final String TAG = "AccountFragment";

    private UserApi userApi;

    // Declare Firebase
    private FirebaseAuth mAuth;

    TextView mFragmentAccountUserList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_account, container, false);

        final Intent intent = new Intent(getActivity(), AddUserActivity.class);
        Button mButton = view.findViewById(R.id.FragmentAccountButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        mFragmentAccountUserList = view.findViewById(R.id.FragmentAccountUserList);
/*
        try {

        OkHttpClient client = new OkHttpClient();

        String JSON = "{\"FirebaseId\": \"WmrzPH8zlGhGisXHZhK18MKsQVD2\"}";

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), JSON);

        Request request = new Request.Builder()
                .url("https://rnozi7c90e.execute-api.us-east-2.amazonaws.com/Prod/app/user/getaccountusers")
                .post(body)
                .build();


        String jsonData = "";

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

            try (Response response = client.newCall(request).execute()) {
                jsonData = response.body().string();
            } catch (Exception e) {
                Log.w("Failed:  ", e);
            }
        }

            JSONObject Jobject = new JSONObject(jsonData);
            JSONArray Jarray = Jobject.getJSONArray("body");

            //define the strings that will temporary store the data
            String fname,lname;

            //get the length of the json array
            int limit = Jarray.length();

            //datastore array of size limit
            String dataStore[] = new String[limit];

            for (int i = 0; i < Jarray.length(); i++) {
                JSONObject object     = Jarray.getJSONObject(i);

                fname = object.getString("firstName");
                lname = object.getString("lastName");

                //store the data into the array
                dataStore[i] = fname + " " + lname;
            }

            String text = "";

            for (String content : dataStore ) {
                text += content + "\n";
            }

            mFragmentAccountUserList.setText(text);

        } catch (Exception e) {
            Log.d("ARRAY CONTENT", e.toString());
        }


*/

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

        UserRecords userRecords = new UserRecords(mAuth.getCurrentUser().getUid());
        Call<UserRecords> call = userApi.getaccountusers(userRecords);

        call.enqueue(new Callback<UserRecords>() {
            @Override
            public void onResponse(Call<UserRecords> call, Response<UserRecords> response) {


                ArrayList<UserList> list = response.body().getBody();

                String text = "";

                for (int i = 0; i < list.size(); i++) {
                    text += list.get(i).getFirstName() + " " + list.get(i).getLastName() + "\n";
                }

                mFragmentAccountUserList.setText(text);

                if (!response.isSuccessful()) {
                    Log.d(TAG, "-----isFalse----");
                } else {
                    Log.d(TAG, "-----isSuccess----");
                }
            }

            @Override
            public void onFailure(Call<UserRecords> call, Throwable t) {
                Log.d(TAG, "----onFailure------");
                Log.e(TAG, t.getMessage());
                Log.d(TAG, "----onFailure------");
            }
        });


        return view;
    }
}
