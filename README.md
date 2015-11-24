# ScreenRecordManager
支持录制屏幕和采集Mic声音

## SDK版本

SDK>=21，在Android5.0以上才可安装

## 权限
```xml
    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```

## 使用方法
  1.初始化
  
   mixureRecordManager = MixureRecordManager.getInstance(audioname,videoname,dir,videosize,context);
   
  2.获取视频录制权限
  
  mixureRecordManager.getScreenRecordPermission();
  
  3.在onActivityResult中调用
  
  mixureRecordManager.startRecord(resultCode,data);开始录制
  
  4.停止录制
  
  mixureRecordManager.stopRecord();
  
  5.在onDestroy中调用
  
   mixureRecordManager.stopRecord();防止用户推出后泄露
