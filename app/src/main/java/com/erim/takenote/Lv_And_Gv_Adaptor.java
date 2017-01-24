package com.erim.takenote;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.erim.takenote.Sqlite.Note;
import java.util.List;

public class Lv_And_Gv_Adaptor extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<Note> notes;


    public Lv_And_Gv_Adaptor(Activity activity, List<Note> notes){
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.notes = notes;

    }


    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View colView = layoutInflater.inflate(R.layout.lv_and_gv_adaptor,null);
        TextView title = (TextView)colView.findViewById(R.id.LSTV_title);
        TextView tarih = (TextView)colView.findViewById(R.id.LSTV_tarih);

        Note note = notes.get(position);
        title.setText(note.getNtitle());
        tarih.setText(note.getNdate());

        return colView;
    }
}
