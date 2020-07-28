package com.muheda.mhdsystemkit.systemUI.conView;

/**
 * Created by 13660 on 2019/4/22.
 */

public class TagDto {
    public String key;
    public String value;
    public Class tag;
    public int top;//单位dp
    public int bottom;//单位dp
    public TagDto(String key,String value,Class tag,int top,int bottom){
        this.value=value;
        this.tag=tag;
        this.key=key;
        this.bottom=bottom;
        this.top=top;

    }
    public TagDto(String key,String value,Class tag){
        this.value=value;
        this.tag=tag;
        this.key=key;

    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Class getTag() {
        return tag;
    }

    public void setTag(Class tag) {
        this.tag = tag;
    }
}
