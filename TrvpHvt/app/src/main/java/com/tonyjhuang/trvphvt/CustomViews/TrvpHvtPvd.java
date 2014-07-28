package com.tonyjhuang.trvphvt.CustomViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tonyjhuang.trvphvt.R;

import info.hoang8f.widget.FButton;

/**
 * Created by tonyhuang on 7/27/14.
 */
public class TrvpHvtPvd extends FButton {
    static SoundPool soundPool = new SoundPool(16, AudioManager.STREAM_MUSIC, 0);
    static int soundId = -1;

    public TrvpHvtPvd(Context context) {
        super(context);
        init(context);
    }

    public TrvpHvtPvd(Context context, AttributeSet attr) {
        super(context, attr);
        loadStateFromAttr(attr);
        init(context);
    }


    private void loadStateFromAttr(AttributeSet attr) {
        if (attr == null) {
            return; // quick exit
        }

        TypedArray a = null;
        try {
            a = getContext().obtainStyledAttributes(attr, R.styleable.TrvpHvtPvd);
            //toast = a.getString(R.styleable.TrvpHvtPvd_toast);
        } finally {
            if (a != null) {
                a.recycle(); // ensure this is always called
            }
        }
    }

    private void init(Context context) {
        if(soundId == -1)
            soundId = soundPool.load(context, R.raw.hihat1, 1);
    }

    public void plvyTrvpHvt() {
        soundPool.play(soundId, 1, 1, 1, 0, 1);
    }
}
