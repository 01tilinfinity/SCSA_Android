package com.scsa.goatsaeng;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;

import java.util.Locale;

import com.scsa.goatsaeng.databinding.ActivityTtsBinding;

public class TtsMainActivity extends AppCompatActivity {
    private static final String TAG = "TtsMainActivity_SCSA";

    private TextToSpeech tts;
    private ActivityTtsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTtsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.KOREA);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        binding.messageTV.setText("Language is not available.");
                    } else {
                        binding.speakBtn.setEnabled(true);
                    }
                } else {
                    binding.messageTV.setText("Could not initialize TextToSpeech.");
                }
            }
        });

        binding.speakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit = findViewById(R.id.inputET);
                String s = edit.getText().toString();
                tts.speak(s, TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

    public void speechQuick(View view) {
        tts.setSpeechRate((float) 2);
    }

    public void speechNormal(View view) {
        tts.setSpeechRate((float) 1);
    }

    public void speechSlow(View view) {
        tts.setSpeechRate((float) 0.5);
    }

    public void pitchHigh(View view) {
        tts.setPitch((float) 2);
    }

    public void pitchNormal(View view) {
        tts.setPitch((float) 1);
    }

    public void pitchLow(View view) {
        tts.setPitch((float) 0.5);
    }
}
