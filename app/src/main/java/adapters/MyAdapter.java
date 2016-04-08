package adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jeffersonalmeida.myappnewasversion.R;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.util.List;

import restapi.Posts;
import restapi.Result;

/**
 * Created by jeffersonalmeida on 4/7/16.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private Result result;

    private List<Posts> posts;

    public MyAdapter(Context context, Result result) {
        this.context = context;
        this.result = result;
        posts = result.getPosts();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View screen = LayoutInflater.
                from(context).inflate(R.layout.layout_main_posts, parent, false);



        return new MyViewHolder(screen);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Posts post = this.posts.get(position);
        String imageURL = post.getAttachments().get(0).getUrl();
        String content = post.getContent();

        org.jsoup.nodes.Document document = Jsoup.parse(content);
        String contentClean = document.body().text();


        String title = post.getTitle();

        holder.content.setText(contentClean);
        holder.title.setText(title);

        Glide
                .with(context)
                .load(imageURL)
                .centerCrop()
                .crossFade()
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return result.getPosts().size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        TextView content;

        public MyViewHolder(View screen) {
            super(screen);
            image = (ImageView) screen.findViewById(R.id.image);
            title = (TextView) screen.findViewById(R.id.title);
            content = (TextView) screen.findViewById(R.id.content);
        }
    }



}


