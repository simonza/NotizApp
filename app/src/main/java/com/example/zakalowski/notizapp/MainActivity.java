package com.example.zakalowski.notizapp;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText editText;
    File ordner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ordner = new File(Environment.getExternalStorageDirectory(), "NotizApp");
        if(!ordner.exists()){
            ordner.mkdirs();
        }
        editText = (EditText)findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() > 0) {

                    File notizdatei = new File(ordner, "Text_" + System.currentTimeMillis() + ".txt");
                    try {
                        OutputStream outputStream = new FileOutputStream(notizdatei);
                        outputStream.write(editText.getText().toString().getBytes());
                        outputStream.close();
                        editText.setText(null);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else {
                    Toast.makeText(getApplicationContext(), "Kein Text!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_view_notes) {
            if(ordner.listFiles().length >0) {
                startActivity(new Intent(MainActivity.this, Notizen_Ansehen_Activity.class));
            }
            else {
                Toast.makeText(getApplicationContext(), "Keine Notiz vorhanden!", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
