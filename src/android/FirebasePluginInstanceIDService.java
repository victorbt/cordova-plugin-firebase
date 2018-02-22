package org.apache.cordova.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import io.intercom.android.sdk.push.IntercomPushClient;

public class FirebasePluginInstanceIDService extends FirebaseInstanceIdService {

    private final IntercomPushClient intercomPushClient = new IntercomPushClient();

    private static final String TAG = "FirebasePlugin";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        intercomPushClient.sendTokenToIntercom(getApplication(), refreshedToken);
        FirebasePlugin.sendToken(refreshedToken);
    }
}
