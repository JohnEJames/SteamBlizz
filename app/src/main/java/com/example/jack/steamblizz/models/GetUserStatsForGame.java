package com.example.jack.steamblizz.Models;

/**
 * Created by dangk on 2016-11-05.
 */

public class GetUserStatsForGame {
    public PlayerStats playerstats;

    public class PlayerStats {
        public String steamID;
        public String gameName;
        public Stats[] stats;
    }

    public class Stats {
        String name;
        Integer value;
    }
}
