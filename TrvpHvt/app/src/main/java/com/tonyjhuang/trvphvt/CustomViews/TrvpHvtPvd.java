package com.tonyjhuang.trvphvt.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tonyjhuang.trvphvt.R;

import info.hoang8f.widget.FButton;

/**
 * Created by tonyhuang on 7/27/14.
 */
public class TrvpHvtPvd extends FButton {
    int toast = -1;
    static SoundPool soundPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 0);
    static int soundId = -1;
    protected boolean isPressed;

    public TrvpHvtPvd(Context context) {
        super(context);
        init(context);
    }

    public TrvpHvtPvd(Context context, AttributeSet attr) {
        super(context, attr);
        loadStateFromAttr(attr);
        init(context);
    }

    /**
     * What soundfile should this pvd plvy?
     * @param attr
     */
    private void loadStateFromAttr(AttributeSet attr) {
        if (attr == null) {
            return; // quick exit
        }

        TypedArray a = null;
        try {
            a = getContext().obtainStyledAttributes(attr, R.styleable.TrvpHvtPvd);
            toast = Integer.parseInt(a.getString(R.styleable.TrvpHvtPvd_toast));
        } finally {
            if (a != null) {
                a.recycle(); // ensure this is always called
            }
        }
    }

    /**
     * Load sound to SoundPool instance
     * @param context
     */
    private void init(Context context) {
        if (soundId == -1)
            soundId = soundPool.load(context, R.raw.hihat1, 1);
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
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            TrvpHvtPvd.this.onTouch(view, event);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    playSound();
                    isPressed = true;
                    break;
                case MotionEvent.ACTION_UP:
                    isPressed = false;
                    break;
            }

            return false;
        }
    }
}
