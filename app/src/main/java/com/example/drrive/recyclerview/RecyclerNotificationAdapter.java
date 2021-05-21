package com.example.drrive.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drrive.R;
import com.example.drrive.model.Post;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerNotificationAdapter extends RecyclerView.Adapter<RecyclerNotificationAdapter.MyViewHolder> {

    private List<Post> postsList;
    private Context context;
    private RecyclerViewClickListener listener;

    public RecyclerNotificationAdapter(RecyclerViewClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Post> postsList) {
        this.postsList = postsList;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {

        Post post = postsList.get(position);

        String postTitle = post.getTitle();
        String postDate = post.getDate();
        String postDescription = post.getDescription();

        holder.postTitleTv.setText(postTitle);
        holder.postDateTv.setText(postDate);
        holder.postDescriptionTv.setText(postDescription);
        holder.postTitleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(post);
            }
        });
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(Post post);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView postTitleTv;
        private final TextView postDateTv;
        private final TextView postDescriptionTv;

        public MyViewHolder(@NonNull @NotNull View view) {
            super(view);
            postTitleTv = view.findViewById(R.id.postTitleTv);
            postDateTv = view.findViewById(R.id.postDateTv);
            postDescriptionTv = view.findViewById(R.id.postDescriptionTv);
        }
    }
}
