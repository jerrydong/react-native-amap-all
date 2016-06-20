package com.banma.amap;
import android.util.Log;
import android.graphics.Color;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.PolylineOptions;
import com.amap.api.maps2d.model.PolygonOptions;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.Callback;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import javax.annotation.Nullable;

import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.CameraUpdate;

public class AMapViewManager extends SimpleViewManager<MapView> {
    public static final String RCT_CLASS = "RCTAMap";

    private MapView mapView;

    @Override
    public String getName() {
        return RCT_CLASS;
    }

    @Override
    protected MapView createViewInstance(ThemedReactContext content) {
        mapView = new MapView(content);
        mapView.onCreate(null);
        return mapView;
    }

    public MapView getMapView() {
        return mapView;
    }

    @ReactProp(name = "zoomControl")
    public void setZoomControls(MapView view, Boolean enabled) {
        AMap aMap = view.getMap();
        aMap.getUiSettings().setZoomControlsEnabled(enabled);
    }

    @ReactProp(name = "zoom")
    public void setZoom(MapView view, float zoomNumber) {
        try{
          AMap aMap = view.getMap();
          CameraUpdate cu = CameraUpdateFactory.zoomTo(zoomNumber);
          aMap.moveCamera(cu);
        }catch(Exception e) {
        }
    }
    @ReactProp(name = "latlng")
    public void setLatLng(MapView mapView, @Nullable ReadableArray latlngArr) {
      if (latlngArr == null || latlngArr.size() != 2) {
        return;
      }
      AMap aMap = mapView.getMap();
      LatLng latlng = new LatLng(latlngArr.getDouble(0), latlngArr.getDouble(1));
      CameraUpdate cu = CameraUpdateFactory.changeLatLng(latlng);
      aMap.moveCamera(cu);
    }

    @ReactProp(name = "markerPosition")
    public void setMarkerPosition(MapView mapView, @Nullable ReadableArray latlng) {
      if (latlng == null || latlng.size() != 2) {
        return;
      }
      AMap aMap = mapView.getMap();

      MarkerOptions markerOption = new MarkerOptions();
      double lat = latlng.getDouble(0);
      double lng = latlng.getDouble(1);
      LatLng position = new LatLng(lat, lng);
      markerOption.position(position);
      aMap.addMarker(markerOption);
    }
}
