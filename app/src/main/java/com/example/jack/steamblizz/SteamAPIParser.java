package com.example.jack.steamblizz;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jack.steamblizz.models.SteamAppList;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

/**
 * Created by Jack on 2016-10-26.
 */

final public class SteamAPIParser {
   HashMap<Integer, String> SteamGamesList;
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

        Gson gson = new Gson();
        SteamAppList list = gson.fromJson(in, SteamAppList.class);
        //list.list.games[0].name
    }

    HashMap<Integer, String> getSteamGameList() {
        return SteamGamesList;
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

