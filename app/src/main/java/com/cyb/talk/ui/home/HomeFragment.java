package com.cyb.talk.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.cyb.talk.LoginActivity;
import com.cyb.talk.MainActivity;
import com.cyb.talk.R;
import com.cyb.talk.common.Constant;
import com.cyb.talk.ui.login.LoginFragment;
import com.cyb.talk.util.HttpUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    private static  String TAG = MainActivity.class.getCanonicalName();
    private LoginFragment loginFragment;
    private JSONObject requestPostMap = new JSONObject();
    private View root;
    private TextView textView;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        System.out.println("----------------------onHiddenChanged " + hidden);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        System.out.println("----------------------HomeFragment startActivity");
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        System.out.println("----------------------HomeFragment onAttachFragment");
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