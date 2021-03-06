package com.example.scott.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Scott on 4/26/2017.
 */

public class Register extends AppCompatActivity implements View.OnClickListener {

    EditText eUser, ePass, eConfirm;
    Button bRegister;
    DatabaseAccess access = new DatabaseAccess(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        initViews();
    }

    private void initViews()
    {
        eUser = (EditText) findViewById(R.id.edtUser);
        ePass = (EditText) findViewById(R.id.edtPass);
        eConfirm = (EditText) findViewById(R.id.edtConfirm);
        bRegister = (Button) findViewById(R.id.btnRegister);
        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnRegister:

                if (!eUser.getText().toString() .trim().isEmpty())
                {
                    if (!ePass.getText().toString().trim().isEmpty())
                    {
                        String pass = ePass.getText().toString();
                        String confirm = eConfirm.getText().toString();

                        if (pass.equals(confirm))
                        //Passwords match
                        {
                            String user = eUser.getText().toString();

                            long conflict = access.addUser(user, pass);

                            if (conflict == -1)
                            //Username already exists
                            {
                                Toast.makeText(this, "Username not available.", Toast.LENGTH_SHORT).show();
                            }
                            else
                            //Worked fine and user added
                            {
                                Intent I = new Intent("com.example.Scott.Database.MainActivity");
                                startActivity(I);
                                break;
                            }
                        }
                        else
                        //Passwords don't match
                        {
                            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                        }

                    } else
                    //Password not entered
                    {
                        Toast.makeText(this, "Password field is empty.", Toast.LENGTH_SHORT).show();
                    }
                } else
                //Username not entered
                {
                    Toast.makeText(this, "Username field is empty.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
