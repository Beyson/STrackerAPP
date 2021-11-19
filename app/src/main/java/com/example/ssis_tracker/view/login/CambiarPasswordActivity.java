package com.example.ssis_tracker.view.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ssis_tracker.R;
import com.example.ssis_tracker.presenter.login.CambiarPasswordActivityPresenter;
import com.example.ssis_tracker.presenter.login.CambiarPasswordActivityPresenterImpl;
import com.example.ssis_tracker.view.home.HomeActivity;

public class CambiarPasswordActivity extends AppCompatActivity implements CambiarPasswordActivityView
{

    private CambiarPasswordActivityPresenter cambiarPasswordActivityPresenter;
    private SharedPreferences sharedPreferences;
    private TextInputEditText EditTextNuevaPass;
    private TextInputEditText EditTextConfirmarrPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_password);
        this.EditTextConfirmarrPass = findViewById(R.id.TextInputEditConfirmarPass);
        this.EditTextNuevaPass      = findViewById(R.id.TextInputEditNuevaPass);
        this.cambiarPasswordActivityPresenter = new CambiarPasswordActivityPresenterImpl(this);
        this.sharedPreferences = getApplicationContext().getSharedPreferences("USER", Context.MODE_PRIVATE);

        Button buttonCambiar = findViewById(R.id.ButtonCambiar);
        buttonCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CambiarPassword();
            }
        });
    }

    @Override
    public void CambiarPassword() {
        if(EditTextNuevaPass.getText().toString().equals(EditTextConfirmarrPass.getText().toString())){
            this.cambiarPasswordActivityPresenter.CambiarPassword(EditTextNuevaPass.getText().toString(),String.valueOf(sharedPreferences.getInt("id_usuario",0)) );
        }else {
            ShowError("Las contraseñas no coinciden");
        }
    }

    @Override
    public void ShowError(String MensajeError) {
        Snackbar.make(this.EditTextConfirmarrPass,MensajeError , Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void CambioCorrecto() {
        Intent intentHome = new Intent( getApplicationContext() , HomeActivity.class);
        getApplicationContext().startActivity(intentHome);
    }
}
