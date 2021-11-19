package com.example.ssis_tracker.view.crearnoticia;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.adapter.crearnoticias.adapterImagenNoticia;
import com.example.ssis_tracker.elements.Permissions;
import com.example.ssis_tracker.presenter.crearnoticia.CrearNoticiaFragmentPresenter;
import com.example.ssis_tracker.presenter.crearnoticia.CrearNoticiaFragmentPresenterImpl;
import com.example.ssis_tracker.view.adjuntarimagenes.ActivityFolderImagenes;
import java.util.ArrayList;

public class CrearNoticiaActivity extends AppCompatActivity implements CrearNoticiasFragmentView {

    private static ArrayList<String[]> ImagenesAdjuntas;
    private EditText EditTextTituloNoticia;
    private EditText EditTextDescripcionNoticia;
    private RecyclerView recyclerViewImagenesAdjuntas;
    private LinearLayoutManager linearLayoutManager;
    private TextView TextViewMsjSinAdjuntos;
    private adapterImagenNoticia adapterImagenNoticia;
    private CrearNoticiaFragmentPresenter crearNoticiaFragmentPresenter;
    public static void setImagenesAdjuntas(ArrayList<String[]> imagenesAdjuntas) {
        ImagenesAdjuntas = imagenesAdjuntas;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearnoticia);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.ImagenesAdjuntas              = new ArrayList<>();
        this.recyclerViewImagenesAdjuntas  = this.findViewById(R.id.recyclerViewImagenesAdjuntas);
        this.TextViewMsjSinAdjuntos        = this.findViewById(R.id.TextViewMsjSinAdjuntos);
        this.EditTextTituloNoticia         = this.findViewById(R.id.EditTextTituloNoticia);
        this.EditTextDescripcionNoticia    = this.findViewById(R.id.EditTextDescripcionNoticia);
        this.linearLayoutManager           = new LinearLayoutManager(this.getApplicationContext());
        this.adapterImagenNoticia          = new adapterImagenNoticia(this.ImagenesAdjuntas , this.getApplicationContext());
        this.crearNoticiaFragmentPresenter = new CrearNoticiaFragmentPresenterImpl(this);

        this.linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        this.recyclerViewImagenesAdjuntas.setLayoutManager(linearLayoutManager);
        this.recyclerViewImagenesAdjuntas.setAdapter(adapterImagenNoticia);

        FloatingActionButton FloatingActionButtonAdjuntarImagen = this.findViewById(R.id.FloatingActionButtonAdjuntarImagen);
        FloatingActionButtonAdjuntarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Permissions permissions = new Permissions(v.getContext() , CrearNoticiaActivity.this );
                boolean granted = permissions.StoragePermissionsGranted();
                if(granted){
                    AbrirActitvityFolderImagenes(v.getContext());
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.continuar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.continuar:
                CreacionNoticia();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return true;
        }
    }

    private void CreacionNoticia(){

        if( this.EditTextTituloNoticia.getText().toString().trim() == null          ||
            this.EditTextTituloNoticia.getText().toString().trim().equals("")       ||
            this.EditTextDescripcionNoticia.getText().toString().trim() == null     ||
            this.EditTextDescripcionNoticia.getText().toString().trim().equals("")
        ){
            Snackbar.make(this.TextViewMsjSinAdjuntos , "Para crear una noticia tiene que ingregsar el titulo y la descripción de la noticia.",Snackbar.LENGTH_LONG).show();
        }else {
            RegistrarNoticia(this.EditTextTituloNoticia.getText().toString(),
                    this.EditTextDescripcionNoticia.getText().toString(),
                    CrearNoticiaActivity.ImagenesAdjuntas
            );
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(this.ImagenesAdjuntas.size() > 0){
            this.TextViewMsjSinAdjuntos.setVisibility(View.GONE);
        }else{
            this.TextViewMsjSinAdjuntos.setVisibility(View.VISIBLE);
        }
        this.adapterImagenNoticia.AgregarImagenes(this.ImagenesAdjuntas );
    }

    private void AbrirActitvityFolderImagenes(Context context){
        if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {
            return;
        }
        Intent intent = new Intent(context , ActivityFolderImagenes.class);
        context.startActivity(intent);
    }

    @Override
    public void RegistrarNoticia(String titulo, String Noticia, ArrayList<String[]> Imagenes) {
        this.crearNoticiaFragmentPresenter.CrearNoticia(titulo, Noticia, Imagenes);
    }

    @Override
    public void NoticiaCreada(Boolean creada,String Mensaje) {
        if(!creada){
            Snackbar.make(this.TextViewMsjSinAdjuntos , Mensaje , Snackbar.LENGTH_LONG).show();
        }else{
            this.EditTextDescripcionNoticia.setText(null);
            this.EditTextTituloNoticia.setText(null);
            this.ImagenesAdjuntas = new ArrayList<>();
            this.adapterImagenNoticia.AgregarImagenes(this.ImagenesAdjuntas );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 4: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    AbrirActitvityFolderImagenes(this.getApplicationContext());
                }else {
                    Snackbar.make(this.TextViewMsjSinAdjuntos , "La aplicación necesita tener acceso al almacenamiento del dispositivo" , Snackbar.LENGTH_LONG).show();
                }
            }
        }
    }
}
