package com.example.ssis_tracker.view.agregarcomentarios;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.presenter.agregarcomentarios.ActivityComentarPresenter;
import com.example.ssis_tracker.presenter.agregarcomentarios.ActivityComentarPresenterImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ActivityComentar extends AppCompatActivity implements ActivityComentarView {

    private EditText EditTextUsuario;
    private EditText EditTextFechaIngreso;
    private EditText EditTextComentario;
    private ActivityComentarPresenter activityComentarPresenter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentar);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        this.sharedPreferences = getApplicationContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        this.EditTextUsuario = findViewById(R.id.EditTextUsuario);
        this.EditTextFechaIngreso = findViewById(R.id.EditTextFechaIngreso);
        this.EditTextComentario = findViewById(R.id.EditTextComentario);
        this.activityComentarPresenter = new ActivityComentarPresenterImpl(this, findViewById(R.id.coordinatorLayoutComentar));
        this.EditTextFechaIngreso.setText(date);
        this.EditTextUsuario.setText(this.sharedPreferences.getString("nombre",""));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.agendar_tema, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.guardarTema:
                AgregarComentario();
                return false;
            default:
                return true;
        }
    }

    @Override
    public void AgregarComentario() {
        if(  EditTextComentario.getText().toString().trim().length() > 0 ){
            this.activityComentarPresenter.AgregarComentario( String.valueOf( this.sharedPreferences.getInt("id_usuario",0) ) , EditTextComentario.getText().toString().trim(), getIntent().getIntExtra("actividad",0));
        }else{
            Snackbar.make(findViewById(R.id.coordinatorLayoutComentar),"Se tiene que escribir un comentario",Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void ComentarioAgregado(int Repuesta) {
        if(Repuesta == 1){
            onBackPressed();
        }else{
            Snackbar.make(findViewById(R.id.coordinatorLayoutComentar),"Error al registrar el comentario",Snackbar.LENGTH_LONG).show();
        }
    }
}
