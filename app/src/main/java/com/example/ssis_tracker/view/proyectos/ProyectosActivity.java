package com.example.ssis_tracker.view.proyectos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.adapter.proyectos.AdapterProyectos;
import com.example.ssis_tracker.model.Proyecto;
import com.example.ssis_tracker.presenter.proyectos.ProyectosActivityPresenter;
import com.example.ssis_tracker.presenter.proyectos.ProyectosActivityPresenterImpl;
import java.util.ArrayList;

public class ProyectosActivity extends AppCompatActivity implements ProyectosActivityView{

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerViewProyectos;
    private AdapterProyectos adapterProyectos;
    private TextView textWithoutData;
    private SharedPreferences sharedPreferences;
    private ProyectosActivityPresenter proyectosActivityPresenter;
    private int codigo;
    private int tipo;

    @Override
    protected void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proyectos);

        this.codigo = (getIntent().getIntExtra("direccion",0) == 0)? getIntent().getIntExtra("cod_proyecto" ,0):getIntent().getIntExtra("direccion",0);
        this.tipo   = (getIntent().getIntExtra("direccion",0) == 0)? 1:0;
        this.proyectosActivityPresenter = new ProyectosActivityPresenterImpl(this);
        this.sharedPreferences = getApplicationContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        this.textWithoutData = findViewById(R.id.textWithoutData);
        this.recyclerViewProyectos = findViewById(R.id.recyclerViewProyectos);
        this.swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        this.adapterProyectos = new AdapterProyectos( getApplicationContext()  ,
                                                      new ArrayList<Proyecto>(),
                                                      String.valueOf(this.sharedPreferences.getInt("id_usuario",0)),
                                                      this.sharedPreferences.getString("rol","") ,
                                                      getIntent().getIntExtra("direccion",0));

        this.recyclerViewProyectos.setLayoutManager(linearLayoutManager);
        this.recyclerViewProyectos.setAdapter(adapterProyectos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProyectos(codigo ,tipo );
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getProyectos(this.codigo,  this.tipo );
    }

    @Override
    public void getProyectos(int codigo , int tipo ) {
        adapterProyectos.adapterDataChange(new ArrayList<Proyecto>());
        showSwipeRefreshLayout(true);
        proyectosActivityPresenter.getProyectos(codigo,tipo);
    }

    @Override
    public void getallProyectos() {
    }

    @Override
    public void showProyectos(ArrayList<Proyecto> proyectoArrayList) {
        showSwipeRefreshLayout(false);
        adapterProyectos.adapterDataChange(proyectoArrayList);
        textWithoutData.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String strMessage) {
        Snackbar.make(findViewById(android.R.id.content), strMessage, Snackbar.LENGTH_LONG).show();
        showSwipeRefreshLayout(false);
        textWithoutData.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSwipeRefreshLayout(boolean bool) {
        recyclerViewProyectos.setVisibility(bool ? View.GONE :View.VISIBLE);
        swipeRefreshLayout.setRefreshing(bool);
    }

    @Override
    public void configAppBar(boolean bolDefault) {

    }
}