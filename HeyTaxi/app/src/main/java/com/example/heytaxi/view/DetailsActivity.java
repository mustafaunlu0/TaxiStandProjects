package com.example.heytaxi.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.heytaxi.R;
import com.example.heytaxi.adapter.TaxiAdapter;
import com.example.heytaxi.databinding.ActivityDetailsBinding;
import com.example.heytaxi.model.Taxi;
import com.example.heytaxi.roomdb.TaxiDao;
import com.example.heytaxi.roomdb.TaxiDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    TaxiDatabase db;
    TaxiDao taxiDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityDetailsBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        Toolbar myToolbar=findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));

        db= Room.databaseBuilder(DetailsActivity.this,TaxiDatabase.class,"Taxis").build();
        taxiDao=db.taxiDao();

        compositeDisposable.add(taxiDao.getAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(DetailsActivity.this::handleResponse)
        );


    }

    private void handleResponse(List<Taxi> taxis) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
        TaxiAdapter taxiAdapter=new TaxiAdapter(taxis);
        binding.recyclerView.setAdapter(taxiAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_station){
            Intent intent=new Intent(DetailsActivity.this,MapsActivity.class);
            intent.putExtra("info","new");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}