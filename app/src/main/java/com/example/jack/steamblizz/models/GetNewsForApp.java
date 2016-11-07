package com.example.jack.steamblizz.Models;

/**
 * Created by dangk on 2016-11-05.
 */

public class GetNewsForApp {
    public AppNew appnews;

    public class AppNew {
        public Integer appid;
        public NewsItem[] newsitems;
    }

    public class NewsItem {
        public String title;
        public String url;
        public String author;
        public String contents;
        public Long date;
    }
}
