package com.example.jack.steamblizz.Models;

/**
 * Created by dangk on 2016-11-05.
 */

public class GetFriendList {
    public FriendList friendslist;

    public class FriendList {
        public Friend[] friends;
    }

    public class Friend {
        public String steamid;
        public Long friend_since;
    }
}
