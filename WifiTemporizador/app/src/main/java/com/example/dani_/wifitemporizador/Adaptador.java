package com.example.dani_.wifitemporizador;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import static com.example.dani_.wifitemporizador.R.id.detalles;

public class Adaptador extends BaseAdapter {

    Context contexto;
    LayoutInflater inflater;
    List<ScanResult> listawifi;
   int intensidad;
//Convertir el list View de contener un solo string a un array de multiples string

    public Adaptador(Context contexto, List<ScanResult> listawifi) {
        this.contexto = contexto;
        this.listawifi = listawifi;
        inflater = (LayoutInflater) contexto
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listawifi.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        view = inflater.inflate(R.layout.activity_adaptador, null);
        TextView textviewdetalles = (TextView) view.findViewById(detalles);


//El list view tendra el SSID y la intensidad de la señal del wifi

        intensidad = listawifi.get(position).level;
        if (intensidad > -70) {
            textviewdetalles.setText("SSID :: " + listawifi.get(position).SSID
                    + "\nIntensidad :: " + "4/4");


            return view;

        } else if (intensidad <= -70 && intensidad >= -80) {
            textviewdetalles.setText("SSID :: " + listawifi.get(position).SSID
                    + "\nIntensidad :: " + "3/4");


            return view;
        } else if (intensidad <= -80 && intensidad >= -90){
            textviewdetalles.setText("SSID :: " + listawifi.get(position).SSID
                    + "\nIntensidad :: " + "2/4");


        return view;

    }else if(intensidad <=-90 && intensidad >=-100){
        textviewdetalles.setText("SSID :: " + listawifi.get(position).SSID
                + "\nIntensidad :: " +"1/4");


        return view;

}return view;}}
