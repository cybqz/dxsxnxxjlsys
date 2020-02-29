package com.cyb.talk.ui.talk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.cyb.talk.R;
import com.cyb.talk.adapter.ForumUserListAdapter;
import com.cyb.talk.adapter.ForumUserTalkListAdapter;
import com.cyb.talk.common.Constant;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TalkFragment extends Fragment implements View.OnClickListener {

    private List<Map<String,Object>> userTalkList=new ArrayList<>();
    private ForumUserTalkListAdapter forumUserTalkListAdapter;
    private ListView talkListView;
    private Button buttonSendMsg;
    private TextView textTalk;
    private View root;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_forum_user_talk, container, false);
        textTalk = root.findViewById(R.id.text_talk_obj);
        talkListView = root.findViewById(R.id.list_view_forum_talk);
        forumUserTalkListAdapter = new ForumUserTalkListAdapter(getContext(), userTalkList);
        talkListView.setAdapter(forumUserTalkListAdapter);
        buttonSendMsg=root.findViewById(R.id.button_send_msg);
        buttonSendMsg.setOnClickListener(this);

        Bundle bundle = getArguments();
        if(null != bundle && bundle.containsKey("name")){
            String name = bundle.getString("name");
            textTalk.setText(name);
        }

        final RefreshLayout refreshLayout = root.findViewById(R.id.refreshLayoutUserTalk);
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
                reload(15);
                refreshLayout.finishRefresh();
                System.out.println("onRefreshListener---Talk----End");
            }
        });

        reload(5);
        System.out.println("----------------------TalkFragment");
        return root;
    }

    private void reload(int count){
        userTalkList.clear();
        for(int i = 1; i < count; i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name","name-"+i);
            map.put("pic", Constant.DEFAULT_USER_IMAGE_BOY);
            userTalkList.add(map);
        }
        forumUserTalkListAdapter.notifyDataSetChanged();
    }

    /**
     * 单击-登陆
     * @param view
     */
    @Override
    public void onClick(View view) {

        reload(20);
        System.out.println("-----------");
    }
}