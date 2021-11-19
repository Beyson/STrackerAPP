package com.example.ssis_tracker.view.agenda;

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
import com.example.ssis_tracker.presenter.agenda.AgendarTemaActivityPresenter;
import com.example.ssis_tracker.presenter.agenda.AgendarTemaActivityPresenterImpl;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgendarTemaActivity extends AppCompatActivity implements AgendarTemaActivityView {

    private AgendarTemaActivityPresenter agendarTemaActivityPresenter;
    private EditText Tema;
    private EditText Comentario;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        agendarTemaActivityPresenter = new AgendarTemaActivityPresenterImpl(this);
        setContentView(R.layout.activity_agendar_tema);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.Tema = findViewById(R.id.EditTextTemaAgendar);
        this.Comentario = findViewById(R.id.EditTextComentario);
        this.sharedPreferences = getApplicationContext().getSharedPreferences("USER", Context.MODE_PRIVATE);

        EditText EditTextUsuario = findViewById(R.id.EditTextUsuario);
        EditText EditTextFechaIngreso = findViewById(R.id.EditTextFechaIngreso);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(new Date());
        if(getIntent().getIntExtra("agendarDirector",1) == 0){
            this.Tema.setText(getIntent().getStringExtra("TemaProyecto"));
            this.Tema.setEnabled(false);
        }

        EditTextUsuario.setText(this.sharedPreferences.getString("nombre",""));
        EditTextFechaIngreso.setText(currentDateandTime);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.agendar_tema, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.guardarTema:
                AgendarTema(Tema.getText().toString() , this.sharedPreferences.getInt("id_usuario",0) , getIntent().getStringExtra("proyecto") , Comentario.getText().toString().toUpperCase());
                return false;
            default:
                return true;
        }
    }

    @Override
    public void AgendarTema(String tema, int usuario, String Proyecto , String Comentario) {
        agendarTemaActivityPresenter.AgendarTema(tema,usuario, Proyecto ,Comentario);
    }

    @Override
    public void TemaRegistrado(boolean registrado) {
        if(registrado){
            onBackPressed();
        }else{
            Snackbar.make(findViewById(R.id.linearLayoutAgendarTema),"Error al agendar el tema",Snackbar.LENGTH_LONG).show();
        }
    }
}
