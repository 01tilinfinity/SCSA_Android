package com.scsa.goatsaeng;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent i) {
		Toast.makeText(context, "Alarm!!", Toast.LENGTH_LONG).show();

		Intent it = new Intent(context, AlarmActivity.class);
		it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(it);
	}
}
