package com.example.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
Button btnInsert, getBtnInsert;
TextView TVResults;
ListView listView;
EditText Inputdate;
EditText inputTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getBtnInsert = findViewById(R.id.btnInsert);
        listView = findViewById(R.id.ListViewResults);
        btnInsert = findViewById(R.id.btnInsert);
        TVResults = findViewById(R.id.TVResults);
        Inputdate = findViewById(R.id.InPutdate);
        inputTask = findViewById(R.id.InputTask);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, (List) listView);
        listView.setAdapter(adapter);
        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DBHelper db = new DBHelper(MainActivity.this);

                db.insertTask(inputTask.getText().toString(), Inputdate.getText().toString());
                db.close();
            }
        });
        getBtnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);


                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                TVResults.setText(txt);
            }
        });

    }
}