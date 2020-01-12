package com.bawei.day01shoopingcar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.day01shoopingcar.MainActivity;
import com.bawei.day01shoopingcar.R;
import com.bawei.day01shoopingcar.entity.EntityBean;
import com.bawei.day01shoopingcar.utils.OkHttpUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;


/**
 * @author: asus
 * @date: 20/1/6
 */
public class TuiAdapter extends RecyclerView.Adapter<TuiAdapter.VH > {
    private  Context context;
    private List<EntityBean.Cart> list;

    public TuiAdapter(Context context, List<EntityBean.Cart> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.layout_item, null);
        VH vh=new VH(inflate);
        return vh;
    }
    public List<EntityBean.Cart> getList() {
        return list;
    }
    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {

        if (list.get(position).isCartChecked){
            holder.che_1.setChecked(true);
        }else{
            holder.che_1.setChecked(false);
        }

        holder.tv.setText(list.get(position).categoryName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).categoryName, Toast.LENGTH_SHORT).show();
            }
        });

        holder.che_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.che_1.isChecked()){
                    list.get(position).isCartChecked = true;
                    for (EntityBean.Cart.Product product : list.get(position).shoppingCartList) {
                        product.isProductChecked = true;
                    }
                }else{
                    list.get(position).isCartChecked = false;
                    for (EntityBean.Cart.Product product : list.get(position).shoppingCartList) {
                        product.isProductChecked = false;
                    }
                }
                notifyDataSetChanged();
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.totalPrice();
            }
        });
        holder.recy.setLayoutManager(new LinearLayoutManager(context));
        OkHttpUtils.getInstance().doGet("http://172.17.8.100/small/commodity/v1/commodityList", new OkHttpUtils.OkHttpCallBack() {
            @Override
            public void scuuess(String string) {

                NeiAdapter neiAdapter = new NeiAdapter(context,list.get(position).shoppingCartList);

                holder.recy.setAdapter(neiAdapter);
            }
            @Override
            public void error(Throwable throwable) {

            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }
      class VH extends RecyclerView.ViewHolder{

          public CheckBox che_1;
          public TextView tv;
          public   RecyclerView recy;

          public VH(@NonNull View itemView) {
            super(itemView);
              tv = itemView.findViewById(R.id.name);
              recy = (RecyclerView)itemView. findViewById(R.id.recy_nei);
              che_1 = (CheckBox)itemView. findViewById(R.id.che_1);

          }
    }

}
