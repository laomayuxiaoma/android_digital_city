package com.lljjcoder.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liguoying on 2017/11/27.
 */

public class NewProvinceBean implements Parcelable {
    private int id;
    private String name;
    private ArrayList<NewCityBean> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NewCityBean> getChildren() {
        return children;
    }


    @Override
    public String toString() {
        return name;
    }

    public void setChildren(List<NewCityBean> children) {
        this.children = (ArrayList<NewCityBean>) children;
    }

    protected NewProvinceBean(Parcel in) {
        id = in.readInt();
        name = in.readString();
        children = new ArrayList<>();
        in.readList(this.children, NewCityBean.class.getClassLoader());
    }

    public static final Creator<NewProvinceBean> CREATOR = new Creator<NewProvinceBean>() {
        @Override
        public NewProvinceBean createFromParcel(Parcel in) {
            return new NewProvinceBean(in);
        }

        @Override
        public NewProvinceBean[] newArray(int size) {
            return new NewProvinceBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeList(this.children);
    }
}
