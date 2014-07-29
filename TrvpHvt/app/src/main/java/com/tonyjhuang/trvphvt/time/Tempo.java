package com.tonyjhuang.trvphvt.time;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by tonyhuang on 7/29/14.
 */
@Singleton
public class Tempo {
    BPMManager bpmManager;

    @Inject
    public Tempo(BPMManager bpmManager) {
        this.bpmManager = bpmManager;
    }

    public interface TempoListener {
        /**
         * Called on whatever interval you've registered for
         */
        public void tick();

        public int getInterval();
    }

    public TempoTimer getTimer(TempoListener tempoListener) {
        return new TempoTimer(bpmManager.getBpm(), tempoListener);
    }

    public class TempoTimer extends Timer {
        /**
         * Interval [0,63] (after modulo).
         * 64th notes tick once every count
         * 32nd notes tick once every other count
         * 16th notes tick once ever 4 count
         * etc.
         */
        int counter = 0;

        int interval;
        TempoListener tempoListener;

        public TempoTimer(int bpm, TempoListener tempoListener) {
            this.interval = tempoListener.getInterval();
            this.tempoListener = tempoListener;
            schedule(incrementAndTick, 0, bpmToMillis(bpm));
        }

        private long bpmToMillis(int bpm) {
            return (long) (240.0 / bpm * 1000 / 64);
        }

        TimerTask incrementAndTick = new TimerTask() {
            @Override
            public void run() {
                if ((counter++ % (interval) == 0))
                    tempoListener.tick();
            }

        };
    }
}
