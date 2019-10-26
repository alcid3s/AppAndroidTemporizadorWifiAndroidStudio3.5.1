package com.example.dani_.wifitemporizador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        Button mostrar = (Button) findViewById(R.id.mostrar);
        Intent intent = getIntent();
       final String usuarios = intent.getExtras().getString("usuarios");

          mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//Intent hacia mostrar redes Wifi y envia el string del usuario recibido
                Intent mostrar2 = new Intent (usuario.this, RedesWifi.class);
                mostrar2.putExtra("usuarios", usuarios);
                startActivity(mostrar2);
            }
        });
    }
    }



