package design.hibiscus.hack4city;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Menu_EN extends AppCompatActivity {

    int Counder;

    protected void intro() {


        MediaPlayer mp = MediaPlayer.create(Menu_EN.this,R.raw.menu_en);
        mp.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MediaPlayer mp1 = MediaPlayer.create(Menu_EN.this,R.raw.tanitim_en);
        mp1.start();
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MediaPlayer mp2 = MediaPlayer.create(Menu_EN.this,R.raw.ulasim_en);
        mp2.start();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MediaPlayer mp3 = MediaPlayer.create(Menu_EN.this,R.raw.kisisel_en);
        mp3.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MediaPlayer mp4 = MediaPlayer.create(Menu_EN.this,R.raw.iletisim_en);
        mp4.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MediaPlayer mp5 = MediaPlayer.create(Menu_EN.this,R.raw.dinle_en);
        mp5.start();
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MediaPlayer mp6 = MediaPlayer.create(Menu_EN.this,R.raw.tr_en);
        mp6.start();
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MediaPlayer mp7 = MediaPlayer.create(Menu_EN.this,R.raw.cikis_en);
        mp7.start();
    }

    protected void nextPage(int a){

        if(Counder == 1){
            MediaPlayer mp11 = MediaPlayer.create(Menu_EN.this,R.raw.tanitim_en1);
            mp11.start();
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MediaPlayer mp12 = MediaPlayer.create(Menu_EN.this,R.raw.tanitim_en2);
            mp12.start();

            Counder =0;


        }

        else if (Counder == 2){
            MediaPlayer mp2 = MediaPlayer.create(Menu_EN.this,R.raw.ulasim_aciklama_en);
            mp2.start();

            Counder =0;

        }
        else if(Counder == 3){

            MediaPlayer mp3 = MediaPlayer.create(Menu_EN.this,R.raw.kisisel_aciklama_en);
            mp3.start();
        }
        else if (Counder == 4){
            startActivity(new Intent(Menu_EN.this,iletisim_en.class));
            Counder =0;
        }

        else if(Counder == 5){
            intro();
            Counder =0;

        }

        else if (Counder == 7){
            Counder =0;
            System.exit(0);
            //   mHandler.removeCallbacks(mResetCounter);
        }

        if(Counder > 7){
            Counder = 0;
            MediaPlayer mp4 = MediaPlayer.create(Menu_EN.this,R.raw.limit_en);
            mp4.start();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__en);
        intro();

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counder++;
                MediaPlayer mp = MediaPlayer.create(Menu_EN.this,R.raw.beep);
                mp.start();
            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                nextPage(Counder);
                Counder = 0;
                return true;
            }
        });



    }
}
