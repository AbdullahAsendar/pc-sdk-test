package com.pc.android.program.sdk;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;

public abstract class PointCheckoutActivity extends Activity implements PermissionAwareActivity {

    protected abstract PointCheckoutClient getPointCheckoutClient(Activity activity);
    protected abstract PointCheckoutScreen getInitalScreen();

    private PointCheckoutClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        client = this.getPointCheckoutClient(this);
        setContentView(client.getScreen(getInitalScreen()));

        client.onBackPressed(new PointCheckoutEventListener() {
            @Override
            public void onEvent(String value) {
                PointCheckoutActivity.this.finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        client.goBack();
    }

    @Override
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        client.requestPermissions(permissions, requestCode, listener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        client.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
