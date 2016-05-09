package com.example.pedrolopes.internshapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompanyActivity extends AppCompatActivity implements Serializable{
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.getBackground().setColorFilter(Color.parseColor("#019439"), PorterDuff.Mode.MULTIPLY);
        myToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        Company c = (Company) getIntent().getSerializableExtra("Company");
        ActionBar bar = getSupportActionBar();
        bar.setTitle(c.getName());

        expListView = (ExpandableListView) findViewById(R.id.lvExpComp);        // preparing list data
        prepareListData(c);
        listAdapter = new com.example.pedrolopes.internshapp.ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setSelected(true);
        expListView.expandGroup(0, true);
        expListView.setOnChildClickListener(onChildClickListener);
        /*TextView companyName = (TextView) findViewById(R.id.company_name);
        companyName.setText(c.getName());

        TextView email =(TextView) findViewById(R.id.e_mail);
        email.setText(c.geteMail());

        TextView phone_address = (TextView) findViewById(R.id.phone_number);
        phone_address.setText(String.valueOf(c.getPhoneNumber()));

        TextView address = (TextView) findViewById(R.id.address_);
        address.setText(c.getAddress());

        ListView interns = (ListView) findViewById(R.id.internships_);


        String[] values = new String[c.getInternships().size()];
        int i=0;
        for(Internship in:c.getInternships().values()){
            values[i++] = in.getTitle();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        interns.setAdapter(adapter);*/
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

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*
     * Preparing the list data
     */
    private void prepareListData(Company data) {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();        // Adding child data
        listDataHeader.add("Email");
        listDataHeader.add("Contacto");
        listDataHeader.add("Morada");        // Adding child data
        List<String> email = new ArrayList<String>();
        email.add(data.geteMail());
        List<String> contact = new ArrayList<String>();
        contact.add(String.valueOf(data.getPhoneNumber()));
        List<String> address = new ArrayList<String>();
        address.add(data.getAddress());
        listDataChild.put(listDataHeader.get(0), email); // Header, Child data
        listDataChild.put(listDataHeader.get(1), contact);

        listDataChild.put(listDataHeader.get(2), address);
    }

    final CompanyActivity th = this;
    ExpandableListView.OnChildClickListener onChildClickListener = new ExpandableListView.OnChildClickListener() {

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

            if(listDataHeader.get(groupPosition).equals("Morada")) {
                loadMap();
            }
            if(listDataHeader.get(groupPosition).equals("Contacto")){
                clickToCall();

            }
            return true;
        }
    };

    private void clickToCall() {
        String number = listDataChild.get("Contacto").get(0);
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            startActivity(intent);

        }


    };

    private void loadMap(){
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("address",listDataChild.get("Morada").get(0));
        startActivity(intent);
    }
}
