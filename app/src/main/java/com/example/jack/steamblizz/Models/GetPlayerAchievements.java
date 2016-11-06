package com.example.jack.steamblizz.Models;

/**
 * Created by dangk on 2016-11-05.
 */

public class GetPlayerAchievements {
    public PlayerStats playerstats;

    public class PlayerStats {
        public String steamID;
        public String gameName;
        public Achievement[] achievements;
    }

    public class Achievement {
        String apiname;
        Integer achieved;
    }
}
