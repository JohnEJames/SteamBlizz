package com.example.jack.steamblizz;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class BattleNetDashboard extends AppCompatActivity {
    static final String WOW = "0";
    static final String Diablo3 = "3";
    static final String SC2 = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_net_dashbored);

        ImageButton diabutton = (ImageButton) findViewById(R.id.D3img);
        ImageButton scbutton = (ImageButton) findViewById(R.id.SC2img);
        ImageButton wowbutton = (ImageButton) findViewById(R.id.WOWimg);

        wowbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gameIntent = new Intent(v.getContext(), BattleNetAPI.class);
                gameIntent.putExtra(WOW, true);

                startActivity(gameIntent);

            }
        });

        scbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gameIntent = new Intent(v.getContext(), BattleNetAPI.class);
                gameIntent.putExtra(SC2, true);
                startActivity(gameIntent);

            }
        });

        diabutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gameIntent = new Intent(v.getContext(), BattleNetAPI.class);
                gameIntent.putExtra(Diablo3, true);
                startActivity(gameIntent);

            }
        });
    }
}
