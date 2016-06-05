package com.example.akshithreddy.b15_weatherfragment.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.akshithreddy.b15_weatherfragment.Parser.Info;
import com.example.akshithreddy.b15_weatherfragment.R;

/**
 * Created by akshithreddy on 9/29/2015.
 */
public class DisplayDetailsFragment extends Fragment {

    private TextView resultTV;
    private Info info;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.displaydetails_layout,container,false);
        resultTV= (TextView) view.findViewById(R.id.resultTv);
        return view;

    }
    public void setInfo(Info info){
        this.info=info;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (info == null || info.getCurrent_observation().getTemp_f() == null) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Error fetching data")
                    .setMessage("please enter a valid state and city")
                    .setPositiveButton(R.string.positivebutton, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }


                    })
                    .show();


        } else {

            String message = "Temp in c=" + info.getCurrent_observation().getTemp_c() + "temp in f=" + info.getCurrent_observation().getTemp_f();
            resultTV.setText(message);

        }
    }
}
