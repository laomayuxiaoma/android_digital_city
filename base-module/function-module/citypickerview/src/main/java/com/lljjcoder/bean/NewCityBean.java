package com.lljjcoder.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liguoying on 2017/11/27.
 */

public class NewCityBean implements Parcelable {
    private int id;
    private String name;
    private int parentId;
    private List<NewDistrictBean> children;

    protected NewCityBean(Parcel in) {
        id = in.readInt();
        name = in.readString();
        parentId = in.readInt();
        this.children = new ArrayList<>();
        in.readList(this.children, NewDistrictBean.class.getClassLoader());

    }

    public static final Creator<NewCityBean> CREATOR = new Creator<NewCityBean>() {
        @Override
        public NewCityBean createFromParcel(Parcel in) {
            return new NewCityBean(in);
        }

        @Override
        public NewCityBean[] newArray(int size) {
            return new NewCityBean[size];
        }
    };

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return name;
    }

    public List<NewDistrictBean> getChildren() {
        return children;
    }

    public void setChildren(List<NewDistrictBean> children) {
        this.children = children;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(parentId);
        parcel.writeList(this.children);
    }
}
