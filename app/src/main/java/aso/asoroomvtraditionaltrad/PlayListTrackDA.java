package aso.asoroomvtraditionaltrad;

import android.content.ContentValues;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class PlayListTrackDA {

    SQLiteDatabase db;

    /*
            PlaylistId` INTEGER NOT NULL,
            `TrackId` INTEGER NOT NULL,
            PRIMARY KEY(`PlaylistId`, `TrackId`),
            FOREIGN KEY(`TrackId`) REFERENCES `tracks`(`TrackId`) ON UPDATE NO ACTION ON DELETE NO ACTION ,
            FOREIGN KEY(`PlaylistId`) REFERENCES `playlists`(`PlaylistId`) ON UPDATE NO ACTION ON DELETE NO ACTION
     */

    public PlayListTrackDA(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertPlayListTrack(Long playListId, Long trackId) {
        if (playListId == null || playListId < 1 || trackId == null || trackId < 1) return -1;
        ContentValues cv = new ContentValues();
        cv.put(AllTables.PlayListTracks.COL_PLAYLISTID,playListId);
        cv.put(AllTables.PlayListTracks.COL_TRACKID,trackId);
        return db.insert(
                AllTables.PlayListTracks.TABLENAME,null,cv
        );
    }

    public long insertPlayListTrack(PlayListTrack playListTrack) {
        return insertPlayListTrack(playListTrack.getmPlayListIdReference(),playListTrack.getmTrackIdReference());
    }

    public int updatePlayListTrack(Long playListId, Long trackId, Long newPlayListId, Long newTrackId) {

        if (playListId == null ||trackId == null || playListId < 1 || trackId < 1) return -1;
        if (newPlayListId == null && newTrackId == null) return -1;
        if (newPlayListId == playListId && newTrackId == trackId) return -1;
        ContentValues cv = new ContentValues();
        if (newPlayListId != null) {
            cv.put(AllTables.PlayListTracks.COL_PLAYLISTID, newPlayListId);
        }
        if (newTrackId != null) {
            cv.put(AllTables.PlayListTracks.COL_TRACKID, newTrackId);
        }
        if (cv.size() < 1) return -1;
        return db.update(
                AllTables.PlayListTracks.TABLENAME,
                cv,
                AllTables.PlayListTracks.COL_PLAYLISTID + "=? AND " +
                        AllTables.PlayListTracks.COL_TRACKID + "=?",
                new String[]{String.valueOf(playListId),String.valueOf(trackId)}
                );
    }

    public int deletePlayListTrack(Long playListId, Long trackId) {
        if (playListId == null || playListId < 1 || trackId == null || trackId < 1) return -1;
        return db.delete(
                AllTables.PlayListTracks.TABLENAME,
                AllTables.PlayListTracks.COL_PLAYLISTID + "=? AND " +
                        AllTables.PlayListTracks.COL_TRACKID + "=?",
                new String[]{String.valueOf(playListId),String.valueOf(trackId)}
        );
    }

    public long getRowCount() {
        return DatabaseUtils.queryNumEntries(db,AllTables.PlayListTracks.TABLENAME);
    }
}
