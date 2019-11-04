package aso.asoroomvtraditionaltrad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase mDB;
    private Context context;
    private ChronicleDBHelper chronicleDBHelper;
    private long runnumber;
    private boolean onCreateCalled = false;
    private static DBHelper instance = null;
    public AlbumsDA albumsDA;
    public ArtistsDA artistsDA;
    public CustomerDA customerDA;
    public EmployeeDA employeeDA;
    public GenreDA genreDA;
    public InvoiceDA invoiceDA;
    public InvoiceItemDA invoiceItemDA;
    public MediaTypeDA mediaTypeDa;
    public PlayListDA playListDA;
    public PlayListTrackDA playListTrackDA;
    public TrackDA trackDA;

    private DBHelper(@Nullable Context context) {
        super(context,AllTables.DBNAME, null, 1);
        this.context = context;
        this.chronicleDBHelper = ChronicleDBHelper.getInstance(context);
        this.runnumber = chronicleDBHelper.getLastRunNumber();
        LogTime.logIt("FORCE OPEN",chronicleDBHelper,runnumber);
        mDB = this.getWritableDatabase(); // Force Open
        albumsDA = new AlbumsDA(mDB);
        artistsDA = new ArtistsDA(mDB);
        customerDA = new CustomerDA(mDB);
        employeeDA = new EmployeeDA(mDB);
        genreDA = new GenreDA(mDB);
        invoiceDA = new InvoiceDA(mDB);
        invoiceItemDA = new InvoiceItemDA(mDB);
        mediaTypeDa = new MediaTypeDA(mDB);
        playListDA = new PlayListDA(mDB);
        playListTrackDA = new PlayListTrackDA(mDB);
        trackDA = new TrackDA(mDB);
        LogTime.logIt("FORCE OPENED",chronicleDBHelper,runnumber);
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
            if (instance.onCreateCalled) {
                instance.loadImportedDataFromRawResources(instance.getWritableDatabase(),context);
                instance.onCreateCalled = false;
            }
        }
        return instance;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        LogTime.logIt("ONCONFIGURE",chronicleDBHelper,runnumber);
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
        db.enableWriteAheadLogging(); //Force WAL mode for true comparison
        LogTime.logIt("ONCONFIGURED",chronicleDBHelper,runnumber);
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        LogTime.logIt("ONOPEN",chronicleDBHelper,runnumber);
        super.onOpen(db);
        LogTime.logIt("ONOPENED",chronicleDBHelper,runnumber);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        onCreateCalled = true;
        LogTime.logIt("ONCREATE",chronicleDBHelper,runnumber);
        AllTables.createAllTables(db);
        //LogTime.logIt("Tables CREATED loading from raw resources",chronicleDBHelper,runnumber);
        //loadImportedDataFromRawResources(db,context);
        LogTime.logIt("ONCREATED",chronicleDBHelper,runnumber);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void loadImportedDataFromRawResources(SQLiteDatabase db, Context context) {
        LogTime.logIt("Loading Data from Raw Resource files",chronicleDBHelper,runnumber);
        String currentTable = "";
        db.setForeignKeyConstraintsEnabled(false);
        db.beginTransaction();
        Field[] fields = R.raw.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            currentTable = fields[i].getName().replace(".sql","");
            if (currentTable.toLowerCase().startsWith("sqlite_")) continue;
            Log.d("RAWINFO","Checking to see if raw resource " + currentTable + " is a table name");
            //if (!ifTableExistsViaCursor(db,currentTable)) continue;
            if (!ifTableExistsViaSQLiteMaster(db,currentTable)) continue;
            Log.d("RAWINFO","Table " + currentTable + " located in DB - processing");
            try {
                loadFromRawResource(db,context,fields[i].getInt(fields[i]));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.setForeignKeyConstraintsEnabled(true);
        LogTime.logIt("Data loaded from Raw Resources",chronicleDBHelper,runnumber);

    }

    private void loadFromRawResource(SQLiteDatabase db, Context context, int resourceId) {
        BufferedReader br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(resourceId)));
        String currentSQL = "";
        try {
            while ((currentSQL = br.readLine()) != null) {
                Log.d("LFRRINFO","Attempting to execute SQL statment :-\n\t" + currentSQL);
                db.execSQL(currentSQL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean ifTableExistsViaCursor(SQLiteDatabase db, String table) {
        Cursor csr = null;
        boolean rv = true;
        try {
            csr = db.query(table,null,null,null,null,null,null,"1");
        } catch (SQLiteException e) {
            rv = false;
        }
        if (csr != null && !csr.isClosed()) {
            csr.close();
        }
        return rv;
    }

    private boolean ifTableExistsViaSQLiteMaster(SQLiteDatabase db, String table) {
        Cursor csr = db.query("sqlite_master",null,"type = 'table' AND name = ?",new String[]{table},null,null,null);
        int count = csr.getCount();
        csr.close();
        return count > 0;
    }

    public long getTableRowCount(String tableName) {
        return DatabaseUtils.queryNumEntries(mDB,tableName);
    }
}
