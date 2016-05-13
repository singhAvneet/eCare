package com.example.owner.assignment4;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Owner on 2015-11-05.
 */
abstract class MainMenuActivity extends ListActivity {

    private SortedMap<String, Object> menu = new TreeMap<String, Object>();

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String key=(String)l.getItemAtPosition(position);
        startActivity((Intent)menu.get(key)  );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences myPref = getSharedPreferences("SharedPreferencesLogin", MODE_PRIVATE);
        String detils = myPref.getString("KeyLogin", "");
        // Inflate the menu; this adds items to the action bar if it is present.
        callMenuOptions(detils);
        String [] keys= menu.keySet().toArray(new String [menu.keySet().size()]);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,keys));

    }

    protected abstract void callMenuOptions(String detils);
    protected void additem(String exercise, Class<?> exerciseClass) {
        menu.put(exercise, new Intent(this, exerciseClass));
    }
}
