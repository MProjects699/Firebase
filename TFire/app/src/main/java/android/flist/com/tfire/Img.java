package android.flist.com.tfire;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import static android.R.attr.data;

public class Img extends AppCompatActivity {

    ImageView iv;
    StorageReference mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Points to the root reference
        StorageReference storageRef = storage.getReferenceFromUrl("gs://nse-ninja.appspot.com");

        // Create a child reference

        // imagesRef now points to "images"
        StorageReference imagesRef = storageRef.child("images");
        StorageReference spaceRef = storageRef.child("images/games.png");

        File localFile = null;
        try {
            localFile = File.createTempFile("games", "png");
        } catch (IOException e) {
            e.printStackTrace();
        }

        spaceRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(), "Failed to download", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
