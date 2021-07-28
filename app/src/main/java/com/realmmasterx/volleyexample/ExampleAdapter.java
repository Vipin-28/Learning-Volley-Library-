package com.realmmasterx.volleyexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>{
    private ArrayList<ExampleItem> mExampleList;
    private OnItemListener mListener;

    public interface OnItemListener {
        void OnItemClick(int position);
    }
    //Constructor of Adapter class
    public ExampleAdapter(OnItemListener onItemListener, ArrayList<ExampleItem> exampleList){
        mListener = onItemListener;
        mExampleList = exampleList;
    }

            public class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
                public ImageView mImageView;
                public TextView mTextViewCreator;
                public TextView mTextViewLikes;
                public OnItemListener mListener;

                public ExampleViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
                    super(itemView);
                    mImageView = itemView.findViewById(R.id.imageView);
                    mTextViewCreator = itemView.findViewById(R.id.textViewCreator);
                    mTextViewLikes = itemView.findViewById(R.id.textViewLikes);
                    mListener = onItemListener;

                    itemView.setOnClickListener(this);
                }

                @Override
                public void onClick(View v) {
                    mListener.OnItemClick(getAdapterPosition());
                };
            }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        return new ExampleViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        String imageUrl = currentItem.getImageUrl();
        String creatorName = currentItem.getCreator();
        Integer likeCount = currentItem.getLikes();

        holder.mTextViewCreator.setText(creatorName);
        holder.mTextViewLikes.setText("Likes: " + likeCount);
//        holder.mImageView.setImageResource(Integer.parseInt(imageUrl));
        Picasso.get().load(imageUrl).fit().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
