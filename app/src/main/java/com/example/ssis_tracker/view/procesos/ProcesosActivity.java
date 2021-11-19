package com.example.ssis_tracker.view.procesos;

import android.app.SearchManager;
import android.content.Context;

import com.google.android.material.snackbar.Snackbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.adapter.procesos.AdapterProcesos;
import com.example.ssis_tracker.model.Proceso;
import com.example.ssis_tracker.presenter.procesos.ProcesosActivityPresenter;
import com.example.ssis_tracker.presenter.procesos.ProcesosActivityPresenterImpl;
import java.util.ArrayList;

public class ProcesosActivity extends AppCompatActivity implements  ProcesosActivityView{

    private ProcesosActivityPresenter procesosActivityPresenter;
    private RecyclerView recyclerViewProcesos;
    private SwipeRefreshLayout swipeRefreshLayout;
    private AdapterProcesos adapterProcesos;
    private TextView textWithoutData;
    private TextView textViewNombreProyecto;
    private int codigo;
    private int tipo;
    private String nombreProyecto;
    private MenuItem searchItem;
    private ArrayList<Proceso> procesosArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procesos);

        this.codigo = (getIntent().getIntExtra("proyecto",0) == 0)? getIntent().getIntExtra("cod_proceso" ,0):getIntent().getIntExtra("proyecto",0);
        this.tipo   = (getIntent().getIntExtra("proyecto",0) == 0)? 1:0;
        this.nombreProyecto = getIntent().getStringExtra("nombre");

        this.procesosActivityPresenter = new ProcesosActivityPresenterImpl(this);
        this.textWithoutData = findViewById(R.id.textWithoutData);
        this.textViewNombreProyecto = findViewById(R.id.textViewNombreProyecto);
        this.recyclerViewProcesos = findViewById(R.id.recyclerViewProcesos);
        this.swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.adapterProcesos = new AdapterProcesos(new ArrayList<Proceso>(), getApplicationContext(), getIntent().getIntExtra("direccion",0));
        this.recyclerViewProcesos.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.recyclerViewProcesos.setAdapter(adapterProcesos);
        this.textViewNombreProyecto.setText(nombreProyecto);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getProcesos(codigo, tipo, getIntent().getIntExtra("direccion",0));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        SearchManager searchManager = (SearchManager) ProcesosActivity.this.getSystemService(Context.SEARCH_SERVICE);

        menuInflater.inflate(R.menu.direcciones, menu);
        this.searchItem = menu.findItem(R.id.searchViewFind);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(ProcesosActivity.this.getComponentName()));
        }
        searchView.setQueryHint("Buscar Proceso...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList <Proceso> listClone = new ArrayList<Proceso>();
                if(procesosArrayList != null || !s.equals("")){
                    for(Proceso proceso : procesosArrayList){
                        if(proceso.getNombre().toUpperCase().contains(s.toUpperCase())){
                            listClone.add(proceso);
                        }
                    }
                    adapterProcesos.adapterDataChange(listClone);
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
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
        int l = getIntent().getIntExtra("direccion",0);
        getProcesos(this.codigo, this.tipo, getIntent().getIntExtra("direccion",0));
    }

    @Override
    public void getProcesos(int codigo , int tipo, int id_direccion) {
        adapterProcesos.adapterDataChange(new ArrayList<Proceso>());
        showSwipeRefreshLayout(true);
        procesosActivityPresenter.getProcesos(codigo , tipo, id_direccion);
    }

    @Override
    public void getallProcesos() {

    }

    @Override
    public void showProcesos(ArrayList<Proceso> procesoArrayList) {
        showSwipeRefreshLayout(false);
        this.procesosArrayList = procesoArrayList;
        adapterProcesos.adapterDataChange(procesoArrayList);
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
        recyclerViewProcesos.setVisibility(bool ? View.GONE :View.VISIBLE);
        swipeRefreshLayout.setRefreshing(bool);
    }
}
