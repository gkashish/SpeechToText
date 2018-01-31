package com.gk.speechtotext;


import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.speech.SpeechRecognizer.isRecognitionAvailable;

public class MainActivity extends Activity {

    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    SpeechRecognizer listen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        listen =SpeechRecognizer.createSpeechRecognizer(this);
        listen.setRecognitionListener(new listener());

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }

    private class listener implements RecognitionListener{

        @Override
        public void onReadyForSpeech(Bundle bundle) {
            Log.i("hey","ReadyForSpeech");
        }

        @Override
        public void onBeginningOfSpeech() {
            Log.i("hey","onBeginningOfSpeech");
        }

        @Override
        public void onRmsChanged(float v) {
            Log.i("hey","onRmsChanged");

        }

        @Override
        public void onBufferReceived(byte[] bytes) {
            Log.i("hey","onBufferReceived");

        }

        @Override
        public void onEndOfSpeech() {
            Log.i("hey","onEndOfSpeech");

        }

        @Override
        public void onError(int i) {
            Log.i("hey","onError: "+i);

        }

        @Override
        public void onResults(Bundle bundle) {
            Log.i("hey","onResults");
        }

        @Override
        public void onPartialResults(Bundle bundle) {
            Log.i("hey","onPartialResults");
            ArrayList data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            String word = (String) data.get(data.size() - 1);
            txtSpeechInput.setText(word);
        }

        @Override
        public void onEvent(int i, Bundle bundle) {

        }
    }


    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"voice.recognition.test");
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS,true);
        listen.startListening(intent);
    }
}