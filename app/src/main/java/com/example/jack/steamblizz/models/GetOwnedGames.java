package com.example.jack.steamblizz.Models;

/**
 * Created by dangk on 2016-11-05.
 */

public class GetOwnedGames {
    public Response response;

    public class Response {
        public Integer game_count;
        public Game[] games;
    }

    public class Game {
        public Integer appid;
        public Integer playtime_forever;
    }
}
