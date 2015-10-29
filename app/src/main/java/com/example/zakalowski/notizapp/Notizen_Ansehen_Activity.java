package com.example.zakalowski.notizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Notizen_Ansehen_Activity extends AppCompatActivity {

    ListView listView;

    ArrayList<File> dateinliste;
    ArrayList<String> texteliste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notizen__ansehen);

        listView = (ListView) findViewById(R.id.listView);

        arraylistSetup();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Notizen_Ansehen_Activity.this, android.R.layout.simple_list_item_1,texteliste);
        listView.setAdapter(arrayAdapter);
       // listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       //     @Override
       //     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       //         Intent editIntent = new Intent(Notizen_Ansehen_Activity.this, Edit_Notes_Activity.class);
       //         editIntent.putExtra("EXTRA_NOTE")
       //     }
        //}
        //);
    }

    private void arraylistSetup() {

        dateinliste = new ArrayList<>();
        texteliste = new ArrayList<>();

        File ordner = new File(Environment.getExternalStorageDirectory(), "NotizApp");

        dateinliste.addAll(Arrays.asList(ordner.listFiles()));

        Collections.sort(dateinliste);
        Collections.reverse(dateinliste);

        int dateicounter = 0;

        while (dateicounter < dateinliste.size()) {
            texteliste.add(getTextFromFile(dateinliste.get(dateicounter)));
            dateicounter++;
        }
    }

        public String getTextFromFile (File datei){

        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(datei));

            String aktuelleZeile;

            while ((aktuelleZeile = bufferedReader.readLine()) !=null){
                stringBuilder.append(aktuelleZeile);
                stringBuilder.append("\n");
            }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString().trim();
        }
}


