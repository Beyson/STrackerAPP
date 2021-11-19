package com.example.ssis_tracker.elements;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permissions {

    private Context context;
    private Activity activity;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 5 ;
    private static final int MY_PERMISSIONS_REQUEST_STORAGE  = 4 ;

    public Permissions(Context cntx , Activity actv){
        this.context = cntx;
        this.activity = actv;
    }

    public boolean LocationPermissionsGranted(){

        if(ContextCompat.checkSelfPermission(this.context , Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this.activity , new String[]{Manifest.permission.ACCESS_FINE_LOCATION } , MY_PERMISSIONS_REQUEST_LOCATION);
            return  false;
        }
        return true;
    }

    public boolean StoragePermissionsGranted(){
        if(ContextCompat.checkSelfPermission(this.context , Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.activity , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE } , MY_PERMISSIONS_REQUEST_STORAGE);
            return  false;
        }
        return true;
    }
}
