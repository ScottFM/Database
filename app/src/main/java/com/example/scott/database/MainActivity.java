package com.example.scott.database;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText eUser, ePass;
    Button bLogin, bRegister;
    DatabaseAccess access = new DatabaseAccess(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews()
    {
        eUser = (EditText) findViewById(R.id.edtUser);
        ePass = (EditText) findViewById(R.id.edtPass);
        bLogin = (Button) findViewById(R.id.btnLogin);
        bLogin.setOnClickListener(this);
        bRegister = (Button) findViewById(R.id.btnRegister);
        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                if (!eUser.getText().toString().trim().isEmpty())
                {
                    if (!ePass.getText().toString().trim().isEmpty())
                    //Should attempt to sign in here
                    {
                        String user = eUser.getText().toString();
                        String pass = ePass.getText().toString();

                        String dbPass = access.userPass(user);
                        if (dbPass.equals(pass))
                        //Sign in to account
                        {
                            SharedPreferences s = getSharedPreferences("LOGIN", 0);
                            SharedPreferences.Editor e = s.edit();
                            e.putString("user", user);
                            e.commit();

                            Intent I = new Intent("com.example.Scott.Database.Home");
                            startActivity(I);
                            break;
                        }
                        else
                        {
                            Toast.makeText(this, "Incorrect password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    //Password not entered
                    {
                        Toast.makeText(this, "Password field is empty.", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                //Username not entered
                {
                    Toast.makeText(this, "Username field is empty.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnRegister:

                Intent I = new Intent("com.example.Scott.Database.Register");
                startActivity(I);
                break;
        }
    }
}
