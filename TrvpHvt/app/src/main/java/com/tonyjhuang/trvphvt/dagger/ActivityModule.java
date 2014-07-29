package com.tonyjhuang.trvphvt.dagger;

import android.app.Activity;

import com.tonyjhuang.trvphvt.MainActivity;

import dagger.Module;

/**
 * This module represents objects which exist only for the scope of a single activity. We can
 * safely create singletons using the activity instance because the entire object graph will only
 * ever exist inside of that activity.
 */
@Module(
        injects = {
                DaggerActivity.class,
                MainActivity.class
        },
        addsTo = ApplicationModule.class,
        library = true
)
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }
}
