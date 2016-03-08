package com.monteapps.test.google.analytics;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class SN_C extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SN_C";

    /**
     * The {@link Tracker} used to record screen views.
     */
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // [START shared_tracker]
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        // [END shared_tracker]

        (findViewById(R.id.btn_c01)).setOnClickListener(this);
        (findViewById(R.id.btn_c02)).setOnClickListener(this);
        (findViewById(R.id.btn_c03)).setOnClickListener(this);

        // Send initial screen screen view hit.
        sendScreenImageName();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_c01:
                // [START custom_event]
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("C01_action")
                        .setLabel("C01_click")
                        .build());
                // [END custom_event]
                break;
            case R.id.btn_c02:
                // [START custom_event]
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("C02_action")
                        .setLabel("C02_click")
                        .build());
                // [END custom_event]
                break;
            case R.id.btn_c03:
                // [START custom_event]
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("C03_action")
                        .setLabel("C03_click")
                        .build());
                // [END custom_event]
                break;
        }
    }

    /**
     * Record a screen view hit.
     */
    private void sendScreenImageName() {
        String name = TAG;

        // [START screen_view_hit]
        Log.i(TAG, "Setting screen name: " + name);
        mTracker.setScreenName(name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        // [END screen_view_hit]
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }
}
