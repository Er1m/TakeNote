package com.erim.takenote;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    private Toolbar toolbar;
    private FrameLayout flyt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        flyt = (FrameLayout)findViewById(R.id.framelayout);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this,AddNote.class));
            }
        });

        frameKontrol();
    }



    //Ayarlardan Liste/Grid Kontrol Edilmesi ve Duruma göre Fragment Atanması
    public void frameKontrol(){

        SharedPreferences pmg = PreferenceManager.getDefaultSharedPreferences(this);
        String defprf = pmg.getString("gorunum","");


        if (defprf == "" || defprf.equals((getResources().getStringArray(R.array.entryler)[0]))){
            getFragmentManager().beginTransaction().replace(R.id.framelayout, new ListFragment()).commit();
        }else if(defprf.equals((getResources().getStringArray(R.array.entryler)[1]))){
            getFragmentManager().beginTransaction().replace(R.id.framelayout, new GridFragment()).commit();
        }
    }




    //Seçenekler Menüsü Ayarları
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,R.string.ayarlar);
        menu.add(0,1,0,R.string.hakkinda);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 0:
                startActivity(new Intent(this,Settings.class));
                finish();
                break;
            case 1:
                Toast.makeText(this,R.string.toasthakkinda,Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}
