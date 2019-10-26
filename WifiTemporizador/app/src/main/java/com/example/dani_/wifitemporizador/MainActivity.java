package com.example.dani_.wifitemporizador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
public Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = (Button) findViewById(R.id.button);

      boton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//Intent hacia la pantalla del login
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
      });
}}
