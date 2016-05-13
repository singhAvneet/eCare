package com.example.owner.assignment4;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Owner on 2015-11-05.
 */
public class EnterPatient extends Activity {
    static TextView display;
    public static DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientinfo);
        db = new DatabaseManager(this);


        final String fields[] = {"patient_id", "doctor_id", "firstname", "lastname", "department", "room"};
        final String record[] = new String[6];
        // Handle Save button
        final Button btnSavePatient = (Button) findViewById(R.id.storepatient);
        final EditText doctor_id = (EditText) findViewById(R.id.doctorId);
        final EditText firstName = (EditText) findViewById(R.id.firsttName);
        final EditText lastName = (EditText) findViewById(R.id.lastName);
        final EditText depatment = (EditText) findViewById(R.id.department);
        final EditText room = (EditText) findViewById(R.id.room);

        btnSavePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record[1]= doctor_id.getText().toString();
                record[2]= firstName.getText().toString();
                record[3]=lastName.getText().toString();
                record[4]= depatment.getText().toString();
                record[5]=room.getText().toString();

                ContentValues values = new ContentValues();
                for (int i=1;i<record.length;i++)
                    values.put(fields[i],record[i]);
                db.addRecord(values, "tbl_patient", fields,record);

            }
        });

    }

    public void goBack(View view) {
        Intent intent =new Intent(this,MenuActivity.class);
        startActivity(intent);
    }
}


