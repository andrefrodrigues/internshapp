package com.example.pedrolopes.internshapp;

import android.content.Intent;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {
    Button loginButton;
    Button registerButton;
    EditText emailInput;
    EditText passwordInput;
    private Firebase loginFirebase;
    private String[] LoginExamples = {"c:1"};

    @Override
    protected void onStart() {
        super.onStart();
        loginFirebase = new Firebase("https://vivid-heat-3007.firebaseio.com/");
        //loginFirebase.setValue(LoginExamples);

        /*loginFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //LoginExamples =
                        ArrayList<Object> obj= (ArrayList<Object>) snapshot.getValue();
                LoginExamples = Arrays.copyOf(obj.toArray(),obj.size(),String[].class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
*/

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        loginButton = (Button)findViewById(R.id.login_button);
        registerButton = (Button)findViewById(R.id.register_button);
        emailInput = (EditText)findViewById(R.id.email_input);
        passwordInput = (EditText)findViewById(R.id.password_input);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                registerUser(email, password);
            }
        });
        Firebase.setAndroidContext(this);
    }

    private void registerUser(final String email, final String password) {
        Intent intent = new Intent(this, ChooseStudentorCompanyActivity.class);
        intent.putExtra("email", (Serializable)email);
        intent.putExtra("password", (Serializable)password);
        startActivity(intent);
        finish();
    }

    private void login() {
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        if (!email.equals("") && !password.equals("")) {
            for (String logins : LoginExamples) {
                String[] parts = logins.split(":");
                if (email.equals(parts[0]) && password.equals(parts[1])) {
                    Intent intent = new Intent(this, InternshipListActivity.class);
                    Student s = new Student("jose@gmail.com", "123", 1, "José", new Date(1992,04,21),
                            "Rua B", 214563322);
                    DataHolder.setCurrentType("student");
                    DataHolder.setCurrentStudent(s);
                    startActivity(intent);
                    finish();
                }
            }
        }


    }
}