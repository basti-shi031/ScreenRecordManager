package com.bzt.screenrecordmanager.utils;

/**
 * Created by Basti031 on 2015/11/23.
 */
public class VideoSize {

    private int width;
    private int height;

    public VideoSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {

        this.width = width;
    }

    public int getWidth() {

        return width;
    }

    public int getHeight() {
        return height;
    }

    public VideoSize() {
        width = 1280;
        height = 720;
    }
}
