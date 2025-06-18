package com.scsa.goatsaeng;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MouseMainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    FrameLayout.LayoutParams params;
    int count = 0;
    int gameSpeed = 1000;
    static boolean threadEndFlag = true;
    MouseTask mouseTask;

    int myWidth;
    int myHeight;
    int imgWidth = 150;
    int imgHeight = 150;
    Random random = new Random();

    SoundPool soundPool;
    int killSound;
    MediaPlayer mediaPlayer;

    int x = 200;
    int y = 200;
    ImageView[] imageViews;

    int level = 1;
    int howManyMoney = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);

        frameLayout = findViewById(R.id.frame);
        params = new FrameLayout.LayoutParams(1, 1);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        myWidth = metrics.widthPixels;
        myHeight = metrics.heightPixels;

        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(attributes)
                .build();
        killSound = soundPool.load(this, R.raw.mouse_scream, 1);
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm);
        mediaPlayer.setLooping(true);

        init(howManyMoney);
    }

    public void init(int nums) {
        count = 0;
        threadEndFlag = true;
        this.howManyMoney = nums;
        frameLayout.removeAllViews();

        imageViews = new ImageView[nums];
        for (int i = 0; i < nums; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.krw);
            frameLayout.addView(iv, params);
            imageViews[i] = iv;
            iv.setOnClickListener(h);
        }

        mouseTask = new MouseTask();
        mouseTask.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
        mouseTask.cancel(true);
        threadEndFlag = false;
    }

    View.OnClickListener h = new View.OnClickListener() {
        public void onClick(View v) {
            count++;
            ImageView iv = (ImageView) v;
            soundPool.play(killSound, 1, 1, 0, 0, 1);
            iv.setVisibility(View.INVISIBLE);

            if (count == howManyMoney) {
                threadEndFlag = false;
                mouseTask.cancel(true);

                int total = howManyMoney * 50000;
                int nextLevel = level + 1;

                AlertDialog.Builder dialog = new AlertDialog.Builder(MouseMainActivity.this);
                dialog.setTitle(level + "단계 통과!");
                dialog.setMessage("5만원권 " + howManyMoney + "장을 주웠어요.\n" + nextLevel + "단계로 넘어갈까요?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        level++;
                        gameSpeed = Math.max(300, gameSpeed - 100);
                        howManyMoney++;
                        init(howManyMoney);
                    }
                });
                dialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.show();
            }
        }
    };

    public void update() {
        if (!threadEndFlag) return;
        for (ImageView img : imageViews) {
            x = random.nextInt(myWidth - imgWidth);
            y = random.nextInt(myHeight - imgHeight);
            img.layout(x, y, x + imgWidth, y + imgHeight);
            img.invalidate();
        }
    }

    class MouseTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            while (threadEndFlag) {
                publishProgress();
                try {
                    Thread.sleep(gameSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            update();
        }
    }
}
