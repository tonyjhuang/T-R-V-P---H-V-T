package com.tonyjhuang.trvphvt.butterknife;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Base activity to handle ButterKnife injections
 * Created by tonyhuang on 7/27/14.
 */
public abstract class ButterKnifeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.inject(this);
    }

    /**
     * Layout resource to call setContentView on
     */
    protected abstract int getLayoutResourceId();
}
