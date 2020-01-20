package com.example.subiect4_2019;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Rezervare.class, version=1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class RezervariDB extends RoomDatabase {
    private static final String DBNAME="rezervari";
    private static RezervariDB instance;

    public static RezervariDB getInstance(Context context) {
        if(instance==null){
            instance= Room.databaseBuilder(context, RezervariDB.class, DBNAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract RezervariDao getRezervariDao();
}
