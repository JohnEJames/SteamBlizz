package com.example.jack.steamblizz.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jack on 2016-10-30.
 */

public class SteamAppList{

    @SerializedName("applist")
    public AppList list;

    public static class AppList{
        @SerializedName("apps")
        public SteamApp[] games;

    }
}