package com.halove.testvideoview;

import android.animation.ValueAnimator;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private static final String TAG = "MainActivity";
    private VideoView mVideoView;
    private Button btnNext, btnComplete;
    //    private SeekBar skbProcess;
    private boolean isDown;
    private FrameLayout  placeHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_main);
        btnComplete = findViewById(R.id.btn_complete);
        btnNext = findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);
        btnComplete.setOnClickListener(this);
        mVideoView = findViewById(R.id.video_view);
        placeHolder = findViewById(R.id.place_holder);
        ValueAnimator alpha = ValueAnimator
                .ofFloat(1.0f, 0.0f);
        alpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                Log.i(TAG, "onAnimationUpdate: " + value);
                placeHolder.setAlpha(value);

            }
        });
        alpha.setDuration(3000);
        alpha.start();

        VideoPlayer.get().init(this, mVideoView);
        VideoPlayer.get().addOnPlayEventListener(playerEventListener);
        VideoPlayer.get().addAndPlay(testData());
    }


    private List<String> testData() {
        List<String> videos = new ArrayList<>();
        videos.add("android.resource://" + getPackageName() + "/raw/" + R.raw.we_chat_sight12);
        videos.add("android.resource://" + getPackageName() + "/raw/" + R.raw.we_chat_sight11);
        videos.add("android.resource://" + getPackageName() + "/raw/" + R.raw.we_chat_sight13);
        return videos;
    }

    private OnPlayerEventListener playerEventListener = new OnPlayerEventListener() {
        @Override
        public void onPublish(int progress, int duration) {
            Log.i(TAG, "onPublish:progress: " + progress + ",duration:" + duration);
            if (!isDown) {
//                skbProcess.setProgress(progress);
            }

        }

        @Override
        public void onPlayPrepared(int duration) {
            Log.i(TAG, "onPlayPrepared: " + duration);
//            View placeholder = findViewById(R.id.place_holder);
//            placeholder.setVisibility(View.GONE);
//            skbProcess.setMax(duration);
        }

        @Override
        public void onPlayerStart() {
//            button.setBackgroundResource(R.drawable.ic_radio_play_small);
        }

        @Override
        public void onPlayerPause() {
//            button.setBackgroundResource(R.drawable.ic_radio_stop_small);
        }

        @Override
        public void onPlayerStop() {
//            button.setBackgroundResource(R.drawable.ic_radio_stop_small);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_play_or_pause:
//                VideoPlayer.get().pause();
//                break;
            case R.id.btn_complete:
                VideoPlayer.get().stop();
                break;
            case R.id.btn_next:
                VideoPlayer.get().next();
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        isDown = true;
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        isDown = false;
//        Log.i(TAG, "onStopTrackingTouch: " + skbProcess.getProgress());
//        VideoPlayer.get().seekTo(skbProcess.getProgress());
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        VideoPlayer.get().stop();
        finish();
        overridePendingTransition(R.anim.fade_close_in, R.anim.fade_close_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
