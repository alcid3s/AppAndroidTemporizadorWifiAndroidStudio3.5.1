package com.example.dani_.wifitemporizador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button anadir = (Button) findViewById(R.id.Anadir);
        Button mostrar = (Button) findViewById(R.id.Mostrar);

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//Intent para acceder a la pantalla de añadir usuarios
                Intent i = new Intent(getApplicationContext(), anadirusuario.class);
                startActivity(i);
            }
        });

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//Intent para acceder a la pantalla para mostrar las redes WIFI
               Intent i = new Intent (getApplicationContext(), RedesWifi.class);
               startActivity(i);
            }
        });
    }

}
