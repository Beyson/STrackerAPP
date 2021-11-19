package com.example.ssis_tracker.view.notificaciones;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ssis_tracker.R;
import com.example.ssis_tracker.view.crearnoticia.CrearNoticiaActivity;

public class NotificacionesHomeFragment extends Fragment implements NoticiasFragmentView {

    private View view;
    private TabLayout TabLayoutAvisostabs;
    private ViewPager ViewPagerAvisos;
    private PagerAdapter pagerAdapter;
    private FloatingActionButton FloatingActionButtonCrearNoticia;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view                = inflater.inflate(R.layout.fragment_notifiaciones, container, false);
        this.ViewPagerAvisos     = this.view.findViewById(R.id.ViewPagerAvisos);
        this.TabLayoutAvisostabs = this.view.findViewById(R.id.TabLayoutAvisostabs);
        this.pagerAdapter        = new PagerAdapter(getFragmentManager());
        this.ViewPagerAvisos.setAdapter(this.pagerAdapter);
        this.ViewPagerAvisos.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.TabLayoutAvisostabs));
        this.TabLayoutAvisostabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(this.ViewPagerAvisos));
        this.FloatingActionButtonCrearNoticia = this.view.findViewById(R.id.FloatingActionButtonCrearNoticia);

        this.FloatingActionButtonCrearNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCrearActivity = new Intent(v.getContext() , CrearNoticiaActivity.class);
                v.getContext().startActivity(intentCrearActivity);
            }
        });

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Notificaciones");
        return this.view;
    }


    @Override
    public void SolicitarNoticias() { }

    @Override
    public void MostarNoticias() { }

    public static class HolderTabs extends Fragment {

        public HolderTabs(){}

        public static Fragment NewInstance(){
            NoticiasFragment noticiasFragment = new NoticiasFragment();

            return noticiasFragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container   ,
                                 @Nullable Bundle savedInstanceState) {
            return  LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_notifiaciones, container , false);
        }
    }

    public class PagerAdapter extends FragmentPagerAdapter {


        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return NotificacionesHomeFragment.HolderTabs.NewInstance();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


}
