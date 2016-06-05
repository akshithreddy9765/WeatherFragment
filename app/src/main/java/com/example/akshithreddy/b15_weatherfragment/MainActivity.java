package com.example.akshithreddy.b15_weatherfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.akshithreddy.b15_weatherfragment.Fragments.DisplayDetailsFragment;
import com.example.akshithreddy.b15_weatherfragment.Fragments.EnterDetailsFragment;
import com.example.akshithreddy.b15_weatherfragment.Interfaces.FragmentCallback;
import com.example.akshithreddy.b15_weatherfragment.Parser.Info;

public class MainActivity extends AppCompatActivity {
    private enum FRAGMENT_LIST{
        ENTER_DETAILS_FRAGMENT,DISPLAY_DETAILS_FRAGMENT
    };

    private EnterDetailsFragment enterDetailsFragment;
    private DisplayDetailsFragment displayDetailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFragment(FRAGMENT_LIST.ENTER_DETAILS_FRAGMENT);

        /*EnterDetailsFragment enterDetailsFragment=new EnterDetailsFragment();
        enterDetailsFragment.setFragmentCallback(fragmentCallback);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.maincontainer,enterDetailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();*/

    }
    FragmentCallback fragmentCallback=new FragmentCallback() {
        @Override
        public void WeatherData(Info info) {
            DisplayDetailsFragment displayDetailsFragment= (DisplayDetailsFragment) createAndGetFragment(FRAGMENT_LIST.DISPLAY_DETAILS_FRAGMENT);
            displayDetailsFragment.setInfo(info);
            showFragment(FRAGMENT_LIST.DISPLAY_DETAILS_FRAGMENT);
            /*DisplayDetailsFragment displayDetailsFragment=new DisplayDetailsFragment();
            displayDetailsFragment.setInfo(info);
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.maincontainer,displayDetailsFragment);
            transaction.addToBackStack(null);
            transaction.commit();*/

        }
    };
    private void showFragment(FRAGMENT_LIST fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();

        Fragment fragmentToShow=null;
        switch (fragment){
            case ENTER_DETAILS_FRAGMENT:
                fragmentToShow=createAndGetFragment(fragment);
                break;
            case DISPLAY_DETAILS_FRAGMENT:
                fragmentToShow=createAndGetFragment(fragment);
                transaction.addToBackStack(null);
                break;
        }
        transaction.replace(R.id.maincontainer,fragmentToShow);
        transaction.commit();
    }
    private Fragment createAndGetFragment(FRAGMENT_LIST fragment){
        Fragment fragmentToReturn=null;
        switch (fragment){
            case ENTER_DETAILS_FRAGMENT:
                if (enterDetailsFragment==null){
                    enterDetailsFragment=new EnterDetailsFragment();
                    enterDetailsFragment.setFragmentCallback(fragmentCallback);
                }
                fragmentToReturn=enterDetailsFragment;
                break;
            case DISPLAY_DETAILS_FRAGMENT:
                if (displayDetailsFragment==null){
                    displayDetailsFragment=new DisplayDetailsFragment();
                }
                fragmentToReturn=displayDetailsFragment;
                break;
        }
        return fragmentToReturn;
    }
}
