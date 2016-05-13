package com.example.owner.assignment4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 2015-11-05.
 */
public class ShowTest extends Activity {
static DatabaseManager db;
    static TextView display;
    static EditText name;
    static String strId;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testinfo);
        db = new DatabaseManager(this);
        display=(TextView)findViewById(R.id.textResults);

    }

    public void showResults(View view) {
        name=(EditText)findViewById(R.id.editResultTestId);
        strId =name.getText().toString();
        List table = db.getTestResults("tbl_test", strId);
        for (Object o : table) {
            ArrayList row = (ArrayList)o;
            String[] fields={"TESTID","PAIENTID","BPL","BPH","TEMP","PRESSURE"};
            String output="TESTID    PATEINTID    BPL    BPH    TEMP    PRESSURE\n";
            for (int i=0;i<row.size();i++) {
                    output += row.get(i).toString();
                for(int j=0;j<(fields[i].length()*2.2+4)-row.get(i).toString().length();j++)
                output+=" ";
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
        Intent intetn=new Intent(this,MenuActivity.class);
        startActivity(intetn);
    }
}

