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


        new SteamAPIParser(this, new SteamAPIParser.AsyncResponse<GetAppList>() {
            @Override
            public void processFinish(GetAppList output) {
                System.out.println(output);
            }
        }).GetAppList();

        new SteamAPIParser(this, new SteamAPIParser.AsyncResponse<GetNewsForApp>() {
            @Override
            public void processFinish(GetNewsForApp output) {
                System.out.println(output);
            }
        }).GetNewsForApp(440);

        new SteamAPIParser(this, new SteamAPIParser.AsyncResponse<GetGlobalAchievementPercentagesForApp>() {
            @Override
            public void processFinish(GetGlobalAchievementPercentagesForApp output) {
                System.out.println(output);
            }
        }).GetGlobalAchievementPercentagesForApp(440);

        new SteamAPIParser(this, new SteamAPIParser.AsyncResponse<GetPlayerSummaries>() {
            @Override
            public void processFinish(GetPlayerSummaries output) {
                System.out.println(output);
            }
        }).GetPlayerSummaries("76561197960435530");

        new SteamAPIParser(this, new SteamAPIParser.AsyncResponse<GetFriendList>() {
            @Override
            public void processFinish(GetFriendList output) {
                System.out.println(output);
            }
        }).GetFriendList("76561197960435530");

        new SteamAPIParser(this, new SteamAPIParser.AsyncResponse<GetPlayerAchievements>() {
            @Override
            public void processFinish(GetPlayerAchievements output) {
                System.out.println(output);
            }
        }).GetPlayerAchievements(440, "76561197960435530");

        new SteamAPIParser(this, new SteamAPIParser.AsyncResponse<GetUserStatsForGame>() {
            @Override
            public void processFinish(GetUserStatsForGame output) {
                System.out.println(output);
            }
        }).GetUserStatsForGame(440, "76561197960435530");

        new SteamAPIParser(this, new SteamAPIParser.AsyncResponse<GetOwnedGames>() {
            @Override
            public void processFinish(GetOwnedGames output) {
                System.out.println(output);
            }
        }).GetOwnedGames("76561197960435530");

        new SteamAPIParser(this, new SteamAPIParser.AsyncResponse<GetRecentlyPlayedGames>() {
            @Override
            public void processFinish(GetRecentlyPlayedGames output) {
                System.out.println(output);
            }
        }).GetRecentlyPlayedGames("76561197960435530");

        new SteamAPIParser(this, new SteamAPIParser.AsyncResponse<GetSchemaForGame>() {
            @Override
            public void processFinish(GetSchemaForGame output) {
                System.out.println(output);
            }
        }).GetSchemaForGame(440);
    }


}
