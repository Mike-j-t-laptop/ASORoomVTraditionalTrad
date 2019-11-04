package aso.asoroomvtraditionaltrad;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class AlbumsDA {

    private SQLiteDatabase db;

    public AlbumsDA(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertAlbum(String title, long artistId) {
        if (title == null || title.length() < 1 || artistId < 1) return -1;
        ContentValues cv = new ContentValues();
        cv.put(AllTables.Albums.COL_TITLE,title);
        cv.put(AllTables.Albums.COL_ARTISTID,artistId);
        return db.insert(AllTables.Albums.TABLENAME,null,cv);
    }

    public long insertAlbum(Album album) {
        return insertAlbum(album.mTitle,album.mArtistIdReference);
    }

    public int updateAlbumById(Long albumId,String title, Long artistId) {
        ContentValues cv = new ContentValues();
        if (title != null && title.length() > 0) {
            cv.put(AllTables.Albums.COL_TITLE,title);
        }
        if (artistId != null && artistId > 0) {
            cv.put(AllTables.Albums.COL_ARTISTID,artistId);
        }
        if (cv.size() < 1) return -1;
        return db.update(
                AllTables.Albums.TABLENAME,
                cv,
                AllTables.Albums.COL_ALBUMID + "=?",
                new String[]{String.valueOf(albumId)}
        );
    }

    public int updateAlbumTitleById(Long albumId, String title) {
        return updateAlbumById(albumId,title,null);
    }

    public int updateAlbumArtistById(Long albumId, Long artistId) {
        return updateAlbumById(albumId,null,artistId);
    }

    public int deleteAlbumById(Long albumId) {
        return db.delete(
                AllTables.Albums.TABLENAME,
                AllTables.Albums.COL_ALBUMID + "=?",
                new String[]{String.valueOf(albumId)}
        );
    }

    public Album getAlbumById(long albumId) {
        Album rv = null;
        Cursor csr = db.query(
                AllTables.Albums.TABLENAME,
                null,
                AllTables.Albums.COL_ALBUMID + "=?",
                new String[]{String.valueOf(albumId)},
                null ,null,null
        );
        if (csr.moveToFirst()) {
            rv = new Album();
            rv.setmAlbumId(csr.getLong(csr.getColumnIndex(AllTables.Albums.COL_ALBUMID)));
            rv.setmTitle(csr.getString(csr.getColumnIndex(AllTables.Albums.COL_TITLE)));
            rv.setmArtistIdReference(csr.getLong(csr.getColumnIndex(AllTables.Albums.COL_ARTISTID)));
        }
        csr.close();
        return rv;
    }

    public static final int SEARCHMODE_STARTSWITH = 0;
    public static final int SEARCHMODE_ENDSWITH = 1;
    public static final int SEARCHMODE_CONTAINS = 2;
    public static final int SEARCHMODE_MATCHES = 3;
    public long[] findAlbumIdsByTitle(String title,int searchMode) {
        long[] rv = new long[0];
        int rowCount;
        String searchArg = title;
        switch (searchMode) {
            case SEARCHMODE_STARTSWITH:
                searchArg = title + "%";
                break;
            case SEARCHMODE_ENDSWITH:
                searchArg = "%" + title;
                break;
            case SEARCHMODE_CONTAINS:
                searchArg = "%" + title + "%";
                break;
            case SEARCHMODE_MATCHES:
                break;
        }
        Cursor csr = db.query(
                AllTables.Albums.TABLENAME,
                new String[]{AllTables.Albums.COL_ARTISTID},
                AllTables.Albums.COL_TITLE + " LIKE ?",
                new String[]{searchArg},
                null,null,null
        );
        rowCount = csr.getCount();
        if ((rowCount) > 0) {
            rv = new long[rowCount];
            int ix = 0;
            while (csr.moveToNext()) {
                rv[ix++] = csr.getLong(csr.getColumnIndex(AllTables.Albums.COL_ARTISTID));
            }
        }
        return rv;
    }

    public int addTracksToNamedAlbum(TrackDA trackDA, String albumName, List<Track> tracksToAdd) {
        int rv = 0;
        if (albumName == null || albumName.length() < 1 || tracksToAdd == null || tracksToAdd.size() < 1) return rv;
        long[] albumIdList = findAlbumIdsByTitle(albumName,SEARCHMODE_MATCHES);
        if (albumIdList.length != 1) return rv;
        for (Long albumid: albumIdList) {
            for (Track t: tracksToAdd) {
                t.setmAlbumIdReference(albumid);
                if (trackDA.insertTrack(t) > 0) {
                    rv++;
                }
            }
        }
        return rv;
    }

    public long getRowCount() {
        return DatabaseUtils.queryNumEntries(db,AllTables.Albums.TABLENAME);
    }
}
