package com.tonyjhuang.trvphvt.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.tonyjhuang.trvphvt.R;
import com.tonyjhuang.trvphvt.time.Tempo;

import info.hoang8f.widget.FButton;

/**
 * Created by tonyhuang on 7/27/14.
 */
public class TrvpHvtPvd extends FButton implements Tempo.TempoListener {
    int interval = -1;
    static SoundPool soundPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 0);
    static int soundId = -1;
    protected boolean isPressed;
    Tempo tempo;

    public TrvpHvtPvd(Context context) {
        super(context);
        init(context);
    }

    public TrvpHvtPvd(Context context, AttributeSet attr) {
        super(context, attr);
        loadStateFromAttr(attr);
        init(context);
    }

    public void setTempo(Tempo tempo) {
        this.tempo = tempo;
    }

    /**
     * What soundfile should this pvd plvy?
     *
     * @param attr
     */
    private void loadStateFromAttr(AttributeSet attr) {
        if (attr == null) {
            return; // quick exit
        }

        TypedArray a = null;
        try {
            a = getContext().obtainStyledAttributes(attr, R.styleable.TrvpHvtPvd);
            interval = a.getInt(R.styleable.TrvpHvtPvd_note, -1);
        } finally {
            if (a != null) {
                a.recycle(); // ensure this is always called
            }
        }
        Log.d("tvp", "" + interval);
    }

    @Override
    public int getInterval() {
        return interval;
    }

    /**
     * Load sound to SoundPool instance
     *
     * @param context
     */
    private void init(Context context) {
        if (soundId == -1)
            soundId = soundPool.load(context, R.raw.hihat2, 1);
    }

    public void tick() {
        if (isPressed)
            playSound();
    }

    /**
     * Play the sound associated with this pvd
     */
    private void playSound() {
        soundPool.play(soundId, 1, 1, 1, 0, 1);
    }

    public TrvpTouchListener getTrvpTouchListener() {
        return new TrvpTouchListener();
    }

    private class TrvpTouchListener implements OnTouchListener {
        Tempo.TempoTimer timer;
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            TrvpHvtPvd.this.onTouch(view, event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    timer = tempo.getTimer(TrvpHvtPvd.this);
                    isPressed = true;
                    break;
                case MotionEvent.ACTION_UP:
                    timer.cancel();
                    isPressed = false;
                    break;
            }

            return false;
        }
    }
}
