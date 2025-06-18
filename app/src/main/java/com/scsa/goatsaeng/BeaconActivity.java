package com.scsa.goatsaeng;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class BeaconActivity extends AppCompatActivity implements BeaconConsumer {

    private BeaconManager beaconManager;
    private TextView distanceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon);

        distanceText = findViewById(R.id.distanceText);

        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(
                new BeaconParser().setBeaconLayout(BeaconParser.IBEACON_LAYOUT)
        );
        beaconManager.bind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.addRangeNotifier((beacons, region) -> {
            if (!beacons.isEmpty()) {
                Beacon firstBeacon = beacons.iterator().next();
                double distance = firstBeacon.getDistance();
                runOnUiThread(() ->
                        distanceText.setText("거리: " + String.format("%.2f", distance) + " m"));
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(
                    new Region("myRegion", null, null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }
}
