package com.example.jack.steamblizz.Models;

/**
 * Created by dangk on 2016-11-05.
 */

public class GetNewsForApp {
    public AppNew appnews;

    public class AppNew {
        Integer appid;
        NewsItem[] newsitems;
    }

    public class NewsItem {
        String title;
        String url;
        String author;
        String contents;
        Long date;
    }
}
