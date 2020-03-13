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
    /*
     *
     * 另一种实现监听事件的实现方式采用接口回调的方式
     *
     * */
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

    /*
     *
     *
     * 创建viewHolder
     * 这个主要作用就是找到item布局
     * 把布局里面有用的控件与Viewholder进行关联
     *
     *
     * */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.itemevent_layout,parent,false);
        return new MyViewHolder(view);
    }
    /*
     *
     *
     * 绑定viewholder
     * 主要是数据源与viewHolder的绑定
     * 这里可以实现监听事件
     *
     * */
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_wenzi.setText(mDataList.get(position).getTitle());
        holder.tv_place.setText(mDataList.get(position).getPlace());
        holder.tv_time.setText(mDataList.get(position).getTime());
        Glide.with(mContext).load(mDataList.get(position).getPath()).into( holder.iv);
        /*
         *
         * 监听事件
         *这是一种方式实现监听事件
         * */
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

        /*
         *
         * 接口回调方式实现监听事件
         *
         * */
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

    /*
     *
     * 获取数据源条数
     *
     * */
    @Override
    public int getItemCount() {
        return mDataList.size();
    }
    /*
     *
     * viewholder与布局中的控件关联
     * view代表一个item
     *
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_wenzi;
        TextView tv_place;
        TextView tv_time;
        ImageView iv;
        View view;
        public MyViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            tv_wenzi=itemView.findViewById(R.id.tv_wenzi);
            tv_place=itemView.findViewById(R.id.tv_place);
            tv_time=itemView.findViewById(R.id.tv_time);
            iv=itemView.findViewById(R.id.iv);

        }
    }
}

