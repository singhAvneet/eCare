package com.example.owner.assignment4;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 2015-11-04.
 */
public class EnterTest extends Activity {
    static  TextView showPatientId ;
    public static   DatabaseManager db;
    static Intent back;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertest);

        db = new DatabaseManager(this);

        final String fields[] = {"test_id", "patient_Id","bpl","bph","temperature","pressure"};
        final String record[] = new String[6];
        // Handle Save button
        final Button btnSaveStudent = (Button) findViewById(R.id.buttonSubmit);
        final Button btnGetData = (Button) findViewById(R.id.buttonGetId);
        final EditText patient_Id = (EditText) findViewById(R.id.editTextPatientId);
        final EditText bpl = (EditText) findViewById(R.id.editTextBPL);
        final EditText temperature = (EditText) findViewById(R.id.editTextTempId);
        final EditText bph = (EditText) findViewById(R.id.editTextBPHid);
        final EditText presure = (EditText) findViewById(R.id.editTextPressure);

        //
        btnSaveStudent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                record[1]= patient_Id.getText().toString();
                record[2]=bpl.getText().toString();
                record[3]= bph.getText().toString();
                record[4]=temperature.getText().toString();
                record[5]= presure.getText().toString();

                ContentValues values = new ContentValues();
                for (int i=1;i<record.length;i++)
                    values.put(fields[i],record[i]);
               db.addRecord(values, "tbl_test", fields,record);

            }
        });




    }
    public void backToMenu(View view) {
        back=new Intent(this,MenuActivity.class);
        startActivity(back);
    }

    public void showPatientList(View view) {
        showPatientId=(TextView)findViewById(R.id.TextpatientId);
        List table = db.getPatientList("tbl_patient");
        for (Object o : table) {
            ArrayList row = (ArrayList)o;
            String output="NAME              ID\n";
            for (int i=0;i<row.size();i+=2) {
                for (int j = i; j < i+2; j++) {
                    output += row.get(j).toString();
                    for(int n=0;n<=19-row.get(j).toString().length();n++)
                        output+=" ";
                }
                output += "\n";
            }
            showPatientId.setText(output);
        }
    }

    public void EnterPatient(View view) {
        back=new Intent(this,EnterPatient.class);
        startActivity(back);
    }
}