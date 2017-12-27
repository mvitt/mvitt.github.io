package com.goldenleafcoding.lozocarinasimulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int upCnote = R.raw.upbutton;
    int downCNote = R.raw.downbutton;
    int leftCNote = R.raw.leftbutton;
    int rightCNote = R.raw.rightbutton;
    int aNote = R.raw.abutton;
    final ArrayList<String> notesPushed = new ArrayList<String>();
    //Song of Storm
    final List<String> sOS = new ArrayList<String>() {{
        add("A");
        add("down");
        add("up");
        add("A");
        add("down");
        add("up");
    }};
    //Zelda's Lullaby
    final List<String> zL = new ArrayList<String>() {{
        add("left");
        add("up");
        add("right");
        add("left");
        add("up");
        add("right");
    }};
    //Epona's Song
    final List<String> eS = new ArrayList<String>() {{
        add("up");
        add("left");
        add("right");
        add("up");
        add("left");
        add("right");
    }};
    //Saria's Song
    final List<String> sS = new ArrayList<String>() {{
        add("down");
        add("right");
        add("left");
        add("down");
        add("right");
        add("left");
    }};
    //Sun's Song
    final List<String> sunS = new ArrayList<String>() {{
        add("right");
        add("down");
        add("up");
        add("right");
        add("down");
        add("up");
    }};
    //Song of Time
    final List<String> sOT = new ArrayList<String>() {{
        add("right");
        add("A");
        add("down");
        add("right");
        add("A");
        add("down");
    }};
    //Minuet of Forest
    final List<String> mOF = new ArrayList<String>() {{
        add("A");
        add("up");
        add("left");
        add("right");
        add("left");
        add("right");
    }};

    //Bolero of Fire
    final List<String> bOF = new ArrayList<String>() {{
        add("down");
        add("A");
        add("down");
        add("A");
        add("right");
        add("down");
        add("right");
        add("down");
    }};

    //Serenade of Water
    final List<String> sOW = new ArrayList<String>() {{
        add("A");
        add("down");
        add("right");
        add("right");
        add("left");

    }};

    //Nocturne of Shadow
    final List<String> nOS = new ArrayList<String>() {{
        add("left");
        add("right");
        add("right");
        add("A");
        add("left");
        add("right");
        add("down");

    }};

    //Requiem of Spirit
    final List<String> rOS = new ArrayList<String>() {{
        add("A");
        add("down");
        add("A");
        add("right");
        add("down");
        add("A");


    }};

    //Prelude of Light
    final List<String> pOL = new ArrayList<String>() {{
        add("up");
        add("right");
        add("up");
        add("right");
        add("left");
        add("up");


    }};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageButton upC = (ImageButton)findViewById(R.id.btnUpC);
        final ImageButton downC = (ImageButton)findViewById(R.id.btnDownC);
        final ImageButton leftC = (ImageButton)findViewById(R.id.btnLeftC);
        final ImageButton rightC = (ImageButton)findViewById(R.id.btnRightC);
        final ImageButton aButton = (ImageButton)findViewById(R.id.btnA);

        playNote(upC, upCnote);
        playNote(downC, downCNote);
        playNote(leftC, leftCNote);
        playNote(rightC, rightCNote);
        playNote(aButton, aNote);
    }

    public void playNote(final ImageButton buttonPress, final int actualNote){


        buttonPress.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final MediaPlayer note = MediaPlayer.create(MainActivity.this, actualNote);

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        buttonPress.setAlpha((float) 1.0);

                        if(actualNote == R.raw.upbutton ){
                            notesPushed.add("up");}
                        if(actualNote == R.raw.downbutton ){
                            notesPushed.add("down");}
                        if(actualNote == R.raw.rightbutton ){
                            notesPushed.add("right");}
                        if(actualNote == R.raw.leftbutton ){
                            notesPushed.add("left");}
                        if(actualNote == R.raw.abutton ){
                            notesPushed.add("A");}
                        note.start();
                        note.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                note.release();
                            }
                        });

                        if(notesPushed.equals(sOS)){
                            final MediaPlayer correct = MediaPlayer.create(MainActivity.this, R.raw.oot_correct);
                            correct.start();
                            correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    correct.release();
                                }
                            });
                            if(correct.isPlaying()){
                                final MediaPlayer songOfStorm = MediaPlayer.create(MainActivity.this, R.raw.song_of_storms);
                                songOfStorm.start();
                                songOfStorm.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        songOfStorm.release();
                                    }
                                });
                                notesPushed.clear();
                            }}

                        if(notesPushed.equals(zL)){
                            final MediaPlayer correct = MediaPlayer.create(MainActivity.this, R.raw.oot_correct);
                            correct.start();
                            correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    correct.release();
                                }
                            });
                            if(correct.isPlaying()){
                                final MediaPlayer zeldasLullaby = MediaPlayer.create(MainActivity.this, R.raw.zeldas_lullaby);
                                zeldasLullaby.start();
                                zeldasLullaby.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        zeldasLullaby.release();
                                    }
                                });
                                notesPushed.clear();
                            }}

                        if(notesPushed.equals(eS)){
                            final MediaPlayer correct = MediaPlayer.create(MainActivity.this, R.raw.oot_correct);
                            correct.start();
                            correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    correct.release();
                                }
                            });
                            if(correct.isPlaying()){
                                final MediaPlayer eponasSong = MediaPlayer.create(MainActivity.this, R.raw.eponas_song);
                                eponasSong.start();
                                eponasSong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        eponasSong.release();
                                    }
                                });
                                notesPushed.clear();
                            }}

                        if(notesPushed.equals(sS)){
                            final MediaPlayer correct = MediaPlayer.create(MainActivity.this, R.raw.oot_correct);
                            correct.start();
                            correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    correct.release();
                                }
                            });
                            if(correct.isPlaying()){
                                final MediaPlayer sariasSong = MediaPlayer.create(MainActivity.this, R.raw.sarias_song);
                                sariasSong.start();
                                sariasSong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        sariasSong.release();
                                    }
                                });
                                notesPushed.clear();
                            }}

                        if(notesPushed.equals(sunS)){
                            final MediaPlayer correct = MediaPlayer.create(MainActivity.this, R.raw.oot_correct);
                            correct.start();
                            correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    correct.release();
                                }
                            });
                            if(correct.isPlaying()){
                                final MediaPlayer sunsSong = MediaPlayer.create(MainActivity.this, R.raw.suns_song);
                                sunsSong.start();
                                sunsSong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        sunsSong.release();
                                    }
                                });
                                notesPushed.clear();
                            }}

                        if(notesPushed.equals(sOT)){
                            final MediaPlayer correct = MediaPlayer.create(MainActivity.this, R.raw.oot_correct);
                            correct.start();
                            correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    correct.release();
                                }
                            });
                            if(correct.isPlaying()){
                                final MediaPlayer songOfTime = MediaPlayer.create(MainActivity.this, R.raw.song_of_time);
                                songOfTime.start();
                                songOfTime.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        songOfTime.release();
                                    }
                                });
                                notesPushed.clear();
                            }}

                        if(notesPushed.equals(mOF)){
                            final MediaPlayer correct = MediaPlayer.create(MainActivity.this, R.raw.oot_correct);
                            correct.start();
                            correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    correct.release();
                                }
                            });
                            if(correct.isPlaying()){
                                final MediaPlayer song = MediaPlayer.create(MainActivity.this, R.raw.minuet_of_forest);
                                song.start();
                                song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        song.release();
                                    }
                                });
                                notesPushed.clear();
                            }}

                        if(notesPushed.equals(bOF)){
                            final MediaPlayer correct = MediaPlayer.create(MainActivity.this, R.raw.oot_correct);
                            correct.start();
                            correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    correct.release();
                                }
                            });
                            if(correct.isPlaying()){
                                final MediaPlayer song = MediaPlayer.create(MainActivity.this, R.raw.bolero_of_fire);
                                song.start();
                                song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        song.release();
                                    }
                                });
                                notesPushed.clear();
                            }}
                        if(notesPushed.equals(sOW)){
                            final MediaPlayer correct = MediaPlayer.create(MainActivity.this, R.raw.oot_correct);
                            correct.start();
                            correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    correct.release();
                                }
                            });
                            if(correct.isPlaying()){
                                final MediaPlayer song = MediaPlayer.create(MainActivity.this, R.raw.serenade_of_water);
                                song.start();
                                song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        song.release();
                                    }
                                });
                                notesPushed.clear();
                            }}

                        if(notesPushed.equals(nOS)){
                            final MediaPlayer correct = MediaPlayer.create(MainActivity.this, R.raw.oot_correct);
                            correct.start();
                            correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    correct.release();
                                }
                            });
                            if(correct.isPlaying()){
                                final MediaPlayer song = MediaPlayer.create(MainActivity.this, R.raw.nocturne_of_shadow);
                                song.start();
                                song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        song.release();
                                    }
                                });
                                notesPushed.clear();
                            }}

                        if(notesPushed.equals(rOS)){
                            final MediaPlayer correct = MediaPlayer.create(MainActivity.this, R.raw.oot_correct);
                            correct.start();
                            correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    correct.release();
                                }
                            });
                            if(correct.isPlaying()){
                                final MediaPlayer song = MediaPlayer.create(MainActivity.this, R.raw.requiem_of_spirit);
                                song.start();
                                song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        song.release();
                                    }
                                });
                                notesPushed.clear();
                            }}

                        if(notesPushed.equals(pOL)){
                            final MediaPlayer correct = MediaPlayer.create(MainActivity.this, R.raw.oot_correct);
                            correct.start();
                            correct.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mp) {
                                    correct.release();
                                }
                            });
                            if(correct.isPlaying()){
                                final MediaPlayer song = MediaPlayer.create(MainActivity.this, R.raw.prelude_of_light);
                                song.start();
                                song.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        song.release();
                                    }
                                });
                                notesPushed.clear();
                            }}




                        if(notesPushed.size() >= 8){
                            if(!notesPushed.equals(sOS) || !notesPushed.equals(zL) ||
                                    !notesPushed.equals(eS) || !notesPushed.equals(sS)
                                    || !notesPushed.equals(sunS) || !notesPushed.equals(sOT)
                                    || !notesPushed.equals(mOF) || !notesPushed.equals(bOF)
                                    || !notesPushed.equals(sOW) || !notesPushed.equals(nOS)
                                    || !notesPushed.equals(rOS) || !notesPushed.equals(pOL)){
                                final MediaPlayer wrong = MediaPlayer.create(MainActivity.this, R.raw.oot_error);
                                wrong.start();
                                wrong.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        wrong.release();
                                    }
                                });
                            }
                            notesPushed.clear();
                        }


                        return true;
                    case MotionEvent.ACTION_UP:

                        buttonPress.setAlpha((float) 0.4);

                        return true;
                }
                return false;
            }
        });
    }


}

