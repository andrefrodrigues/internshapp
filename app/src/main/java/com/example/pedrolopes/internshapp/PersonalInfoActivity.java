package com.example.pedrolopes.internshapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalInfoActivity extends AppCompatActivity {

    Button changePhoto;
    Button applyChanges;
    Bitmap currentImage;
    ImageView profilePic;
    TextView name;
    TextView studentNum;
    TextView birthDate;
    EditText address;
    EditText phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar bar = getSupportActionBar();
        //aqui muda-se a cor e e so acrescentar ao gosto e tal
        myToolbar.getBackground().setColorFilter(Color.parseColor("#019439"), PorterDuff.Mode.MULTIPLY);
        myToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        bar.setTitle("Perfil");

        name = (TextView)findViewById(R.id.textViewName);
        studentNum = (TextView)findViewById(R.id.textViewStudentNumber);
        birthDate = (TextView)findViewById(R.id.textViewBirth);
        address = (EditText)findViewById(R.id.editTextAddress);
        phone = (EditText)findViewById(R.id.editTextPhone);
        //Carregar aqui os dados para as TextView
        Student current = DataHolder.getCurrentStudent();
        name.setText(current.getName());
        studentNum.setText(String.valueOf(current.getnStudent()));
        birthDate.setText(current.getBirth().toString());
        address.setText(current.getAddress());
        phone.setText(String.valueOf(current.getPhoneNumber()));

        profilePic = (ImageView)findViewById(R.id.imageViewProfilePic);
        changePhoto = (Button)findViewById(R.id.buttonAltPhoto);
        changePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });

        applyChanges = (Button)findViewById(R.id.buttonApply);
        applyChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aceder à BD e gravar os novos dados
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri photoUri = data.getData();
            if (photoUri != null) {
                try {
                    currentImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
                    profilePic.setImageBitmap(currentImage);
                    profilePic.setMinimumWidth(150);
                    profilePic.setMaxWidth(150);
                    profilePic.setMinimumHeight(150);
                    profilePic.setMaxHeight(150);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}