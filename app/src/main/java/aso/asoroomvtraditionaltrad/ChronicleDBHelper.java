package aso.asoroomvtraditionaltrad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ChronicleDBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "chronicle";
    public static final int DBVERSION = 1;

    public static final String CHRONICLE_TABLENAME = "chronicle";
    public static final String CHRONICLE_COL_TIMESTAMP = "timestamp";
    public static final String CHRONICLE_COL_EVENT = "event";
    public static final String CHRONICLE_COL_RUNNUMBER = "run_number";
    private static final String CHRONICLE_TABLECREATE_SQL = "CREATE TABLE IF NOT EXISTS " + CHRONICLE_TABLENAME + "(" +
            CHRONICLE_COL_TIMESTAMP + " INTEGER PRIMARY KEY, " +
            CHRONICLE_COL_EVENT + " TEXT," +
            CHRONICLE_COL_RUNNUMBER + " INTEGER " +
            ")";

    private static ChronicleDBHelper instance = null;
    private static SQLiteDatabase db;


    private ChronicleDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
        db = this.getWritableDatabase();
    }

    public synchronized static ChronicleDBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new ChronicleDBHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CHRONICLE_TABLECREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertChronicleEntry(String event, long runnumber) {
        ContentValues cv = new ContentValues();
        cv.put(CHRONICLE_COL_EVENT,event);
        cv.put(CHRONICLE_COL_TIMESTAMP,System.currentTimeMillis());
        cv.put(CHRONICLE_COL_RUNNUMBER,runnumber);
        return db.insert(CHRONICLE_TABLENAME,null,cv);
    }

    public long getLastRunNumber() {
        long rv = 0;
        Cursor csr = db.query(
                CHRONICLE_TABLENAME,new String[]{"max(" + CHRONICLE_COL_RUNNUMBER +
                        ") AS " + CHRONICLE_COL_RUNNUMBER},null,null,null,null,null);
        if (csr.moveToFirst()) {
            rv = csr.getLong(csr.getColumnIndex(CHRONICLE_COL_RUNNUMBER));
        }
        csr.close();
        return rv;
    }

    private void logChronicle(long runnumber) {

        String whereclause = " WHERE " + CHRONICLE_COL_RUNNUMBER + " = ?";
        String[] whereargs = new String[]{String.valueOf(runnumber)};

        String column_elapsed = "elapsed", totElapsedHeading = "TOTAL ELAPSED";
        String tablealias = "c";
        String ctename = "cte", cte2name = "cte2";

        String rq = "WITH " + ctename + " AS " +
                "(" +
                "SELECT * FROM " + CHRONICLE_TABLENAME +
                whereclause +
                " ORDER BY " + CHRONICLE_COL_TIMESTAMP + " ASC " +
                "), " +
                " " +
                cte2name +
                " AS (SELECT " + runnumber + ")" +

                "SELECT datetime(" + CHRONICLE_COL_TIMESTAMP + "/1000,'unixepoch')||'.'||(" + CHRONICLE_COL_TIMESTAMP + " % 1000) AS " + CHRONICLE_COL_TIMESTAMP + "," +
                "coalesce(" + CHRONICLE_COL_TIMESTAMP + " - (SELECT max(" + CHRONICLE_COL_TIMESTAMP + ") " +
                "FROM cte WHERE " + CHRONICLE_COL_TIMESTAMP + " < " + tablealias + "." + CHRONICLE_COL_TIMESTAMP + "),0) AS " + column_elapsed + "," +
                CHRONICLE_COL_EVENT + ", " +
                CHRONICLE_COL_TIMESTAMP + " AS TSSORT " +
                " FROM cte AS " + tablealias + " " +
                "UNION SELECT " +
                "'TOTAL ELAPSED', " +
                "(SELECT max(" + CHRONICLE_COL_TIMESTAMP + ") FROM " + ctename + ") - (SELECT min(" + CHRONICLE_COL_TIMESTAMP + ") " +
                "FROM " + ctename +
                "), " +
                "'Run Number = ' || + (SELECT * FROM " +
                cte2name +
                "), " +
                "99999999999999 AS TSSORT" +
                " ORDER BY TSSORT ASC" +
                ";"
                ;

        Cursor csr = db.rawQuery(rq,whereargs);
        while (csr.moveToNext()) {
            String message = csr.getString(csr.getColumnIndex(CHRONICLE_COL_TIMESTAMP)) +
                    " " + column_elapsed + " = " + csr.getString(csr.getColumnIndex(column_elapsed)) +
                    " " + CHRONICLE_COL_EVENT + " = " + csr.getString(csr.getColumnIndex(CHRONICLE_COL_EVENT));

            if (csr.getString(csr.getColumnIndex(CHRONICLE_COL_TIMESTAMP)).equals(totElapsedHeading)) {
                message = totElapsedHeading + " for " +csr.getString(csr.getColumnIndex(CHRONICLE_COL_EVENT)) + " was " + csr.getString(csr.getColumnIndex(column_elapsed));
            }
            Log.d("TIMINGINFO",message);
        }
        csr.close();
    }

    public void logAllRuns() {
        Cursor csr = db.query(CHRONICLE_TABLENAME,new String[]{CHRONICLE_COL_RUNNUMBER},null,null,CHRONICLE_COL_RUNNUMBER,null,CHRONICLE_COL_RUNNUMBER + " ASC");
        while (csr.moveToNext()) {
            logChronicle(csr.getLong(csr.getColumnIndex(CHRONICLE_COL_RUNNUMBER)));
        }
        csr.close();
    }

    public void logAllRunStats() {
        String column_sum = "sum", column_started = "started", column_finished = "finished";

        /*
            WITH c1 AS(SELECT max(timestamp) - min(timestamp) AS sum, run_number, datetime(min(timestamp)/1000,'unixepoch') AS started, datetime(max(timestamp)/1000,'unixepoch') AS finished FROM Chronicle as c1 GROUP BY run_number ORDER BY timestamp)
            SELECT * FROM c1 UNION ALL SELECT sum(sum) AS total,'ALL', min(started) AS first, max(finished) as last FROM c1;
         */

        String sql = "WITH c1 AS(" +
                "SELECT " +
                "max(" + CHRONICLE_COL_TIMESTAMP +
                ") - min(" +
                CHRONICLE_COL_TIMESTAMP +
                ") AS " + column_sum + ", " +
                CHRONICLE_COL_RUNNUMBER + ", " +
                "datetime(min(" + CHRONICLE_COL_TIMESTAMP + ")/1000,'unixepoch') AS " + column_started + ", " +
                "datetime(max(" + CHRONICLE_COL_TIMESTAMP + ")/1000,'unixepoch') AS " + column_finished + " " +
                "FROM " +
                CHRONICLE_TABLENAME +
                " AS c1 GROUP BY " +
                CHRONICLE_COL_RUNNUMBER +
                " ORDER BY " +
                CHRONICLE_COL_TIMESTAMP +
                ")" +
                "SELECT * FROM c1 UNION ALL SELECT sum(" + column_sum +
                ") AS total," +
                "'ALL', " +
                "min(started) AS first, " +
                "max(finished) AS last " +
                "FROM c1;";
        Cursor csr = db.rawQuery(sql,null);
        while (csr.moveToNext()) {

            Log.d(
                    "CHRONICLESTATS",
                    "Run " + csr.getString(csr.getColumnIndex(CHRONICLE_COL_RUNNUMBER)) +
                            " From " + csr.getString(csr.getColumnIndex(column_started)) +
                            " To " + csr.getString(csr.getColumnIndex(column_finished)) +
                            " Elapsed = " + csr.getString(csr.getColumnIndex(column_sum))

            );
        }
    }
}
