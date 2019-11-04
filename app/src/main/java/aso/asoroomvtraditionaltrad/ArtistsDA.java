package aso.asoroomvtraditionaltrad;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class ArtistsDA {

    private SQLiteDatabase db;

    public ArtistsDA(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertArtist(String name) {
        if (name == null && name.length() < 1) return -1;
        ContentValues cv = new ContentValues();
        cv.put(AllTables.Artists.COL_NAME,name);
        return db.insert(AllTables.Artists.TABLENAME,null,cv);
    }

    public long insertArtist(Artist artist) {
        return insertArtist(artist.getmName());
    }

    public int updateArtistById(Long artistid, String name) {
        if (name == null || name.length() < 1 || artistid == null) return -1;
        ContentValues cv = new ContentValues();
        cv.put(AllTables.Artists.COL_NAME,name);
        return db.update(
                AllTables.Artists.TABLENAME,
                cv,
                AllTables.Artists.COL_ARTISTID + "=?",
                new String[]{String.valueOf(artistid)}
                );
    }

    public int deleteArtistById(Long artistId) {
        return db.delete(AllTables.Artists.TABLENAME,
                AllTables.Artists.COL_ARTISTID + "=?",
                new String[]{String.valueOf(artistId)}
                );
    }

    public Artist getArtistById(Long artistId) {
        Artist rv = null;
        Cursor csr = db.query(
                AllTables.Artists.TABLENAME,
                null,
                AllTables.Artists.COL_ARTISTID + "=?",
                new String[]{String.valueOf(artistId)},
                null,null,null
        );
        if (csr.moveToFirst()) {
            rv = new Artist();
            rv.setmArtistid(csr.getLong(csr.getColumnIndex(AllTables.Artists.COL_ARTISTID)));
            rv.setmName(csr.getString(csr.getColumnIndex(AllTables.Artists.COL_NAME)));
        }
        csr.close();
        return rv;
    }

    public long getRowCount() {
        return DatabaseUtils.queryNumEntries(db,AllTables.Artists.TABLENAME);
    }
}
