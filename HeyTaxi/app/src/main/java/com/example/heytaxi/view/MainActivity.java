package com.example.heytaxi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.heytaxi.databinding.ActivityMainBinding;
import com.example.heytaxi.model.Taxi;
import com.example.heytaxi.roomdb.TaxiDao;
import com.example.heytaxi.roomdb.TaxiDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);



    }

    public void login(View view){
        String userName=binding.userNameEditText.getText().toString();
        String password=binding.passwordEditText.getText().toString();

        if(userName.matches("admin") && password.matches("123")){
            Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
            startActivity(intent);
        }
    }
}