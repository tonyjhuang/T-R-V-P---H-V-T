package com.tonyjhuang.trvphvt;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.tonyjhuang.trvphvt.customviews.TrvpHvtPvd;
import com.tonyjhuang.trvphvt.dagger.DaggerActivity;
import com.tonyjhuang.trvphvt.time.BPMManager;
import com.tonyjhuang.trvphvt.time.Tempo;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectViews;


public class MainActivity extends DaggerActivity {
    @Inject
    BPMManager bpmManager;
    @Inject
    Tempo tempo;

    @InjectViews({R.id.test_pad_1,
            R.id.test_pad_2,
            R.id.test_pad_3,
            R.id.test_pad_4,
            R.id.test_pad_5,
            R.id.test_pad_6,
            R.id.test_pad_7,
            R.id.test_pad_8})
    List<TrvpHvtPvd> trvpHvtPvds;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        for(TrvpHvtPvd pvd : trvpHvtPvds) {
            pvd.setTempo(tempo);
            pvd.setOnTouchListener(pvd.getTrvpTouchListener());
        }

    }

    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            bpmManager.showBPMDialog(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
