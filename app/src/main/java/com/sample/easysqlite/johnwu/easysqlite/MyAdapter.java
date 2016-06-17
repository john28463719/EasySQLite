package com.sample.easysqlite.johnwu.easysqlite;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sample.easysqlite.johnwu.easysqlite.databinding.ItemBinding;

import java.util.List;

/**
 * Created by JohnWu on 2016/5/17.
 */
public class MyAdapter extends RecyclerView.Adapter {

    List<Article> list;


    public MyAdapter(List<Article> list){
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        myViewHolder.getItemBinding().setArticle(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? null:list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemBinding itemBinding;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemBinding = DataBindingUtil.bind(itemView);
        }

        public ItemBinding getItemBinding() {
            return itemBinding;
        }
    }
}
