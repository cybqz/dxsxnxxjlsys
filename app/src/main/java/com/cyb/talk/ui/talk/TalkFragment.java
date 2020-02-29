package com.cyb.talk.ui.talk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.cyb.talk.R;

public class TalkFragment extends Fragment implements View.OnClickListener {

    private Button buttonSendMsg;
    private TextView textTalk;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_talk, container, false);
        textTalk = view.findViewById(R.id.text_talk);
        buttonSendMsg=view.findViewById(R.id.button_send_msg);
        buttonSendMsg.setOnClickListener(this);

        Bundle bundle = getArguments();
        if(null != bundle && bundle.containsKey("name")){
            String name = bundle.getString("name");
            textTalk.setText(name);
        }

        System.out.println("----------------------TalkFragment");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //登陆成功，跳转到主页

    }

    /**
     * 单击-登陆
     * @param view
     */
    @Override
    public void onClick(View view) {

        System.out.println("-----------");
    }
}