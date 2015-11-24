package com.bzt.screenrecordmanager.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by Basti031 on 2015/11/23.
 */
public class ScreenRecordManager {

    private ScreenRecorder screenRecorder;
    private String mDir,mFileName;
    private VideoSize mSize;
    private MediaProjection mediaProjection;
    private MediaProjectionManager mediaProjectionManager;
    private Context mContext;
    public static final int REQUEST_CODE = 1;
    public static final int BITRATE = 6000000;

    private static ScreenRecordManager mInstance;

    private ScreenRecordManager(VideoSize size,String dir,String fileName,Context context){
        mDir = dir;
        mFileName = fileName;
        mSize = size;
        mContext = context;
        mediaProjectionManager = (MediaProjectionManager) context.getSystemService(Context.MEDIA_PROJECTION_SERVICE);
    }

    public static ScreenRecordManager getInstance(VideoSize size,String dir,String fileName,Context context){

        if (mInstance == null){
            synchronized (ScreenRecordManager.class){
                if (mInstance == null){
                    mInstance = new ScreenRecordManager(size,dir,fileName,context);
                }
            }
        }
        return mInstance;
    }

    public static ScreenRecordManager getInstance(String dir,String fileName,Context context){

        if (mInstance == null){
            synchronized (ScreenRecordManager.class){
                if (mInstance == null){
                    mInstance = new ScreenRecordManager(new VideoSize(),dir,fileName,context);
                }
            }
        }
        return mInstance;
    }

    //获取权限，弹出窗口获得用户同意
    public void getPermission(){
        Intent captureIntent = mediaProjectionManager.createScreenCaptureIntent();
        ((Activity)mContext).startActivityForResult(captureIntent, REQUEST_CODE);
    }

    //开始录屏，在onActivityResult中调用
    public void startRecord(int resultCode,Intent data){
        mediaProjection = mediaProjectionManager.getMediaProjection(resultCode,data);
        File file = new File(Environment.getExternalStorageDirectory().getPath()+mDir,
                mFileName+ ".mp4");
        screenRecorder = new ScreenRecorder(mSize, BITRATE, 1, mediaProjection, file.getAbsolutePath());
        screenRecorder.start();
    }

    public void stopRecord(){
        if (screenRecorder != null) {
            Log.e("stop", "stop1");
            screenRecorder.quit();
            screenRecorder = null;
        }
    }

}
