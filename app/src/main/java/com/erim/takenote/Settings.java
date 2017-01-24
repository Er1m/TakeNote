package com.erim.takenote;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.os.Bundle;

public class Settings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,Main.class));
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}
