package com.example.ssis_tracker.view.login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.elements.Permissions;
import com.example.ssis_tracker.model.Login;
import com.example.ssis_tracker.presenter.login.LoginActivityPresenter;
import com.example.ssis_tracker.presenter.login.LoginActivityPresenterImpl;
import com.example.ssis_tracker.view.home.HomeActivity;
import com.google.gson.Gson;
import java.util.List;


public class LoginActivity extends AppCompatActivity implements LoginActivityView {

    private Button buttonLoging;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharePreferencesEditor;
    private TextInputEditText TextInputEditTextUser;
    private TextInputEditText TextInputEditTextPassword;
    private LoginActivityPresenter loginActivityPresenter;
    private double latitud;
    private double longitud;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.buttonLoging = findViewById(R.id.ButtonLogin);
        this.TextInputEditTextUser = findViewById(R.id.TextInputEditTextUser);
        this.TextInputEditTextPassword = findViewById(R.id.TextInputEditTextPassword);
        this.sharedPreferences = getApplicationContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        this.sharePreferencesEditor = this.sharedPreferences.edit();

        this.loginActivityPresenter = new LoginActivityPresenterImpl(this);
        this.buttonLoging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Permissions permissions = new Permissions(getApplicationContext(), LoginActivity.this);
                boolean granted = permissions.LocationPermissionsGranted();
                if (granted) {
                    LogInUsuario();
                }
            }
        });
    }

    @Override
    public void LogInUsuario() {
        SolicitarPosicionGeografia();
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(getApplicationContext().WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        this.loginActivityPresenter.LogInUsuario(TextInputEditTextUser.getText().toString(), TextInputEditTextPassword.getText().toString(), ip, latitud, longitud);
    }

    @Override
    public void UsuarioLogeado(Login login) {

        this.sharePreferencesEditor.putInt("id_usuario", login.getId_usuario());
        this.sharePreferencesEditor.putString("nombre", login.getNombre());
        this.sharePreferencesEditor.putString("unidad", login.getUnidad());
        this.sharePreferencesEditor.putInt("id_unidad", login.getId_unidad());
        this.sharePreferencesEditor.putString("direccion", login.getDireccion());
        this.sharePreferencesEditor.putInt("id_direccion", login.getId_direccion());
        this.sharePreferencesEditor.putString("siglas", login.getSiglas());
        this.sharePreferencesEditor.putInt("id_rol", login.getId_rol());
        this.sharePreferencesEditor.putString("rol", login.getRol());
        this.sharePreferencesEditor.putString("permisos", new Gson().toJson(login.getMenu()));
        this.sharePreferencesEditor.commit();
        this.TextInputEditTextUser.setText("");
        this.TextInputEditTextPassword.setText("");

        if (login.isCambiar_password()) {
            Intent intentCambiarPassword = new Intent(getApplicationContext(), CambiarPasswordActivity.class);
            getApplicationContext().startActivity(intentCambiarPassword);
        } else {
            Intent intentHome = new Intent(getApplicationContext(), HomeActivity.class);
            getApplicationContext().startActivity(intentHome);
        }

    }

    @Override
    public void ShowErrosMsj(String Msj) {
        Snackbar.make(this.TextInputEditTextUser, Msj, Snackbar.LENGTH_LONG).show();
    }

    public void SolicitarPosicionGeografia() {

        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(getApplicationContext().LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        try{

            Location location = getLastKnownLocation(locationManager);// locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            latitud = location.getLatitude();
            longitud = location.getLongitude();
        }catch (Exception e){
            Snackbar.make(this.TextInputEditTextUser , "Es necesario habilitar la opción de localización del dispositivo" , Snackbar.LENGTH_LONG).show();
        }
    }

    private Location getLastKnownLocation(LocationManager locationManager) {
        locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
              break;
            }
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }
        return bestLocation;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 5: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    LogInUsuario();
                }else {
                    Snackbar.make(this.TextInputEditTextUser , "La aplicación necesita tener acceso a la ubicación geografica" , Snackbar.LENGTH_LONG).show();
                }
            }
        }
    }
}
