package com.tonyjhuang.trvphvt.CustomViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.Toast;

import com.tonyjhuang.trvphvt.R;

/**
 * Created by tonyhuang on 7/27/14.
 */
public class TrvpHvtPvd extends Button {
    String toast;

    public TrvpHvtPvd(Context context) {
        super(context);

    }

    public TrvpHvtPvd(Context context, AttributeSet attr) {
        super(context, attr);
        loadStateFromAttr(attr);
    }

    private void loadStateFromAttr(AttributeSet attr) {
        if (attr == null) {
            return; // quick exit
        }

        TypedArray a = null;
        try {
            a = getContext().obtainStyledAttributes(attr, R.styleable.TrvpHvtPvd);
            toast = a.getString(R.styleable.TrvpHvtPvd_toast);
        } finally {
            if (a != null) {
                a.recycle(); // ensure this is always called
            }
        }
    }

    public void showToast() {
        if (toast != null)
            Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
    }
}
