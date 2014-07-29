package com.tonyjhuang.trvphvt.dagger;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by tonyhuang on 7/28/14.
 */
public abstract class DaggerFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((DaggerActivity) getActivity()).inject(this);
    }
}
