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

final public class SteamAPIParser <T> extends AsyncTask<String, Void, T> {
    private Context context_;
    private Gson gson = new Gson();

    public interface AsyncResponse<T> {
        void processFinish(T output);
    }

    SteamAPIParser(Context context, AsyncResponse<T> delegate) {
        this.delegate = delegate;
        context_ = context;
    }

    public AsyncResponse delegate;

    @Override
    protected T doInBackground(String... urls) {
        T ret = null;
        try {
            //call our downloadAndParse function
            ret = downloadAndParse(urls[0]);
            //handle cancelling the download
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return ret;
        }
    }

    @Override
    protected void onPostExecute(T result) {
        delegate.processFinish(result);
    }

    private T downloadAndParse(String link) throws IOException {
        URL url = new URL(link);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
        // for JsonReader
        //JsonReader reader = new JsonReader(in);

        if (link.matches("(.*)GetAppList(.*)")) {
            return (T)gson.fromJson(in, GetAppList.class);
        } else if (link.matches("(.*)GetNewsForApp(.*)")) {
            return (T)gson.fromJson(in, GetNewsForApp.class);
        } else if (link.matches("(.*)GetGlobalAchievementPercentagesForApp(.*)")) {
            return (T)gson.fromJson(in, GetGlobalAchievementPercentagesForApp.class);
        } else if (link.matches("(.*)GetPlayerSummaries(.*)")) {
            return (T)gson.fromJson(in, GetPlayerSummaries.class);
        } else if (link.matches("(.*)GetFriendList(.*)")) {
            return (T)gson.fromJson(in, GetFriendList.class);
        } else if (link.matches("(.*)GetPlayerAchievements(.*)")) {
            return (T)gson.fromJson(in, GetPlayerAchievements.class);
        } else if (link.matches("(.*)GetUserStatsForGame(.*)")) {
            return (T)gson.fromJson(in, GetUserStatsForGame.class);
        } else if (link.matches("(.*)GetOwnedGames(.*)")) {
            return (T)gson.fromJson(in, GetOwnedGames.class);
        } else if (link.matches("(.*)GetRecentlyPlayedGames(.*)")) {
            return (T)gson.fromJson(in, GetRecentlyPlayedGames.class);
        } else if (link.matches("(.*)GetSchemaForGame(.*)")) {
            return (T)gson.fromJson(in, GetSchemaForGame.class);
        } else {
            Log.e("Internal Error", "Invalid Request");
            return null;
        }
    }

    void GetAppList() {
        String request = context_.getResources().getString(R.string.GetAppList);

        try {
            this.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void GetNewsForApp(Integer appid) {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetNewsForApp))
                .append("appid=").append(appid)
                .append("&count=7&maxlength=300&format=json").toString();

        try {
            this.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void GetGlobalAchievementPercentagesForApp(Integer gameid)  {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetGlobalAchievementPercentagesForApp))
                .append("gameid=").append(gameid)
                .append("&format=json").toString();

        try {
            this.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void GetPlayerSummaries(String...steamids)  {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetPlayerSummaries))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamids=").toString();
        // this interface can get multiple players info in one request
        for (int i = 0; i < steamids.length; i++) {
            request += new StringBuilder(steamids[i]).append(",");
        }

        try {
            this.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void GetFriendList(String steamid) {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetFriendList))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamid=").append(steamid).append("&relationship=friend").toString();

        try {
            this.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void GetPlayerAchievements(Integer appid, String steamid) {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetPlayerAchievements))
                .append("appid=").append(appid)
                .append("&key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamid=").append(steamid).toString();

        try {
            this.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void GetUserStatsForGame(Integer appid, String steamid) {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetUserStatsForGame))
                .append("appid=").append(appid)
                .append("&key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamid=").append(steamid).toString();

        try {
            this.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void GetOwnedGames(String steamid) {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetOwnedGames))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamid=").append(steamid)
                .append("&format=json").toString();

        try {
            this.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void GetRecentlyPlayedGames(String steamid) {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetRecentlyPlayedGames))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamid=").append(steamid)
                .append("&format=json").toString();

        try {
            this.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void GetSchemaForGame(Integer appid) {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetSchemaForGame))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&appid=").append(appid)
                .toString();

        try {
            this.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
