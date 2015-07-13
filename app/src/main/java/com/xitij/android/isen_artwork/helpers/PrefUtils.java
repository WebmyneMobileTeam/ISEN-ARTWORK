package com.xitij.android.isen_artwork.helpers;

import android.content.Context;

import com.xitij.android.isen_artwork.model.User;


/**
 * Created by xitij on 17-03-2015.
 */
public class PrefUtils {


    public static boolean isLoggedIn(Context ctx){
        boolean isLogged = false;
        isLogged=Prefs.with(ctx).getBoolean("logged",false);
        return isLogged;
    }

    public static void setUser(Context _context,User user){

        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(_context, "user_pref", 0);
        complexPreferences.putObject("current_user",user);
        complexPreferences.commit();

    }

    public static User getCurrentUser(Context _context){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(_context, "user_pref", 0);
        User user = complexPreferences.getObject("current_user",User.class);
        return user;
    }

    public static void setLogin(Context _context,boolean value){

        Prefs prefs = Prefs.with(_context);
        prefs.save("logged",value);


    }






}
