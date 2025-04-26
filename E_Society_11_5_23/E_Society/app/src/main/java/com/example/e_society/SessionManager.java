package com.example.e_society;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE=0;
    private static final String PREF_NAME="filechanav";

    private static final String IS_LOGIN="isloggedin";

    public static final String CONTACT="contact";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=sharedPreferences.edit();
    }
    public void createloginsession(String mobile)
    {
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(CONTACT,mobile);
        editor.commit();
    }
    public void checkLogin()
    {
        if(!this.isloggedin())
        {
            Intent intent =new Intent(context,MemberLogin.class);
            //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);

            context.startActivity(intent);
        }
    }
    public HashMap<String,String> getUserDetails()
    {
        HashMap<String,String> user=new HashMap<String, String>();
        user.put(CONTACT,sharedPreferences.getString(CONTACT,null));
        return user;
    }
    public String getUserMobile()
    {
        String user="";
        user =sharedPreferences.getString(CONTACT,null);
        return user;
    }

    public boolean isloggedin()
    {
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }
    public void logoutuser()
    {
        editor.clear();
        editor.commit();
        Intent intent =new Intent(context,MemberLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

    }
}
