package com.dl7.coordinator.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dl7.coordinator.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by long on 2016/7/8.
 */
public class ListAdapter extends BaseRecyclerAdapter<String> {


    public ListAdapter(Context context) {
        super(context);
    }

    public ListAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    public ListAdapter(Context context, String[] datas) {
        super(context, datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mTvTitle.setText(mDatas.get(position));
        if (position % 2 == 0) {
            viewHolder.mIvIcon.setImageResource(R.mipmap.ic_face_funny);
        } else {
            viewHolder.mIvIcon.setImageResource(R.mipmap.ic_face_sad);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_title)
        TextView mTvTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
