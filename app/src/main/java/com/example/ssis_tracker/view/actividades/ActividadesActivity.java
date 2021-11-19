package com.example.ssis_tracker.view.actividades;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.viewpager.widget.ViewPager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.adapter.actividades.AdapterFragmentActividades;
import com.example.ssis_tracker.model.Actividad;
import com.example.ssis_tracker.presenter.actividades.ActividadesActivityPresenter;
import com.example.ssis_tracker.presenter.actividades.ActividadesActivityPresenterImpl;
import java.util.ArrayList;

public class ActividadesActivity extends AppCompatActivity implements ActividadesActivityView {

    private ViewPager viewPagerActividades;
    private ActividadesActivityPresenter actividadesActivityPresenter;
    private TextView textWithoutData;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int codigo;
    private int tipo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);
        this.codigo = (getIntent().getIntExtra("proceso",0) == 0)? getIntent().getIntExtra("cod_actividad" ,0):getIntent().getIntExtra("proceso",0);
        this.tipo   = (getIntent().getIntExtra("proceso",0) == 0)? 1:0;


        this.actividadesActivityPresenter = new ActividadesActivityPresenterImpl(this,null);
        this.viewPagerActividades = findViewById(R.id.viewPagerActividades);
        this.textWithoutData = findViewById(R.id.textWithoutData);
        this.swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        String nombreProceso = getIntent().getStringExtra("nombre");
        TextView textViewNombreProceso = findViewById(R.id.textViewNombreProceso);
        textViewNombreProceso.setText(nombreProceso);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getActividades(codigo, tipo ,   getIntent().getIntExtra("direccion",0));
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
        getActividades(this.codigo, this.tipo,  getIntent().getIntExtra("direccion",0));
    }

    public void setViewPager(ArrayList<Actividad> actividadArrayList){
        int count = 0;
        AdapterFragmentActividades adapterFragmentActividades;
        adapterFragmentActividades = new AdapterFragmentActividades(getSupportFragmentManager());

        for(Actividad actividad: actividadArrayList){
            count += 1;
            ActividadesFragment actividadesFragment = new ActividadesFragment();
            actividadesFragment.actividad = actividad;
            actividadesFragment.posicion = String.valueOf(count) + '/' + actividadArrayList.size();

            adapterFragmentActividades.AddFragment(actividadesFragment, null);
        }
        viewPagerActividades.setAdapter(adapterFragmentActividades);
    }

    @Override
    public void getActividades(int codigo ,int tipo , int id_direccion) {
        showSwipeRefreshLayout(true);
        actividadesActivityPresenter.getActividades(codigo, tipo, id_direccion);
    }

    @Override
    public void getallActividades() {

    }

    @Override
    public void showActividades(ArrayList<Actividad> actividadArrayList) {
        showSwipeRefreshLayout(false);
        swipeRefreshLayout.setRefreshing(false);
        setViewPager(actividadArrayList);
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
        viewPagerActividades.setVisibility(bool ? View.GONE :View.VISIBLE);
        //swipeRefreshLayout.setRefreshing(bool);
    }
}
