package com.example.appmp3;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button playpause,stop,previous,next;
    TextView judul;
    MediaPlayer mp;
    boolean play = true;
     int Index = 0;

    @Override
    //type data
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previous = (Button)findViewById(R.id.idprevious);
        next = (Button)findViewById(R.id.idnext);
        playpause = (Button)findViewById(R.id.idplay);
        stop = (Button)findViewById(R.id.idstop);
        judul = (TextView) findViewById(R.id.judul);
        final int[][] currentIndex = {{0}};

        //membuat aray list untuk lagu
        final ArrayList<Integer> songs = new ArrayList<>();
        songs.add(0, R.raw.boywithuke);
        songs.add(1, R.raw.lifegoon);
        songs.add(2, R.raw.linting);
        songs.add(3, R.raw.yahya);

        //inisialisasi mediaplayer

        mp = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex[0][0]));




//logic next music
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                {
                    if (currentIndex[0][0] < songs.size() - 1) {
                        currentIndex[0][0]++;

                    } else {
                        //currentIndex = 0;
                        Toast.makeText(getApplicationContext(), "Can't Play the Songs", Toast.LENGTH_SHORT).show();
                    }
                    if (mp.isPlaying()) {
                        mp.stop();
                        playpause.setText("Play");
                        play = true;

                    }

                    mp = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex[0][0]));
                    mp.start();
                    playpause.setText("pause");
                    play = false;

                }

            }
        });

//logic previous music
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIndex[0][0] > 0){
                    currentIndex[0][0] --;
                }else{
//                    currentIndex = songs.size()- 1;
                    Toast.makeText(getApplicationContext(), "Can't Play the Songs", Toast.LENGTH_SHORT).show();
                }
                if (mp.isPlaying()){
                    mp.stop();

                }

                mp = MediaPlayer.create(getApplicationContext(),songs.get(currentIndex[0][0]));
                mp.start();

            }
        });


     //Logic button play and pause

        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(play == true){
                    mp.start();
                    playpause.setText("Pause");
                    play = false;
                    namaLagu();

                }else{
                    mp.pause();
                    playpause.setText("Play");
                    play = true;
                }

            }
        });

        //logic button stop

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    mp.stop();
                    play = true;
                    mp.prepare();
                    mp.seekTo(0);
                    playpause.setText("Play");
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    private void namaLagu() {
        if(Index == 0){
            judul.setText("withboyuke - Toxic Friend");
        } else if(Index == 1) {
            judul.setText(" Life go on");
        }else if(Index == 2){
            judul.setText("Linting Daun");
        }else if (Index == 3){
            judul.setText("yahya - keep You Safe");
        }

    }

}