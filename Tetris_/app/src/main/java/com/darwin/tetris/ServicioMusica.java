package com.darwin.tetris;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.telephony.UiccCardInfo;
import android.util.Log;
import android.view.InputDevice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServicioMusica extends Service {
    private MediaPlayer[] mediaplayer;
    private int indice;
    private CountDownTimer countmusic;
    public ServicioMusica() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        indice = 0;
        mediaplayer = new MediaPlayer[]{
                MediaPlayer.create(this, R.raw.sweet_dreams),
                MediaPlayer.create(this, R.raw.barbie_girl),
                MediaPlayer.create(this, R.raw.super_freak),
                MediaPlayer.create(this, R.raw.tetris_sound)};

        mediaplayer[indice].start();
        countmusic = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("Count mediaplayer: ","SEGUNDOS PARA CAMBIAR DE MUSICA "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                mediaplayer[indice].stop();
                try {
                    mediaplayer[indice].prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                indice++;
                if(indice%4 == 0){
                    indice = 0;
                }
                mediaplayer[indice].start();
                countmusic.start();
            }
        }.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        countmusic.cancel();
        mediaplayer[indice].stop();
        mediaplayer[indice].release();
    }

}
