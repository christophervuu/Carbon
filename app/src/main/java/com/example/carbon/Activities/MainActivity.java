package com.example.carbon.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carbon.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Token";

    private FirebaseAuth mAuth;

    private TextView mTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mTest = findViewById(R.id.test);

        findViewById(R.id.TestButton).setOnClickListener(this);
    }

    public void retreieveTokens() {
        mTest.setText(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.TestButton) {
            retreieveTokens();
        }
    }
}
