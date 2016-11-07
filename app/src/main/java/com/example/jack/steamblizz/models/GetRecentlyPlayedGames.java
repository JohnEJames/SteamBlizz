package com.example.jack.steamblizz.Models;

/**
 * Created by dangk on 2016-11-05.
 */

public class GetRecentlyPlayedGames {
    public Response response;

    public class Response {
        public Integer total_count;
        public Game[] games;
    }

    public class Game {
        public Integer appid;
        public String name;
        public Integer playtime_2weeks;
        public Integer playtime_forever;
    }
}
