package com.example.ssis_tracker.adapter.actividades;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class AdapterFragmentActividades extends FragmentStatePagerAdapter {

    private List<Fragment> FragmentPageList   = new ArrayList<>();

    public AdapterFragmentActividades(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return FragmentPageList.get(i);
    }

    @Override
    public int getCount() {
        return FragmentPageList.size();
    }

    public void AddFragment(Fragment fragmentActividad , String Titulo){
        FragmentPageList.add(fragmentActividad);
    }

    @Override
    public int getItemPosition(Object object){
        return PagerAdapter.POSITION_NONE;
    }
}
