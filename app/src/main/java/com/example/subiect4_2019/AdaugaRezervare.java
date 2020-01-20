package com.example.subiect4_2019;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
@TargetApi(23)
public class AdaugaRezervare extends AppCompatActivity {
    EditText id;
    EditText nume;
    EditText suma;
    EditText durata;
    Spinner tip;
    DatePicker data;
    TimePicker ora;
    int r_id;
    String r_nume;
    double r_suma;
    int r_durata;
    int ok=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_rezervare);
        nume=findViewById(R.id.et_nume);
        id=findViewById(R.id.et_id);
        suma=findViewById(R.id.et_suma);
        durata=findViewById(R.id.et_durata);
        tip=findViewById(R.id.spinner);
        data=findViewById(R.id.dp);
        ora=findViewById(R.id.tp);

        Intent intent=getIntent();
        if(intent.hasExtra("rezervare")){
            Rezervare r=(Rezervare)intent.getSerializableExtra("rezervare");
            nume.setText(r.getNumeClient().toString());
            id.setText(String.valueOf(r.getIdRezervare()));
            suma.setText(String.valueOf(r.getSumaPlata()));
            durata.setText(String.valueOf(r.getDurataSejur()));
            if(r.getTipCamera().equals("S"))
                tip.setSelection(0);
            else
                tip.setSelection(1);
            Calendar calendar=Calendar.getInstance();
            calendar.setTimeInMillis(r.getDataCazare().getTime());
            data.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
            ora.setHour(calendar.get(Calendar.HOUR_OF_DAY));
            ora.setMinute(calendar.get(Calendar.MINUTE));
        }
    }

    public void adauga(View view) {
        ok=1;
        try{
            r_id=Integer.parseInt(id.getText().toString());
        } catch(Exception ex){
            ex.printStackTrace();
            Toast.makeText(this, "Put a number id.", Toast.LENGTH_LONG).show();
            ok=0;
        }
        r_nume=nume.getText().toString();
        if(r_nume.equals(null)||r_nume.equals("")){
            Toast.makeText(this, "Put a name.", Toast.LENGTH_LONG).show();
            ok=0;
        }
        try{
            r_durata=Integer.parseInt(durata.getText().toString());
        } catch(Exception ex){
            ex.printStackTrace();
            Toast.makeText(this, "Put a number duration.", Toast.LENGTH_LONG).show();
            ok=0;
        }

        try{
            r_suma=Double.parseDouble(suma.getText().toString());
        } catch(Exception ex){
            ex.printStackTrace();
            Toast.makeText(this, "Put a number with decimals sum.", Toast.LENGTH_LONG).show();
            ok=0;
        }
        if(ok==1){
            String r_tip=null;
            r_tip=tip.getSelectedItem().toString();


            Calendar calendar=Calendar.getInstance();
            calendar.set(data.getYear(), data.getMonth(), data.getDayOfMonth(), ora.getHour(), ora.getMinute());
            Rezervare r=new Rezervare(r_id, r_nume, r_tip, r_durata, r_suma, calendar.getTime());
            Intent intent=new Intent();
            intent.putExtra("rezervare",r);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
