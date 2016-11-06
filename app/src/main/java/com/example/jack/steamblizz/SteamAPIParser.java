package com.example.jack.steamblizz;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

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
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

/**
 * Created by Jack on 2016-10-26.
 */

final public class SteamAPIParser {
    private Context context_;
    private GetAppList appList;
    private GetFriendList friendList;
    private GetGlobalAchievementPercentagesForApp globalAchievementPercentages;
    private GetNewsForApp newsForApp;
    private GetOwnedGames ownedGames;
    private GetPlayerAchievements playerAchievements;
    private GetPlayerSummaries playerSummaries;
    private GetRecentlyPlayedGames recentlyPlayedGames;
    private GetSchemaForGame schemaForGame;
    private GetUserStatsForGame userStatsForGame;
    private Gson gson = new Gson();

    public interface AsyncResponse<T> {
        T processFinish(T output);
    }

    SteamAPIParser(Context context) {

        context_ = context;
    }

    private class DownloadAndParseJSON<T> extends AsyncTask<String, Void, T> {
        public AsyncResponse delegate;

        public DownloadAndParseJSON(AsyncResponse delegate) {
            this.delegate = delegate;
        }

        @Override
        protected T doInBackground(String... urls) {
            try {
                //call our downloadAndParse function
                downloadAndParse(urls[0]);
                //handle cancelling the download
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(T result) {
            delegate.processFinish(result);
        }
    }

    private void downloadAndParse(String link) throws IOException {
        URL url = new URL(link);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
        // for JsonReader
        //JsonReader reader = new JsonReader(in);

        if (link.matches("(.*)GetAppList(.*)")) {
            appList = gson.fromJson(in, GetAppList.class);
        } else if (link.matches("(.*)GetNewsForApp(.*)")) {
            newsForApp = gson.fromJson(in, GetNewsForApp.class);
        } else if (link.matches("(.*)GetGlobalAchievementPercentagesForApp(.*)")) {
            globalAchievementPercentages = gson.fromJson(in, GetGlobalAchievementPercentagesForApp.class);
        } else if (link.matches("(.*)GetPlayerSummaries(.*)")) {
            playerSummaries = gson.fromJson(in, GetPlayerSummaries.class);
        } else if (link.matches("(.*)GetFriendList(.*)")) {
            friendList = gson.fromJson(in, GetFriendList.class);
        } else if (link.matches("(.*)GetPlayerAchievements(.*)")) {
            playerAchievements = gson.fromJson(in, GetPlayerAchievements.class);
        } else if (link.matches("(.*)GetUserStatsForGame(.*)")) {
            userStatsForGame = gson.fromJson(in, GetUserStatsForGame.class);
        } else if (link.matches("(.*)GetOwnedGames(.*)")) {
            ownedGames = gson.fromJson(in, GetOwnedGames.class);
        } else if (link.matches("(.*)GetRecentlyPlayedGames(.*)")) {
            recentlyPlayedGames = gson.fromJson(in, GetRecentlyPlayedGames.class);
        } else if (link.matches("(.*)GetSchemaForGame(.*)")) {
            schemaForGame = gson.fromJson(in, GetSchemaForGame.class);
        } else {
            Log.e("Internal Error", "Invalid Request");
        }
    }

    GetAppList GetAppList() {
        String request = context_.getResources().getString(R.string.GetAppList);

        try {
            new DownloadAndParseJSON<GetAppList>(new AsyncResponse<GetAppList>(){

                @Override
                public GetAppList processFinish(GetAppList output) {
                    return appList;
                }
            }).execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appList;
    }

    GetNewsForApp GetNewsForApp(Integer appid) {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetNewsForApp))
                .append("appid=").append(appid)
                .append("&count=7&maxlength=300&format=json").toString();

        try {
            new DownloadAndParseJSON().execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsForApp;
    }

    GetGlobalAchievementPercentagesForApp GetGlobalAchievementPercentagesForApp(Integer gameid)  {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetGlobalAchievementPercentagesForApp))
                .append("gameid=").append(gameid)
                .append("&format=json").toString();

        try {
            new DownloadAndParseJSON().execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return globalAchievementPercentages;
    }

    GetPlayerSummaries GetPlayerSummaries(String...steamids)  {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetPlayerSummaries))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamids=").toString();
        // this interface can get multiple players info in one request
        for (int i = 0; i < steamids.length; i++) {
            request += new StringBuilder(steamids[i]).append(",");
        }

        try {
            new DownloadAndParseJSON().execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playerSummaries;
    }

    GetFriendList GetFriendList(String steamid) {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetFriendList))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamid=").append(steamid).append("&relationship=friend").toString();

        try {
            new DownloadAndParseJSON().execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return friendList;
    }

    GetPlayerAchievements GetPlayerAchievements(Integer appid, String steamid) {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetPlayerAchievements))
                .append("appid=").append(appid)
                .append("&key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamid=").append(steamid).toString();

        try {
            new DownloadAndParseJSON().execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playerAchievements;
    }

    GetUserStatsForGame GetUserStatsForGame(Integer appid, String steamid) {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetUserStatsForGame))
                .append("appid=").append(appid)
                .append("&key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamid=").append(steamid).toString();

        try {
            new DownloadAndParseJSON().execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userStatsForGame;
    }

    GetOwnedGames GetOwnedGames(String steamid) {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetOwnedGames))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamid=").append(steamid)
                .append("&format=json").toString();

        try {
            new DownloadAndParseJSON().execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ownedGames;
    }

    GetRecentlyPlayedGames GetRecentlyPlayedGames(String steamid) {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetRecentlyPlayedGames))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamid=").append(steamid)
                .append("&format=json").toString();

        try {
            new DownloadAndParseJSON().execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recentlyPlayedGames;
    }

    GetSchemaForGame GetSchemaForGame(Integer appid) {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetSchemaForGame))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&appid=").append(appid)
                .toString();

        try {
            new DownloadAndParseJSON().execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schemaForGame;
    }
}
