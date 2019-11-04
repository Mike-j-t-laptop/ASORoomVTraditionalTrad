package aso.asoroomvtraditionaltrad;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class InvoiceDA {

    SQLiteDatabase db;

    /*
            `InvoiceId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
            `CustomerId` INTEGER NOT NULL,
            `InvoiceDate` TEXT NOT NULL,
            `BillingAddress` TEXT,
            `BillingCity` TEXT,
            `BillingState` TEXT,
            `BillingCountry` TEXT,
            `BillingPostalCode` TEXT,
            `Total` TEXT NOT NULL,
            FOREIGN KEY(`CustomerId`) REFERENCES `customers`(`CustomerId`) ON UPDATE NO ACTION ON DELETE NO ACTION )
     */
    public InvoiceDA(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertInvoice(Long customerId,
                              String invoiceDate,
                              String billingAddress, String billingCity,
                              String billingState, String billingCountry, String billingPostCode,
                              String total
    ) {
        if (customerId == null || customerId < 1) return -1;
        ContentValues cv = new ContentValues();
        if (invoiceDate != null && invoiceDate.length() > 0) {
            cv.put(AllTables.Invoices.COL_INVOICEDATE,invoiceDate);
        }
        if (billingAddress != null && billingAddress.length() > 0) {
            cv.put(AllTables.Invoices.COL_BILLINGADDRESS,billingAddress);
        }
        if (billingCity != null  && billingCity.length() > 0) {
            cv.put(AllTables.Invoices.COL_BILLINGCITY,billingCity);
        }
        if (billingState != null && billingState.length() > 0) {
            cv.put(AllTables.Invoices.COL_BILLINGSTATE,billingState);
        }
        if (billingCountry != null && billingCountry.length() > 0) {
            cv.put(AllTables.Invoices.COL_BILLINGCOUNTRY,billingCountry);
        }
        if (billingPostCode != null && billingPostCode.length() > 0) {
            cv.put(AllTables.Invoices.COL_BILLINGPOSTCODE,billingPostCode);
        }
        if (total != null && total.length() > 0) {
            cv.put(AllTables.Invoices.COL_TOTAL,total);
        }
        if (cv.size() < 1) return -1;
        cv.put(AllTables.Invoices.COL_CUSTOMERID,customerId);
        return db.insert(AllTables.Invoices.TABLENAME,null,cv);
    }

    public long insertInvoice(Invoice invoice) {
        return insertInvoice(
                invoice.getmCustomerIdReference(),
                invoice.mInvoiceDate,
                invoice.mBillingAddress,invoice.mBillingCity,invoice.mBillingState,
                invoice.mBillingCountry,invoice.mBillingPostcode,
                invoice.mTotal);
    }

    public int updateInvoiceById(Long invoiceId,
                                  Long customerId,
                                  String invoiceDate,
                                  String billingAddress, String billingCity, String billingState,
                                  String billingCountry, String billingPostCode,
                                  String total) {
        ContentValues cv = new ContentValues();
        if (invoiceDate != null && invoiceDate.length() > 0) {
            cv.put(AllTables.Invoices.COL_INVOICEDATE,invoiceDate);
        }
        if (billingAddress != null && billingAddress.length() > 0) {
            cv.put(AllTables.Invoices.COL_BILLINGADDRESS,billingAddress);
        }
        if (billingCity != null  && billingCity.length() > 0) {
            cv.put(AllTables.Invoices.COL_BILLINGCITY,billingCity);
        }
        if (billingState != null && billingState.length() > 0) {
            cv.put(AllTables.Invoices.COL_BILLINGSTATE,billingState);
        }
        if (billingCountry != null && billingCountry.length() > 0) {
            cv.put(AllTables.Invoices.COL_BILLINGCOUNTRY,billingCountry);
        }
        if (billingPostCode != null && billingPostCode.length() > 0) {
            cv.put(AllTables.Invoices.COL_BILLINGPOSTCODE,billingPostCode);
        }
        if (total != null && total.length() > 0) {
            cv.put(AllTables.Invoices.COL_TOTAL,total);
        }
        if (customerId != null && customerId > 0) {
            cv.put(AllTables.Invoices.COL_CUSTOMERID,customerId);
        }
        return db.update(
                AllTables.Invoices.TABLENAME,
                cv,
                AllTables.Invoices.COL_INVOICEID + "=?",
                new String[]{String.valueOf(invoiceId)}
                );
    }

    public int deleteInvoiceById(long invoiceId) {
        return db.delete(
                AllTables.Invoices.TABLENAME,
                AllTables.Invoices.COL_INVOICEID,
                new String[]{String.valueOf(invoiceId)}
                );
    }

    public Invoice getInvoiceById(long invoiceId) {
        Invoice rv = null;
        Cursor csr = db.query(
                AllTables.Invoices.TABLENAME,
                null,
                AllTables.Invoices.COL_INVOICEID,
                new String[]{String.valueOf(invoiceId)},
                null,null,null
        );
        if (csr.moveToFirst()) {
            rv = new Invoice();
            rv.setmInvoiceId(csr.getLong(csr.getColumnIndex(AllTables.Invoices.COL_INVOICEID)));
            rv.setmCustomerIdReference(csr.getLong(csr.getColumnIndex(AllTables.Invoices.COL_CUSTOMERID)));
            rv.setmInvoiceDate(csr.getString(csr.getColumnIndex(AllTables.Invoices.COL_INVOICEDATE)));
            rv.setmBillingAddress(csr.getString(csr.getColumnIndex(AllTables.Invoices.COL_BILLINGADDRESS)));
            rv.setmBillingCity(csr.getString(csr.getColumnIndex(AllTables.Invoices.COL_BILLINGCITY)));
            rv.setmBillingState(csr.getColumnName(csr.getColumnIndex(AllTables.Invoices.COL_BILLINGSTATE)));
            rv.setmBillingCountry(csr.getColumnName(csr.getColumnIndex(AllTables.Invoices.COL_BILLINGCOUNTRY)));
            rv.setmBillingPostcode(csr.getString(csr.getColumnIndex(AllTables.Invoices.COL_BILLINGPOSTCODE)));
            rv.setmTotal(csr.getString(csr.getColumnIndex(AllTables.Invoices.COL_TOTAL)));
        }
        csr.close();
        return rv;
    }
    public long getRowCount() {
        return DatabaseUtils.queryNumEntries(db,AllTables.Invoices.TABLENAME);
    }
}
