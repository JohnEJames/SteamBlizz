package com.example.jack.steamblizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SelectPlatform extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_platform);

        ImageButton Bnet = (ImageButton) findViewById(R.id.BattleNet);
        ImageButton Steam = (ImageButton) findViewById(R.id.Steam);

        Bnet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent blizzardIntent = new Intent(v.getContext(), BattleNetDashboard.class);
                startActivity(blizzardIntent);

            }
        });

        Steam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent steamIntent = new Intent(v.getContext(), SteamDashboard.class);
                startActivity(steamIntent);
            }
        });
    }
}
