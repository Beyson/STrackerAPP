package com.example.ssis_tracker.view.busquedas;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.ssis_tracker.R;
import com.example.ssis_tracker.model.Actividad;
import com.example.ssis_tracker.model.Proceso;
import com.example.ssis_tracker.model.Proyecto;
import com.example.ssis_tracker.model.Searched;
import com.example.ssis_tracker.presenter.actividades.ActividadesActivityPresenter;
import com.example.ssis_tracker.presenter.actividades.ActividadesActivityPresenterImpl;
import com.example.ssis_tracker.presenter.procesos.ProcesosActivityPresenter;
import com.example.ssis_tracker.presenter.procesos.ProcesosActivityPresenterImpl;
import com.example.ssis_tracker.presenter.proyectos.ProyectosActivityPresenter;
import com.example.ssis_tracker.presenter.proyectos.ProyectosActivityPresenterImpl;
import com.example.ssis_tracker.view.actividades.ActividadesActivityView;
import com.example.ssis_tracker.view.procesos.ProcesosActivityView;
import com.example.ssis_tracker.view.proyectos.ProyectosActivityView;

import java.util.ArrayList;
import java.util.List;

public class BusquedaFragment extends Fragment implements  ProyectosActivityView, ProcesosActivityView, ActividadesActivityView {

    private View view;
    private MenuItem searchItem;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Searched> arrayListProjects,arrayListProcess,arrayListActivities;
    private ProyectosActivityPresenter proyectosActivityPresenter;
    private ProcesosActivityPresenter procesosActivityPresenter;
    private ActividadesActivityPresenter actividadesActivityPresenter;
    private int tabIndex = 0;
    private SharedPreferences sharedPreferences;
    private int tabIndexAnterior = 0;
    private int[] tabIcons = {
            R.drawable.ic_assignment,
            R.drawable.ic_settings,
            R.drawable.ic_history
    };

    public BusquedaFragment(){
        this.arrayListProjects= new ArrayList<>();
        this.arrayListProcess= new ArrayList<>();
        this.arrayListActivities=new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_busquedas, container, false);
        this.viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        this.tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        this.sharedPreferences = getContext().getSharedPreferences("USER", Context.MODE_PRIVATE);

        this.proyectosActivityPresenter = new ProyectosActivityPresenterImpl(this);
        this.proyectosActivityPresenter.getallProyectos();

        this.procesosActivityPresenter = new ProcesosActivityPresenterImpl(this);
        this.procesosActivityPresenter.getallProcesos();

        this.actividadesActivityPresenter = new ActividadesActivityPresenterImpl(this,null);
        this.actividadesActivityPresenter.getallActividades();

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Busqueda");
        setHasOptionsMenu(true);
        return this.view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.direcciones, menu);
        this.searchItem = menu.findItem(R.id.searchViewFind);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.searchItem.getIcon().setTint(Color.WHITE);
        }
        this.searchItem.setEnabled(true);
        this.searchItem.setVisible(true);

        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Searched> listCloneProyects = SearchArray(arrayListProjects,s);
                ArrayList<Searched> listCloneProcess  = SearchArray(arrayListProcess,s);
                ArrayList<Searched> lisCloneActivities= SearchArray(arrayListActivities,s);

                setupViewPager(viewPager,listCloneProyects,listCloneProcess,lisCloneActivities);
                return false;
            }
        });
        searchView.setQueryHint("Buscar...");

        super.onCreateOptionsMenu(menu, inflater);
    }

    public ArrayList<Searched> SearchArray(ArrayList<Searched> arrayListSearch, String s){
        ArrayList <Searched> listClone = new ArrayList<Searched>();
        if(arrayListSearch != null || !s.equals("")){
            for(Searched search : arrayListSearch){
                if(search.getTitulo().toUpperCase().contains(s.toUpperCase())){
                    listClone.add(search);
                }
            }
        }
        return listClone;
    }

    private void setupViewPager(ViewPager viewPager, ArrayList<Searched> arrayProjects,
                                ArrayList<Searched> arrayProcess, ArrayList<Searched> arrayActivities) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new EncontradosFragment(arrayProjects), "Proyectos ("+arrayProjects.size()+")");
        adapter.addFragment(new EncontradosFragment(arrayProcess), "Procesos ("+arrayProcess.size()+")");
        adapter.addFragment(new EncontradosFragment(arrayActivities), "Actividades ("+arrayActivities.size()+")");
        viewPager.setAdapter(adapter);

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);

        tabLayout.getTabAt(tabIndexAnterior).select();
    }

    @Override
    public void getProyectos(int codigo, int tipo) {

    }

    @Override
    public void getProcesos(int codigo, int tipo, int id_direccion) {

    }

    @Override
    public void getActividades(int proceso, int cod_actividad, int id_direccion) {

    }

    @Override
    public void getallProyectos() {
        proyectosActivityPresenter.getallProyectos();
    }

    @Override
    public void showProyectos(ArrayList<Proyecto> proyectoArrayList) {
        for(int i=0; i<proyectoArrayList.size(); i++) {
            String NombreProyecto = proyectoArrayList.get(i).getNombre();
            String DescProyecto   = proyectoArrayList.get(i).getDescripcion();
            int IdProyecto        = proyectoArrayList.get(i).getId();
            String Estado         = proyectoArrayList.get(i).getEstado();
            String Color          = proyectoArrayList.get(i).getColor();
            arrayListProjects.add(new Searched(NombreProyecto,DescProyecto,IdProyecto,Estado,Color,0));
        }
        SetConfigurationPager();
    }



    @Override
    public void getallProcesos() {
        procesosActivityPresenter.getallProcesos();
    }

    @Override
    public void showProcesos(ArrayList<Proceso> procesoArrayList) {
        for(int i=0; i<procesoArrayList.size();i++){
            String NombreProceso = procesoArrayList.get(i).getNombre();
            String DescProceso   = procesoArrayList.get(i).getDescripcion();
            int IdProceso        = procesoArrayList.get(i).getId();
            String Estado        = procesoArrayList.get(i).getEstado();
            String Color         = procesoArrayList.get(i).getColor();
            arrayListProcess.add(new Searched(NombreProceso,DescProceso,IdProceso,Estado, Color,1));
        }
        SetConfigurationPager();
    }

    @Override
    public void getallActividades() {
        actividadesActivityPresenter.getallActividades();
    }

    @Override
    public void showActividades(ArrayList<Actividad> actividadArrayList) {
        for(int i=0; i<actividadArrayList.size(); i++){
            String NombreActividad = actividadArrayList.get(i).getNombre();
            String DescActividad   = actividadArrayList.get(i).getDescripcion();
            int IdActividad        = actividadArrayList.get(i).getId();
            String Estado          = actividadArrayList.get(i).getEstado();
            String Color           = actividadArrayList.get(i).getColor();
            arrayListActivities.add(new Searched(NombreActividad,DescActividad,IdActividad,Estado,Color,2));
        }
        SetConfigurationPager();
    }

    @Override
    public void showMessage(String strMessage) {
    }

    @Override
    public void showSwipeRefreshLayout(boolean bool) {}

    @Override
    public void configAppBar(boolean bolDefault) {

    }

    public void SetConfigurationPager(){
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager,arrayListProjects,arrayListProcess,arrayListActivities);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabIndexAnterior = tabIndex;
                tabIndex = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
