package com.tonyjhuang.trvphvt.time;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.tonyjhuang.trvphvt.R;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Keeps track of the user's set bpm
 * Created by tonyhuang on 7/28/14.
 */
@Singleton
public class BPMManager {
    LayoutInflater layoutInflater;
    private int bpm = 120;
    private BPMDialog dialog;
    private BPMChangeListener listener;

    public int getBpm() {
        return bpm;
    }

    @Inject
    public BPMManager(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public void showBPMDialog(Activity activity) {
        dialog = new BPMDialog(activity);

        dialog.showDialog();
    }

    public void setBPMChangeListener(BPMChangeListener listener) {
        this.listener = listener;
    }

    public interface BPMChangeListener {
        public void onBPMChanged(int bpm);
    }

    /**
     * Handles a little dialog box that allows the user to set their bpm.
     */
    class BPMDialog {
        private Context context;
        private NumberPicker bpmPicker;
        private AlertDialog dialog;

        public BPMDialog(Context context) {
            this.context = context;
        }

        public void showDialog() {
            if (bpmPicker == null || dialog == null)
                createDialog();

            bpmPicker.setValue(bpm);
            dialog.show();
        }

        private void createDialog() {
            View dialogView = layoutInflater.inflate(R.layout.dialog_bpm, null);

            bpmPicker = (NumberPicker) dialogView.findViewById(R.id.bpm);
            bpmPicker.setMinValue(40);
            bpmPicker.setMaxValue(200);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(dialogView)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            bpm = bpmPicker.getValue();
                            listener.onBPMChanged(bpm);
                        }
                    })
                    .setTitle("Set BPM");

            dialog = builder.create();
        }
    }

}
