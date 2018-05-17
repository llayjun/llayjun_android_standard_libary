package com.example.common.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.common.R;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Created by zhangyinlei on 2018/3/2 0002.
 */

public class GridImageChooseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static int TYPE_ADD = 0;//添加图片
    private static int TYPE_COMMON = 1;//普通图片展示

    private Context context;
    private LayoutInflater mLayoutInflater;

    //data
    private int mMaxAlbum;//最大选择图片的数量
    private List<String> mStringList;//图片url集合

    public GridImageChooseAdapter(Context context, List<String> mStringList, int maxAlbum) {
        this.context = context;
        this.mStringList = mStringList;
        this.mMaxAlbum = maxAlbum;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ADD) {
            return new ItemViewHolderAdd(mLayoutInflater.inflate(R.layout.item_album_selected_add, parent, false));
        } else {
            return new ItemViewHolderCommon(mLayoutInflater.inflate(R.layout.item_album_selected_common, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        View itemView = null;
        if (holder instanceof ItemViewHolderAdd) {
            ItemViewHolderAdd itemViewHolderAdd = (ItemViewHolderAdd) holder;
            if (position >= mMaxAlbum) {
                itemViewHolderAdd.itemView.setVisibility(View.GONE);
            } else {
                itemViewHolderAdd.tvNum.setText(position + "/" + mMaxAlbum);
                itemViewHolderAdd.itemView.setVisibility(View.VISIBLE);
                itemView = ((ItemViewHolderAdd) holder).itemView;
            }
        } else if (holder instanceof ItemViewHolderCommon) {
            String url = mStringList.get(position);
            Glide.with(context).load(url)
                    .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(10)))
                    .transition(withCrossFade())
                    .into(((ItemViewHolderCommon) holder).ivCommon);
            itemView = ((ItemViewHolderCommon) holder).itemView;
        }
        if (mOnItemClickListener != null && null != itemView) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == mStringList.size() ? TYPE_ADD : TYPE_COMMON;
    }

    @Override
    public int getItemCount() {
        return mStringList.size() + 1;//加一代表最后一个添加图片按钮
    }

    public static class ItemViewHolderAdd extends RecyclerView.ViewHolder {
        private TextView tvNum;

        public ItemViewHolderAdd(View itemView) {
            super(itemView);
            tvNum = itemView.findViewById(R.id.tv_album_selected_num);
        }
    }

    public static class ItemViewHolderCommon extends RecyclerView.ViewHolder {
        private ImageView ivCommon;

        public ItemViewHolderCommon(View itemView) {
            super(itemView);
            ivCommon = itemView.findViewById(R.id.iv_album_selected);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
