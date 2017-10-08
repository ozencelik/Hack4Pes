package design.hibiscus.hack4city;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Menu_TR extends AppCompatActivity {


    boolean flag = true;
    int Counter = 0;
    int Counder = 0;

    protected void intro() {


        MediaPlayer mp = MediaPlayer.create(Menu_TR.this,R.raw.menu_tr);
        mp.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MediaPlayer mp1 = MediaPlayer.create(Menu_TR.this,R.raw.tanitim1);
        mp1.start();
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MediaPlayer mp2 = MediaPlayer.create(Menu_TR.this,R.raw.ulasim2);
        mp2.start();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MediaPlayer mp3 = MediaPlayer.create(Menu_TR.this,R.raw.kisisel3);
        mp3.start();

        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MediaPlayer mp4 = MediaPlayer.create(Menu_TR.this,R.raw.iletisim4);
        mp4.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MediaPlayer mp5 = MediaPlayer.create(Menu_TR.this,R.raw.dinle5);
        mp5.start();
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MediaPlayer mp6 = MediaPlayer.create(Menu_TR.this,R.raw.english6);
        mp6.start();
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MediaPlayer mp7 = MediaPlayer.create(Menu_TR.this,R.raw.cikis_tr);
        mp7.start();
    }

    protected void nextPage(int a){

        if(Counder == 1){
            MediaPlayer mp11 = MediaPlayer.create(Menu_TR.this,R.raw.tanitim11);
            mp11.start();
            try {
                Thread.sleep(8800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            MediaPlayer mp12 = MediaPlayer.create(Menu_TR.this,R.raw.aciklama12);
            mp12.start();

            Counder =0;


        }

        else if (Counder == 2){
            MediaPlayer mp2 = MediaPlayer.create(Menu_TR.this,R.raw.ulasim_aciklama);
            mp2.start();

            Counder =0;

        }
        else if(Counder == 3){

            MediaPlayer mp3 = MediaPlayer.create(Menu_TR.this,R.raw.kisisel_aciklama);
            mp3.start();
        }
        else if (Counder == 4){
            startActivity(new Intent(Menu_TR.this,iletisim.class));
            Counder =0;
        }

        else if(Counder == 5){
            intro();
            Counder =0;

        }
        else if(Counder == 6){
            startActivity(new Intent(Menu_TR.this,Menu_EN.class));

        }

        else if (Counder == 7){
            Counder =0;
            System.exit(0);
            //   mHandler.removeCallbacks(mResetCounter);
        }
        if(Counder > 7){
            Counder = 0;
            MediaPlayer mp4 = MediaPlayer.create(Menu_TR.this,R.raw.limit_tr);
            mp4.start();
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__tr);
        intro();

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counter++;
                MediaPlayer mp = MediaPlayer.create(Menu_TR.this,R.raw.beep);
                mp.start();
            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Counder = Counter;
                Counter = 0;
                nextPage(Counder);
                return true;
            }
        });





        }

}

