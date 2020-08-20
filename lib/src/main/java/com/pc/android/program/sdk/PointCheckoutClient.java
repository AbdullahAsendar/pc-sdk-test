package com.pc.android.program.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.modules.core.PermissionListener;
import com.pc.android.program.sdk.internal.event.EventManager;
import com.pc.android.program.sdk.internal.event.InternalEvent;
import com.pc.android.program.sdk.internal.reactnative.RNConfig;
import com.pc.android.program.sdk.internal.reactnative.RNLoader;

public class PointCheckoutClient {

    private RNLoader rnLoader;
    private Activity activity;

    @Nullable
    private PermissionListener mPermissionListener;

    private PointCheckoutClient(Activity activity, RNConfig config) {
        this.rnLoader = new RNLoader(activity, config);
        this.activity = activity;
    }

    public View getScreen(PointCheckoutScreen screen) {
        return rnLoader.getScreen(screen);
    }

    public View getMainScreen() {
        return rnLoader.getMainScreen();
    }

    public View getMerchantScreen() {
        return rnLoader.getMerchantScreen();
    }

    public View getTransactionScreen() {
        return rnLoader.getTransactionScreen();
    }

    public View getPromotionScreen() {
        return rnLoader.getPromotionScreen();
    }

    public View getQuickPayScreen() {
        return rnLoader.getQuickPayScreen();
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissions(String[] permissions, int requestCode, PermissionListener listener) {
        mPermissionListener = listener;
        activity.requestPermissions(permissions, requestCode);
    }

    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        if (mPermissionListener != null && mPermissionListener.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            mPermissionListener = null;
        }

    }

    public void onBackPressed(PointCheckoutEventListener listener) {
        EventManager.instance.addEventListener(PointCheckoutEvent.EXIT_REQUEST, listener);
    }

    public void goBack() {
        this.rnLoader.onReactEvent(PointCheckoutEvent.GO_BACK);
    }

    public void onException(PointCheckoutEventListener listener) {
        EventManager.instance.addEventListener(PointCheckoutEvent.EXCEPTION, listener);
    }

    public void handleDeeplinkingUrl(String url) {
        rnLoader.onReactEvent(InternalEvent.DEEPLINKING, url);
    }


    public static class Builder {
        private Activity activity;
        private PointCheckoutEnvironment environment;
        private String authToken;
        private PointCheckoutTheme theme;
        private PointCheckoutLanguage language;
        private boolean handleExceptions;

        public Builder activity(Activity activity){
            this.activity = activity;
            return this;
        }

        public Builder environment(PointCheckoutEnvironment environment){
            this.environment = environment;
            return this;
        }

        public Builder authToken(String authToken){
            this.authToken = authToken;
            return this;
        }

        public Builder theme(PointCheckoutTheme theme){
            this.theme = theme;
            return this;
        }

        public Builder language(PointCheckoutLanguage language){
            this.language = language;
            return this;
        }

        public Builder handleExceptions(boolean handleExceptions){
            this.handleExceptions = handleExceptions;
            return this;
        }

        public PointCheckoutClient build(){
            assert this.environment != null;
            assert this.authToken != null;
            assert this.theme != null;
            assert this.language != null;

            RNConfig config = RNConfig.Builder()//
                    .environment(this.environment)//
                    .authToken(this.authToken)//
                    .theme(this.theme)//
                    .language(this.language)//
                    .handleExceptions(this.handleExceptions)//
                    .build();

            return new PointCheckoutClient(this.activity, config);
        }
    }

}
