package com.halove.testvideoview;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.devbrackets.android.exomedia.listener.OnPreparedListener;
import com.devbrackets.android.exomedia.ui.widget.VideoView;

import java.net.URL;

import cn.jzvd.JZVideoPlayerStandard;

public class Main3Activity extends AppCompatActivity implements OnPreparedListener{

    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        JZVideoPlayerStandard jzVideoPlayerStandard = findViewById(R.id.video_player);
        jzVideoPlayerStandard.setUp("http://www.iqiyi.com/v_19rrcp9j34.html?list=19rrlqo06a"
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");
//        jzVideoPlayerStandard.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
//     videoView = findViewById(R.id.video_view);
//     videoView.setOnPreparedListener(this);
//
//     videoView.setVideoURI(Uri.parse("https://archive.org/download/Popeye_forPresident/Popeye_forPresident_512kb.mp4"));
    }

    @Override
    public void onPrepared() {
//        videoView.start();
    }
}
