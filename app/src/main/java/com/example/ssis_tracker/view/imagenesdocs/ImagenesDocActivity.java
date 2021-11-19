package com.example.ssis_tracker.view.imagenesdocs;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.adapter.actividades.FragmentAdapterImageDocs;
import com.example.ssis_tracker.api.ConfigAPI;
import com.example.ssis_tracker.model.Actividad.documentos_adjuntos;
import java.util.ArrayList;

public class ImagenesDocActivity extends AppCompatActivity {

    private ViewPager imagenesDocViewPager;
    private FragmentAdapterImageDocs viewPagerAdapter;
    private ArrayList<documentos_adjuntos> arrayImagenes;
    private ConfigAPI configAPI;
    private int Actividad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagendocs);
        this.configAPI = new ConfigAPI();
        this.arrayImagenes = getIntent().getParcelableArrayListExtra("imagenes");
        this.Actividad = getIntent().getIntExtra("actividad",0);
        this.imagenesDocViewPager = findViewById(R.id.viewPagerImgDocs);
        SetViewPager();
        getSupportActionBar().setTitle(getIntent().getStringExtra("titulo"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void SetViewPager(){
        viewPagerAdapter = new FragmentAdapterImageDocs(getSupportFragmentManager());
        for(int i = 0; i  < arrayImagenes.size(); i++) {
            viewPagerAdapter.AddFragment(ImagenesDocsFragment.NuevaInstancia(this.configAPI.getURL()+"/actividad/documento/"+String.valueOf(this.Actividad)+"/"+ String.valueOf( arrayImagenes.get(i).getId_documento()) ) );
        }
        imagenesDocViewPager.setAdapter(viewPagerAdapter);
        imagenesDocViewPager.setCurrentItem(getIntent().getIntExtra("positionImg",0) );
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
