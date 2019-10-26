package com.example.dani_.wifitemporizador;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class anadirusuario extends AppCompatActivity {
    EditText usuario;
    EditText contrasena;
    Button anadir;
    String usuarionuevo;
    String contrasenanueva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadirusuario);

        BDWifi resg = new BDWifi(this, "BDWifi", null, 1);
        final SQLiteDatabase bd = resg.getReadableDatabase();

        anadir = (Button) findViewById(R.id.anadir);
        usuario = (EditText)findViewById(R.id.NombreEdit);
        contrasena = (EditText)findViewById(R.id.ContrasenaEdit);



        anadir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//Mete al usuario y la contraseña en la base de datos y muestra un mensaje que lo confirma
                usuarionuevo = usuario.getText().toString();
                contrasenanueva = contrasena.getText().toString();
                bd.execSQL("INSERT INTO Usuarios (Nombres,contrasena) VALUES('" + usuarionuevo + "','" + contrasenanueva + "')");
                Toast.makeText(getBaseContext(), "Nuevo usuario añadido", Toast.LENGTH_SHORT).show();
            };

        });
    };
}
