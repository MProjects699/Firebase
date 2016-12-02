package android.flist.com.tfire;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Contacts extends AppCompatActivity {

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.show();

        ListView lst = (ListView) findViewById(R.id.list);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://tfire-46b36.firebaseio.com/contacts");

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(this, String.class, android.R.layout.simple_list_item_1, databaseReference) {
            @Override
            protected void populateView(View v, String model, int position) {

                TextView textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText(model);
            }
        };

        lst.setAdapter(firebaseListAdapter);

        progressDialog.dismiss();


    }
}
