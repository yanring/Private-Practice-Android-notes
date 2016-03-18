package com.example.yanring.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yanring on 2016/1/31.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context mContext;
    //private String[] mNames = {"小明","小花"};
    private LayoutInflater mLayoutInflater;
    private List<UserInfo> mUserInfos = new ArrayList<>();//申明数组
    public GridViewAdapter(Context context, List<UserInfo> userInfos){
        mContext = context;
        mUserInfos = userInfos;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mUserInfos.size();//返回大小
    }

    @Override
    public Object getItem(int position) {
        return mUserInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return a view,和数据之间进行绑定
        ViewHolder viewHolder;
        if(convertView == null){//避免重复生成浪费资源
            convertView = mLayoutInflater.inflate(R.layout.item_phone_book_friends,null);//将XML解析成一个view
            viewHolder = new ViewHolder();
             viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.name_text_view);//找到控件
             viewHolder.ageTextView = (TextView) convertView.findViewById(R.id.age_text_view);
             viewHolder.avatarImageView = (ImageView) convertView.findViewById(R.id.avatar_image_view);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //convertView = mLayoutInflater.inflate(R.layout.item_phone_book_friends,null);//将XML解析成一个view
//       viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.name_text_view);//找到控件
//       viewHolder.ageTextView = (TextView) convertView.findViewById(R.id.age_text_view);
//       viewHolder.avatarImageView = (ImageView) convertView.findViewById(R.id.avatar_image_view);
         viewHolder.ageTextView.setText("年龄:"+mUserInfos.get(position).getmAge());
         viewHolder.nameTextView.setText("姓名:"+mUserInfos.get(position).getmUserName());//得到convertview(item_phone_book_friends)中的TextView控件并设置它的文本
         viewHolder.avatarImageView.setImageResource(R.drawable.ic_launcher);
        return convertView;//返回convertview
    }
    class ViewHolder{
        TextView nameTextView ;
        TextView ageTextView ;
        ImageView avatarImageView;
    }
    public void refreData(List<UserInfo> userInfos){
        mUserInfos = userInfos;
        notifyDataSetChanged();//更改ListView的数据

    }
}
