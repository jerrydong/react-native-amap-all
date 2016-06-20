package com.banma.amap;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AMapReactPackage implements ReactPackage {

    private AMapViewManager glManager;

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
         return Arrays.<NativeModule>asList(new AMapLocationReactModule(reactContext));
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        glManager = new AMapViewManager();
        return Arrays.<ViewManager>asList(
                glManager
        );
    }

    public AMapViewManager getManager() {
        return glManager;
    }
}
