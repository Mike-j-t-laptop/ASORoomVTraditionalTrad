package aso.asoroomvtraditionaltrad;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class TrackDA {

    SQLiteDatabase db;

    /*
            `TrackId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
            `Name` TEXT NOT NULL,
            `AlbumId` INTEGER NOT NULL,
            `MediaTypeId` INTEGER NOT NULL,
            `GenreId` INTEGER NOT NULL,
            `Composer` TEXT,
            `Milliseconds` INTEGER NOT NULL,
            `Bytes` INTEGER,
            `UnitPrice` TEXT NOT NULL,
            FOREIGN KEY(`MediaTypeId`) REFERENCES `media_types`(`MediaTypeId`) ON UPDATE NO ACTION ON DELETE NO ACTION ,
            FOREIGN KEY(`GenreId`) REFERENCES `genres`(`GenreId`) ON UPDATE NO ACTION ON DELETE NO ACTION ,
            FOREIGN KEY(`AlbumId`) REFERENCES `albums`(`AlbumId`) ON UPDATE NO ACTION ON DELETE NO ACTION
     */

    public TrackDA(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertTrack(String name,
                            Long albumId, Long mediaTypeId, Long genreId,
                            String composer,
                            Long millisecs, Long bytes,
                            String unitPrice) {
        if (albumId == null || albumId < 1 ||
                mediaTypeId == null || mediaTypeId < 1 ||
                genreId == null || genreId < 1)
            return -1;
        ContentValues cv = new ContentValues();
        if (name != null && name.length() > 0) {
            cv.put(AllTables.Tracks.COL_NAME,name);
        }
        if (composer != null && composer.length() > 0) {
            cv.put(AllTables.Tracks.COL_COMPOSER,composer);
        }
        if (unitPrice != null && unitPrice.length() > 0) {
            cv.put(AllTables.Tracks.COl_UNITPRICE,unitPrice);
        }
        if (millisecs != null) {
            cv.put(AllTables.Tracks.COL_MILLISECONDS,millisecs);
        }
        if (bytes != null) {
            cv.put(AllTables.Tracks.COL_BYTES,bytes);
        }
        if (cv.size() < 1) return -1;
        cv.put(AllTables.Tracks.COL_ALBUMID,albumId);
        cv.put(AllTables.Tracks.COL_MEDIATYPEID,mediaTypeId);
        cv.put(AllTables.Tracks.COl_GENREID,genreId);
        return db.insert(AllTables.Tracks.TABLENAME,null,cv);
    }

    public long insertTrack(Track track) {
        if (track == null) return -1;
        return insertTrack(
                track.getmName(),
                track.getmAlbumIdReference(),
                track.getmMediaTypeIdReference(),
                track.getmGenreIdReference(),
                track.getmComposer(),track.getmMilliseconds(),track.getmBytes(),track.getmUnitPrice()
        );
    }

    public int updateTrackById(
            Long trackId,
            String newName,
            Long newAlbumId, Long newMediaTypeId, Long newGenreId,
            String newComposer, Long newMillisecs, Long newBytes, String newUnitPrice) {
        if (trackId == null || trackId < 1) return -1;
        ContentValues cv = new ContentValues();
        if (newName != null && newName.length() > 0) {
            cv.put(AllTables.Tracks.COL_NAME,newName);
        }
        if (newAlbumId != null && newAlbumId > 0) {
            cv.put(AllTables.Tracks.COL_ALBUMID,newAlbumId);
        }
        if (newMediaTypeId != null && newMediaTypeId > 0) {
            cv.put(AllTables.Tracks.COL_MEDIATYPEID,newMediaTypeId);
        }
        if (newGenreId != null && newGenreId > 0) {
            cv.put(AllTables.Tracks.COl_GENREID,newGenreId);
        }
        if (newComposer != null && newComposer.length() > 0) {
            cv.put(AllTables.Tracks.COL_COMPOSER,newComposer);
        }
        if (newMillisecs != null) {
            cv.put(AllTables.Tracks.COL_MILLISECONDS,newMillisecs);
        }
        if (newBytes != null) {
            cv.put(AllTables.Tracks.COL_BYTES,newBytes);
        }
        if (newUnitPrice != null && newUnitPrice.length() > 0) {
            cv.put(AllTables.Tracks.COl_UNITPRICE,newUnitPrice);
        }
        return db.update(
                AllTables.Tracks.TABLENAME,
                cv,
                AllTables.Tracks.COL_TRACKID + "=?",
                new String[]{String.valueOf(trackId)}
                );
    }

    public int deleteTrackById(long trackId) {
        return db.delete(
                AllTables.Tracks.TABLENAME,
                AllTables.Tracks.COL_TRACKID + "=?",
                new String[]{String.valueOf(trackId)}
        );
    }

    public Track getTrackById(long trackId) {
        Track rv = null;
        Cursor csr = db.query(
                AllTables.Tracks.TABLENAME,
                null,
                AllTables.Tracks.COL_TRACKID + "=?",
                new String[]{String.valueOf(trackId)},
                null,null,null
        );
        if (csr.moveToFirst()) {
            rv.setmTrackId(csr.getLong(csr.getColumnIndex(AllTables.Tracks.COL_TRACKID)));
            rv.setmName(csr.getString(csr.getColumnIndex(AllTables.Tracks.COL_NAME)));
            rv.setmAlbumIdReference(csr.getLong(csr.getColumnIndex(AllTables.Tracks.COL_ALBUMID)));
            rv.setmMediaTypeIdReference(csr.getLong(csr.getColumnIndex(AllTables.Tracks.COL_MEDIATYPEID)));
            rv.setmGenreIdReference(csr.getLong(csr.getColumnIndex(AllTables.Tracks.COl_GENREID)));
            rv.setmComposer(csr.getColumnName(csr.getColumnIndex(AllTables.Tracks.COL_COMPOSER)));
            rv.setmMilliseconds(csr.getLong(csr.getColumnIndex(AllTables.Tracks.COL_MILLISECONDS)));
            rv.setmBytes(csr.getLong(csr.getColumnIndex(AllTables.Tracks.COL_BYTES)));
            rv.setmUnitPrice(csr.getString(csr.getColumnIndex(AllTables.Tracks.COl_UNITPRICE)));
        }
        csr.close();
        return rv;
    }

    public long getRowCount() {
        return DatabaseUtils.queryNumEntries(db,AllTables.Tracks.TABLENAME);
    }
}
