package com.example.carbon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity {

    private TextView textViewResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private ArrayList<Info> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/christophervuu/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        //getPosts();
        //getComments();
        getInfo();
    }

    private void getInfo() {
        Call<DeviceResponse> call = jsonPlaceHolderApi.getDeviceResponse();

        call.enqueue(new Callback<DeviceResponse>() {
            @Override
            public void onResponse(Call<DeviceResponse> call, Response<DeviceResponse> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

               List<Info> infos = response.body().getInfo();

                for (Info info : infos) {
                    String content = "";
                    content += "DeviceID: " + info.getDeviceId() + "\n";
                    content += "DeviceType: " + info.getDeviceType() + "\n";
                    content += "DeviceName: " + info.getDeviceName() + "\n";
                    content += "UpdatedOn: " + info.getUpdatedOn() + "\n";
                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<DeviceResponse> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    /*private void getPosts() {
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(4);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";;

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getComments() {
        Call<List<Comments>> call = jsonPlaceHolderApi.getComments(3);

        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Comments> comments = response.body();

                for (Comments comment : comments) {
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post ID: " + comment.getPostId() + "\n";
                    content += "Name: " + comment.getName() + "\n";
                    content += "Email: " + comment.getEmail() + "\n";
                    content += "Text: " + comment.getText() + "\n\n";;

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }*/
}
