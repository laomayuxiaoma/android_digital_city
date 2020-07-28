package com.lljjcoder.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liguoying on 2017/11/27.
 */

public class NewDistrictBean implements Parcelable {

    private int id;
    private String name;
    private int parentId;

    protected NewDistrictBean(Parcel in) {
        id = in.readInt();
        name = in.readString();
        parentId = in.readInt();
    }

    public static final Creator<NewDistrictBean> CREATOR = new Creator<NewDistrictBean>() {
        @Override
        public NewDistrictBean createFromParcel(Parcel in) {
            return new NewDistrictBean(in);
        }

        @Override
        public NewDistrictBean[] newArray(int size) {
            return new NewDistrictBean[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(parentId);
    }
}
