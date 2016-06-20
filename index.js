import {PropTypes} from 'react';
var React = require('React');
var View = require('View');
var requireNativeComponent = require('requireNativeComponent');
var ReactNative = require('react-native');

var AMapView = React.createClass({
    propTypes: {
      ...View.propTypes,
        zoomControl: PropTypes.bool,
        zoom: PropTypes.number,
        latlng: PropTypes.array,
        markerPosition: PropTypes.array
  },
  setNativeProps: function(z){
     this._root.setNativeProps(z);
  },
    render: function() {
    return (
      <NativeAmapView ref={c=>this._root=c}
        style={this.props.style}
        zoomControl={this.props.zoomControl}
        zoom={this.props.zoom}
        latlng={this.props.latlng}
        markerPosition={this.props.markerPosition}
      />
    );
}

});

var NativeAmapView = requireNativeComponent('RCTAMap', AMapView, {
  nativeOnly: {zoomControl: true, zoom: true}
});
import {NativeModules, DeviceEventEmitter} from 'react-native';

const AMapLocation = NativeModules.AMapLocation;
const onLocationChanged = 'onLocationChangedAMAPLOCATION';


class ALocation {

  static startLocation(options) {
    AMapLocation.startLocation(options);
  }

  static stopLocation() {
    AMapLocation.stopLocation();
  }

  static addEventListener(handler) {
    const listener = DeviceEventEmitter.addListener(
        onLocationChanged,
        (loc) => {
            handler(loc);
        }
    );
    return listener.remove;
  }
}
module.exports.AMapView = AMapView;
module.exports.AMapLocation = ALocation;
