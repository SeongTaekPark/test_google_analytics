package com.monteapps.test.google.analytics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class SN_A extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "SN_A";

    /**
     * The {@link Tracker} used to record screen views.
     */
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        // Set the dispatch period in seconds.
        // 5분마다 데이터 전송.
        GoogleAnalytics.getInstance(this).setLocalDispatchPeriod(5);

        // [START shared_tracker]
        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
        // [END shared_tracker]

        (findViewById(R.id.btn_a01)).setOnClickListener(this);
        (findViewById(R.id.btn_a02)).setOnClickListener(this);
        (findViewById(R.id.btn_a03)).setOnClickListener(this);



        // Send initial screen screen view hit.
//        sendScreeneName();
        sendCustomDimension();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_a01:
                // [START custom_event]
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("A01_action")
                        .setLabel("A01_click")
                        .build());
                // [END custom_event]
                break;
            case R.id.btn_a02:
                // [START custom_event]
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("A02_action")
                        .setLabel("A02_click")
                        .build());
                // [END custom_event]
                break;
            case R.id.btn_a03:
                // [START custom_event]
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Action")
                        .setAction("A03_action")
                        .setLabel("A03_click")
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
            case R.id.menu_next:
                startActivity(new Intent(getApplicationContext(), SN_B.class));

                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Record a screen view hit.
     */
    private void sendScreeneName() {
        String name = TAG;

        // [START screen_view_hit]
        Log.i(TAG, "Setting screen name: " + name);
        mTracker.setScreenName(name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        // [END screen_view_hit]
    }

    /**
     * Send Custom Dimensions.
     */
    private void sendCustomDimension() {
        String name = TAG;
        String dimensionValue = "USER02";           // 사용자 세션ID

        // Get tracker.
        mTracker.setScreenName(name);

        // Send the custom dimension value with a screen view.
        // Note that the value only needs to be sent once.
        mTracker.send(new HitBuilders.ScreenViewBuilder().setCustomDimension(1, dimensionValue).build());
    }
}
