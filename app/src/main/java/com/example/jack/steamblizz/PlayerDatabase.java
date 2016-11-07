package com.example.jack.steamblizz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public  class PlayerDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SteamDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ACCOUNT = "Players";

    //TABLE COLUMNS
    private static final String KEY_ID = "ID";
    private static final String KEY_USER_Name = "Name";
    private static final String KEY_PROFILE_URL = "ProfileURL";
    private static final String KEY_Avatar_FULL = "AvatarFuLL";
    private static final String KEY_PERSONAL_STATE = "PersonalState";
    private static final String KEY_LAST_LOG_OFF = "LastLogOFF";
    private static final String KEY_COUNTRY_CODE = "CountryCode";
    private static final String KEY_TIME_CREATED = "TimeCreated";

    public PlayerDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLAYERS_TABLE = "CREATE TABLE " + TABLE_ACCOUNT + " (" + KEY_ID + "TEXT PRIMARY KEY" +
                " , " + KEY_USER_Name + " TEXT" +
                " , " + KEY_PROFILE_URL + " TEXT" +
                " , " + KEY_Avatar_FULL + " TEXT" +
                " , " + KEY_PERSONAL_STATE + " INTEGER" +
                " , " + KEY_LAST_LOG_OFF + " INTEGER" +
                " , " + KEY_COUNTRY_CODE + "TEXT" +
                " , " + KEY_TIME_CREATED + "INTEGER)";
        db.execSQL(CREATE_PLAYERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop tables if exists" + TABLE_ACCOUNT);
        onCreate(db);
    }
    public void ResetTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Drop tables if exists" + TABLE_ACCOUNT);
        onCreate(db);
    }

    public boolean insertPlayer(String ID, String Name, String Profile, String Avatar, Integer state, Integer Lastondate, String Country, Integer CreateDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, ID);
        contentValues.put(KEY_USER_Name, Name);
        contentValues.put(KEY_PROFILE_URL, Profile);
        contentValues.put(KEY_Avatar_FULL, Avatar);
        contentValues.put(KEY_PERSONAL_STATE, state);
        contentValues.put(KEY_LAST_LOG_OFF, Lastondate);
        contentValues.put(KEY_COUNTRY_CODE, Country);
        contentValues.put(KEY_TIME_CREATED, CreateDate);
        db.insert(TABLE_ACCOUNT, null, contentValues);
        return true;
    }

    public Cursor getPlayer(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from player where id=" + id + "", null);
        return res;
    }

    public Boolean deletePlayer(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("Delete from " + TABLE_ACCOUNT + "Where ID=" + id);
        return true;
    }

    public boolean updatePlayer(String SteamID, String Name, String Profile, String Avatar, Integer state, Integer Lastondate, String Country, Integer CreateDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, SteamID);
        contentValues.put(KEY_USER_Name, Name);
        contentValues.put(KEY_PROFILE_URL, Profile);
        contentValues.put(KEY_Avatar_FULL, Avatar);
        contentValues.put(KEY_PERSONAL_STATE, state);
        contentValues.put(KEY_LAST_LOG_OFF, Lastondate);
        contentValues.put(KEY_COUNTRY_CODE, Country);
        contentValues.put(KEY_TIME_CREATED, CreateDate);
        db.insert(TABLE_ACCOUNT, null, contentValues);
        db.close();
        return true;
    }

    public String getPlayerID(String id) {
        if (!id.isEmpty()) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_ACCOUNT, new String[]{
                            KEY_ID, KEY_USER_Name, KEY_PROFILE_URL, KEY_Avatar_FULL, KEY_PERSONAL_STATE, KEY_LAST_LOG_OFF, KEY_COUNTRY_CODE, KEY_TIME_CREATED},
                    KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
            /* till we get player object written
               Player player = new Player();
                player.setID(cursor.getString(0));
                player.setProfile(cursor.getString(1));
                player.setAvatar(cursor.getString(2));
                player.setPeronsalSate(Integer.parseInt(cursor.getString(3)));
                player.setLastLogOff(Integer.parseInt(cursor.getString(4)));
                player.setCountryCode(cursor.getString(5));
                player.setTimeCreated(Integer.parseInt(cursor.getString(6)));

                return player
             */

        }else {
            Log.i("Devel", "INvalid ID provided for getPLayer method");
        }
        return id;
    }
}