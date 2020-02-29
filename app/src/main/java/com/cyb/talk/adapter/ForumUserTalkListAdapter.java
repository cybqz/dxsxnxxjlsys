package com.cyb.talk.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.cyb.talk.R;

import java.util.List;
import java.util.Map;

public class ForumUserTalkListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Map<String,Object>> list;
    private Context context;


    public ForumUserTalkListAdapter(Context context , List<Map<String,Object>> list){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.fragment_forum_user_talk_list, null);
            holder.name = convertView.findViewById(R.id.text_forum_user_talk);
            holder.pic = convertView.findViewById(R.id.img_forum_user_talk);
            System.out.println("holder.pic\t" + holder.pic);
            Glide.with(context).load(list.get(position).get("pic")).into(holder.pic);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.name.setText((String)list.get(position).get("name"));
        return convertView;
    }
    public final class ViewHolder{

        public TextView name;
        public ImageView pic;
    }
}
