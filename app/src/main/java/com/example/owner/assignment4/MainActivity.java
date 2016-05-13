package com.example.owner.assignment4;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String tables[]={"tbl_nurse","tbl_test","tbl_doctor","tbl_patient"};
    private static final String tableCreatorString[] ={"CREATE TABLE tbl_nurse (nurse_id INTEGER PRIMARY KEY AUTOINCREMENT , firstname TEXT, lastname TEXT, department TEXT);",
            "CREATE TABLE tbl_doctor ( doctor_id INTEGER PRIMARY KEY AUTOINCREMENT,firstname TEXT,lastname TEXT ,department TEXT); " ,
            "CREATE TABLE tbl_test (test_id INTEGER PRIMARY KEY AUTOINCREMENT , patient_Id INTEGER, bpl TEXT, bph TEXT, temperature TEXT, pressure TEXT,FOREIGN KEY (patient_id) REFERENCES tbl_patient (patient_id) on UPDATE CASCADE );",
            "CREATE TABLE tbl_patient ( patient_id INTEGER PRIMARY KEY AUTOINCREMENT,doctor_id INTEGER REFERENCES tbl_doctor(doctor_id),firstname TEXT,lastname TEXT ,department TEXT ,room TEXT);"};

    static  String[] NurseRecord = new String[4];
    static final String[] Nursefields = {"nurse_id", "firstname","lastname","department"};
    static  String[] DoctorRecord = new String[4];
    static final String[] Doctorfields = {"doctor_id", "firstname","lastname","department"};

    public static   DatabaseManager db;
   static String str,loginId;
    static Intent intent ;
    static RadioGroup rg;
   static EditText editTextLoginId;
    static int radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseManager(this);
        db.dbInitialize(tables, tableCreatorString);

        rg=(RadioGroup)findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                SharedPreferences myPreference = getSharedPreferences("SharedPreferencesLogin", 0);
                SharedPreferences.Editor prefEditor = myPreference.edit();
                radioButton = checkedId;
                RadioButton rb = (RadioButton) findViewById(checkedId);
                if (checkedId != -1) {
                    if (rb != null) {
                        str = (String) rb.getText();
                        prefEditor.putString("KeyLogin", str);
                        prefEditor.commit();
                    }
                }
            }
        });
    }



    public void SignIn(View view) {
        NurseRecord[1]= "Mrs. Koyela";
        NurseRecord[2]="Koyela";
        NurseRecord[3]= "finance";
        DoctorRecord[1]= "Dr. Koyela";
        DoctorRecord[2]="Koyela";
        DoctorRecord[3]= "Security";
        ContentValues values = new ContentValues();
        for (int i=1;i<NurseRecord.length;i++)
            values.put(Nursefields[i],NurseRecord[i]);
            db.addRecord(values, "tbl_nurse", Nursefields,NurseRecord);
        for (int i=1;i<DoctorRecord.length;i++)
            values.put(Doctorfields[i],DoctorRecord[i]);
        db.addRecord(values, "tbl_doctor", Doctorfields,DoctorRecord);


        intent =new Intent(this,MenuActivity.class);
        editTextLoginId=(EditText)findViewById(R.id.editTextLoginId);
        loginId=editTextLoginId.getText().toString();

                if((loginId.equals("nurse@123")&&str.equals("Nurse"))||(str.equals("Doctor")&&loginId.equals("doctor@123"))) {
                    intent.putExtra("key", loginId);
                    startActivity(intent);
                }
                    else
                        Toast.makeText(getApplicationContext(),"Either you did'nt selected catogary \n \t\t\t or \n Entered login is incorrect",Toast.LENGTH_SHORT).show();

        radioButton=-1;
    }

    }

