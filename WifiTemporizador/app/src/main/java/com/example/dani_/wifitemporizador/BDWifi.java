package com.example.dani_.wifitemporizador;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDWifi extends SQLiteOpenHelper {
//Base de datos con dos tablas
    //la primera es sobre los usuarios con su contraseña
    //la segunda son los redes wifi
    private String sentenciaSQL = "CREATE TABLE Usuarios (Nombres TEXT, contrasena TEXT);";
    private String sentenciaSQL2 = "INSERT INTO Usuarios VALUES ('admin','admin');";
    private String sentenciaSQL3 = "INSERT INTO Usuarios VALUES ('usuario1','usuario1');";
    private String sentenciaSQL4 = "CREATE TABLE WIFI (SSID TEXT, contrasena TEXT)";
    private String sentenciaSQL6 = "INSERT INTO WIFI VALUES ( 'MOVISTAR_PLUS_7887', '2999BB661BA827D5EF86')";
    private String sentenciaSQL7 = "INSERT INTO WIFI VALUES ( 'Wlancenec', '917LqnE@2051')";
    public BDWifi(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "BDWifi", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//Ejecuto las sentencias SQL
        db.execSQL(sentenciaSQL);
        db.execSQL(sentenciaSQL2);
        db.execSQL(sentenciaSQL3);
        db.execSQL(sentenciaSQL4);
        db.execSQL(sentenciaSQL6);
        db.execSQL(sentenciaSQL7);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}