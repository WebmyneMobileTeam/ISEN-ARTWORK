package com.xitij.android.isen_artwork.helpers;

import android.content.Context;

import com.webmyne.android.jumpsum.model.Location;
import com.webmyne.android.jumpsum.model.User;

/**
 * Created by xitij on 17-03-2015.
 */
public class PrefUtils {

    public static void setCurrentUser(User currentUser, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "user_prefs", 0);
        complexPreferences.putObject("current_user", currentUser);
        complexPreferences.commit();
    }

    public static User getCurrentUser(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "user_prefs", 0);
        User currentUser = complexPreferences.getObject("current_user",User.class);
        return currentUser;

    }


    public static void setCurrentLocation(Location currentLocation, Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "user_prefs", 0);
        complexPreferences.putObject("current_location", currentLocation);
        complexPreferences.commit();
    }

    public static Location getCurrentLocation(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "user_prefs", 0);
        Location currentUser = complexPreferences.getObject("current_location",Location.class);
        return currentUser;

    }


    public static boolean isLoggedIn(Context ctx){
        boolean isLogged = false;
        isLogged=Prefs.with(ctx).getBoolean("isLoggedIn",false);
        return isLogged;
    }

    public static void setLoggedIn(Context ctx,boolean value){
        Prefs.with(ctx).save("isLoggedIn",value);

    }

}
