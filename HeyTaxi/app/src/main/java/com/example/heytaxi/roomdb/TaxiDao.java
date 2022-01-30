package com.example.heytaxi.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.heytaxi.model.Taxi;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
@Dao
public interface TaxiDao {

    @Query("SELECT * FROM Taxi")
    Flowable<List<Taxi>> getAll();

    @Insert
    Completable insert(Taxi taxi);

    @Delete
    Completable delete(Taxi taxi);
}
