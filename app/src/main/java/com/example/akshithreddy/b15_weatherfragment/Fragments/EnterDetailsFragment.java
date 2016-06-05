package com.example.akshithreddy.b15_weatherfragment.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.akshithreddy.b15_weatherfragment.Common.Wconstants;
import com.example.akshithreddy.b15_weatherfragment.Interfaces.FragmentCallback;
import com.example.akshithreddy.b15_weatherfragment.Interfaces.WeatherTaskCallback;
import com.example.akshithreddy.b15_weatherfragment.Parser.Info;
import com.example.akshithreddy.b15_weatherfragment.R;
import com.example.akshithreddy.b15_weatherfragment.Tasks.WeatherTask;
import com.example.akshithreddy.b15_weatherfragment.Util.StringUtil;

/**
 * Created by akshithreddy on 9/29/2015.
 */
public class EnterDetailsFragment extends Fragment {


    private Button getDataBtn;
    private EditText cityET, stateET;
    private ProgressDialog pd;
    private FragmentCallback fragmentCallback;

    public void setFragmentCallback(FragmentCallback fragmentCallback){
        this.fragmentCallback=fragmentCallback;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.enterdetails_layout, container, false);
        getDataBtn = (Button) view.findViewById(R.id.button);
        cityET = (EditText) view.findViewById(R.id.cityET);
        stateET = (EditText) view.findViewById(R.id.stateET);
        return view;
    }

    public void onResume() {
        super.onResume();
        getDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityET.getText().toString();
                String state = stateET.getText().toString();
                city=StringUtil.getValidString(city);
                state=StringUtil.getValidString(state);



                String url = Wconstants.URL_HEAD + state + "/" + city + Wconstants.URL_TAIL;
                String[] urlArray = {url};
                WeatherTask weatherTask = new WeatherTask(weatherTaskCallback);
                weatherTask.execute(urlArray);
                pd = ProgressDialog.show(getActivity(), "Loading....", "Please Wait");
            }
        });
    }

    WeatherTaskCallback weatherTaskCallback = new WeatherTaskCallback() {

        @Override
        public void getWeatherData(Info info) {
            String message = "Temp in c=" + info.getCurrent_observation().getTemp_c() + "temp in f=" + info.getCurrent_observation().getTemp_f();

            if (pd.isShowing())
                pd.dismiss();

            if (fragmentCallback != null)
                fragmentCallback.WeatherData(info);
        }


    };
    }



