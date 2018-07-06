package com.example.al_hussein.client;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Al-Hussein on 7/3/2018.
 */

public class fragmentFav extends Fragment {

    View v;
    public fragmentFav() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fav_fragment,container,false);
        return v;
    }
}
