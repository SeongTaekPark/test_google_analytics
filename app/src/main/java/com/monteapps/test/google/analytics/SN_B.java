package com.monteapps.test.google.analytics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class SN_B extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SN_B";

    /**
     * The {@link Tracker} used to record screen views.
     */
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // [START shared_tracker]
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        // [END shared_tracker]

        (findViewById(R.id.btn_b01)).setOnClickListener(this);
        (findViewById(R.id.btn_b02)).setOnClickListener(this);
        (findViewById(R.id.btn_b03)).setOnClickListener(this);

        // Send initial screen screen view hit.
        sendScreenImageName();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_b01:
                // [START custom_event]
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("B01_action")
                        .setLabel("B01_click")
                        .build());
                // [END custom_event]
                break;
            case R.id.btn_b02:
                // [START custom_event]
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("B02_action")
                        .setLabel("B02_click")
                        .build());
                // [END custom_event]
                break;
            case R.id.btn_b03:
                // [START custom_event]
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("B03_action")
                        .setLabel("B03_click")
                        .build());
                // [END custom_event]
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem saveItem = menu.add(0, R.id.menu_next, 0, "Next");
        saveItem.setIcon(R.drawable.ic_action_send);

        MenuItemCompat.setShowAsAction(saveItem, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_next:
                startActivity(new Intent(getApplicationContext(), SN_C.class));
                return true;

            default: return super.onOptionsItemSelected(item);
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
}
