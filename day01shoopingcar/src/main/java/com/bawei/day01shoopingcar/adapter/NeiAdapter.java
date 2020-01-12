package com.bawei.day01shoopingcar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.day01shoopingcar.R;
import com.bawei.day01shoopingcar.entity.EntityBean;
import com.bumptech.glide.Glide;

import java.util.List;


/**
 * @author: asus
 * @date: 20/1/6
 */
public class NeiAdapter extends RecyclerView.Adapter<NeiAdapter.VH > {
    private  Context context;
    private List<EntityBean.Cart.Product> list;

    public NeiAdapter(Context context, List<EntityBean.Cart.Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.nei, null);
        VH vh=new VH(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {
        if (list.get(position).isProductChecked){
            holder.che_2.setChecked(true);
        }else{
            holder.che_2.setChecked(false);
        }

        //holder.price.setText(list.get(position).price+"");

        Glide.with(context).load(list.get(position).pic)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.im);
        holder.tv.setText(list.get(position).commodityName);
        holder.tv_jia.setText(list.get(position).price+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).commodityName, Toast.LENGTH_SHORT).show();
            }
        });
        
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
      class VH extends RecyclerView.ViewHolder{

          private final TextView tv;
          private final ImageView im;
          private final CheckBox che_2;
          private final TextView tv_jia;

          public VH(@NonNull View itemView) {
            super(itemView);
              tv = itemView.findViewById(R.id.name);
              im = (ImageView)itemView. findViewById(R.id.im);
              che_2 = (CheckBox)itemView. findViewById(R.id.che_2);
              tv_jia = (TextView)itemView. findViewById(R.id.tv_jie);

          }
    }

}
