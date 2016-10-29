package com.example.jack.steamblizz;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;

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
    Context context_;
    Hashtable<Integer, String> SteamGameList = new Hashtable<Integer, String>(31933);
    Vector<Integer> OwnedGame = new Vector<Integer>();
    String steamid_;

    SteamAPIParser(Context context, String steamid) {
        context_ = context;
        steamid_ = steamid;
        String gameList = context_.getResources().getString(R.string.GetAppList);
        try {
            new DownloadAndParseJSON().execute(gameList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void downloadAndParse(String link) throws IOException {

        URL url = new URL(link);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
        JsonReader reader = new JsonReader(in);

        if (link.matches("(.*)GetAppList(.*)")) {
            GetAppList(reader);
        } else if (link.matches("(.*)GetNewsForApp(.*)")) {
            GetNewsForApp(reader);
        } else if (link.matches("(.*)GetGlobalAchievementPercentagesForApp(.*)")) {
            GetGlobalAchievementPercentagesForApp(reader);
        } else if (link.matches("(.*)GetPlayerSummaries(.*)")) {
            GetPlayerSummaries(reader);
        } else if (link.matches("(.*)GetFriendList(.*)")) {
            GetFriendList(reader);
        } else if (link.matches("(.*)GetPlayerAchievements(.*)")) {
            GetPlayerAchievements(reader);
        } else if (link.matches("(.*)GetUserStatsForGame(.*)")) {
            GetUserStatsForGame(reader);
        } else if (link.matches("(.*)GetOwnedGames(.*)")) {
            GetOwnedGames(reader);
        } else if (link.matches("(.*)GetRecentlyPlayedGames(.*)")) {
            GetRecentlyPlayedGames(reader);
        } else if (link.matches("(.*)GetSchemaForGame(.*)")) {
            GetSchemaForGame(reader);
        }
    }


    Hashtable<Integer, String> getSteamGameList() {
        return SteamGameList;
    }

    void GetNewsForApp(String appid) throws IOException {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetNewsForApp))
                .append("appid=").append(appid)
                .append("&count=7&maxlength=300&format=json").toString();
        downloadAndParse(request);
    }

    void GetGlobalAchievementPercentagesForApp(String gameid)  throws IOException {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetGlobalAchievementPercentagesForApp))
                .append("gameid=").append(gameid)
                .append("&format=xml").toString();
        downloadAndParse(request);
    }

    void GetPlayerSummaries(String...steamids)  throws IOException {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetPlayerSummaries))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamids=").toString();
        // this interface can get multiple players info in one request
        for (int i = 0; i < steamids.length; i++) {
            request += new StringBuilder(steamids[i]).append(",");
        }
        downloadAndParse(request);
    }

    void GetFriendList(String steamid) throws IOException {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetFriendList))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("steamid=").append(steamid).append("&relationship=friend").toString();
        downloadAndParse(request);
    }

    void GetPlayerAchievements(String appid, String steamid) throws IOException {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetPlayerAchievements))
                .append("appid=").append(appid)
                .append("&key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamid=").append(steamid).toString();
        downloadAndParse(request);
    }

    void GetUserStatsForGame(String appid, String steamid) throws IOException {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetUserStatsForGame))
                .append("appid=").append(appid)
                .append("&key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamid=").append(steamid).toString();
        downloadAndParse(request);
    }

    void GetOwnedGames(String steamid) throws IOException {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetOwnedGames))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamid=").append(steamid)
                .append("&format=json").toString();
        downloadAndParse(request);
    }

    void GetRecentlyPlayedGames(String steamid) throws IOException {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetRecentlyPlayedGames))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&steamid=").append(steamid)
                .append("&format=json").toString();
        downloadAndParse(request);
    }

    void GetSchemaForGame(String appid) throws IOException {
        String request = new StringBuilder(context_.getResources().getString(R.string.GetSchemaForGame))
                .append("key=").append(context_.getResources().getString(R.string.steam_key))
                .append("&appid=").append(appid)
                .toString();
        downloadAndParse(request);
    }

    void GetAppList(JsonReader reader) throws IOException {
        reader.beginObject(); // {
        reader.nextName(); // "applist":
        reader.beginObject(); // {
        reader.nextName(); // "apps":
        reader.beginArray(); // [
        while (reader.hasNext()) { // loop until end of array
            Integer appID = 0;
            String gameName = "";
            reader.beginObject(); // {
            while (reader.hasNext()) { // loop until end of object
                String name = reader.nextName();
                if (name.equals("appid")) {
                    appID = reader.nextInt();
                } else if (name.equals("name")) {
                    gameName = reader.nextString();
                } else {
                    reader.skipValue(); // not needed property
                }
            }
            reader.endObject(); // }
            // TODO this is where to insert the entry into the database
            SteamGameList.put(appID, gameName); // TODO TEMP
        }
        reader.endArray(); // ]
        reader.endObject(); // }
        reader.endObject(); // }
    }

    void GetNewsForApp(JsonReader reader) throws IOException {
        String gid;
        String title;
        String url;
        String author;
        String content;
        Date date;

        reader.beginObject(); // {
        reader.nextName(); // "appnews":
        reader.beginObject(); // {
        reader.skipValue(); // "appid" : 9999,
        reader.nextName(); // "newsitem":
        reader.beginArray(); // [
        while (reader.hasNext()) { // loop until end of array
            reader.beginObject(); // {
            while (reader.hasNext()) { // loop until end of object
                String name = reader.nextName();
                if (name.equals("gid")) {
                    gid = reader.nextString();
                } else if (name.equals("title")) {
                    title = reader.nextString();
                } else if (name.equals("url")) {
                    url = reader.nextString();
                } else if (name.equals("author")) {
                    author = reader.nextString();
                } else if (name.equals("content")) {
                    content = reader.nextString();
                } else if (name.equals("date")) {
                    date = new Date(reader.nextLong());
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject(); // }
            // TODO this is where to insert the entry into the database
        }
        reader.endArray(); // ]
        reader.endObject(); // }
        reader.endObject(); // }
    }

    void GetGlobalAchievementPercentagesForApp(JsonReader reader) throws IOException {
        String achie;
        Double percent;

        reader.beginObject(); // {
        reader.nextName(); // "achievementpercentages":
        reader.beginObject(); // {
        reader.nextName(); // "achievements":
        reader.beginArray(); // [
        while (reader.hasNext()) { // loop until end of array
            reader.beginObject(); // {
            while (reader.hasNext()) { // loop until end of object
                String name = reader.nextName();
                if (name.equals("name")) {
                    achie = reader.nextString();
                } else if (name.equals("percent")) {
                    percent = reader.nextDouble();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject(); // }
            // TODO this is where to insert the entry into the database
        }
        reader.endArray(); // ]
        reader.endObject(); // }
        reader.endObject(); // }
    }

    void GetPlayerSummaries(JsonReader reader) throws IOException {
        String steamid;
        String personaName;
        Date lastLogOff;
        String profileURL;
        String avatarFull;
        Integer personaState;
        String realName;
        Date timeCreated;
        String countryCode;

        reader.beginObject(); // {
        reader.nextName(); // "response":
        reader.beginObject(); // {
        reader.nextName(); // "players":
        reader.beginArray(); // {
        while (reader.hasNext()) { //loop until end of array
            reader.beginObject(); // {
            while (reader.hasNext()) { // loop until end of object
                String name = reader.nextName();
                if (name.equals("steamid")) {
                    steamid = reader.nextString();
                } else if (name.equals("personaName")) {
                    personaName = reader.nextString();
                } else if (name.equals("lastlogoff")) {
                    lastLogOff = new Date(reader.nextLong());
                } else if (name.equals("profileurl")) {
                    profileURL = reader.nextString();
                } else if (name.equals("avatarfull")) {
                    avatarFull = reader.nextString();
                } else if (name.equals("personastate")) {
                    personaState = reader.nextInt();
                } else if (name.equals("realname")) {
                    realName = reader.nextString();
                } else if (name.equals("timecreated")) {
                    timeCreated = new Date(reader.nextLong());
                } else if (name.equals("loccountrycode")) {
                    countryCode = reader.nextString();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject(); // }
            // TODO this is where to insert the entry into the database
        }
        reader.endArray(); // ]
        reader.endObject(); // }
        reader.endObject(); // }
    }

    void GetFriendList(JsonReader reader) throws IOException {
        String steamid;
        Date since;

        reader.beginObject(); // {
        reader.nextName(); // "friendslist":
        reader.beginObject(); // {
        reader.nextName(); // "friends":
        reader.beginArray(); // [
        while (reader.hasNext()) { // loop until end of array
            reader.beginObject(); // {
            while (reader.hasNext()) { // loop until end of object
                String name = reader.nextName();
                if (name.equals("steamid")) {
                    steamid = reader.nextString();
                } else if (name.equals("friend_since")) {
                    since = new Date(reader.nextLong());
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject(); // }
            // TODO this is where to insert the entry into the database
        }
        reader.endArray(); // ]
        reader.endObject(); // }
        reader.endObject(); // }
    }

    void GetPlayerAchievements(JsonReader reader) throws IOException {

    }

    void GetUserStatsForGame(JsonReader reader) throws IOException {

    }

    void GetOwnedGames(JsonReader reader) throws IOException {

    }

    void GetRecentlyPlayedGames(JsonReader reader) throws IOException {

    }

    void GetSchemaForGame(JsonReader reader) throws IOException {

    }

    private class DownloadAndParseJSON extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... urls) {
            try {
                //call our downloadAndParse function
                downloadAndParse(urls[0]);
                //handle cancelling the download
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
