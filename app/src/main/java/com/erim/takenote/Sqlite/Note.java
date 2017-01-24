package com.erim.takenote.Sqlite;

import java.io.Serializable;

/**
 * Created by UsErim on 20.1.2017.
 */

public class Note implements Serializable{

    private int Id;
    private String ntitle;
    private String nnote;
    private String ndate;

    public Note(){

    }

    public Note(String ntitle,String nnote,String ndate){
        this.ntitle = ntitle;
        this.nnote = nnote;
        this.ndate = ndate;

    }

    public Note(int Id ,String ntitle,String nnote,String ndate){
        this.Id = Id;
        this.ntitle = ntitle;
        this.nnote = nnote;
        this.ndate = ndate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getNnote() {
        return nnote;
    }

    public void setNnote(String nnote) {
        this.nnote = nnote;
    }

    public String getNdate() {
        return ndate;
    }

    public void setNdate(String ndate) {
        this.ndate = ndate;
    }
}
