package aso.asoroomvtraditionaltrad;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class PlayListDA {

    SQLiteDatabase db;

    /*
            PlaylistId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
            `Name` TEXT
     */

    public PlayListDA(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertPlayList(String name) {
        if (name == null || name.length() < 1) return -1;
        ContentValues cv = new ContentValues();
        cv.put(AllTables.PlayLists.COl_NAME,name);
        return db.insert(
                AllTables.PlayLists.TABLENAME,
                null,
                cv
        );
    }

    public long insertPlayList(PlayList playList) {
        return insertPlayList(playList.getmName());
    }

    public int updatePlayListById(Long playListId, String name) {
        if (name == null || name.length() < 1) return -1;
        ContentValues cv = new ContentValues();
        cv.put(AllTables.PlayLists.COl_NAME,name);
        return db.update(
                AllTables.PlayLists.TABLENAME,
                cv,
                AllTables.PlayLists.COL_PLAYLISTID,
                new String[]{String.valueOf(playListId)}
        );
    }

    public int deletePlayListById(Long playListId) {
        if (playListId == null) return -1;
        return db.delete(
                AllTables.PlayLists.TABLENAME,
                AllTables.PlayLists.COL_PLAYLISTID,
                new String[]{String.valueOf(playListId)}
        );
    }

    public PlayList getPlayListById(Long playListId) {
        PlayList rv = null;
        Cursor csr = db.query(
                AllTables.PlayLists.TABLENAME,
                null,
                AllTables.PlayLists.COL_PLAYLISTID,
                new String[]{String.valueOf(playListId)},
                null,null,null
        );
        if (csr.moveToFirst()) {
            rv = new PlayList();
            rv.setmPlayListId(csr.getLong(csr.getColumnIndex(AllTables.PlayLists.COL_PLAYLISTID)));
            rv.setmName(csr.getString(csr.getColumnIndex(AllTables.PlayLists.COl_NAME)));
        }
        csr.close();
        return rv;
    }

    public long getRowCount() {
        return DatabaseUtils.queryNumEntries(db,AllTables.PlayLists.TABLENAME);
    }
}
