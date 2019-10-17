package com.cs.recyclerviewmvp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.recyclerviewmvp.R;
import com.cs.recyclerviewmvp.framework.view.OnHolderClickListener;
import com.cs.recyclerviewmvp.framework.view.OnRecyclerViewClickListener;
import com.cs.recyclerviewmvp.model.CountryModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Vijay Patel on 29/11/18.
 */

public class CountryFeedAdapter extends RecyclerView.Adapter<CountryFeedAdapter.FeedViewHolder> implements OnHolderClickListener {
    private List<CountryModel> countryModels;
    private Context mContext;
    private OnRecyclerViewClickListener clickListener;

    public CountryFeedAdapter(Context context, List<CountryModel> events, OnRecyclerViewClickListener clickListener) {
        this.mContext = context;
        this.countryModels = events;
        this.clickListener = clickListener;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_feed_item, parent, false);
        return new FeedViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(final FeedViewHolder holder, int position) {
        holder.countryFeed = countryModels.get(position);
        holder.holderPosition = position;
        holder.mTitle.setText(holder.countryFeed.getTitle());
        holder.mDescription.setText(holder.countryFeed.getDescription());
        Picasso.with(mContext).load(holder.countryFeed.getImageHref())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mImage);
    }

    @Override
    public void onHolderClicked(int holderPosition, View clickedImage) {
        clickListener.onRecyclerViewClick(countryModels.get(holderPosition), clickedImage);
    }

    @Override
    public int getItemCount() {
        return countryModels.size();
    }

    public void setPosts(List<CountryModel> posts) {
        this.countryModels = posts;
    }

    public void clearData() {
        countryModels.clear();
    }

    public void addAllData(List<CountryModel> posts) {
        this.countryModels.addAll(posts);
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {

        private CountryModel countryFeed;
        private int holderPosition;
        private OnHolderClickListener clickListener;


        @BindView(R.id.tv_title)
        TextView mTitle;

        @BindView(R.id.tv_description)
        TextView mDescription;

        @BindView(R.id.iv_feed_image)
        ImageView mImage;

        public FeedViewHolder(View view, OnHolderClickListener clickListener) {
            super(view);
            this.clickListener = clickListener;
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.card_view)
        public void onClick() {
            if (clickListener != null) {
                clickListener.onHolderClicked(holderPosition, mImage);
            }
        }
    }
}
