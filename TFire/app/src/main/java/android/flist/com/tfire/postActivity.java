package android.flist.com.tfire;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.R.attr.data;

public class postActivity extends AppCompatActivity {

    private ImageButton imgbtn;
    private EditText title,des;
    private Button submit;
    ProgressDialog mProgress;

    private Uri imageUri = null;

    StorageReference mStorage;
    DatabaseReference databaseReference;

    private static final int GALLERY_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mStorage = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("blog");

        imgbtn = (ImageButton) findViewById(R.id.imageButton);
        title = (EditText) findViewById(R.id.editText8);
        des = (EditText) findViewById(R.id.editText9);
        submit = (Button) findViewById(R.id.button5);
        mProgress = new ProgressDialog(this);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mProgress.setMessage("Posting to Blog ....");
                mProgress.show();

                final String title_Val = title.getText().toString();
                final String desc_Val = des.getText().toString();

                if(!TextUtils.isEmpty(title_Val) && !TextUtils.isEmpty(desc_Val) && imageUri != null ){

                    StorageReference filepath = mStorage.child("Blog_Images").child(imageUri.getLastPathSegment());

                    filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Uri downloadUrl = taskSnapshot.getDownloadUrl();

                            DatabaseReference newPost = databaseReference.push();

                            newPost.child("title").setValue(title_Val);
                            newPost.child("des").setValue(desc_Val);
                            newPost.child("image").setValue(downloadUrl.toString( ));

                            mProgress.dismiss();

                        }
                    });

                }

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){

            imageUri = data.getData();
            imgbtn.setImageURI(imageUri);

        }

    }
}



