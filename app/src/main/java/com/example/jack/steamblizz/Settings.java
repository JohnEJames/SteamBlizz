package com.example.jack.steamblizz;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jack.steamblizz.Models.GetPlayerSummaries;
import com.example.jack.steamblizz.PlayerDatabase;


import com.example.jack.steamblizz.Models.GetPlayerSummaries;

import java.util.Date;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final Activity activity = this;
        Button SteamID = (Button) findViewById(R.id.InsertSteamID);
        Button DeleteDB = (Button) findViewById(R.id.idRemoval);
        Button Appsettings = (Button) findViewById(R.id.ApplySettings);
        SteamID.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView steam = (TextView) findViewById(R.id.AddSteamID);
                String ID = steam.getText().toString();

                new SteamAPIParser(activity, new SteamAPIParser.AsyncResponse<GetPlayerSummaries>() {
                    @Override
                    public void processFinish(GetPlayerSummaries output) {
                        System.out.println(output);

                        PlayerDatabase pDatabase = new PlayerDatabase(getApplicationContext());
                        pDatabase.insertPlayer(output.response.players[0].steamid,
                                output.response.players[0].personaname,
                                output.response.players[0].profileurl,
                                output.response.players[0].avatarfull,
                                output.response.players[0].personastate,
                                output.response.players[0].lastlogoff,
                                output.response.players[0].loccountrycode,
                                output.response.players[0].timecreated);
                    }
                }).GetPlayerSummaries(ID);



            }
        });

        DeleteDB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView usertext = (TextView) findViewById(R.id.removeID);
                String usr = usertext.getText().toString();
                SQLiteDatabase DeleteDB = null;

                PlayerDatabase pDatabase = new PlayerDatabase(getApplicationContext());
                pDatabase.deletePlayer(usr);

                //  DeleteDB = SQLiteDatabase.openDatabase(PlayerDatabase, null, SQLiteDatabase.OPEN_READWRITE);

                //  DeleteDB.deletePlayer(DelId);


            }
        });

        Appsettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CheckBox appRST = (CheckBox) findViewById(R.id.AppReset);
                CheckBox dbRST = (CheckBox) findViewById(R.id.DatabaseReset);


                if (dbRST.isChecked()) {

                    PlayerDatabase pDatabase = new PlayerDatabase(getApplicationContext());
                    pDatabase.ResetTable();
                }


            }
        });


    }
}
