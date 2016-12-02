package android.flist.com.tfire;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class simpleBlog extends AppCompatActivity {

    private RecyclerView blogList;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_blog);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("blog");

        blogList = (RecyclerView) findViewById(R.id.blist);
        blogList.setHasFixedSize(true);
        blogList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart(){
        super.onStart();

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(

                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                databaseReference

        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDes(model.getDes());
                viewHolder.setImage(getApplicationContext(), model.getImage());

            }
        };

        blogList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public BlogViewHolder(View itemView){
            super(itemView);

            mView = itemView;

        }

        public void setTitle(String title){

            TextView post_title = (TextView) mView.findViewById(R.id.btitle);
            post_title.setText(title);

        }

        public void setDes(String des){

            TextView post_description = (TextView) mView.findViewById(R.id.bdes);
            post_description.setText(des);

        }

        public void setImage(Context ctx, String image){

            ImageView imgView = (ImageView) mView.findViewById(R.id.imageView);
            Picasso.with(ctx).load(image).into(imgView);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_add)
        {
            startActivity(new Intent(getApplicationContext(), postActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
