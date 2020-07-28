package com.mhd.basekit.viewkit.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.mhd.basekit.R;
import com.muheda.mhdsystemkit.sytemUtil.functionutil.MMKVUtil;

import java.io.IOException;

/**
 * @author zhangming
 * @Date 2019/7/9 10:52
 * @Description: 音频播放
 */
public class MediaPlayerUtils {
    private static MediaPlayer mediaPlayer;
    private static final String OPEN = "1";

    //播放系统提示音
    public static void defaultMediaPlayer(Context mContext) throws Exception {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(mContext, notification);
        r.play();
    }

    //播放提示音
    public static void audioTone(Context mContext, int raw) throws Exception {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }
        mediaPlayer.reset();
        try {
            AssetFileDescriptor file = mContext.getResources().openRawResourceFd(raw);
            mediaPlayer.setDataSource(file.getFileDescriptor(),
                    file.getStartOffset(), file.getLength());
            file.close();
            mediaPlayer.setVolume(0.50f, 0.50f);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            mediaPlayer = null;
        }
    }

    //急加速提示
    public static void rapidlyAccelerateReminder(Context mContext) throws Exception {
        if (OPEN.equalsIgnoreCase(MMKVUtil.getString("setting" + Common.getId() + "add", ""))) {
            audioTone(mContext, R.raw.accelerate);
        }
    }

    //急减速提示
    public static void sharpSlowdownReminder(Context mContext) throws Exception {
        if (OPEN.equalsIgnoreCase(MMKVUtil.getString("setting" + Common.getId() + "minus", ""))) {
            audioTone(mContext, R.raw.slowdown);
        }
    }

    //急转弯提示
    public static void quickTurnReminder(Context mContext) throws Exception {
        if (OPEN.equalsIgnoreCase(MMKVUtil.getString("setting" + Common.getId() + "wheel", ""))) {
            audioTone(mContext, R.raw.wheel);
        }
    }

    //疲劳驾驶提示
    public static void fatigueDrivingReminder(Context mContext) throws Exception {
        //        if (OPEN.equalsIgnoreCase(MMKVUtil.getString("setting" + Common.getId() + "tired", ""))) {
        audioTone(mContext, R.raw.tired);
        //        }
    }


    //警告提示
    public static void warningReminder(Context mContext, int type) {
        try {
            switch (type) {
                case 1:
                case 1001://急加速
                    rapidlyAccelerateReminder(mContext);
                    break;
                case 2:
                case 1002://急减速
                    sharpSlowdownReminder(mContext);
                    break;
                case 3://急转弯
                    quickTurnReminder(mContext);
                    break;
                case 1004://疲劳驾驶
                    fatigueDrivingReminder(mContext);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
