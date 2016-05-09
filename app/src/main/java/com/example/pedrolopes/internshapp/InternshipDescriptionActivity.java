package com.example.pedrolopes.internshapp;import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;import java.util.ArrayList;import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
public class InternshipDescriptionActivity extends AppCompatActivity implements Serializable{
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    Company comp;
   CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internship_description);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        myToolbar.getBackground().setColorFilter(Color.parseColor("#019439"), PorterDuff.Mode.MULTIPLY);
        myToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        setSupportActionBar(myToolbar);
        ActionBar bar = getSupportActionBar();
        bar.setTitle("Descrição de Estágio");
        Internship data = (Internship) getIntent().getSerializableExtra("Internship");
        TextView txt = (TextView)findViewById(R.id.textViewOfferNum);
        CheckBox button = (CheckBox) findViewById(R.id.checkBox);
        if(DataHolder.getCurrentStudent().getFavByName(data.getTitle().toString())!=null)
            button.setChecked(true);
        if(DataHolder.getCurrentStudent().getFavByName(data.getTitle().toString())==null)
            button.setChecked(false);



        txt.setText(String.valueOf(data.getId()));
        comp = data.getCompany();
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);        // preparing list data
        prepareListData(data);
        listAdapter = new com.example.pedrolopes.internshapp.ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setSelected(true);
        expListView.expandGroup(0, true);
        expListView.setOnChildClickListener(onChildClickListener);
final CheckBox b = button;
        final Internship dat = data;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b.isChecked())
                    DataHolder.getCurrentStudent().setInternsipAsFavorite((Internship) dat);
                else
                    DataHolder.getCurrentStudent().removeFav(((Internship) dat).getTitle());

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
    /*
     * Preparing the list data
     */
    private void prepareListData(Internship data) {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();        // Adding child data
        listDataHeader.add("Descrição");
        listDataHeader.add("Coordenador");
        listDataHeader.add("Empresa");        // Adding child data
        List<String> desc = new ArrayList<String>();
        desc.add(data.getInfo());
        List<String> coordinator = new ArrayList<String>();
        coordinator.add(data.getCoordinator().getName());
        coordinator.add(data.getCoordinator().geteMail());
        List<String> company = new ArrayList<String>();
        company.add(data.getCompany().getName());
        listDataChild.put(listDataHeader.get(0), desc); // Header, Child data
        listDataChild.put(listDataHeader.get(1), coordinator);
        listDataChild.put(listDataHeader.get(2), company);
    }

    final InternshipDescriptionActivity th = this;
    ExpandableListView.OnChildClickListener onChildClickListener = new ExpandableListView.OnChildClickListener() {

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

            if(listDataHeader.get(groupPosition).equals("Empresa")) {
                Intent intent = new Intent(th, CompanyActivity.class);
                intent.putExtra("Company", (Serializable) comp);
                startActivity(intent);
            }
            return true;
        }
    };
}