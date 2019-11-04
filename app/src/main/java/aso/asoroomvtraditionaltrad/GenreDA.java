package aso.asoroomvtraditionaltrad;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class GenreDA {

    SQLiteDatabase db;
    /*
        GenreId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `Name` TEXT
     */

    public GenreDA(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertGenre(String name) {
        if (name == null || name.length() < 1) return -1;
        ContentValues cv = new ContentValues();
        cv.put(AllTables.Genres.COL_NAME,name);
        return db.insert(AllTables.Genres.TABLENAME,null,cv);
    }

    public long insertGenre(Genre genre) {
        return insertGenre(genre.getmName());
    }

    public int updateGenreById(Long genreId, String name) {
        ContentValues cv = new ContentValues();
        if (name != null && name.length() > 0) {
            cv.put(AllTables.Genres.COL_NAME,name);
        }
        if (cv.size() < 1) return -1;
        return db.update(
                AllTables.Genres.TABLENAME,
                cv,
                AllTables.Genres.COL_GENREID + "=?",
                new String[]{String.valueOf(genreId)}
                );
    }

    public int deleteGenreById(Long genreId) {
        return db.delete(
                AllTables.Genres.TABLENAME,
                AllTables.Genres.COL_GENREID + "=?",
                new String[]{String.valueOf(genreId)}
                );
    }

    public Genre getGenreById(Long genreId) {
        Genre rv = null;
        Cursor csr = db.query(AllTables.Genres.TABLENAME,
                null,
                AllTables.Genres.COL_GENREID + "=?",
                new String[]{String.valueOf(genreId)},
                null,null,null);
        if (csr.moveToFirst()) {
            rv.setmGenreId(csr.getLong(csr.getColumnIndex(AllTables.Genres.COL_GENREID)));
            rv.setmName(csr.getString(csr.getColumnIndex(AllTables.Genres.COL_NAME)));
        }
        csr.close();
        return rv;
    }

    public long getRowCount() {
        return DatabaseUtils.queryNumEntries(db,AllTables.Genres.TABLENAME);
    }
}
