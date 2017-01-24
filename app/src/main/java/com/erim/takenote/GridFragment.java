package com.erim.takenote;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.erim.takenote.Sqlite.DatabaseHandler;
import com.erim.takenote.Sqlite.Note;
import java.util.ArrayList;
import java.util.Collections;

public class GridFragment extends Fragment {

    GridView gridView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_grid_fragment,container,false);

        gridView = (GridView)view.findViewById(R.id.gridview);

        DatabaseHandler db = new DatabaseHandler(getActivity());
        final ArrayList<Note> notlar = db.getNotes();
        Collections.reverse(notlar);

        Lv_And_Gv_Adaptor adaptor = new Lv_And_Gv_Adaptor(getActivity(), notlar);
        gridView.setAdapter(adaptor);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(),Details.class);
                intent.putExtra("title",notlar.get(position).getNtitle());
                intent.putExtra("note",notlar.get(position).getNnote());
                intent.putExtra("date",notlar.get(position).getNdate());
                intent.putExtra("id",notlar.get(position).getId());
                startActivity(intent);
            }
        });
        return view;
    }
}
