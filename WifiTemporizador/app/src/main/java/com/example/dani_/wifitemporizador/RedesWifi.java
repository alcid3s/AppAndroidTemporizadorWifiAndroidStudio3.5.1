package com.example.dani_.wifitemporizador;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class RedesWifi  extends Activity  {

    private WifiManager wifi;
    private WifiReceiver receptor;
    private Button actualizar;
    Adaptador adaptador;
    ListView detalleswifi;
    List<ScanResult> listawifi;
    Cursor listaRegistros;
    String wificontrasena;
    CountDownTimer contadordetiempo;
    Intent intent;
    String usuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Intent que coge el usuario que mandó el anterior activity
       intent = getIntent();
        if(intent.hasExtra("usuarios")) {

            usuarios = intent.getExtras().getString("usuarios");
        }


        setContentView(R.layout.activity_redes_wifi);
        BDWifi resg = new BDWifi(this, "BDWifi", null, 1);
        final SQLiteDatabase bd = resg.getReadableDatabase();
        detalleswifi = (ListView) findViewById(R.id.detalleswifi);
        actualizar = (Button) findViewById(R.id.Actualizar);
        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        receptor = new WifiReceiver();
        registerReceiver(receptor, new IntentFilter(
                WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        scanWifiList();

        actualizar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                scanWifiList();

            }
        });
        detalleswifi.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub


                //Informa del SSID del WIFI elegido
                Toast.makeText(RedesWifi.this,listawifi.get(position).SSID , Toast.LENGTH_SHORT).show();

                String SSID = "\"" + listawifi.get(position).SSID.toString() + "\"";;

                //Coge la contraseña de la wifi elegida en la base de datos
               listaRegistros = bd.rawQuery("SELECT contrasena FROM WIFI WHERE SSID = '"+listawifi.get(position).SSID.toString()+"'", null);
                if (listaRegistros != null ) {
                    if  (listaRegistros.moveToFirst()) {
                        do {
                            wificontrasena = listaRegistros.getString(listaRegistros.getColumnIndex("contrasena"));

                        }while (listaRegistros.moveToNext());
                    }
                }
                listaRegistros.close();


                String Contraseña = "\"" + wificontrasena + "\"";
                WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                WifiConfiguration wc = new WifiConfiguration();
                wc.SSID = SSID;
                wc.preSharedKey = Contraseña;
                wc.hiddenSSID = true;
                wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                wc.status = WifiConfiguration.Status.DISABLED;
                wc.status = WifiConfiguration.Status.ENABLED;
                wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                wc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                    int red = wifi.addNetwork(wc);
                     boolean b = wifi.enableNetwork(red, true);


//Contador de una hora
                contadordetiempo = new CountDownTimer(3600000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
//Al finalizar el contador de una hora borra el usuario y lo devuelve a la pantalla del login
                        listaRegistros = bd.rawQuery("DELETE * FROM Usuarios WHERE Nombres = '"+usuarios+"'", null);
                       Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                       startActivity(i);
                    }
                }.start();


            }
        });

    }


    private void setAdapter() {
        adaptador = new Adaptador(getApplicationContext(), listawifi);
        detalleswifi.setAdapter(adaptador);

    }

    private void scanWifiList() {
        wifi.startScan();
        listawifi = wifi.getScanResults();

        setAdapter();

    }}

class WifiReceiver extends BroadcastReceiver {
    public void onReceive(Context c, Intent intent) {
    }
}

