package com.halove.testvideoview;


/**
 * Created by zqy on 2018/3/29.
 * 播放器进度监听器
 */

public interface OnPlayerEventListener {
    //更新进度
    void onPublish(int progress, int duration);
    //准备就绪
    void onPlayPrepared(int duration);
    //继续播放
    void onPlayerStart();
    //暂停播放
    void onPlayerPause();
    //停止播放
    void onPlayerStop();
}
