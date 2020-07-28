package com.muheda.mdsearchview.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 创建日期：2019/11/27 on 17:50
 * 描述:
 * 作者: zhangming
 */
public class MySearchDto  {
    /**
     * code : 200
     * success : true
     * data : [{"stationId":1,"stationName":"测试站点A","stationLng":116.552611,"stationLat":40.026118,"stationType":"公共","servicerName":null,"imageLogo":"http://172.17.3.142:8090/download/3/01/6270c3938e6447acbf8d39e89a9367f7?sig=81bf3b294941f46f1c02e43b77a1ac19","pileType":null,"price":null,"distance":0,"power":null,"serviceId":null,"chargingMode":"直流","stationStatus":"正常使用","construction":"居民区","parkFee":"限时收费","supportFacility":"商场,餐厅","weightScore":0,"address":"表达式"},{"stationId":2,"stationName":"测试站点B","stationLng":116.533611,"stationLat":40.036118,"stationType":"公共","servicerName":null,"imageLogo":"http://172.17.3.142:8090/download/3/01/6270c3938e6447acbf8d39e89a9367f7?sig=81bf3b294941f46f1c02e43b77a1ac19","pileType":null,"price":null,"distance":0,"power":null,"serviceId":null,"chargingMode":"直流","stationStatus":"正常使用","construction":"居民区","parkFee":"全天收费","supportFacility":"商场,餐厅","weightScore":0,"address":"表达式"},{"stationId":3,"stationName":"测试站点F","stationLng":116.153611,"stationLat":40.046118,"stationType":"公共","servicerName":null,"imageLogo":"http://172.17.3.142:8090/download/3/01/6270c3938e6447acbf8d39e89a9367f7?sig=81bf3b294941f46f1c02e43b77a1ac19","pileType":null,"price":null,"distance":0,"power":null,"serviceId":null,"chargingMode":"直流","stationStatus":"正常使用","construction":"居民区","parkFee":"全天收费","supportFacility":"商场,餐厅","weightScore":0,"address":"表达式"},{"stationId":5,"stationName":"测试站点C","stationLng":116.553711,"stationLat":31.066118,"stationType":"公共","servicerName":null,"imageLogo":"http://172.17.3.142:8090/download/3/01/6270c3938e6447acbf8d39e89a9367f7?sig=81bf3b294941f46f1c02e43b77a1ac19","pileType":null,"price":null,"distance":0,"power":null,"serviceId":null,"chargingMode":"直流","stationStatus":"正常使用","construction":"居民区","parkFee":"全天收费","supportFacility":"商场,餐厅","weightScore":0,"address":"表达式"},{"stationId":24,"stationName":"测试站点Y","stationLng":116.553711,"stationLat":31.066118,"stationType":"公共","servicerName":null,"imageLogo":"http://172.17.3.142:8090/download/3/01/6270c3938e6447acbf8d39e89a9367f7?sig=81bf3b294941f46f1c02e43b77a1ac19","pileType":null,"price":null,"distance":0,"power":null,"serviceId":null,"chargingMode":"直流","stationStatus":"正常使用","construction":"居民区","parkFee":"免费停车","supportFacility":"商城","weightScore":0,"address":"表达式"},{"stationId":26,"stationName":"测试站点H","stationLng":116.553711,"stationLat":31.066118,"stationType":"公共1","servicerName":null,"imageLogo":"http://172.17.3.142:8090/download/3/01/6270c3938e6447acbf8d39e89a9367f7?sig=81bf3b294941f46f1c02e43b77a1ac19","pileType":null,"price":null,"distance":0,"power":null,"serviceId":null,"chargingMode":"直流1","stationStatus":"正常使用1","construction":"居民区1","parkFee":"免费停车1","supportFacility":"商城1","weightScore":0,"address":"表达式1"},{"stationId":27,"stationName":"测试站点test","stationLng":116.553711,"stationLat":31.066118,"stationType":"公共1","servicerName":null,"imageLogo":"http://172.17.3.142:8090/download/3/01/6270c3938e6447acbf8d39e89a9367f7?sig=81bf3b294941f46f1c02e43b77a1ac19","pileType":null,"price":null,"distance":0,"power":null,"serviceId":null,"chargingMode":"直流1","stationStatus":"正常使用1","construction":"居民区1","parkFee":"免费停车1","supportFacility":"商城1","weightScore":0,"address":"表达式1"}]
     */

    private SearchDto data;

    public SearchDto getData() {
        return data;
    }

    public void setData(SearchDto data) {
        this.data = data;
    }

    public static class SearchDto {
        private ArrayList<DataBean> rows;

        public ArrayList<DataBean> getRows() {
            return rows;
        }

        public void setRows(ArrayList<DataBean> rows) {
            this.rows = rows;
        }

        public static class DataBean implements Serializable {
            /**
             * stationId : 1
             * stationName : 测试站点A
             * stationLng : 116.552611
             * stationLat : 40.026118
             * stationType : 公共
             * servicerName : null
             * imageLogo : http://172.17.3.142:8090/download/3/01/6270c3938e6447acbf8d39e89a9367f7?sig=81bf3b294941f46f1c02e43b77a1ac19
             * pileType : null
             * price : null
             * distance : 0.0
             * power : null
             * serviceId : null
             * chargingMode : 直流
             * stationStatus : 正常使用
             * construction : 居民区
             * parkFee : 限时收费
             * supportFacility : 商场,餐厅
             * weightScore : 0.0
             * address : 表达式
             */

            private int stationId;
            private String stationName;
            private double stationLng;
            private double stationLat;
            private String stationType;
            private Object servicerName;
            private String imageLogo;
            private ArrayList<PileTypeBean> pileType;
            private Object price;
            private double distance;
            private Object power;
            private Object serviceId;
            private String chargingMode;
            private String stationStatus;
            private String construction;
            private String parkFee;
            private String supportFacility;
            private double weightScore;
            private String address;

            private Boolean isItem = false;
            private Boolean isFuzzy = false;

            public int getStationId() {
                return stationId;
            }

            public void setStationId(int stationId) {
                this.stationId = stationId;
            }

            public String getStationName() {
                return stationName;
            }

            public void setStationName(String stationName) {
                this.stationName = stationName;
            }

            public double getStationLng() {
                return stationLng;
            }

            public void setStationLng(double stationLng) {
                this.stationLng = stationLng;
            }

            public double getStationLat() {
                return stationLat;
            }

            public void setStationLat(double stationLat) {
                this.stationLat = stationLat;
            }

            public String getStationType() {
                return stationType;
            }

            public void setStationType(String stationType) {
                this.stationType = stationType;
            }

            public Object getServicerName() {
                return servicerName;
            }

            public void setServicerName(Object servicerName) {
                this.servicerName = servicerName;
            }

            public String getImageLogo() {
                return imageLogo;
            }

            public void setImageLogo(String imageLogo) {
                this.imageLogo = imageLogo;
            }

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }

            public Object getPower() {
                return power;
            }

            public void setPower(Object power) {
                this.power = power;
            }

            public Object getServiceId() {
                return serviceId;
            }

            public void setServiceId(Object serviceId) {
                this.serviceId = serviceId;
            }

            public String getChargingMode() {
                return chargingMode;
            }

            public void setChargingMode(String chargingMode) {
                this.chargingMode = chargingMode;
            }

            public String getStationStatus() {
                return stationStatus;
            }

            public void setStationStatus(String stationStatus) {
                this.stationStatus = stationStatus;
            }

            public String getConstruction() {
                return construction;
            }

            public void setConstruction(String construction) {
                this.construction = construction;
            }

            public String getParkFee() {
                return parkFee;
            }

            public void setParkFee(String parkFee) {
                this.parkFee = parkFee;
            }

            public String getSupportFacility() {
                return supportFacility;
            }

            public void setSupportFacility(String supportFacility) {
                this.supportFacility = supportFacility;
            }

            public double getWeightScore() {
                return weightScore;
            }

            public void setWeightScore(double weightScore) {
                this.weightScore = weightScore;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public Boolean getItem() {
                return isItem;
            }

            public void setItem(Boolean item) {
                isItem = item;
            }

            public Boolean getFuzzy() {
                return isFuzzy;
            }

            public void setFuzzy(Boolean fuzzy) {
                isFuzzy = fuzzy;
            }

            public ArrayList<PileTypeBean> getPileType() {
                return pileType;
            }

            public void setPileType(ArrayList<PileTypeBean> pileType) {
                this.pileType = pileType;
            }

            public static class PileTypeBean {
                private String type;
                private int pileFree;
                private int allPile;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public int getPileFree() {
                    return pileFree;
                }

                public void setPileFree(int pileFree) {
                    this.pileFree = pileFree;
                }

                public int getAllPile() {
                    return allPile;
                }

                public void setAllPile(int allPile) {
                    this.allPile = allPile;
                }
            }
        }
    }
}
