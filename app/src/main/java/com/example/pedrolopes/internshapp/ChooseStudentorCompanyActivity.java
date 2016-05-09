package com.example.pedrolopes.internshapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class ChooseStudentorCompanyActivity extends Activity {

    String email;
    String password;
    Button chooseStudent;
    Button chooseCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_studentor_company);

        email = (String) getIntent().getSerializableExtra("email");
        password = (String) getIntent().getSerializableExtra("password");
        chooseCompany = (Button)findViewById(R.id.buttonCompany);
        chooseStudent = (Button)findViewById(R.id.buttonStudent);

        chooseCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToCompanyRegister();
            }
        });
        chooseStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToStudentRegister();
            }
        });



    }

    private void changeToCompanyRegister() {
        Intent intent = new Intent(this, RegisterCompanyActivity.class);
        intent.putExtra("email", (Serializable)email);
        intent.putExtra("password", (Serializable)password);
        startActivity(intent);
    }
    private void changeToStudentRegister() {
        Intent intent = new Intent(this, RegisterStudentsActivity.class);
        intent.putExtra("email", (Serializable)email);
        intent.putExtra("password", (Serializable)password);
        startActivity(intent);
    }

}
