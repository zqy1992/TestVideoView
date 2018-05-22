package com.halove.testvideoview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main5Activity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private String currTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        fragmentManager = getSupportFragmentManager();
    }

    private void switchFragment(String name) {
        if (currTag != null && currTag.equals(name)) {
            return;
        }
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        Fragment currentFragment = fragmentManager.findFragmentByTag(currTag);
        if(currentFragment!=null){
            ft.hide(currentFragment);
        }
        Fragment foundFragment = fragmentManager.findFragmentByTag(name);
        if(foundFragment==null){
            switch (name){
                case "分类":
                    foundFragment =new TestFragment();
                    ft.add(R.id.jz_fullscreen_id,foundFragment,name);
                    break;
            }
        }else if(foundFragment.isAdded()){
            ft.show(foundFragment);
        }

        ft.commit();
        currTag = name;
    }
}
