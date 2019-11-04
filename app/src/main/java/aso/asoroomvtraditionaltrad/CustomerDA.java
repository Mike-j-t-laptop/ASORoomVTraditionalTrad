package aso.asoroomvtraditionaltrad;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class CustomerDA {

    SQLiteDatabase db;

    /*
        `CustomerId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        `FirstName` TEXT NOT NULL,
        `LastName` TEXT NOT NULL,
        `Company` TEXT,
        `Address` TEXT,
        `City` TEXT,
        `State` TEXT,
        `Country` TEXT,
        `PostalCode` TEXT,
        `Phone` TEXT,
        `Fax` TEXT,
        `Email` TEXT NOT NULL,
        `SupportRepId` INTEGER NOT NULL,
        FOREIGN KEY(`SupportRepId`) REFERENCES `employees`(`EmployeeId`) ON UPDATE NO ACTION ON DELETE NO ACTION )")
     */

    public CustomerDA(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertCustomer(String firstName, String lastName,
                               String company,
                               String address, String city, String state, String country, String postCode,
                               String phone, String fax, String email,
                               Long supportRepId
    ) {
        if (supportRepId == null || supportRepId < 1) return -1;
        ContentValues cv = new ContentValues();
        if (firstName != null && firstName.length() > 0) {
            cv.put(AllTables.Customers.COl_FIRSTNAME,firstName);
        }
        if (lastName != null && lastName.length() > 0) {
            cv.put(AllTables.Customers.COL_LASTNAME,lastName);
        }
        if (company != null && company.length() > 0) {
            cv.put(AllTables.Customers.COL_COMPANY,company);
        }
        if (address != null && address.length() > 0) {
            cv.put(AllTables.Customers.COL_ADDRESS,address);
        }
        if (city != null && city.length() > 0) {
            cv.put(AllTables.Customers.COL_CITY,city);
        }
        if (state != null && state.length() > 0) {
            cv.put(AllTables.Customers.COL_STATE,state);
        }
        if (country != null && country.length() >0) {
            cv.put(AllTables.Customers.COL_COUNTRY,country);
        }
        if (postCode != null && postCode.length() > 0) {
            cv.put(AllTables.Customers.COl_POSTCODE,postCode);
        }
        if (phone != null && phone.length() > 0) {
            cv.put(AllTables.Customers.COl_POSTCODE,postCode);
        }
        if (fax != null && fax.length() > 0) {
            cv.put(AllTables.Customers.COl_FAX,fax);
        }
        if (email != null && email.length() > 0) {
            cv.put(AllTables.Customers.COL_EMAIL,email);
        }
        if (cv.size() < 1) return -1;
        cv.put(AllTables.Customers.COL_SUPPORTREPID,supportRepId);
        return db.insert(AllTables.Customers.TABLENAME,null,cv);
    }

    public long insertCustomer(Customer customer) {
        return insertCustomer(
                customer.getmFirstname(),customer.getmLastName(),
                customer.getmCompany(),
                customer.getmAddress(),customer.getmCity(),customer.getmState(),customer.getmCountry(),customer.getmPostCode(),
                customer.getmPhone(),customer.getmFax(),customer.getmEmail(),
                customer.getmSupportRepReference()
        );
    }

    public int updateCustomerById(
            Long customerId,
            String newFirstName, String newLastName,
            String newCompany,
            String newAddress, String newCity, String newState, String newCountry, String newPostCode,
            String newPhone, String newFax,String newEmail, Long newSupportRepId) {
        ContentValues cv = new ContentValues();
        if (newFirstName != null && newFirstName.length() > 0) {
            cv.put(AllTables.Customers.COl_FIRSTNAME,newFirstName);
        }
        if (newLastName != null && newLastName.length() > 0) {
            cv.put(AllTables.Customers.COL_LASTNAME,newLastName);
        }
        if (newCompany != null && newCompany.length() > 0) {
            cv.put(AllTables.Customers.COL_COMPANY,newCompany);
        }
        if (newAddress != null && newAddress.length() > 0) {
            cv.put(AllTables.Customers.COL_ADDRESS,newAddress);
        }
        if (newCity != null && newCity.length() > 0) {
            cv.put(AllTables.Customers.COL_CITY,newCity);
        }
        if (newState != null && newState.length() > 0 ) {
            cv.put(AllTables.Customers.COL_STATE,newState);
        }
        if (newCountry != null && newCountry.length() > 0 ) {
            cv.put(AllTables.Customers.COL_COUNTRY,newCountry);
        }
        if (newPostCode != null && newPostCode.length() > 0) {
            cv.put(AllTables.Customers.COl_POSTCODE,newPostCode);
        }
        if (newPhone != null && newPhone.length() > 0) {
            cv.put(AllTables.Customers.COL_PHONE,newPhone);
        }
        if (newFax != null && newFax.length() > 0) {
            cv.put(AllTables.Customers.COl_FAX,newFax);
        }
        if (newEmail != null && newEmail.length() > 0) {
            cv.put(AllTables.Customers.COL_EMAIL,newEmail);
        }
        if (newSupportRepId != null && newSupportRepId > 0) {
            cv.put(AllTables.Customers.COL_SUPPORTREPID,newSupportRepId);
        }
        if (cv.size() < 1) return -1;
        return db.update(
                AllTables.Customers.TABLENAME,
                cv,
                AllTables.Customers.COl_CUSTOMERID + "=?",
                new String[]{String.valueOf(customerId)}
                );

    }

    public int deleteCustomerById(long customerId) {
        return db.delete(AllTables.Customers.TABLENAME,
                AllTables.Customers.COl_CUSTOMERID + "=?",
                new String[]{String.valueOf(customerId)}
                );
    }

    public Customer getCustomerById(long customerId) {
        Customer rv = null;
        Cursor csr = db.query(
                AllTables.Customers.TABLENAME,
                null,
                AllTables.Customers.COl_CUSTOMERID + "=?",
                new String[]{String.valueOf(customerId)},
                null,null,null
        );
        if (csr.moveToFirst()) {
            rv = new Customer();
            rv.setmCustomerid(csr.getLong(csr.getColumnIndex(AllTables.Customers.COl_CUSTOMERID)));
            rv.setmFirstname(csr.getString(csr.getColumnIndex(AllTables.Customers.COl_FIRSTNAME)));
            rv.setmLastName(csr.getString(csr.getColumnIndex(AllTables.Customers.COL_LASTNAME)));
            rv.setmCompany(csr.getString(csr.getColumnIndex(AllTables.Customers.COL_COMPANY)));
            rv.setmAddress(csr.getString(csr.getColumnIndex(AllTables.Customers.COL_ADDRESS)));
            rv.setmCity(csr.getString(csr.getColumnIndex(AllTables.Customers.COL_CITY)));
            rv.setmState(csr.getString(csr.getColumnIndex(AllTables.Customers.COL_STATE)));
            rv.setmCountry(csr.getString(csr.getColumnIndex(AllTables.Customers.COL_COUNTRY)));
            rv.setmPostCode(csr.getString(csr.getColumnIndex(AllTables.Customers.COl_POSTCODE)));
            rv.setmPhone(csr.getString(csr.getColumnIndex(AllTables.Customers.COL_PHONE)));
            rv.setmFax(csr.getString(csr.getColumnIndex(AllTables.Customers.COl_FAX)));
            rv.setmEmail(csr.getString(csr.getColumnIndex(AllTables.Customers.COL_EMAIL)));
            rv.setmSupportRepReference(csr.getLong(csr.getColumnIndex(AllTables.Customers.COL_SUPPORTREPID)));
        }
        csr.close();
        return rv;
    }

    public long getRowCount() {
        return DatabaseUtils.queryNumEntries(db,AllTables.Customers.TABLENAME);
    }
}
