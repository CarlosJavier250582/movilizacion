package com.example.carlosje.movilizacion;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import static java.lang.String.valueOf;

public class MenuActivity extends AppCompatActivity  {
    private String usuario;
    private TextView Fecha_Nacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        usuario = getIntent().getStringExtra("usuario");



    }





    public  void buscar(View view){




    }


    public  void nuevo(View view){

        Intent activity_captura = new Intent(getApplicationContext(), CapturaActivity.class);
        activity_captura.putExtra("usuario", usuario);
        startActivity(activity_captura);
    }

}
