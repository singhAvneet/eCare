package com.example.owner.assignment4;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 2015-11-05.
 */
public class ShowPatient extends Activity{
    static DatabaseManager db;
    static TextView display;
    static EditText name;
    static String strName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientinformation);
        db = new DatabaseManager(this);
        display=(TextView)findViewById(R.id.textResults);
    }

    public void showData(View view) {
        name = (EditText) findViewById(R.id.editResultTestId);
        strName = name.getText().toString();
        List table = db.getDataByName("tbl_patient", strName);
        for (Object o : table) {
            ArrayList row = (ArrayList)o;
            String[] fields={"PAIENTID","DOC.ID","1stNAME","LAST","DPT","ROOM"};
            String output="";
            for (int i=0;i<row.size();i++) {
                output+=fields[i]+" : ";
                //output += row.get(i).toString();
                for(int j=0;j<17-fields[i].length();j++)
                    output+=" ";
                output+=row.get(i).toString()+"\n";

            }
            display.setText(output);
        }
    }

    public void Patientlist(View view) {
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
            display.setText(output);
        }
    }

    public void goBack(View view) {
        Intent intent=new Intent(this,MenuActivity.class);
        startActivity(intent);
    }
}
