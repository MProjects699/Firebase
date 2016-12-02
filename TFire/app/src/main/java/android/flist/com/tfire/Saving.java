package android.flist.com.tfire;

import android.renderscript.Script;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class Saving extends AppCompatActivity {

    EditText cname,cemail,cpassword;
    Button btn;
    Firebase mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);

        Firebase.setAndroidContext(this);

        mRootRef = new Firebase("https://tfire-46b36.firebaseio.com/save");

        cname = (EditText) findViewById(R.id.editText);
        cemail = (EditText) findViewById(R.id.editText2);
        cpassword = (EditText) findViewById(R.id.editText3);
        btn = (Button) findViewById(R.id.save);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = cname.getText().toString();
                String email = cemail.getText().toString();
                String password = cpassword.getText().toString();

                Firebase childRef = mRootRef.child("email");
                childRef.push().setValue(email);

                Firebase chilRef2 = mRootRef.child("name");
                chilRef2.push().setValue(name);

                Firebase childRef3 = mRootRef.child("password");
                childRef3.push().setValue(password);

                //---display file saved message---
                Toast.makeText(getBaseContext(),
                        "Thanks "+name+" for registeration",
                        Toast.LENGTH_SHORT).show();


            }
        });



    }
}
