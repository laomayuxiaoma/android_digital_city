package com.luck.picture.lib.entity;

public class PhotoDisposeDto {
    private String path;
    private int position;
    private int dtoPosition;

    public int getDtoPosition() {
        return dtoPosition;
    }

    public void setDtoPosition(int dtoPosition) {
        this.dtoPosition = dtoPosition;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
