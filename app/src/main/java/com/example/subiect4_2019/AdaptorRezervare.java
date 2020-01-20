package com.example.subiect4_2019;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdaptorRezervare extends ArrayAdapter {
    private int resId;
    public AdaptorRezervare(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resId=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Rezervare r=(Rezervare)getItem(position);
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View view=inflater.inflate(resId,null);
        TextView id=view.findViewById(R.id.tv_id);
        TextView nume=view.findViewById(R.id.tv_nume);
        TextView suma=view.findViewById(R.id.tv_suma);
        TextView durata=view.findViewById(R.id.tv_durata);
        TextView tip=view.findViewById(R.id.tv_tip);
        TextView data=view.findViewById(R.id.tv_data);
        id.setText(String.valueOf(r.getIdRezervare()));
        nume.setText(String.valueOf(r.getNumeClient()));
        suma.setText(String.valueOf(r.getSumaPlata()));
        durata.setText(String.valueOf(r.getDurataSejur()));
        tip.setText(String.valueOf(r.getTipCamera()));
        data.setText(String.valueOf(r.getDataCazare()));
        return view;
    }
}
