package com.example.jack.steamblizz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.jack.steamblizz.Models.GetAppList;
import com.example.jack.steamblizz.Models.GetFriendList;
import com.example.jack.steamblizz.Models.GetGlobalAchievementPercentagesForApp;
import com.example.jack.steamblizz.Models.GetNewsForApp;
import com.example.jack.steamblizz.Models.GetOwnedGames;
import com.example.jack.steamblizz.Models.GetPlayerAchievements;
import com.example.jack.steamblizz.Models.GetPlayerSummaries;
import com.example.jack.steamblizz.Models.GetRecentlyPlayedGames;
import com.example.jack.steamblizz.Models.GetSchemaForGame;
import com.example.jack.steamblizz.Models.GetUserStatsForGame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class SteamDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steam_dashboard);

        SteamAPIParser steamAPIParser = new SteamAPIParser(this);

        GetAppList appList = steamAPIParser.GetAppList();
        GetNewsForApp newsForApp = steamAPIParser.GetNewsForApp(440);
        GetGlobalAchievementPercentagesForApp globalAchievementPercentagesForApp = steamAPIParser.GetGlobalAchievementPercentagesForApp(440);
        GetPlayerSummaries playerSummaries = steamAPIParser.GetPlayerSummaries("76561197960435530");
        GetFriendList friendList = steamAPIParser.GetFriendList("76561197960435530");
        GetPlayerAchievements playerAchievements = steamAPIParser.GetPlayerAchievements(440, "76561197960435530");
        GetUserStatsForGame userStatsForGame = steamAPIParser.GetUserStatsForGame(440, "76561197960435530");
        GetOwnedGames ownedGames = steamAPIParser.GetOwnedGames("76561197960435530");
        GetRecentlyPlayedGames recentlyPlayedGames = steamAPIParser.GetRecentlyPlayedGames("76561197960435530");
        GetSchemaForGame schemaForGame = steamAPIParser.GetSchemaForGame(440);

    }


}
