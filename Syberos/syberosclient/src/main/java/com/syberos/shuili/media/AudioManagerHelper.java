package  com.syberos.shuili.media;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.util.Log;

import com.syberos.shuili.App;

/**
 * Created by legend on 2017/5/3.
 */

public class AudioManagerHelper {

    private static final String TAG = AudioManagerHelper.class.getSimpleName();
    private static AudioManager mAudioMgr;
    /**
     * //Build.VERSION.SDK_INT表示当前SDK的版本，Build.VERSION_CODES.ECLAIR_MR1为SDK 7版本 ，
     * //因为AudioManager.OnAudioFocusChangeListener在SDK8版本开始才有。
     */

    private static AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            Log.d(TAG, "focusChange= " + focusChange);
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                //失去焦点之后的操作
                Log.d(TAG, "失去焦点. ");
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) {
                //获得焦点之后的操作
                Log.d(TAG, "获得焦点");
            }
        }
    };


    /**
     * 请求获取焦点
     */
    public static void requestAudioFocus() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ECLAIR_MR1) {
            return;
        }
        if (mAudioMgr == null) {

            mAudioMgr = (AudioManager) App.app.getSystemService(Context.AUDIO_SERVICE);
        }
        if (mAudioMgr != null) {

            int ret = mAudioMgr.requestAudioFocus(new AudioManager.OnAudioFocusChangeListener() {
                                                      @Override
                                                      public void onAudioFocusChange(int focusChange) {
                                                          Log.d(TAG, "focusChange= " + focusChange);
                                                          if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                                                              //失去焦点之后的操作
                                                              Log.d(TAG, "失去焦点. ");
                                                          } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) {
                                                              //获得焦点之后的操作
                                                              Log.d(TAG, "获得焦点");
                                                          }
                                                      }
                                                  },
                    AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            if (ret != AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                Log.d(TAG, "request audio focus fail. " + ret);

            } else {
                Log.d(TAG, "request audio focus success. " + ret);
            }

        }

    }

    /**
     * 放弃焦点
     */
    public static void abandonAudioFocus() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.ECLAIR_MR1) {
            return;
        }
        if (mAudioMgr != null) {

            mAudioMgr.abandonAudioFocus(new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    Log.d(TAG, "focusChange= " + focusChange);
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        //失去焦点之后的操作
                        Log.d(TAG, "失去焦点. ");
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) {
                        //获得焦点之后的操作
                        Log.d(TAG, "获得焦点");
                    }
                }
            });
            Log.d(TAG, "放弃焦点");
            mAudioMgr = null;
        }
    }

}
