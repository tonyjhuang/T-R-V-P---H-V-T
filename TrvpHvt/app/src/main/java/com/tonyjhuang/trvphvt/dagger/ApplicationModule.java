package com.tonyjhuang.trvphvt.dagger;

import android.content.Context;
import android.view.LayoutInflater;

import com.tonyjhuang.trvphvt.BPMManager;
import com.tonyjhuang.trvphvt.Trvpplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tonyhuang on 7/28/14.
 */
@Module(library = true)
public class ApplicationModule {
    Trvpplication application;

    public ApplicationModule(Trvpplication trvpplication) {
        application = trvpplication;
    }

    @Provides
    @Singleton
    BPMManager provideBPMManager(LayoutInflater layoutInflater) {
        return new BPMManager(layoutInflater);
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    LayoutInflater provideLayoutInflater() {
        return (LayoutInflater) application.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}
