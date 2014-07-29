package com.tonyjhuang.trvphvt;

import android.app.Application;

import com.tonyjhuang.trvphvt.dagger.ApplicationModule;

import dagger.ObjectGraph;

/**
 * Created by tonyhuang on 7/28/14.
 */
public class Trvpplication extends Application {
    private ObjectGraph applicationGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationGraph = ObjectGraph.create(new ApplicationModule(this));
    }

    public ObjectGraph getApplicationGraph() {
        return applicationGraph;
    }
}
