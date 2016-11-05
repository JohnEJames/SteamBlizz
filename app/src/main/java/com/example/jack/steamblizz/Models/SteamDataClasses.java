package com.example.jack.steamblizz.Models;

import java.util.Date;

/**
 * Created by TDK on 2016-11-02.
 */

public class SteamDataClasses {
    class applist {
        apps apps[];
    }

    class apps {
        Integer appid;
        String name;
    }

    class appnews {
        String appid;
        newsitems news[];
    }

    class newsitems {
        String title;
        String url;
        String author;
        String contents;
        Long date;
    }

    class achievementpercentages {
        achievements achievements[];
    }

    class achievements {
        String name;
        Double percent;
        String apiname; // for GetPLayerAchievements
        Boolean achieved;
        String displayName;
        String description;
        String icon;
        String icongray;
    }

    class response {
        players players[];
        Integer game_count; // for GetOwnedGames
        Integer total_count; // for GetRecentlyPlayedGames
        games games[];
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

    class friendslist {
        friends friends[];
    }

    class friends {
        String steamid;
        Long friend_since;
    }

    class playerstats {
        String steamID;
        String gameName;
        achievements achievements[];
        stats stats[];
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

    class game {
        String gameName;
        availableGameStats availableGameStats;
    }

    class availableGameStats {
        achievements achievements[];
        stats stats[];
    }
}
