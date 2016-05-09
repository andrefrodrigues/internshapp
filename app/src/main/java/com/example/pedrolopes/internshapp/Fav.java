package com.example.pedrolopes.internshapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Fav extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    Button submit;
    Spinner spinner1, spinner2, spinner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //aqui muda-se a cor e e so acrescentar ao gosto e tal
        myToolbar.getBackground().setColorFilter(Color.parseColor("#019439"), PorterDuff.Mode.MULTIPLY);
        myToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        ActionBar bar = getSupportActionBar();
        bar.setTitle("Favoritos");

        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        submit = (Button) findViewById(R.id.sub);

        List<String> list = new ArrayList<String>();
        HashSet<Internship> set = DataHolder.getCurrentStudent().getFavoriteInterships();
        for(Internship i: set){
            list.add(i.getTitle());
        }

        final ArrayList<String> internshipsAll = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);        // Assign adapter to ListView
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);

        final Spinner s1 = spinner1;
        final Spinner s2 = spinner2;
        final Spinner s3 = spinner3;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataHolder.getCurrentStudent().chooseInternshipPriority(1, DataHolder.getCurrentStudent().getFavByName(s1.getSelectedItem().toString()));
                DataHolder.getCurrentStudent().chooseInternshipPriority(2, DataHolder.getCurrentStudent().getFavByName(s2.getSelectedItem().toString()));
                DataHolder.getCurrentStudent().chooseInternshipPriority(3, DataHolder.getCurrentStudent().getFavByName(s3.getSelectedItem().toString()));


            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_profile:
                menuPersonalInfo();
                return true;
            case R.id.action_chosen:
                Intent intent = new Intent(this, Chosen.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.action_favorites:
                Intent intent2 = new Intent(this, Fav.class);
                startActivity(intent2);
                finish();
                return true;
            case R.id.action_prop:
                Intent intent3 = new Intent(this, InternshipListActivity.class);
                startActivity(intent3);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void menuPersonalInfo() {
        Intent intent = new Intent(this, PersonalInfoActivity.class);
        startActivity(intent);
        finish();
    }
}
