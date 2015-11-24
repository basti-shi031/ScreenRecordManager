package com.bzt.screenrecordmanager.utils;

import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * 录音工具类
 * Created by Basti031 on 2015/11/23.
 */
public class AudioManager {

    private MediaRecorder mMediaRecorder;
    private String mFileName;
    private String mDir;

    private static AudioManager mInstance;

    private AudioManager(String fileName,String dir) {
        mFileName = fileName;
        mDir = Environment.getExternalStorageDirectory().getPath()+dir;
    }

    //单例模式
    public static AudioManager getInstance(String fileName,String dir){
        if (mInstance== null){
            synchronized (AudioManager.class){
                if (mInstance == null){
                    mInstance = new AudioManager(fileName,dir);
                }
            }
        }
        return mInstance;
    }

    //AudioManager准备
    public void prepareAudio(){
        try {
            File path1 = new File(mDir);
            if (!path1.exists()) {
                //若不存在，创建目录，可以在应用启动的时候创建
                path1.mkdirs();
            }
            File file = new File(mDir,mFileName+".amr");
            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.setOutputFile(file.getAbsolutePath());

            //来源
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            //格式
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
            //编码方式
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mMediaRecorder.prepare();
            mMediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void release(){
        if (mMediaRecorder!=null){
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
    }

}
