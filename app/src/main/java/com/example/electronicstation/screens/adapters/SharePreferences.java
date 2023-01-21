package com.example.electronicstation.screens.adapters;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferences {
    private Context context;
    private SharedPreferences sharedPreferences;
    private static final String PREFERENCES = "Preferences";
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    public SharePreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFERENCES,context.MODE_PRIVATE);
    }
    public  void addUserData(String key, String value){
        editor =  sharedPreferences.edit();
        preferences = context.getSharedPreferences(PREFERENCES,context.MODE_PRIVATE);
        editor.putString(key, value);
        editor.apply();
    }
    public  void removeData (String key){
        sharedPreferences.edit().remove(key).apply();
    }
    public String readUserData (String key){
        preferences = context.getSharedPreferences(PREFERENCES,context.MODE_PRIVATE);
        String userName = preferences.getString(key,null);
        return userName;
    }
    public void setUserVisibility(Boolean val){
        editor =  sharedPreferences.edit();
        editor.putBoolean("isOwner",val);
        editor.apply();

    }
    public boolean isOwner(){
        preferences = context.getSharedPreferences(PREFERENCES,context.MODE_PRIVATE);
        boolean isNotificationShown = preferences.getBoolean("isOwner",true);
        return isNotificationShown;
    }

}
