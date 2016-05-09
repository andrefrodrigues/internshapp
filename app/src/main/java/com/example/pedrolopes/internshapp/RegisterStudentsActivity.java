package com.example.pedrolopes.internshapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import static com.example.pedrolopes.internshapp.R.*;

public class RegisterStudentsActivity extends AppCompatActivity {

    Button registerButton;
    EditText nome;
    EditText number;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_register_students);
        Toolbar toolbar = (Toolbar) findViewById(id.toolbar);
        setSupportActionBar(toolbar);

        registerButton = (Button)findViewById(id.button_register_aluno);
        nome = (EditText)findViewById(id.nomeAluno);
        number = (EditText)findViewById(id.nAluno);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerStudent(nome.getText().toString(), number.getText().toString(), new Date(1994,05,31), "Rua A", 210002211);
            }
        });

        email = (String) getIntent().getSerializableExtra("email");
        password = (String) getIntent().getSerializableExtra("password");

    }

    private void registerStudent(String nome, String number, Date birth, String address, int phone) {

        Student student = new Student(email, password, Integer.parseInt(number), nome, birth, address, phone);



    }

}
