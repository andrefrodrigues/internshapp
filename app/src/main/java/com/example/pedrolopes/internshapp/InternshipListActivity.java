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

public class InternshipListActivity extends AppCompatActivity implements Serializable {
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
        bar.setTitle("Página Inicial");
        internships = (ListView) findViewById(R.id.internship_offers);        // Defined Array values to show in ListView
        Company c = new Company("OutSystems Portugal", "outsystems@outsystems.pt", 961063549, "R. Central Park 2A, 2795-242 Linda-a-Velha");
        Company c1 = new Company("FaberVentures", "contact@faber-ventures.com", 961063549, "Avenida da Liberdade, 230");
        Company c2 = new Company("CaixaMágica", "geral@caixamagica.pt", 961063549, "Rua Soeiro Pereira Gomes");
        Company c3 = new Company("NOVABASE", "info@novabase.pt", 961063549, "Av. D. Joao II");


        final ArrayList<Internship> internshipsAll = new ArrayList<Internship>();
        internshipsAll.add(new Internship(0, "Desenvolvimento Dinamico", "Desenvolver uma aplicação que requer conhecimentos de programação dinâmica",
                new Coordinator("João", "joao@paulo.pt", 213934999), c));
        internshipsAll.add(new Internship(1, "Android App", "Aplicação mobile",
                new Coordinator("Gui", "gui@baba.pt", 964832218), c));
        internshipsAll.add(new Internship(2, "WebRTC-M", "Servidor webrtc multiponto",
                new Coordinator("Badjoras", "badjoras@pop.pt", 964854548), c1));
        internshipsAll.add(new Internship(3, "Software Development", "Desenvolvimento de plataforma de gestão",
                new Coordinator("Badjoras", "badjoras@pop.pt", 964854548), c1));
        internshipsAll.add(new Internship(4, "DerpBox", "Serviço cloud desenvolvido com requinte",
                new Coordinator("Senhor António", "ahaha@pop.pt", 964854548), c2));
        internshipsAll.add(new Internship(5, "Aplicação Linux", "Aplicação desenvolvida no ambiente linux",
                new Coordinator("Senhor Atónio", "ahaha@pop.pt", 964854548), c2));
        internshipsAll.add(new Internship(6, "Conference with x participants", "Criação de software de conferencias de video",
                new Coordinator("Manel", "ahaha@pop.pt", 964854548), c3));
        internshipsAll.add(new Internship(7, "Click to interact", "Chat incorporado em pagina web",
                new Coordinator("Manel", "ahaha@pop.pt", 964854548), c3));
        String[] values = new String[internshipsAll.size()];
        int i = 0;
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
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);        // Assign adapter to ListView
        internships.setAdapter(adapter);
        final InternshipListActivity th = this;
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