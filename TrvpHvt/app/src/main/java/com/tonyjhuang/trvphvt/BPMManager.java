package com.tonyjhuang.trvphvt;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Keeps track of the user's set bpm
 * Created by tonyhuang on 7/28/14.
 */
@Singleton
public class BPMManager {
    private int field = 0;

    @Inject
    public BPMManager() {

    }

    public int act() {
        return ++field;
    }
}
