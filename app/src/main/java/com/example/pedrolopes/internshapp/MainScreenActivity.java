package com.example.pedrolopes.internshapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainScreenActivity extends AppCompatActivity {

    ListView internships;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.getBackground().setColorFilter(Color.parseColor("#019439"), PorterDuff.Mode.MULTIPLY);
        myToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        ActionBar bar = getSupportActionBar();
        bar.setTitle("Página Inicial");


        internships = (ListView) findViewById(R.id.internship_offers);

        // Defined Array values to show in ListView
        Company c = new Company("A", "A", 9, "Rua A");
        ArrayList<Internship> internshipsAll = new ArrayList<Internship>();
        internshipsAll.add(new Internship(0, "Estágio A", "Fazer coise",
                new Coordinator("João", "joao@paulo.pt", 213934999), c));
        internshipsAll.add(new Internship(1, "Estágio B", "Fazer outra coisa",
                new Coordinator("Gui", "gui@baba.pt", 964832218), c));
        internshipsAll.add(new Internship(2, "Estágio C", "Faltar ao trabalho",
                new Coordinator("Badjoras", "badjoras@pop.pt", 964854548), c));

        String[] values = new String[internshipsAll.size()];
        int i=0;
        for (Internship inter : internshipsAll) {
            values[i] = inter.getTitle() + "\n" + inter.getCompany().getName();
            i++;
        }
        /*String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };*/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        internships.setAdapter(adapter);
    }
}
