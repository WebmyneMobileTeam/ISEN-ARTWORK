package com.xitij.android.isen_artwork.helpers;

import android.content.Context;


/**
 * Created by xitij on 17-03-2015.
 */
public class PrefUtils {




    public static boolean isLoggedIn(Context ctx){
        boolean isLogged = false;
        isLogged=Prefs.with(ctx).getBoolean("isLoggedIn",false);
        return isLogged;
    }

    public static void setLoggedIn(Context ctx,boolean value){
        Prefs.with(ctx).save("isLoggedIn",value);

    }

}
