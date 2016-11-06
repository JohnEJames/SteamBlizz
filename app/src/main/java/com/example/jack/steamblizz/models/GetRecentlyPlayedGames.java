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
        Integer appid;
        String name;
        Integer playtime_2weeks;
        Integer playtime_forever;
    }
}
