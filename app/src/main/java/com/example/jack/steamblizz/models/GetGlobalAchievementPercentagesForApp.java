package com.example.jack.steamblizz.Models;

/**
 * Created by dangk on 2016-11-05.
 */

public class GetGlobalAchievementPercentagesForApp {
    public AchievementPercentage achievementpercentages;

    public class AchievementPercentage {
        public Achievement[] achievements;
    }

    public class Achievement {
        String name;
        Double percent;
    }
}
