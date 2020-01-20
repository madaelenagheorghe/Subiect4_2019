package com.example.subiect4_2019;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonRead extends AsyncTask<URL, Void, String> {
    @Override
    protected String doInBackground(URL... urls) {
        HttpURLConnection conn=null;
        StringBuilder sb=new StringBuilder();
        try {
            conn=(HttpURLConnection)urls[0].openConnection();
            InputStream is = conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(isr);
            String line="";
            while((line=br.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn!=null)
                conn.disconnect();
        }
        return sb.toString();
    }

    public List<Rezervare> parseJson (String json){
        List<Rezervare> rezJson=new ArrayList<>();
        if(json!=null){
            try {
                JSONObject jsonObject=new JSONObject(json);
                JSONObject rezervariObject=jsonObject.getJSONObject("rezervari");
                JSONArray rezervariArray=rezervariObject.getJSONArray("rezervare");
                for(int i=0;i<rezervariArray.length();i++){
                    JSONObject rez=rezervariArray.getJSONObject(i);
                    int id=rez.getInt("idRezervare");
                    String nume=rez.getString("numeClient");
                    String tip=rez.getString("tipCamera");
                    int durata=rez.getInt("durataSejur");
                    double suma=rez.getDouble("sumaPlata");
                    SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy hh:mm");
                    Date data=format.parse(rez.getString("dataCazare"));
                    Rezervare r=new Rezervare(id, nume, tip, durata, suma, data);
                    rezJson.add(r);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } return rezJson;
    }
}
