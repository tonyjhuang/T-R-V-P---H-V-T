package com.tonyjhuang.trvphvt.dagger;

import android.os.Bundle;

import com.tonyjhuang.trvphvt.Trvpplication;
import com.tonyjhuang.trvphvt.butterknife.ButterKnifeActivity;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by tonyhuang on 7/28/14.
 */
public abstract class DaggerActivity extends ButterKnifeActivity {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Trvpplication trvpplication = (Trvpplication) getApplication();
        ObjectGraph objectGraph = trvpplication.getApplicationGraph().plus(new ActivityModule(this));
        objectGraph.inject(this);
    }

    /**
     * A list of modules to use for the individual activity graph. Subclasses can override this
     * method to provide additional modules provided they call and include the modules returned by
     * calling {@code super.getModules()}.
     */
    protected List<Object> getModules() {
        return Arrays.<Object>asList();
    }

    @Override
    protected void onDestroy() {
        // Eagerly clear the reference to the activity graph to allow it to be garbage collected as
        // soon as possible.
        objectGraph = null;

        super.onDestroy();
    }

    /**
     * Inject the supplied {@code object} using the activity-specific graph.
     */
    public void inject(Object object) {
        objectGraph.inject(object);
    }

}
