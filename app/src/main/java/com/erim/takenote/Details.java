package com.erim.takenote;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.KeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.erim.takenote.Sqlite.DatabaseHandler;
import com.erim.takenote.Sqlite.Note;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by UsErim on 23.1.2017.
 */
public class Details extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText etTitle,etNote;
    private TextView textView;
    int kaydedilsinmi = 0;

    private KeyListener ettitle,etnote;
    private Drawable tbg,nbg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_detail_note);

        Intent intent = getIntent();

        //Toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(intent.getStringExtra("title"));

        etTitle = (EditText)findViewById(R.id.asTitle);
        etNote = (EditText)findViewById(R.id.asNote);
        textView = (TextView)findViewById(R.id.asDate);

        ettitle = etTitle.getKeyListener();
        etnote = etNote.getKeyListener();



        //Görünümler
        etNote.setKeyListener(null);
        etTitle.setKeyListener(null);
        textView.setVisibility(View.VISIBLE);

        etNote.setTextSize(15);
        etTitle.setTextSize(18);
        textView.setTextSize(15);

        etNote.setTextColor(Color.parseColor("#595959"));
        etTitle.setTextColor(Color.parseColor("#595959"));
        textView.setTextColor(Color.parseColor("#595959"));

        nbg = etNote.getBackground();
        tbg = etTitle.getBackground();
        etNote.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        etTitle.setBackgroundColor(getResources().getColor(android.R.color.transparent));


        //Text atama
        etTitle.setText(intent.getStringExtra("title"));
        etNote.setText(intent.getStringExtra("note"));
        textView.setText(intent.getStringExtra("date"));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0,0,0,R.string.duzenle);
        menu.add(0,1,0,R.string.sil);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                etNote.setKeyListener(etnote);
                etTitle.setKeyListener(ettitle);
                textView.setVisibility(View.INVISIBLE);
                kaydedilsinmi = 1;
                etNote.setBackgroundDrawable(nbg);
                etTitle.setBackgroundDrawable(tbg);
                item.setVisible(false);
                break;

            case 1:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.sil)
                        .setMessage(R.string.silinsinmi)
                        .setPositiveButton(R.string.evet, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHandler db = new DatabaseHandler(Details.this);
                        Intent intent = getIntent();
                        db.deleteNote(db.getNote(intent.getIntExtra("id",-1)));
                        db.close();
                        startActivity(new Intent(Details.this,Main.class));
                        finish();
                    }
                })
                .setNegativeButton(R.string.hayır,null)
                .show();
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if(kaydedilsinmi == 1){
            DatabaseHandler db = new DatabaseHandler(this);
            Intent intent = getIntent();

            Note note = new Note();
            note.setNtitle(etTitle.getText().toString());
            note.setNnote(etNote.getText().toString());
            note.setId(intent.getIntExtra("id",-1));

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            note.setNdate(dateFormat.format(System.currentTimeMillis()));

            db.updateNote(note);
            db.close();
            Toast.makeText(this,R.string.kaydedilmistir,Toast.LENGTH_LONG).show();
        }

        startActivity(new Intent(this,Main.class));
        super.onBackPressed();

    }
}
