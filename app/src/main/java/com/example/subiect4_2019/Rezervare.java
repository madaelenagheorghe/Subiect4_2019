package com.example.subiect4_2019;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
@Entity (tableName = "rezervari")
public class Rezervare implements Serializable {
    @PrimaryKey (autoGenerate = true)
    private int idRezervare;
    private String numeClient;
    private String tipCamera;
    private int durataSejur;
    private double sumaPlata;
    private Date dataCazare;

    public Rezervare() {
    }

    public Rezervare(int idRezervare, String numeClient, String tipCamera, int durataSejur, double sumaPlata, Date dataCazare) {
        this.idRezervare = idRezervare;
        this.numeClient = numeClient;
        this.tipCamera = tipCamera;
        this.durataSejur = durataSejur;
        this.sumaPlata = sumaPlata;
        this.dataCazare = dataCazare;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "idRezervare=" + idRezervare +
                ", numeClient='" + numeClient + '\'' +
                ", tipCamera='" + tipCamera + '\'' +
                ", durataSejur=" + durataSejur +
                ", sumaPlata=" + sumaPlata +
                ", dataCazare=" + dataCazare +
                '}';
    }

    public int getIdRezervare() {
        return idRezervare;
    }

    public void setIdRezervare(int idRezervare) {
        this.idRezervare = idRezervare;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getTipCamera() {
        return tipCamera;
    }

    public void setTipCamera(String tipCamera) {
        this.tipCamera = tipCamera;
    }

    public int getDurataSejur() {
        return durataSejur;
    }

    public void setDurataSejur(int durataSejur) {
        this.durataSejur = durataSejur;
    }

    public double getSumaPlata() {
        return sumaPlata;
    }

    public void setSumaPlata(double sumaPlata) {
        this.sumaPlata = sumaPlata;
    }

    public Date getDataCazare() {
        return dataCazare;
    }

    public void setDataCazare(Date dataCazare) {
        this.dataCazare = dataCazare;
    }
}
