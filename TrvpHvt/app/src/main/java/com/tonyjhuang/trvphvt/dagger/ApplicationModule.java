package com.tonyjhuang.trvphvt.dagger;

import android.content.Context;

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
    BPMManager provideBPMManager() {
        return new BPMManager();
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }
}
