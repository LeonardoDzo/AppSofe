package com.leonardodurazo.appsofe.Actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.leonardodurazo.appsofe.Fragmentos.MainFragment;
import com.leonardodurazo.appsofe.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.container,
                    new MainFragment(), "MainFragment").
                    commit();

        }
    }

}
