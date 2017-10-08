package design.hibiscus.hack4city;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    Button firstpage_button ;
    ImageButton disabled_people;

            @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstpage_button = (Button)findViewById(R.id.button_loginscreen);
        disabled_people = (ImageButton)findViewById(R.id.engelsiz);
        firstpage_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(MainActivity.this, LoginActivity.class));

            }
        });

        disabled_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Menu_TR.class));
            }
        });
    }
}
