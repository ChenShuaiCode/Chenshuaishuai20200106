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
import com.bawei.day01shoopingcar.entity.DiZhiEntity;
import com.bawei.day01shoopingcar.entity.EntityBean;
import com.bumptech.glide.Glide;

import java.util.List;


/**
 * @author: asus
 * @date: 20/1/6
 */
public class DiZhiAdapter extends RecyclerView.Adapter<DiZhiAdapter.VH > {
    private  Context context;
    private List<DiZhiEntity.ResultBean> list;

    public DiZhiAdapter(Context context, List<DiZhiEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.layout_dizhi, null);
        VH vh=new VH(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {


        //holder.price.setText(list.get(position).price+"");

        holder.name.setText(list.get(position).getRealName());
        holder.phone.setText(list.get(position).getPhone());
        holder.addres.setText(list.get(position).getAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getRealName(), Toast.LENGTH_SHORT).show();
            }
        });
        
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
      class VH extends RecyclerView.ViewHolder{


          private final TextView name;
          private final TextView phone;
          private final TextView addres;

          public VH(@NonNull View itemView) {
            super(itemView);
              name = (TextView)itemView. findViewById(R.id.name);
              phone = (TextView)itemView. findViewById(R.id.phone);
              addres = (TextView)itemView. findViewById(R.id.addres);

          }
    }

}
