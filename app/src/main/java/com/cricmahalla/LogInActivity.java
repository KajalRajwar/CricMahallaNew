package com.cricmahalla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cricmahalla.ui.social.social;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LogInActivity extends AppCompatActivity implements OnClickListener, View.OnKeyListener {
    Boolean signUpModeActive = true;
    Button signUpButton;
    Button logInButton;
    EditText userEmail;
    EditText userName;
    EditText userPassword;

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
            signUpClicked(view);
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.logInButton){
           signUpButton = findViewById(R.id.signUpButton);
            if(signUpModeActive){
                signUpModeActive = false;
                signUpButton.setText("Log In");
                logInButton.setText("Sign Up");
            }else{
                signUpModeActive = true;
                signUpButton.setText("Sign Up");
                logInButton.setText("Log In");
            }
        }else if (view.getId() == R.id.logoImageView || view.getId() == R.id.backgroundLayout){
            InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void signUpClicked(View view){
        if(userEmail.getText().toString().matches("") || userName.getText().toString().matches("")|| userPassword.getText().toString().matches("")){
            Toast.makeText(this, "A email,user name and a password required ", Toast.LENGTH_SHORT).show();
        }else{
            if(signUpModeActive) {
                ParseUser user = new ParseUser();
                user.setEmail(userEmail.getText().toString());
                user.setUsername(userName.getText().toString());
                user.setPassword(userPassword.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i("SignUP", "Success");
                            Toast.makeText(LogInActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                            //After sign up open social fragment
                            Intent intent = new Intent(getApplicationContext(), social.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LogInActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else{
                ParseUser.logInInBackground(userName.getText().toString(), userPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null){
                            Toast.makeText(LogInActivity.this, "Log In Successful", Toast.LENGTH_SHORT).show();
                            //After Log In open social fragment
                            Intent intent = new Intent(getApplicationContext(), social.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LogInActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    public void logInClicked(View view){
        Intent intent = new Intent(getApplicationContext(), LogInActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        logInButton = findViewById(R.id.logInButton);
        logInButton.setOnClickListener(this);

        userEmail =findViewById(R.id.userEmail);
        userName =findViewById(R.id.userName);
        userPassword =findViewById(R.id.userPassword);

        ImageView logoImageView = findViewById(R.id.logoImageView);
        ConstraintLayout backgroundLayout =findViewById(R.id.backgroundLayout);

        logoImageView.setOnClickListener(this);
        backgroundLayout.setOnClickListener(this);

        userPassword.setOnKeyListener(this);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}