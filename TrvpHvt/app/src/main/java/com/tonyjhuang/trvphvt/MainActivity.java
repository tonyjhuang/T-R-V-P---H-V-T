package com.tonyjhuang.trvphvt;

import android.view.Menu;
import android.view.MenuItem;

import com.tonyjhuang.trvphvt.ButterKnife.ButterKnifeActivity;
import com.tonyjhuang.trvphvt.CustomViews.TrvpHvtPvd;

import java.util.List;

import butterknife.InjectViews;
import butterknife.OnClick;


public class MainActivity extends ButterKnifeActivity {

    @InjectViews({ R.id.test_pad_1,
            R.id.test_pad_2,
            R.id.test_pad_3,
            R.id.test_pad_4,
            R.id.test_pad_5,
            R.id.test_pad_6,
            R.id.test_pad_7,
            R.id.test_pad_8 })
    List<TrvpHvtPvd> trvpHvtPvds;

    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @OnClick({ R.id.test_pad_1,
            R.id.test_pad_2,
            R.id.test_pad_3,
            R.id.test_pad_4,
            R.id.test_pad_5,
            R.id.test_pad_6,
            R.id.test_pad_7,
            R.id.test_pad_8 })
    public void tvpPvd(TrvpHvtPvd trvpHvtPvd) {
        trvpHvtPvd.plvyTrvpHvt();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
