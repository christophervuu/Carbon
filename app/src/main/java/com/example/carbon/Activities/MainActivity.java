package com.example.carbon.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carbon.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    //private static final String TAG1 = "Token";
    private static final String TAG = "MyFirebaseMsgService";

    //private FirebaseAuth mAuth;

    private TextView mTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mTest = findViewById(R.id.test);

        findViewById(R.id.TestButton).setOnClickListener(this);
        */

        Button logTokenButton = findViewById(R.id.TestButton);
        logTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseInstanceId.getInstance().getInstanceId()
                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                            @Override
                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "getInstanceId failed", task.getException());
                                    return;
                                }

                                String token = task.getResult().getToken();



                                String msg = getString(R.string.msg_token_fmt, token);

                                String date = Instant.now().toString();


                                Log.d(TAG, msg);
                                Toast.makeText(MainActivity.this, date, Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });

    }

    /*
    public void retreieveTokens() {
        mTest.setText(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.TestButton) {
            retreieveTokens();
        }
    }*/
}
