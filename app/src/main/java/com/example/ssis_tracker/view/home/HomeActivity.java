package com.example.ssis_tracker.view.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.view.agenda.AgendaFragment;
import com.example.ssis_tracker.view.busquedas.BusquedaFragment;
import com.example.ssis_tracker.view.direcciones.DireccionesFragment;
import com.example.ssis_tracker.view.login.LoginActivity;
import com.example.ssis_tracker.view.notificaciones.NotificacionesHomeFragment;
import com.example.ssis_tracker.view.performance.PerformanceFragment;
import org.json.JSONArray;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeActivityView {

    private int itemIdSelect;
    private DrawerLayout drawerLayout;
    private SharedPreferences sharedPreferences;
    private NavigationView navigationView;
    private TextView TextViewUsuarioLogeado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        this.sharedPreferences = getApplicationContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        if(sharedPreferences.getInt("id_usuario",0)==0){
            Intent intent = new Intent(getApplicationContext() , LoginActivity.class );
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplicationContext().startActivity(intent);
        }

        this.navigationView         = findViewById(R.id.nav_view);
        this.navigationView.setNavigationItemSelectedListener(this);
        this.TextViewUsuarioLogeado = this.navigationView.getHeaderView(0).findViewById(R.id.TextViewUsuarioLogeado);
        this.TextViewUsuarioLogeado.setText(this.sharedPreferences.getString("nombre",""));

        CrearMenu();

        getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new PerformanceFragment()).addToBackStack(null).commit();
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.constraintLayout);
                if (fragment != null) {
                    SeleccionMenuOnBackPressed(fragment);
                }
            }
        });
    }

    private void SeleccionMenuOnBackPressed( Fragment fragment){

        int index = 0;
        ViewCompat.setElevation(findViewById(R.id.appbar),8);

        if(fragment instanceof  PerformanceFragment){
            index = IndexItemMenuSeleccionado(navigationView.getMenu()
                    .findItem(R.id.nav_performance).getTitle().toString());

            onNavigationItemSelected( navigationView.getMenu().getItem( 0 ).getSubMenu().getItem(index) );
        }else if(fragment instanceof DireccionesFragment){

            index = IndexItemMenuSeleccionado(navigationView.getMenu()
                    .findItem(R.id.nav_direcciones).getTitle().toString());

            onNavigationItemSelected( navigationView.getMenu().getItem( 0 ).getSubMenu().getItem(index) );
        }else if(fragment instanceof BusquedaFragment){

            ViewCompat.setElevation(findViewById(R.id.appbar),0);
            index = IndexItemMenuSeleccionado(navigationView.getMenu()
                    .findItem(R.id.nav_find).getTitle().toString());

            onNavigationItemSelected( navigationView.getMenu().getItem( 1 ).getSubMenu().getItem(index) );
        } else if(fragment instanceof AgendaFragment){

            index = IndexItemMenuSeleccionado(navigationView.getMenu()
                    .findItem(R.id.nav_agenda).getTitle().toString());

            onNavigationItemSelected(  navigationView.getMenu().getItem( 2 ).getSubMenu().getItem(index) );
        }else if(fragment instanceof NotificacionesHomeFragment) {

            ViewCompat.setElevation(findViewById(R.id.appbar),0);
            index = IndexItemMenuSeleccionado(navigationView.getMenu()
                    .findItem(R.id.nav_notificaciones).getTitle().toString());

            onNavigationItemSelected( navigationView.getMenu().getItem( 2 ).getSubMenu().getItem(index) );
        }
    }

    private int IndexItemMenuSeleccionado(String MenuItem){
        int Index=0;
        for(int y = 0; y < navigationView.getMenu().size() -1; y++){
           for(int i = 0; i <  navigationView.getMenu().getItem(y).getSubMenu().size(); i++){
               if(MenuItem == navigationView.getMenu().getItem(y).getSubMenu().getItem(i).getTitle().toString()) {
                   Index = i;
                   break;
               }
           }
        }
        return Index;
    }

    public void CrearMenu(){
        try{
            JSONArray jsonArray =  new JSONArray( this.sharedPreferences.getString("permisos","") );
            for(int i = 0; i < jsonArray.length(); i++){
                MenuItem itemGrupo = navigationView.getMenu().findItem(getResources().getIdentifier(jsonArray.getJSONObject(i).getString("id"),"id",getPackageName()));
                itemGrupo.setTitle(jsonArray.getJSONObject(i).getString("opcion"));
                JSONArray ArraySubOpciones = new JSONArray( jsonArray.getJSONObject(i).getString ("sub_opciones"));

                for(int y = 0; y  < ArraySubOpciones.length(); y++  ){
                    MenuItem item = navigationView.getMenu().findItem(getResources().getIdentifier(ArraySubOpciones.getJSONObject(y).getString("id"),"id",getPackageName()));
                    if(item != null){
                        item.setTitle(ArraySubOpciones.getJSONObject(y).getString("sub_opcion"));
                        item.setVisible(true);
                        item.setEnabled(
                                (ArraySubOpciones.getJSONObject(y).getString("habilitado") =="true")?true:false
                        );
                    }
                }
            }
        }
        catch(Exception e){
            int l =0;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if(getSupportFragmentManager().getBackStackEntryCount()>1){
                super.onBackPressed();
            }else{
                getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new PerformanceFragment()).addToBackStack(null).commit();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if(itemId != itemIdSelect) {
            if(itemId == R.id.nav_performance){
                getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new PerformanceFragment()).addToBackStack(null).commit();
            }else if (itemId == R.id.nav_direcciones) {
                getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new DireccionesFragment()).addToBackStack(null).commit();
            } else if (itemId == R.id.nav_agenda) {
                getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new AgendaFragment()).addToBackStack(null).commit();
            } else if (itemId == R.id.nav_find) {
                getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new BusquedaFragment()).addToBackStack(null).commit();
            } /*else if (itemId == R.id.nav_manage) {

            } else if (itemId == R.id.nav_share) {

            } else if (itemId == R.id.nav_send) {

            }*/
            else if(itemId == R.id.nav_notificaciones){
                getSupportFragmentManager().beginTransaction().replace(R.id.constraintLayout, new NotificacionesHomeFragment()).addToBackStack(null).commit();
            }
            else if(itemId == R.id.nav_logout){
                Intent intentLogOut = new Intent(getApplicationContext() , LoginActivity.class);
                this.sharedPreferences.edit().clear().apply();
                finish();
                getApplicationContext().startActivity(intentLogOut);
            }
            cleanFragmmentViewGroup();
        }

        itemIdSelect = itemId;
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void cleanFragmmentViewGroup() {
        List<Fragment> fragmentsList = getSupportFragmentManager().getFragments();
        for(Fragment fragment: fragmentsList){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }
}
