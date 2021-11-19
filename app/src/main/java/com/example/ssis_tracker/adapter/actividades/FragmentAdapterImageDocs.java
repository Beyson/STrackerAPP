package com.example.ssis_tracker.adapter.actividades;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class FragmentAdapterImageDocs extends FragmentPagerAdapter {

    private List<Fragment> FragmentPageList   = new ArrayList<>();

    public FragmentAdapterImageDocs(FragmentManager fm) {
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

    public void AddFragment(Fragment fragmentImgDoc){
        FragmentPageList.add(fragmentImgDoc);
    }
}
