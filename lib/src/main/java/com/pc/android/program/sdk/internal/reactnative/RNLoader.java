package com.pc.android.program.sdk.internal.reactnative;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.azendoo.reactnativesnackbar.SnackbarPackage;
import com.babisoft.ReactNativeLocalization.ReactNativeLocalizationPackage;
import com.dylanvann.fastimage.FastImageViewPackage;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.horcrux.svg.SvgPackage;
import com.oblador.vectoricons.VectorIconsPackage;
import com.pc.android.program.sdk.PointCheckoutEnvironment;
import com.pc.android.program.sdk.PointCheckoutScreen;
import com.pc.android.program.sdk.PointCheckoutEvent;
import com.pc.android.program.sdk.internal.event.InternalEvent;
import com.reactcommunity.rnlocalize.RNLocalizePackage;
import com.reactnativecommunity.slider.ReactSliderPackage;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;

import org.reactnative.camera.RNCameraPackage;

import java.util.Arrays;
import java.util.List;

import iyegoroff.RNColorMatrixImageFilters.ColorMatrixImageFiltersPackage;

public class RNLoader {

    private static final String APP_NAME = "programsdk";
    private static final String EVENT_NAME = "onEvent";
    private static final String EVENT_EVENT = "event";
    private static final String EVENT_DATA = "data";
    private static final String ENV = "ENV";
    private static final String AUTH_TOKEN = "AUTH_TOKEN";
    private static final String THEME = "THEME";
    private static final String LANGUAGE = "LANGUAGE";
    private static final String HANDLE_EXCEPTIONS = "HANDLE_EXCEPTIONS";
    private static final String OPEN_SCREEN = "OPEN_SCREEN";


    private ReactInstanceManager mReactInstanceManager;
    private ReactRootView mReactRootView;
    private Activity activity;
    private RNConfig config;


    public RNLoader(Activity activity, RNConfig config) {
        this.activity = activity;
        this.config = config;

        SoLoader.init(getContext(), false);

        mReactRootView = new ReactRootView(getContext());


        ReactInstanceManagerBuilder builder = ReactInstanceManager.builder()
                .setApplication(activity.getApplication())
                .setCurrentActivity(activity)
                .setJSMainModulePath("index")
                .addPackages(getPackages())
                .setInitialLifecycleState(LifecycleState.RESUMED);

        if (this.config.environment == PointCheckoutEnvironment.DEBUG) {
            builder.setUseDeveloperSupport(true);
        } else {
            builder.setBundleAssetName("index.android.bundle");
        }

        mReactInstanceManager = builder.build();
    }

    public void onReactEvent(PointCheckoutEvent event) {
        onReactEvent(event, null);
    }

    public void onReactEvent(PointCheckoutEvent event, @Nullable String data) {
        onReactEvent(event.name(), data);
    }

    public void onReactEvent(InternalEvent event, String data) {
        onReactEvent(event.name(), data);
    }

    public void onReactEvent(String event, String data) {
        WritableMap params = Arguments.createMap();
        params.putString(EVENT_EVENT, event);
        params.putString(EVENT_DATA, data);
        this.sendEvent(this.mReactInstanceManager.getCurrentReactContext(), EVENT_NAME, params);
    }

    public View getScreen(PointCheckoutScreen screen) {
        return getReactRootView(screen);
    }


    public View getMainScreen() {
        return getScreen(PointCheckoutScreen.MAIN_SCREEN);
    }

    public View getMerchantScreen() {
        return getScreen(PointCheckoutScreen.MERCHANT_SCREEN);
    }

    public View getTransactionScreen() {
        return getScreen(PointCheckoutScreen.TRANSACTION_SCREEN);
    }

    public View getPromotionScreen() {
        return getScreen(PointCheckoutScreen.PROMOTION_SCREEN);
    }

    public View getQuickPayScreen() {
        return getScreen(PointCheckoutScreen.QUICK_PAY_SCREEN);
    }


    protected List<ReactPackage> getPackages() {
        return Arrays.asList(
                new MainReactPackage(),
                new RNGestureHandlerPackage(),
                new SafeAreaContextPackage(),
                new RNLocalizePackage(),
                new ReactNativeLocalizationPackage(),
                new SnackbarPackage(),
                new VectorIconsPackage(),
                new ColorMatrixImageFiltersPackage(),
                new RNCWebViewPackage(),
                new ReactSliderPackage(),
                new SvgPackage(),
                new FastImageViewPackage(),
                new RNCameraPackage(),
                new RNEventHandlerPackage()
        );
    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    private Context getContext() {
        return activity;
    }

    private Bundle getInitialProps(PointCheckoutScreen screen) {
        final Bundle initialProperties = new Bundle();
        initialProperties.putString(OPEN_SCREEN, screen.getRoute());
        initialProperties.putString(ENV, this.config.environment.name());
        initialProperties.putString(AUTH_TOKEN, this.config.authToken);
        initialProperties.putString(THEME, this.config.theme.serialize(this.activity));
        initialProperties.putString(LANGUAGE, this.config.language.getIso2());
        initialProperties.putString(HANDLE_EXCEPTIONS, Boolean.toString(this.config.handleExceptions));
        return initialProperties;
    }

    private ReactRootView getReactRootView(PointCheckoutScreen screen) {
        mReactRootView.startReactApplication(mReactInstanceManager, APP_NAME, getInitialProps(screen));
        return mReactRootView;
    }


}
