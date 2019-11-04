package aso.asoroomvtraditionaltrad;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class InvoiceItemDA {

    SQLiteDatabase db;

    /*
            InvoiceLineId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
            `InvoiceId` INTEGER NOT NULL,
            `TrackId` INTEGER NOT NULL,
            `UnitPrice` TEXT NOT NULL,
            `Quantity` INTEGER NOT NULL,
            FOREIGN KEY(`TrackId`) REFERENCES `tracks`(`TrackId`) ON UPDATE NO ACTION ON DELETE NO ACTION ,
            FOREIGN KEY(`InvoiceId`) REFERENCES `invoices`(`InvoiceId`) ON UPDATE NO ACTION ON DELETE NO ACTION )
     */

    public InvoiceItemDA(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertInvoiceItem(Long invoiceId, Long trackId, String unitPrice, Integer quantity) {
        if (invoiceId == null || invoiceId < 1 || trackId == null || trackId < 1 ) return -1;
        ContentValues cv = new ContentValues();
        if (unitPrice != null && unitPrice.length() > 0) {
            cv.put(AllTables.InvoiceItems.COL_UNITPRICE,unitPrice);
        }
        if (quantity != null) {
            cv.put(AllTables.InvoiceItems.COL_QUANTITY,quantity);
        }
        if (cv.size() < 1) return -1;
        cv.put(AllTables.InvoiceItems.COl_INVOICEID,invoiceId);
        cv.put(AllTables.InvoiceItems.COL_TRACKID,trackId);
        return db.insert(
                AllTables.InvoiceItems.TABLENAME,
                null,
                cv
        );
    }

    public long insertInvoiceItem(InvoiceItem invoiceItem) {
        return insertInvoiceItem(
                invoiceItem.getmInvoiceIdReference(),
                invoiceItem.getmTrackIdReference(),
                invoiceItem.getmUnitPrice(),
                invoiceItem.getmQuantity()
        );
    }

    public int updateInvoiceItemById(Long invoiceItemId,
                                     Long invoiceIdReference,
                                     Long trackIdReference,
                                     String unitPrice,
                                     Integer quantity) {
        ContentValues cv = new ContentValues();
        if (invoiceIdReference != null && invoiceIdReference > 0) {
            cv.put(AllTables.InvoiceItems.COl_INVOICEID,invoiceIdReference);
        }
        if (trackIdReference != null && trackIdReference > 0) {
            cv.put(AllTables.InvoiceItems.COL_TRACKID,trackIdReference);
        }
        if (unitPrice != null && unitPrice.length() > 0) {
            cv.put(AllTables.InvoiceItems.COL_UNITPRICE,unitPrice);
        }
        if (quantity != null) {
            cv.put(AllTables.InvoiceItems.COL_QUANTITY,quantity);
        }
        if (cv.size() < 1) return -1;
        return db.update(
                AllTables.InvoiceItems.TABLENAME,
                cv,
                AllTables.InvoiceItems.COl_INVOICEITEMID + "=?",
                new String[]{String.valueOf(invoiceItemId)}
                );
    }

    public int deleteInvoiceItemById(long invoiceItemId) {
        return db.delete(AllTables.InvoiceItems.TABLENAME,
                AllTables.InvoiceItems.COl_INVOICEITEMID + "=?",
                new String[]{String.valueOf(invoiceItemId)}
                );
    }

    public InvoiceItem getInvoiceItemById(long invoiceItemId) {
        InvoiceItem rv = null;
        Cursor csr = db.query(
                AllTables.InvoiceItems.TABLENAME,
                null,
                AllTables.InvoiceItems.COl_INVOICEITEMID + "=?",
                new String[]{String.valueOf(invoiceItemId)},
                null,null,null
        );
        if (csr.moveToFirst()) {
            rv = new InvoiceItem();
            rv.setmInvoiceItemId(csr.getLong(csr.getColumnIndex(AllTables.InvoiceItems.COl_INVOICEITEMID)));
            rv.setmInvoiceIdReference(csr.getLong(csr.getColumnIndex(AllTables.InvoiceItems.COl_INVOICEID)));
            rv.setmTrackIdReference(csr.getLong(csr.getColumnIndex(AllTables.InvoiceItems.COL_TRACKID)));
            rv.setmUnitPrice(csr.getString(csr.getColumnIndex(AllTables.InvoiceItems.COL_UNITPRICE)));
            rv.setmQuantity(csr.getInt(csr.getColumnIndex(AllTables.InvoiceItems.COL_QUANTITY)));
        }
        csr.close();
        return rv;
    }

    public long getRowCount() {
        return DatabaseUtils.queryNumEntries(db,AllTables.InvoiceItems.TABLENAME);
    }
}
