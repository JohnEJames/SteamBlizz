package com.example.jack.steamblizz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Hashtable;

public class SteamDasboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steam_dasboard);

        ListView listView = (ListView) findViewById(R.id.listAllGames);
        // listView.setAdapter(adapter);
        SteamAPIParser steamAPIParser = new SteamAPIParser(this, "");
        Hashtable<Integer, String> games = steamAPIParser.getSteamGameList();

        System.out.print(games);
    }
}
