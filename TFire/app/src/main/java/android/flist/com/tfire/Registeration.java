package android.flist.com.tfire;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registeration extends AppCompatActivity {

    private EditText cemail, cpassword;
    Button signup;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        mAuth = FirebaseAuth.getInstance();

        cemail = (EditText) findViewById(R.id.editText4);
        cpassword = (EditText) findViewById(R.id.editText7);

        signup = (Button) findViewById(R.id.button6);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SignUp();

            }
        });

    }

    private void SignUp(){

        String email = cemail.getText().toString();
        String password = cpassword.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){

            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_LONG).show();

        }else{

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(getApplicationContext(), "Registered Successfully",
                                    Toast.LENGTH_SHORT).show();

                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Authentication failed",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

        }

    }

}
