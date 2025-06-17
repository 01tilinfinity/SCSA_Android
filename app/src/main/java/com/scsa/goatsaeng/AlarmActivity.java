package com.scsa.goatsaeng;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.scsa.goatsaeng.databinding.ActivityAlarmBinding;

public class AlarmActivity extends AppCompatActivity {
	MediaPlayer player;
	private ActivityAlarmBinding binding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityAlarmBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
				| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
				| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

		player = MediaPlayer.create(this, R.raw.sound1);
		player.start();

		binding.btnAlarmOff.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				releasePlayer();
				finish();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		releasePlayer();
	}

	private void releasePlayer() {
		if (player != null && player.isPlaying()) {
			player.stop();
			player.release();
			player = null;
		}
	}
}
