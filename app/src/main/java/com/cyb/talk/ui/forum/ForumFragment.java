package com.cyb.talk.ui.forum;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.cyb.talk.MainActivity;
import com.cyb.talk.R;
import com.cyb.talk.common.Constant;
import com.cyb.talk.ui.login.LoginFragment;
import com.cyb.talk.util.HttpUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import org.json.JSONObject;

import java.util.List;

public class ForumFragment extends Fragment {

    private static  String TAG = MainActivity.class.getCanonicalName();
    private LoginFragment loginFragment;
    private JSONObject requestPostMap = new JSONObject();
    private View root;
    private TextView textView;
    private ForumViewModel forumViewModel;

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

        RefreshLayout mRefreshLayout = root.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                System.out.println("onLoadmoreListener");
            }
        });

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                System.out.println("onRefreshListener");
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