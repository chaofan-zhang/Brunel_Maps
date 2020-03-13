package com.example.brunel_maps.ui.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brunel_maps.R;
import com.example.brunel_maps.base.BaseFragment;
import com.example.brunel_maps.ui.events.adapter.EventsAdapter;
import com.example.brunel_maps.ui.events.bean.EventBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class EventsFragment extends Fragment {

    // @BindView(R.id.events_rv)
    // RecyclerView eventsRv;
    private EventsViewModel mapViewModel;
    private RecyclerView eventsRv;


    public void initData() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);
        List<EventBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new EventBean("West London higher education", format, "China", "https://public-1254365843.cos.ap-beijing.myqcloud.com/illustration/share/00021802_List_Picture.jpg"));
        }

        EventsAdapter adapter = new EventsAdapter(getContext(), list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        eventsRv.setLayoutManager(linearLayoutManager);
        eventsRv.setAdapter(adapter);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mapViewModel =
                ViewModelProviders.of(this).get(EventsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_events, container, false);
        eventsRv = root.findViewById(R.id.events_rv);


//        mapViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


}