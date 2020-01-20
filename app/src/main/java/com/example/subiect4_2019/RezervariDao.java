package com.example.subiect4_2019;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RezervariDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    public void insert(Rezervare rezervare);

    @Query("SELECT * FROM rezervari;")
    List<Rezervare> getRezervari();

    @Query("DELETE FROM rezervari")
    public void deleteAll();
}
