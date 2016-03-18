package com.example.yanring.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Yanring on 2016/2/5.
 */
public class TestFragment extends Fragment {

    public static final String ARGUMENT_NAME = "argument_name";
    public static final String ARGUMENT_NUMBER = "argument_number";
    private String mName;
    private int mNumber;

    public static  TestFragment newInsatance(String nameString, int number){
        TestFragment testFragment = new TestFragment();

        Bundle bundle =new Bundle();//构造一个bundle,将数据放在里面
        bundle.putString(ARGUMENT_NAME,nameString);
        bundle.putInt(ARGUMENT_NUMBER,number);

        testFragment.setArguments(bundle);//将bundle设为参数进行传递

        return testFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();//获取参数
        if(bundle != null){
            mName = bundle.getString(ARGUMENT_NAME);
            mNumber = bundle.getInt(ARGUMENT_NUMBER);
        }
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.item_phone_book_friends,container,false);//是绑定到root→container中,如果绑定了则在fragmentTransaction会出错

        TextView nameTextView = (TextView) view.findViewById(R.id.name_text_view);
        nameTextView.setText(mName);
        TextView ageTextView = (TextView) view.findViewById(R.id.age_text_view);
        ageTextView.setText(mNumber+"");

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
