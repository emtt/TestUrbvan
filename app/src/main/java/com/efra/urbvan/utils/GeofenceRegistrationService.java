package com.efra.urbvan.utils;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceRegistrationService extends IntentService {
    Handler mHandler;
    private static final String TAG = "GeoIntentService";

    public GeofenceRegistrationService() {
        super(TAG);
        mHandler = new Handler();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            Log.d(TAG, "GeofencingEvent error " + geofencingEvent.getErrorCode());
        } else {
            int transaction = geofencingEvent.getGeofenceTransition();
            List<Geofence> geofences = geofencingEvent.getTriggeringGeofences();
            Geofence geofence = geofences.get(0);
            if (transaction == Geofence.GEOFENCE_TRANSITION_ENTER && geofence.getRequestId().equals("URBVANHQ")) {
                Log.d(TAG, "ESTÁS DENTRO DE URBVAN HQ");
                mHandler.post(new DisplayToast(this, "ESTÁS DENTRO DE URBVAN HQ :)"));
            } else {
                Log.d(TAG, "ESTÁS FUERA DE URBVAN HQ");
                mHandler.post(new DisplayToast(this, "ESTÁS FUERA DE URBVAN HQ :("));
            }
        }
    }
}
