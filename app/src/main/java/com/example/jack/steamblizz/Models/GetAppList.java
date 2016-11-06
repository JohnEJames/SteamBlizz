package com.example.jack.steamblizz.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dangk on 2016-11-05.
 */

public class GetAppList {
    public  AppList applist;

    public class AppList {
        App[] apps;
    }

    public class App {
        Integer appid;
        String name;
    }
}
