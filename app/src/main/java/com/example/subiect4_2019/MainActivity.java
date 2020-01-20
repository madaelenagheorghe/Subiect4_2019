package com.example.subiect4_2019;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Rezervare> rezervari=new ArrayList<>();
    ListView lv;
    int prevPos;
    RezervariDB database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.lv_rezervari);
        final AdaptorRezervare adaptor=new AdaptorRezervare(this, R.layout.item_layout, rezervari);
        lv.setAdapter(adaptor);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Delete the item?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rezervari.remove(position);
                        lv.setAdapter(adaptor);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Deletion cancelled.", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog=builder.show();
                return false;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Rezervare r=rezervari.get(position);
                prevPos=position;
                Intent intent=new Intent(MainActivity.this, AdaugaRezervare.class);
                intent.putExtra("rezervare",r);
                startActivityForResult(intent,2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_rezervari, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.adauga_rezervare:
                Intent intent = new Intent(this, AdaugaRezervare.class);
                startActivityForResult(intent,1);
                break;
            case R.id.preluare_online:
                final JsonRead jsonRead=new JsonRead(){
                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        List<Rezervare> rezervariJSON=parseJson(s);
                        for(Rezervare r:rezervariJSON){
                            rezervari.add(r);
                            Log.e("JSON", r.toString());
                        }
                        AdaptorRezervare adaptor=new AdaptorRezervare(getApplicationContext(), R.layout.item_layout, rezervari);
                        lv.setAdapter(adaptor);
                    }

                };
                try{
                    jsonRead.execute(new URL("http://pdm.ase.ro/examen/rezervari.json.txt"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.salvare_bd:
                database=RezervariDB.getInstance(this);
                for(Rezervare r:rezervari){
                    database.getRezervariDao().insert(r);
                }
                List<Rezervare> rezervariBD=database.getRezervariDao().getRezervari();
                for(Rezervare r:rezervariBD){
                    Log.e("REZERVARE", r.toString());
                }
                break;
            case R.id.grafic: break;
            case R.id.despre:
            {AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getResources().getString(R.string.author) + " " + Calendar.getInstance().getTime())
                .setIcon(R.drawable.ic_android_black_24dp)
                .create();
                AlertDialog dialog=builder.show();
                break;}
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1) {
            if (resultCode == RESULT_OK) {
                Rezervare r = (Rezervare) data.getSerializableExtra("rezervare");
                rezervari.add(r);
                Toast.makeText(this, r.toString(), Toast.LENGTH_LONG).show();
                lv = findViewById(R.id.lv_rezervari);
                AdaptorRezervare adaptor = new AdaptorRezervare(this, R.layout.item_layout, rezervari);
                lv.setAdapter(adaptor);
            }
        }
        if(requestCode==2){
            if(resultCode==RESULT_OK){
                Rezervare r=(Rezervare)data.getSerializableExtra("rezervare");
                rezervari.get(prevPos).setNumeClient(r.getNumeClient());
                rezervari.get(prevPos).setIdRezervare(r.getIdRezervare());
                rezervari.get(prevPos).setDataCazare(r.getDataCazare());
                rezervari.get(prevPos).setDurataSejur(r.getDurataSejur());
                rezervari.get(prevPos).setTipCamera(r.getTipCamera());
                rezervari.get(prevPos).setSumaPlata(r.getSumaPlata());
                lv = findViewById(R.id.lv_rezervari);
                AdaptorRezervare adaptor = new AdaptorRezervare(this, R.layout.item_layout, rezervari);
                lv.setAdapter(adaptor);
            }
        }
    }
}


