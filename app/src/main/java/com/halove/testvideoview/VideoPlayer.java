package com.halove.testvideoview;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqy on 2018/5/7.
 */

public class VideoPlayer implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {

    private static final String TAG = "VideoPlayer";
    private VideoView mVideoView;
    private List<OnPlayerEventListener> listeners = new ArrayList<>();
    private Handler mHander;
    private List<String> videoPaths = new ArrayList<>();
    private int index = 0;



    private static class SingletonHolder {
        private static VideoPlayer instance = new VideoPlayer();
    }

    public static VideoPlayer get() {
        return SingletonHolder.instance;
    }

    public void init(Context context, VideoView mVideoView) {
        this.mVideoView = mVideoView;
        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnErrorListener(this);
        mHander = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.i(TAG, "onCompletion: ");
//        stop();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        stop();
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        for (OnPlayerEventListener listener : listeners) {
            listener.onPlayPrepared(mp.getDuration());
        }
        pause();
        mp.setLooping(true);
    }

    public void addAndPlay(List<String> paths) {
        if (paths != null) {
            videoPaths.clear();
            videoPaths.addAll(paths);
            index = 0;
            play(0);
        }


    }

    public void play(int position) {
        if (position < 0 || position > videoPaths.size()-1) {
            return;
        }
        index = position;
        mVideoView.setVideoURI(Uri.parse(videoPaths.get(position)));
    }

    public void stop() {
        mVideoView.stopPlayback();
        mHander.removeCallbacks(mRunnable);
    }


    public void pause() {
        if (mVideoView.isPlaying()) {
            mHander.removeCallbacks(mRunnable);
            mVideoView.pause();
            for (OnPlayerEventListener listener : listeners) {
                listener.onPlayerPause();
            }
        } else {
            mVideoView.start();
            mHander.post(mRunnable);
            for (OnPlayerEventListener listener : listeners) {
                listener.onPlayerStart();
            }
        }

    }

    public void next() {
        play(index + 1);
    }

    public void seekTo(int progress) {
        mVideoView.seekTo(progress);
    }

    public void addOnPlayEventListener(OnPlayerEventListener listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    public void removeOnPlayEventListener(OnPlayerEventListener listener) {
        listeners.remove(listener);
    }


    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            for (OnPlayerEventListener listener : listeners) {
                listener.onPublish(mVideoView.getCurrentPosition(), mVideoView.getDuration());
            }
            mHander.post(this);
        }
    };


}
