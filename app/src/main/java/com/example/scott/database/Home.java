package com.example.scott.database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Scott on 4/26/2017.
 */

public class Home extends AppCompatActivity{

    TextView welcome;
    String user;
    DatabaseAccess access = new DatabaseAccess(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        initViews();
    }

    private void initViews()
    {
        welcome = (TextView) findViewById(R.id.txtWelcome);

        SharedPreferences s = getSharedPreferences("LOGIN", 0);
        user = s.getString("user", "");

        welcome.setText("Welcome, " + user +"!");
    }
}