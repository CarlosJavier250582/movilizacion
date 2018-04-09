package com.example.carlosje.movilizacion;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SCActivity extends AppCompatActivity {

    private ListView listSC;
    private List items = new ArrayList();


    private String tipo,SC_Sel,ActCodResult,AC_Sel,Comp_sel,Act_sel;



    ListView simpleList;


    String Secc[] = {"4022", "4023", "4024", "4025"};
    String SeccDesc[] = {"", "", "", ""};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seccion_cod);

        tipo = getIntent().getStringExtra("tipo");





        if (tipo.equals("seccion")){

            final int requestCode = 1;

            simpleList =  (ListView) findViewById(R.id.listSC);


            CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), Secc, SeccDesc);
            simpleList.setAdapter(customAdapter);


            simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final int pos = position;

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("seccion_seleccion",Secc[pos]);

                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();

                }
            });

        }



    }




}