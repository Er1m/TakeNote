package com.erim.takenote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.erim.takenote.Sqlite.DatabaseHandler;
import com.erim.takenote.Sqlite.Note;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AddNote extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText etTitle,etNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_detail_note);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.notekle);
    }

    @Override
    public void onBackPressed() {
        etTitle = (EditText)findViewById(R.id.asTitle);
        etNote = (EditText)findViewById(R.id.asNote);


        if (!(String.valueOf(etTitle.getText()).equals(""))   ||   !(String.valueOf(etNote.getText()).equals(""))){

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            DatabaseHandler db = new DatabaseHandler(AddNote.this);
            db.addNote(new Note(String.valueOf(etTitle.getText()),String.valueOf(etNote.getText()),dateFormat.format(System.currentTimeMillis())));
            db.close();

            finish();
            startActivity(new Intent(AddNote.this,Main.class));
            Toast.makeText(this,R.string.kaydedilmistir ,Toast.LENGTH_LONG).show();

        }else{
            startActivity(new Intent(AddNote.this,Main.class));
            finish();
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,R.string.kaydetme);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                finish();
                startActivity(new Intent(AddNote.this,Main.class));
                break;
        }
        return true;
    }

}
