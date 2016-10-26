package com.example.jack.steamblizz;

import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

/**
 * Created by Jack on 2016-10-26.
 */

final public class SteamAPIParser {
    Hashtable<Integer, String> SteamGameList = new Hashtable<Integer, String>(31933);
    Vector<Integer> OwnedGame = new Vector<Integer>();
    String steamid;

    SteamAPIParser(String steamid) {
        URL gameList = null;
        try {
            gameList = new URL("http://api.steampowered.com/ISteamApps/GetAppList/v2");
            new DownloadAndParseJSON().execute(gameList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void downloadAndParse(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
        JsonReader reader = new JsonReader(in);

        reader.beginObject();
        reader.nextName();
        reader.beginObject();
        reader.nextName();
        reader.beginArray();
        while (reader.hasNext()) {
            Integer appID = 0;
            String gameName = "";
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("appid")) {
                    appID = reader.nextInt();
                } else if (name.equals("name")) {
                    gameName = reader.nextString();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            SteamGameList.put(appID, gameName);
        }
        reader.endArray();
        reader.endObject();
        reader.endObject();
    }

    Hashtable<Integer, String> getSteamGameList() {
        return SteamGameList;
    }

    private class DownloadAndParseJSON extends AsyncTask<URL, Void, Void> {
        protected Void doInBackground(URL... urls) {
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
