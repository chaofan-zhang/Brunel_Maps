package com.example.brunel_maps.ui.events.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.brunel_maps.R;
import com.example.brunel_maps.ui.events.bean.EventBean;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Author by linkaikai
 * Date on 2020/3/13
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder>{


    private Context mContext;
    private List<EventBean> mDataList;

    public EventsAdapter(Context context, List<EventBean> dataList) {
        this.mContext = context;
        this.mDataList = dataList;
    }

    public interface OnItemClickListener{
        public void onItemClickListener(int position);
        public void onItemLongClickListener(int position);
        public void onTextClickListener(int position);
        public void onTextLongClickListener(int position);
    }

    OnItemClickListener onItemClickListener;

    public void setOnViewClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.itemevent_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_wenzi.setText(mDataList.get(position).getTitle());
        holder.tv_place.setText(mDataList.get(position).getPlace());
        holder.tv_time.setText(mDataList.get(position).getTime());
        holder.tv_title.setText("");
        holder.iv.setImageResource(mDataList.get(position).getPath());
       // Glide.with(mContext).load(mDataList.get(position).getPath()).into( holder.iv);

       /* holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "view"+position, Toast.LENGTH_SHORT).show();
            }
        });
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "text"+position, Toast.LENGTH_SHORT).show();
            }
        });*/

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null) {
                    onItemClickListener.onItemClickListener(position);
                }
            }
        });
        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(onItemClickListener!=null) {
                    onItemClickListener.onItemLongClickListener(position);
                }
                return false;
            }
        });







    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_wenzi;
        TextView tv_place;
        TextView tv_time;
        TextView tv_title;
        ImageView iv;
        View view;
        public MyViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            tv_wenzi=itemView.findViewById(R.id.tv_wenzi);
            tv_place=itemView.findViewById(R.id.tv_place);
            tv_time=itemView.findViewById(R.id.tv_time);
            tv_title=itemView.findViewById(R.id.tv_title);
            iv=itemView.findViewById(R.id.iv);

        }
    }
}

