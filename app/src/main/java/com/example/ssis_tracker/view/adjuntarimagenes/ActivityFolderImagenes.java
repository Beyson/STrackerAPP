package com.example.ssis_tracker.view.adjuntarimagenes;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.adapter.adjuntarimagenes.AdapterFolderImagenes;
import com.example.ssis_tracker.elements.LayoutGrid;
import com.example.ssis_tracker.presenter.adjuntarimagenes.AdjuntarImagenesPresenter;
import com.example.ssis_tracker.presenter.adjuntarimagenes.AdjuntarImagenesPresenterImpl;
import java.util.ArrayList;

public class ActivityFolderImagenes extends AppCompatActivity implements AdjuntarImagenesView {

    private AdjuntarImagenesPresenter adjuntarImagenesPresenter;
    private RecyclerView recyclerViewFolder;
    private AdapterFolderImagenes adapterFolderImagenes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_imagen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.adjuntarImagenesPresenter = new AdjuntarImagenesPresenterImpl(this , getApplicationContext());
        this.recyclerViewFolder        = findViewById(R.id.recyclerViewFolder);
        this.adapterFolderImagenes     = new AdapterFolderImagenes(this.getApplicationContext(), this);
        LayoutGrid layoutGrid = new LayoutGrid(getApplicationContext(),500);

        this.recyclerViewFolder.setLayoutManager(layoutGrid);
        this.recyclerViewFolder.setAdapter(this.adapterFolderImagenes);
        BuscarFolderImagenes();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void BuscarFolderImagenes() {
        this.adjuntarImagenesPresenter.BuscarFolderImagenes();
    }

    @Override
    public void MostarFolderImagenes(ArrayList<String[]> FolderImagenes) {
        this.adapterFolderImagenes.agregarFolderImagenes(FolderImagenes);
    }


    @Override
    public void MostarImagenes(ArrayList<String[]> Imagenes) {

    }

    @Override
    public void BuscarImagenes(String NombreFolder) {
       // this.adjuntarImagenesPresenter.BuscarImagenesEnCarpeta(NombreFolder);
        Intent intent = new Intent(getApplicationContext() , ActivityImagenesEnCarpeta.class);
        intent.putExtra("CarpetaNombre", NombreFolder);
        getApplicationContext().startActivity(intent);
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
}
