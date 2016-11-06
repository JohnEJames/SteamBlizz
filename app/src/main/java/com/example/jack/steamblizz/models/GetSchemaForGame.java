package com.example.jack.steamblizz.Models;

/**
 * Created by dangk on 2016-11-05.
 */

public class GetSchemaForGame {
    public Game game;

    public class Game {
        public String gameName;
        public AvailableGameStats availableGameStats;
    }

    public class AvailableGameStats {
        public Achievement[] achievements;
        public Stats[] stats;
    }

    public class Achievement {
        public String name;
        public String displayName;
        public String description;
        public String icon;
        public String icongray;
    }

    public class Stats {
        public String name;
        public String displayName;
    }
}
