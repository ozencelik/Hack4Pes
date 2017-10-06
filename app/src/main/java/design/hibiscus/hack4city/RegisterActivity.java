package design.hibiscus.hack4city;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by ASUS-PC on 7.10.2017.
 */

public class RegisterActivity extends Activity {

    private final String TAG = "REGISTER";

    private EditText email;
    private EditText cardNumber; //Eshot Card Number For User
    private EditText password;

    private Button register;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText)findViewById(R.id.email_text);
        password = (EditText)findViewById(R.id.password_text);
        cardNumber = (EditText)findViewById(R.id.cardNumber_text);
        mAuth = FirebaseAuth.getInstance();

        register = (Button)findViewById(R.id.button_register);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String Email = email.getText().toString();
                String PassWord = password.getText().toString();



                mAuth.createUserWithEmailAndPassword(Email, PassWord)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });









            }
        });



    }


}


