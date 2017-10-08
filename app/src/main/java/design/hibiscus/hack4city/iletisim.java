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

public class iletisim extends AppCompatActivity {
    int Counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iletisim);
        MediaPlayer mp4 = MediaPlayer.create(iletisim.this, R.raw.eshot);
        mp4.start();
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MediaPlayer mp5 = MediaPlayer.create(iletisim.this, R.raw.anamenuye);
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
                Counter++;
                MediaPlayer mp = MediaPlayer.create(iletisim.this, R.raw.beep);
                mp.start();
            }
        });
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                navigation(Counter);
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
            startActivity(new Intent(iletisim.this,Menu_TR.class));

        }

        if(a>2){
            MediaPlayer mp = MediaPlayer.create(iletisim.this, R.raw.limit_tr);
            mp.start();
            Counter=0;
            try {
                Thread.sleep(6500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
