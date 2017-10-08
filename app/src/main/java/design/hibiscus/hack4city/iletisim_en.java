package design.hibiscus.hack4city;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class iletisim_en extends AppCompatActivity {

    int Counder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iletisim_en);
        MediaPlayer mp4 = MediaPlayer.create(iletisim_en.this, R.raw.eshot_en);
        mp4.start();
        try {
            Thread.sleep(2750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MediaPlayer mp5 = MediaPlayer.create(iletisim_en.this, R.raw.anamenuye_en);
        mp5.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Counder++;
                MediaPlayer mp = MediaPlayer.create(iletisim_en.this, R.raw.beep);
                mp.start();
            }
        });
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                navigation(Counder);
                return false;
            }
        });
    }

    protected void navigation(int a) {
        if (a == 1) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:0(232)3200320"));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(callIntent);

        }

        else if (a==2){
            startActivity(new Intent(iletisim_en.this,Menu_EN.class));

        }
        if(a>3){
            MediaPlayer mp = MediaPlayer.create(iletisim_en.this, R.raw.limit_en);
            mp.start();
            Counder=0;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
