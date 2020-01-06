package com.bawei.chenshuaishuai20200106.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.chenshuaishuai20200106.R;
import com.bawei.chenshuaishuai20200106.model.entity.LeftEntity;
import com.bawei.chenshuaishuai20200106.model.entity.RightEntity;
import com.bawei.chenshuaishuai20200106.utils.OkHttpUtils;
import com.bumptech.glide.Glide;

import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.MyViewHolder> {

    private Context context;
    private List<RightEntity.DataBean> list;

    public RightAdapter(Context context, List<RightEntity.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.right_item_layout,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tv.setText(list.get(position).getGoods_english_name());
        Glide.with(context).load(list.get(position).getGoods_thumb())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.im);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getGoods_english_name(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private  ImageView im;
        private TextView tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_1);
            im = (ImageView)itemView. findViewById(R.id.im);
        }
    }

    private LeftClick leftClick;

    public void setLeftClick(LeftClick leftClick) {
        this.leftClick = leftClick;
    }

    public interface LeftClick{
        void leftItemClick(String id);
    }
}
