package com.cyb.talk.ui.forum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import com.cyb.talk.MainActivity;
import com.cyb.talk.R;
import com.cyb.talk.adapter.ForumUserListAdapter;
import com.cyb.talk.common.Constant;
import com.cyb.talk.ui.talk.TalkFragment;
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
    private JSONObject requestPostMap = new JSONObject();
    private List<Map<String,Object>> userList=new ArrayList<>();
    private View root;
    private ListView userListView;
    private ForumViewModel forumViewModel;
    private ForumUserListAdapter forumUserListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        forumViewModel = ViewModelProviders.of(this).get(ForumViewModel.class);
        root = inflater.inflate(R.layout.fragment_forum, container, false);

        userListView = root.findViewById(R.id.list_view_forum_user);
        //footView = inflater.inflate(R.layout.fragment_loading, container, false);
        forumUserListAdapter = new ForumUserListAdapter(getContext(), userList);
        userListView.setAdapter(forumUserListAdapter);

        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> image = userList.get(position);
                String name = image.get("name").toString();
                switchFragment(name);
            }
        });

        final RefreshLayout refreshLayout = root.findViewById(R.id.refreshLayoutForum);
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
                reload(10);
                System.out.println("onRefreshListener--------End");
                refreshLayout.finishRefresh();
            }
        });

        reload(5);
        System.out.println("----------------------ForumFragment");
        return root;
    }

    private void reload(int count){
        userList.clear();
        for(int i = 1; i < count; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name","name-"+i);
            map.put("pic",Constant.DEFAULT_USER_IMAGE_BOY);
            userList.add(map);
        }
        forumUserListAdapter.notifyDataSetChanged();
    }

    Runnable postRun = new Runnable() {

        @Override
        public void run() {
            requestPostMap = HttpUtil.requestPost(TAG, Constant.LOGINED_USER_URL, null);
        }
    };

    //正确的做法
    private void switchFragment(String name) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        TalkFragment talkFragment = new TalkFragment();
        Bundle bundle=new Bundle();
        bundle.putString("name", name);
        talkFragment.setArguments(bundle);
        transaction.replace(R.id.nav_host_fragment, talkFragment);
        transaction.commit();
    }

}