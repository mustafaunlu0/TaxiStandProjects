package com.example.heytaxi.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.heytaxi.model.Taxi;

@Database(entities = {Taxi.class},version = 1)
public abstract class TaxiDatabase extends RoomDatabase {
    public abstract  TaxiDao taxiDao();
}
