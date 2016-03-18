package com.example.yanring.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by Yanring on 2016/2/3.
 */
public class TestViewFragmentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test );
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        TestFragment testFragment = TestFragment.newInsatance("Yanring",22);

        //TestFragment testFragment = new TestFragment();
        fragmentTransaction.add(R.id.fragemnt_linear_layout,testFragment).commit();//后面一个参数可以放TAG 然后通过get TAG  得到Fragement
        //fragmentTransaction.remove(testFragment).commit();
        //将testFragment放在Container→fragemnt_linear_layou中
        Fragment fragment =fragmentManager.findFragmentById(R.id.fragment_test_1);


        if (fragment instanceof TestFragment){

        }else{
            throw new IllegalStateException("is not testFragement");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
