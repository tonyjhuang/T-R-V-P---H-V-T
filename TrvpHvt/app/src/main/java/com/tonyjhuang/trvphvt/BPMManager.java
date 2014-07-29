package com.tonyjhuang.trvphvt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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

    @Inject
    public BPMManager(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public void showBPMDialog(Activity activity) {
        dialog = new BPMDialog(activity);

        dialog.showDialog();
    }

    /**
     * Handles a little dialog box that allows the user to set their bpm.
     */
    class BPMDialog {
        private Context context;
        private EditText bpmEditText;
        private AlertDialog dialog;

        public BPMDialog(Context context) {
            this.context = context;
        }

        public void showDialog() {
            if (bpmEditText == null || dialog == null)
                createDialog();

            bpmEditText.setText(String.valueOf(bpm));
            dialog.show();
        }

        private void createDialog() {
            View dialogView = layoutInflater.inflate(R.layout.dialog_bpm, null);
            bpmEditText = (EditText) dialogView.findViewById(R.id.bpm);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(dialogView)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String newBpm = bpmEditText.getText().toString();
                            if (!TextUtils.isEmpty(newBpm))
                                bpm = Integer.parseInt(newBpm);
                        }
                    })
                    .setTitle("Set BPM");

            dialog = builder.create();
        }
    }

}
