package com.example.carbon.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carbon.HttpRequest.UserApi;
import com.example.carbon.Model.ModelCreateUserAccount;
import com.example.carbon.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.gson.Gson;

import java.time.Instant;
import java.util.UUID;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthenticationActivity extends AppCompatActivity implements View.OnClickListener {

    // TAGS
    private static final String TAG = "EmailPassword";
    private static final String GOOGLETAG = "Google Activity";
    private static final int RC_SIGN_IN = 9001;

    // Declare Firebase and Google
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    // Declare EditText variables
    private EditText mEmailField, mPasswordField;

    //Declare UserApi
    UserApi userApi;

    private ProgressBar mProgressBar;
    private RelativeLayout mProgressBarContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Views
        mEmailField = findViewById(R.id.EmailEditText);
        mPasswordField = findViewById(R.id.PasswordEditText);

        setProgressBar(R.id.progressBar);
        mProgressBarContainer = findViewById(R.id.ProgressBarContainer);

        // Button listeners
        findViewById(R.id.SignInButton).setOnClickListener(this);
        findViewById(R.id.SignUpTextView).setOnClickListener(this);
        findViewById(R.id.ForgotPasswordTextView).setOnClickListener(this);
        findViewById(R.id.sign_in_button).setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if the user is already signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                FirebaseAuthWithGoogle(account);

            } catch (ApiException e) {
                Log.w(GOOGLETAG, "Google sign in failed", e);
                //updateUI(null);
            }
        }
    }


    // Authenticate using email and password
    private void signIn(String email, String password) {
        Log.d(TAG, "signIx`n:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressBar();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);

                            Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AuthenticationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            //mStatusTextView.setText(R.string.auth_failed);
                        }
                        hideProgressBar();
                    }
                });
    }

    // Authenticate with Google
    private void FirebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(GOOGLETAG, "FirebaseAuthWithGoogle:" + acct.getId());

        //showProgressBar();

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);

                            GoogleCheckEmailExist(user, user.getEmail());

                            Intent intent = new Intent(AuthenticationActivity.this, NavigationDrawerActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AuthenticationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        //showProgressBar();
                    }
                });
    }

    private void GoogleSignIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void GoogleCheckEmailExist(final FirebaseUser user, String email) {
        mAuth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if (!task.isSuccessful()) {
                            CreateUserInDatabase(user);
                        }
                    }
                });
    }

    private void CreateUserInDatabase(FirebaseUser user) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rnozi7c90e.execute-api.us-east-2.amazonaws.com/Prod/app/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build();

        userApi = retrofit.create(UserApi.class);

        ModelCreateUserAccount modelCreateUserAccount = new ModelCreateUserAccount(UUID.randomUUID().toString(), UUID.randomUUID().toString(), user.getDisplayName(), user.getDisplayName(), "2000-12-31", user.getEmail(), null, false, false, false, true, user.getUid(), Instant.now().toString(), Instant.now().toString());
        Call<ModelCreateUserAccount> call = userApi.createUser(modelCreateUserAccount);

        call.enqueue(new Callback<ModelCreateUserAccount>() {
            @Override
            public void onResponse(Call<ModelCreateUserAccount> call, Response<ModelCreateUserAccount> response) {
                Log.w("2.0 getFeed > Full json res wrapped in gson => ", new Gson().toJson(response));

                if (!response.isSuccessful()) {
                    Log.d(TAG, "-----isFalse----");
                } else {
                    Log.d(TAG, "-----isSuccess----");
                }
            }

            @Override
            public void onFailure(Call<ModelCreateUserAccount> call, Throwable t) {
                Log.d(TAG, "----onFailure------");
                Log.e(TAG, t.getMessage());
                Log.d(TAG, "----onFailure------");
            }
        });
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.SignUpTextView) {
            Intent intent = new Intent(getApplicationContext(), SignUpAccount.class);
            startActivity(intent);
        } else if (i == R.id.ForgotPasswordTextView) {
            Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        } else if (i == R.id.SignInButton) {
            signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
        } else if (i == R.id.sign_in_button) {
            GoogleSignIn();
        }
    }

    public void setProgressBar(int resId) {
        mProgressBar = findViewById(resId);
    }

    public void showProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBarContainer.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.INVISIBLE);
            mProgressBarContainer.setVisibility(View.INVISIBLE);
        }
    }

    public void hideKeyboard(View view) {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressBar();
    }
}
