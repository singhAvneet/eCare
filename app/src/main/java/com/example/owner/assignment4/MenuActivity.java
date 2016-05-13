package com.example.owner.assignment4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends MainMenuActivity {

    @Override
    public void callMenuOptions(String detils) {


        additem("Enter Test Information ",EnterTest.class);
        if(detils.equals("Nurse"))
        additem("Enter Patient Information",EnterPatient.class);
        additem("Test Information",ShowTest.class);
        additem("Patient Information",ShowPatient.class);

    }
}
