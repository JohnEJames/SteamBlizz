package com.example.jack.steamblizz.Models;

/**
 * Created by TDK on 2016-11-02.
 */

public class SteamDataClasses {
    class apps {
        Integer appid;
        String name;
    }

    class newsitems {
        String title;
        String url;
        String author;
        String contents;
        Long date;
    }

    class achievements {
        String name;
        Double percent;
        String apiname; // for GetPLayerAchievements
        Integer achieved;
        String displayName;
        String description;
        String icon;
        String icongray;
    }

    class players {
        String steamid;
        Integer communityvisibilitystate;
        String personaname;
        Long lastlogoff;
        String profileurl;
        String avatarfull;
        Integer personastate;
        String realname;
        Long timecreated;
        String loccountrycode;
    }

    class friends {
        String steamid;
        Long friend_since;
    }

    class stats {
        String name;
        Integer value;
    }

    class games {
        Integer appid;
        Integer playtime_forever;
        String name; // for GetRecentlyPlayedGames
        Integer playtime_2weeks;
    }
}
