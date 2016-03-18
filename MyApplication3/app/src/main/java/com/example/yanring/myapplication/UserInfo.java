package com.example.yanring.myapplication;

import java.io.Serializable;

/**
 * Created by Yanring on 2016/1/25.
 */
public class UserInfo implements Serializable {
    private String mUserName;
    private String mAge;

    public UserInfo (String mUserName, String mAge) {
        this.mUserName = mUserName;
        this.mAge = mAge;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmAge() {
        return mAge;
    }

    public void setmAge(String mAge) {
        this.mAge = mAge;
    }
}
