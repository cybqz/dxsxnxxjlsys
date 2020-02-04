package com.cyb.talk.ui.forum;

import android.content.Intent;
import android.os.Bundle;
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
import org.json.JSONObject;

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
        root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);
        forumViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        System.out.println("----------------------HomeFragment");
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
        System.out.println("----------------------HomeFragment startActivity");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("----------------------HomeFragment onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("----------------------HomeFragment onStart");
    }

}