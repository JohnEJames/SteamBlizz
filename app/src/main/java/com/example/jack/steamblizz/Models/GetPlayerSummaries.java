package com.example.jack.steamblizz.Models;

/**
 * Created by dangk on 2016-11-05.
 */

public class GetPlayerSummaries {
    public Response response;

    public class Response {
        public Player[] players;
    }

    public class Player {
        String steamid;
        Integer communityvisibilitystate;
        String personaname;
        Long lastlogoff;
        String profileurl;
        String avatarfull;
        Integer personastate;
        String realname;
        Long timecreated;
        String loccountrycode;
    }
}
