package com.xitij.android.isen_artwork.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Dhruvil on 11-07-2015.
 */
public class User {

    @SerializedName("loginStatArr")
    public LoginState loginState;
    @SerializedName("artworkTypeArr")
    public ArrayList<ArtworkType> artworkTypes;
    @SerializedName("sizeTypeIDArr")
    public ArrayList<StyleType> styleTypes;
    @SerializedName("mediumTypeArr")
    public ArrayList<MediumType> mediumTypes;

}
