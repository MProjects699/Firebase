package android.flist.com.tfire;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText cemail, cpassword;
    private Button login;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    TextView registeration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        cemail = (EditText) findViewById(R.id.editText5);
        cpassword = (EditText) findViewById(R.id.editText6);
        login = (Button) findViewById(R.id.login);
        registeration = (TextView) findViewById(R.id.signup);
        registeration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Registeration.class));

            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() !=  null){

                    startActivity(new Intent(getApplicationContext(), Home.class));
                    finish();

                }

            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSignIn();

            }
        });


    }

    private void startSignIn(){

        String email = cemail.getText().toString();
        String password = cpassword.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){

            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_LONG).show();

        }else{

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(getApplicationContext(), "SignIn Sucessfully", Toast.LENGTH_LONG).show();

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            startActivity(intent);
                            finish();

                            // ...
                        }
            });

        }

    }


}
