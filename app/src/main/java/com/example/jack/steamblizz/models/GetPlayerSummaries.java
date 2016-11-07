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
        public String steamid;
        public Integer communityvisibilitystate;
        public String personaname;
        public Integer lastlogoff;
        public String profileurl;
        public String avatarfull;
        public Integer personastate;
        public String realname;
        public Integer timecreated;
        public String loccountrycode;
    }
}
