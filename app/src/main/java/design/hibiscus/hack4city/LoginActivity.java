package design.hibiscus.hack4city;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class LoginActivity extends Activity {

    private final String TAG = "LOGIN";

    private EditText email;
    private EditText password;
    private Button logIn;
    private Button directRegisterScreen;

    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        logIn = (Button)findViewById(R.id.logIn);
        directRegisterScreen = (Button)findViewById(R.id.directRegister);
        mAuth = FirebaseAuth.getInstance();

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                mAuth.signInWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);
                                    Intent i = new Intent(getBaseContext(), LocationActivity.class);
                                    startActivity(i);


                                } else {
                                    // If sign in fails, display a message to the user.

                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });


            }
        });


        directRegisterScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, TabActivity.class));
            }
        });

    }
}
