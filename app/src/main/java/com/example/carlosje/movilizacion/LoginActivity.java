package com.example.carlosje.movilizacion;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LoginActivity extends AppCompatActivity {

    private String username, password;
    private EditText usuario, pass;
    private TextView tv_no_conect;
    private ProgressBar progressBar;
    private RelativeLayout RL_login;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText) findViewById(R.id.field_email);
        pass = (EditText) findViewById(R.id.field_password);
        tv_no_conect= (TextView) findViewById(R.id.tv_no_conect);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        tv_no_conect.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        RL_login=(RelativeLayout) findViewById(R.id.RL_login);



        progressBar.setVisibility(View.GONE);
        RL_login.setVisibility(View.GONE);
        checkPermission();


        String fileUser="";

        String[] archivos = fileList();
        String archbusca = "user.txt";
        int flagFiles = 0;

        for (int f = 0; f < archivos.length; f++) {
            if (archbusca.equals(archivos[f])) {
                flagFiles = 1;
                fileUser= archivos[f];


            }
        }

        if (flagFiles == 1) {




            try {////////////llena el post

                String F_user="";
                InputStreamReader archivo = new InputStreamReader(openFileInput(fileUser));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                F_user = "";
                while (linea != null) {
                    F_user = F_user + linea;
                    linea = br.readLine();

                }
                br.close();
                archivo.close();


                usuario.setText(F_user);




            } catch (IOException e) {
                progressBar.setVisibility(View.GONE);
                Context context = getApplicationContext();
                CharSequence text = "Error al leer archivo: " +e;
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }




        }

    }

    private static final int REQUEST_CODE_ASK_PERMISSIONS_camera = 123;
    private static final int REQUEST_CODE_ASK_PERMISSIONS_Storage_read = 321;
    private static final int REQUEST_CODE_ASK_PERMISSIONS_Storage_write = 789;
    private static final int REQUEST_CODE_ASK_PERMISSIONS_location = 987;

    private void checkPermission() {



        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            //Toast.makeText(this, "This version is not Android 6 or later " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            RL_login.setVisibility(View.VISIBLE);
        } else {

            int hasWriteContactsPermission_camera = checkSelfPermission(android.Manifest.permission.CAMERA);

            int hasWriteContactsPermission_location = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);


            if (hasWriteContactsPermission_camera != PackageManager.PERMISSION_GRANTED) {

                RL_login.setVisibility(View.GONE);
                ActivityCompat.requestPermissions(
                        this, new String[]{android.Manifest.permission.CAMERA},  REQUEST_CODE_ASK_PERMISSIONS_camera);

                Toast.makeText(this, "Requesting permissions Camera", Toast.LENGTH_SHORT).show();
                return;

            }else if (hasWriteContactsPermission_camera == PackageManager.PERMISSION_GRANTED){

                // Toast.makeText(this, "The permissions are already granted ", Toast.LENGTH_LONG).show();
                // openCamera();
                RL_login.setVisibility(View.VISIBLE);
            }




            if (hasWriteContactsPermission_location != PackageManager.PERMISSION_GRANTED) {

                RL_login.setVisibility(View.GONE);
                ActivityCompat.requestPermissions(
                        this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},  REQUEST_CODE_ASK_PERMISSIONS_location);

                Toast.makeText(this, "Requesting permissions Ubication", Toast.LENGTH_SHORT).show();
                return;

            }else if (hasWriteContactsPermission_location == PackageManager.PERMISSION_GRANTED){

                //Toast.makeText(this, "The permissions are already granted ", Toast.LENGTH_LONG).show();
                // openCamera();
                RL_login.setVisibility(View.VISIBLE);
            }




        }

        return;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {




        if(grantResults.length > 0 && REQUEST_CODE_ASK_PERMISSIONS_camera == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "OK Permissions granted ! <img draggable="false" class="emoji" alt="🙂" src="https://s.w.org/images/core/emoji/2.4/svg/1f642.svg"> " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                RL_login.setVisibility(View.VISIBLE);


            } else {
                RL_login.setVisibility(View.GONE);

            }

            checkPermission();
        }


        if(grantResults.length > 0 && REQUEST_CODE_ASK_PERMISSIONS_Storage_read == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                RL_login.setVisibility(View.VISIBLE);

            } else {
                RL_login.setVisibility(View.GONE);

            }
            checkPermission();
        }


        if(grantResults.length > 0 && REQUEST_CODE_ASK_PERMISSIONS_Storage_write == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                RL_login.setVisibility(View.VISIBLE);

            } else {
                RL_login.setVisibility(View.GONE);

            }
            checkPermission();
        }

        if(grantResults.length > 0 && REQUEST_CODE_ASK_PERMISSIONS_location == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                RL_login.setVisibility(View.VISIBLE);

            } else {
                RL_login.setVisibility(View.GONE);

            }
            checkPermission();
        }
        //checkPermission();







    }





    public void login(View view) {

        tv_no_conect.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);



        username=usuario.getText().toString() + "@gmail.com";
        password=pass.getText().toString();



        if (usuario.getText().toString().equals("")){

            Context context = getApplicationContext();
            CharSequence text = "Por favor introduce tu email" ;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            progressBar.setVisibility(View.GONE);
            return;


        }
        if (pass.getText().toString().equals("")){
            Context context = getApplicationContext();
            CharSequence text = "Por favor introduce tu password" ;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            progressBar.setVisibility(View.GONE);
            return;



        }


        mAuth = FirebaseAuth.getInstance();


        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information




                            String[] archivos = fileList();
                            String archbusca = "user.txt";
                            int flagFiles = 0;

                            for (int f = 0; f < archivos.length; f++) {
                                if (archbusca.equals(archivos[f])) {
                                    flagFiles = 1;
                                }
                            }


                            if (flagFiles == 0) {

                                try {
                                    String filename = "user.txt";

                                    OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(filename, Activity.MODE_PRIVATE));
                                    archivo.write(usuario.getText().toString());
                                    archivo.flush();
                                    archivo.close();

                                } catch (IOException e) {
                                }





                            }









                            FirebaseUser user = mAuth.getCurrentUser();


                            Intent activity__menu = new Intent(getApplicationContext(), MenuActivity.class);
                            activity__menu.putExtra("usuario", usuario.getText().toString());
                            startActivity(activity__menu);



                        } else {
                            // If sign in fails, display a message to the user.

                            Context context = getApplicationContext();
                            CharSequence text = "Error de autentificación" ;
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();

                        }


                    }
                });


    }







}
