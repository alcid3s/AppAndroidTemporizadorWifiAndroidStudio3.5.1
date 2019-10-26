package com.example.dani_.wifitemporizador;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class LoginActivity extends AppCompatActivity {

    public EditText Usuario;
    public EditText Contrasena;
    public Button iniciar;
    public String usuarios;
    public String contrasenas;
    Cursor listaRegistros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Usuario = (EditText) findViewById(R.id.NombreEdit);
        Contrasena = (EditText)findViewById(R.id.ContrasenaEdit);
        iniciar = (Button)findViewById(R.id.iniciar);

        BDWifi resg = new BDWifi(this, "BDWifi", null, 1);
        final SQLiteDatabase bd = resg.getReadableDatabase();
        iniciar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                usuarios = Usuario.getText().toString();
                contrasenas = Contrasena.getText().toString();

//Busca si el usuario esta en la base de datos
                listaRegistros = bd.rawQuery("SELECT * FROM Usuarios WHERE Nombres = '"+usuarios+"' AND Contrasena = '"+contrasenas+"'"  , null);
                if (listaRegistros.moveToFirst()) {

//Si es igual que admin va a la pantalla admin
                if (usuarios.equals("admin") && contrasenas.equals("admin")) {
              Intent  i = new Intent(getApplicationContext(), admin.class);
                    startActivity(i);
                }else{
                    //si es usuario normal va a la pantalla de usuarios
                    Intent   i = new Intent(getApplicationContext(), usuario.class);
                    i.putExtra("usuarios",usuarios);
                    i.putExtra("contrasenas",contrasenas);
                    startActivity(i);
                }
                }
                    else{
                    //El usuario o la contraseña es incorrecta
                    Toast.makeText(getBaseContext(), "Sin usuario//sin contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

