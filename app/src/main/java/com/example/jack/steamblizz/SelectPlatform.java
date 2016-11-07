package com.example.jack.steamblizz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class SelectPlatform extends AppCompatActivity {
    SharedPreferences mPrefs;
    final String welcomeScreenShownPref = "SetupScreenShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_platform);

        PlayerDatabase pDatabase = new PlayerDatabase(getApplicationContext());


       /* mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        Boolean welcomeScreenShow = mPrefs.getBoolean(welcomeScreenShownPref, false);


        if(!welcomeScreenShow){
            Intent setup = new Intent( getBaseContext(), SetUp.class );
            startActivity(setup);
        }

 */
        ImageButton Bnet = (ImageButton) findViewById(R.id.BattleNet);
        ImageButton Steam = (ImageButton) findViewById(R.id.Steam);

        Bnet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent BnetInent = new Intent(v.getContext(), BattleNetDashboard.class);
                startActivity(BnetInent);

            }
        });

        Steam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent steamIntent = new Intent(v.getContext(), SteamDashboard.class);
                startActivity(steamIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {
        // Handle item selection
        String help = "The main screen is to help navigate you towards helpful sites, and give you links to them";
        Context context = this;
        switch (menu.getItemId()) {
            case R.id.menu_main_settings:
                Intent settings = new Intent(this, Settings.class);
                startActivity(settings);
                return true;
            case R.id.about_main_about:
                Intent about = new Intent(this, About.class);
                startActivity(about);
                return true;
            default:
                return super.onOptionsItemSelected(menu);
        }
    }
}
