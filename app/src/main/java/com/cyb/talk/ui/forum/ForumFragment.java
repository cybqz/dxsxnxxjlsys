package com.cyb.talk.ui.forum;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.cyb.talk.MainActivity;
import com.cyb.talk.R;
import com.cyb.talk.adapter.ForumUserListAdapter;
import com.cyb.talk.common.Constant;
import com.cyb.talk.ui.login.LoginFragment;
import com.cyb.talk.util.HttpUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForumFragment extends Fragment {

    private static  String TAG = MainActivity.class.getCanonicalName();
    private LoginFragment loginFragment;
    private JSONObject requestPostMap = new JSONObject();
    private List<Map<String,Object>> images=new ArrayList<>();
    private View root;
    private View footView;
    private ListView listView;
    private TextView textView;
    private ForumViewModel forumViewModel;
    private ForumUserListAdapter forumUserListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        forumViewModel = ViewModelProviders.of(this).get(ForumViewModel.class);
        root = inflater.inflate(R.layout.fragment_forum, container, false);
        textView = root.findViewById(R.id.text_forum);
        forumViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        listView = root.findViewById(R.id.list_view_forum);
        //footView = inflater.inflate(R.layout.fragment_loading, container, false);
        forumUserListAdapter = new ForumUserListAdapter(getContext(), images);
        listView.setAdapter(forumUserListAdapter);


        final RefreshLayout refreshLayout = root.findViewById(R.id.refreshLayout);
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                System.out.println("onLoadmoreListener");

                refreshLayout.setLoadmoreFinished(true);
                System.out.println("onLoadmoreListener--------End");
                refreshLayout.finishRefresh();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                //listView.addFooterView(footView);
                images.clear();
                for(int i = 1; i <5; i++){
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("name","name-"+i);
                    map.put("pic",Constant.DEFAULT_USER_IMAGE_BOY);
                    images.add(map);
                }
                forumUserListAdapter.notifyDataSetChanged();
                System.out.println("onRefreshListener--------End");
                refreshLayout.finishRefresh();
            }
        });

        System.out.println("----------------------ForumFragment");
        return root;
    }



    Runnable postRun = new Runnable() {

        @Override
        public void run() {
            requestPostMap = HttpUtil.requestPost(TAG, Constant.LOGINED_USER_URL, null);
        }
    };

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        System.out.println("----------------------ForumFragment startActivity");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("----------------------ForumFragment onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("----------------------ForumFragment onStart");
    }
}