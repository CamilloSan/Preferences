package com.example.camillo.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Camillo on 02.10.2016.
 */

public class ApplicationSetings {
    public static final String SETTINGS = "settings";
    public static final String KAY_NAME = "name";
    public static final String KAY_SURNAME = "surname";
    public static final String KAY_SEX = "sex";
    public static final String AGREEKAY_AGRE = "agree";
    private final SharedPreferences mSharedPreferences;


    public ApplicationSetings(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(SETTINGS,Context.MODE_PRIVATE);
    }

    public  String getName(){
     return mSharedPreferences.getString(KAY_NAME,null);
    }
    public  void setName(String name){
        mSharedPreferences.edit().putString(KAY_NAME,name).apply();
    }


    public  String getSurname(){
     return mSharedPreferences.getString(KAY_SURNAME,null);
    }
    public  void setSurname(String surname){
        mSharedPreferences.edit().putString(KAY_SURNAME,surname).apply();
    }

    public  String getSex(){
     return mSharedPreferences.getString(KAY_SEX,null);
    }
    public  void setSex(String sex){
        mSharedPreferences.edit().putString(KAY_SEX,sex).apply();
    }


    public  boolean getAgree(){
      return  mSharedPreferences.getBoolean(AGREEKAY_AGRE,false);
    }
    public  void setAgree(boolean agree){
        mSharedPreferences.edit().putBoolean(AGREEKAY_AGRE,agree).apply();
    }


}
