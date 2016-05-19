package com.sample.easysqlite.johnwu.easysqlite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        myViewHolder.title.setText(list.get(position).getTitle());
        myViewHolder.subtitle.setText(list.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return list == null ? null:list.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title,subtitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            subtitle = (TextView)itemView.findViewById(R.id.subtitle);
        }
    }
}
