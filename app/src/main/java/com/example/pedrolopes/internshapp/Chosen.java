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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class Chosen extends AppCompatActivity implements Serializable {
    ListView internships;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship_list);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //aqui muda-se a cor e e so acrescentar ao gosto e tal
        myToolbar.getBackground().setColorFilter(Color.parseColor("#019439"), PorterDuff.Mode.MULTIPLY);
        myToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        ActionBar bar = getSupportActionBar();
        bar.setTitle("Escolhas");
        internships = (ListView) findViewById(R.id.internship_offers);        // Defined Array values to show in ListView
        final ArrayList<Internship> internshipsAll = new ArrayList<Internship>();
        internshipsAll.addAll(DataHolder.getCurrentStudent().getChosenInterships().values());
        String[] values = new String[internshipsAll.size()];
        int i = 0;
        for (Internship inter : internshipsAll) {
            values[i] = i+1 + ": " + inter.getTitle();
            i++;
        }

     /*   String s1, s2, s3;
        s1 = DataHolder.getCurrentStudent().getChosenInterships().get(1).getTitle();
        s2 = DataHolder.getCurrentStudent().getChosenInterships().get(2).getTitle();
        s3 = DataHolder.getCurrentStudent().getChosenInterships().get(3).getTitle();
        int p1,p2,p3;*/



       /*String[] values = new String[] { "Android List View",
               "Adapter implementation",
               "Simple List View In Android",
               "Create List View Android",
               "Android Example",
               "List View Source Code",
               "List View Array Adapter",
               "Android Example List View"
       };*/
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);        // Assign adapter to ListView
        internships.setAdapter(adapter);
/*
        p1 = adapter.getPosition(s1);
        p2 = adapter.getPosition(s2);
        p3 = adapter.getPosition(s3);

*/
        final Chosen th = this;
        internships.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Internship selectedFromList = internshipsAll.get(position);
                Intent intent = new Intent(th, InternshipDescriptionActivity.class);
                intent.putExtra("Internship", (Serializable) selectedFromList);
                startActivity(intent);
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