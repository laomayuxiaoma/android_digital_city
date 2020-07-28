package com.example.amapservice;

import com.amap.api.location.AMapLocation;

import java.io.Serializable;

public interface OnLocationListener extends Serializable {
    void getData(AMapLocation aMapLocation);
}
