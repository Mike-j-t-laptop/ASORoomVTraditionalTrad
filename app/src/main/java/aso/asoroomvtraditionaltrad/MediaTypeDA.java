package aso.asoroomvtraditionaltrad;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class MediaTypeDA {

    SQLiteDatabase db;

    /*
            MediaTypeId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
            `Name` TEXT
     */

    public MediaTypeDA(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertMediaType(String name) {
        if (name == null || name.length() < 1) return -1;
        ContentValues cv = new ContentValues();
        cv.put(AllTables.MediaTypes.COl_NAME,name);
        return db.insert(AllTables.MediaTypes.TABLENAME,null,cv);
    }

    public long insertMediaType(MediaType mediaType) {
        return insertMediaType(mediaType.getmName());
    }

    public int updateMediaTypeById(Long mediaTypeId, String name) {
        ContentValues cv = new ContentValues();
        if (name != null && name.length() > 0) {
            cv.put(AllTables.MediaTypes.COl_NAME,name);
        }
        if (cv.size() < 1) return -1;
        return db.update(
                AllTables.MediaTypes.TABLENAME,
                cv,
                AllTables.MediaTypes.COL_MEDIATYPEID + "=?",
                new String[]{String.valueOf(mediaTypeId)}
        );
    }

    public int deleteMediaTypeById(Long mediaTypeId) {
        if (mediaTypeId == null) return -1;
        return db.delete(
                AllTables.MediaTypes.TABLENAME,
                AllTables.MediaTypes.COL_MEDIATYPEID + "=?",
                new String[]{String.valueOf(mediaTypeId)}
                );
    }

    public MediaType getMediaTypeById(Long mediaTypeId) {
        MediaType rv = null;
        Cursor csr = db.query(
                AllTables.MediaTypes.TABLENAME,
                null,
                AllTables.MediaTypes.COL_MEDIATYPEID + "=?",
                new String[]{String.valueOf(mediaTypeId)},
                null,null,null
                );
        if (csr.moveToFirst()) {
            rv = new MediaType();
            rv.setmMediaTypeId(csr.getLong(csr.getColumnIndex(AllTables.MediaTypes.COL_MEDIATYPEID)));
            rv.setmName(csr.getString(csr.getColumnIndex(AllTables.MediaTypes.COl_NAME)));
        }
        csr.close();
        return rv;
    }

    public long getRowCount() {
        return DatabaseUtils.queryNumEntries(db,AllTables.MediaTypes.TABLENAME);
    }

}
