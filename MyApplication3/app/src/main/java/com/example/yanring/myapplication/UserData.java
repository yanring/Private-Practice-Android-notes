package com.example.yanring.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Yanring on 2016/3/12.
 */
public class UserData {
    @SerializedName("title")
    private String mTitle;
    @SerializedName("content")
    private String mContent;
    @SerializedName("user")
    private String mUser;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getUser() {
        return mUser;
    }

    public void setUser(String user) {
        mUser = user;
    }

    public List<String> getImages() {
        return mImages;
    }

    public void setImages(List<String> images) {
        mImages = images;
    }

    @SerializedName("images")
    private List<String> mImages;

    public class User{
        @SerializedName("id")
        private long mID;

        @SerializedName("name")
        private String mName;

        @SerializedName("avatar")
        private String mAvatar;

        public long getID() {
            return mID;
        }

        public void setID(long ID) {
            mID = ID;
        }

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            mName = name;
        }

        public String getAvatar() {
            return mAvatar;
        }

        public void setAvatar(String avatar) {
            mAvatar = avatar;
        }
    }

}
