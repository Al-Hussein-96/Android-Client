package com.example.al_hussein.client;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

/**
 * Created by Al-Hussein on 7/3/2018.
 */

public class fragmentCall extends Fragment {
    View v;
    public fragmentCall() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.call_fragment,container,false);
        return v;
    }
}
