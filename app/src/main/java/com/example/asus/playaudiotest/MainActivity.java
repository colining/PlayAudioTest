package com.example.asus.playaudiotest;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button play;
    private Button pause;
    private Button stop;
    private MediaPlayer mediaPlayer = new MediaPlayer();//.create(this,R.raw.music);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        pause.setOnClickListener(this);
        Log.e("mediaactivity", "start");

        initMediaPlayer();}

    private void initMediaPlayer() {
        try {
            String a=Environment.getExternalStorageState();
            File file =new File(Environment.getExternalStorageDirectory(),"music.mp3");

            Log.e("mediaactivity",file.getPath());
            Log.e("mediaactivity",a);
            if(file.exists())
            {
                Log.e("mediaactivity","true");
            }
            if(!file.exists())
            {
                Log.e("mediaactivity","false");
            }
           mediaPlayer.setDataSource("/sdcard/music.mp3");
            //mediaPlayer.setDataSource(this,R.raw.music);
           // mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
           // mediaPlayer.start();
          /*  String path ="/sdcard/music.mp3";
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();*/
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
    }
    @Override
    public  void  onClick(View v)
    {
        switch (v.getId())
        {
            case  R.id.play:
                if(!mediaPlayer.isPlaying())
                {
                    mediaPlayer.start();
                    Log.e("mediaactivity","play");

                }
                break;
            case R.id.pause:
                if(mediaPlayer.isPlaying())
                {
                    Log.e("mediaactivity","pause");
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop:
                if(mediaPlayer.isPlaying())
                {
                    mediaPlayer.reset();
                    Log.e("mediaactivity","stop");
                    initMediaPlayer();
                }
                break;
            default:
                break;
        }
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
