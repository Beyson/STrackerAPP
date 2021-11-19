package com.example.ssis_tracker.view.adjuntarimagenes;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.adapter.adjuntarimagenes.AdapterImagenesAdjuntar;
import com.example.ssis_tracker.elements.LayoutGrid;
import com.example.ssis_tracker.presenter.adjuntarimagenes.AdjuntarImagenesPresenter;
import com.example.ssis_tracker.presenter.adjuntarimagenes.AdjuntarImagenesPresenterImpl;
import java.io.File;
import java.util.ArrayList;

public class ActivityImagenesEnCarpeta extends AppCompatActivity implements AdjuntarImagenesView
{
    private AdjuntarImagenesPresenter adjuntarImagenesPresenter;
    private RecyclerView  RecyclerViewImagenesAdjuntar;
    private AdapterImagenesAdjuntar adapterImagenesAdjuntar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenesadjuntar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.adjuntarImagenesPresenter = new AdjuntarImagenesPresenterImpl(this, getApplicationContext());
        this.RecyclerViewImagenesAdjuntar = findViewById(R.id.RecyclerViewImagenesAdjuntar);
        this.adapterImagenesAdjuntar = new AdapterImagenesAdjuntar(this.getApplicationContext());
        LayoutGrid layoutGrid = new LayoutGrid(this.getApplicationContext() , 500);
        this.RecyclerViewImagenesAdjuntar.setAdapter(this.adapterImagenesAdjuntar);
        this.RecyclerViewImagenesAdjuntar.setLayoutManager(layoutGrid);

        File file = new File(getIntent().getStringExtra("CarpetaNombre")).getParentFile();
        BuscarImagenes(file.getAbsolutePath());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.continuar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.continuar:
                this.adapterImagenesAdjuntar.ImagenesSeleccionadas();
                return true;
            default:
                return true;
        }
    }

    @Override
    public void BuscarFolderImagenes() { }

    @Override
    public void MostarFolderImagenes(ArrayList<String[]> FolderImagenes) { }

    @Override
    public void MostarImagenes(ArrayList<String[]> Imagenes) {
        this.adapterImagenesAdjuntar.AgregarImagenesAdapter(Imagenes);
    }

    @Override
    public void BuscarImagenes(String NombreFolder) {
        this.adjuntarImagenesPresenter.BuscarImagenesEnCarpeta(NombreFolder);
    }
}
