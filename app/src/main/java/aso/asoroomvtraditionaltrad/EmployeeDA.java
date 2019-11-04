package aso.asoroomvtraditionaltrad;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDA {

    SQLiteDatabase db;

    /*
        EmployeeId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
        `LastName` TEXT NOT NULL,
        `FirstName` TEXT NOT NULL,
        `Title` TEXT,
        `ReportsTo` INTEGER NOT NULL,
        `BirthDate` TEXT,
        `HireDate` TEXT,
        `Address` TEXT,
        `City` TEXT,
        `State` TEXT,
        `Country` TEXT,
        `PostalCode` TEXT,
        `Phone` TEXT,
        `Fax` TEXT,
        `Email` TEXT,
        FOREIGN KEY(`ReportsTo`) REFERENCES `employees`(`EmployeeId`) ON UPDATE NO ACTION ON DELETE NO ACTION
     */

    public EmployeeDA(SQLiteDatabase db) {
        this.db = db;
    }

    public long insertEmployee(String firstName, String lastName,
                               String title,
                               Long reportsTo,
                               String birthDate, String hireDate,
                               String address, String city, String state, String country, String postCode,
                               String phone, String fax, String email) {
        if (reportsTo == null || reportsTo < 1) return -1;
        ContentValues cv = new ContentValues();
        if (firstName != null && firstName.length() > 0) {
            cv.put(AllTables.Employees.COL_FIRSTNAME,firstName);
        }
        if (lastName != null && lastName.length() > 0) {
            cv.put(AllTables.Employees.COL_LASTNAME,lastName);
        }
        if (title != null && title.length() >0) {
            cv.put(AllTables.Employees.COL_TITLE,title);
        }
        if (birthDate != null && birthDate.length() > 0) {
            cv.put(AllTables.Employees.COL_BIRTHDATE,birthDate);
        }
        if (hireDate != null && hireDate.length() > 0) {
            cv.put(AllTables.Employees.COL_HIREDATE,hireDate);
        }
        if (address != null && address.length()> 0) {
            cv.put(AllTables.Employees.COL_ADDRESS,address);
        }
        if (city != null && city.length() > 0) {
            cv.put(AllTables.Employees.COL_CITY,city);
        }
        if (state != null && state.length() >0) {
            cv.put(AllTables.Employees.COL_STATE,state);
        }
        if (country != null && country.length() >0) {
            cv.put(AllTables.Employees.COL_COUNTRY,country);
        }
        if (postCode != null && postCode.length() > 0) {
            cv.put(AllTables.Employees.COL_POSTCODE,postCode);
        }
        if (phone != null && phone.length()>0) {
            cv.put(AllTables.Employees.COL_PHONE,phone);
        }
        if (fax != null && fax.length() > 0) {
            cv.put(AllTables.Employees.COl_FAX,fax);
        }
        if (email != null && email.length() >0) {
            cv.put(AllTables.Employees.COL_EMAIL,email);
        }
        if (cv.size() < 1) return -1;
        cv.put(AllTables.Employees.COL_REPORTSTO,reportsTo);
        return db.insert(AllTables.Employees.TABLENAME,null,cv);
    }

    public long insertEmployee(Employee employee) {
        return insertEmployee(employee.getmFirstName(),employee.getmLastName(),
                employee.getmTitle(),
                employee.getmReportsTo(),employee.getmBirthDate(),employee.getmHireDate(),
                employee.getmAddress(),employee.getmCity(),employee.getmState(),employee.getmCountry(),employee.getmPostCode(),
                employee.getmPhone(),employee.getmFax(),employee.getmEmail()
        );
    }

    public int updateEmployeeById (Long employeeId,
                                   String firstName, String lastName,
                                   String title,
                                   Long reportsTo,
                                   String birthDate, String hireDate,
                                   String address, String city, String state, String country, String postCode,
                                   String phone, String fax, String email) {
        ContentValues cv = new ContentValues();
        if (firstName != null && firstName.length() > 0) {
            cv.put(AllTables.Employees.COL_FIRSTNAME,firstName);
        }
        if (lastName != null && lastName.length() > 0) {
            cv.put(AllTables.Employees.COL_LASTNAME,lastName);
        }
        if (title != null && title.length() >0) {
            cv.put(AllTables.Employees.COL_TITLE,title);
        }
        if (birthDate != null && birthDate.length() > 0) {
            cv.put(AllTables.Employees.COL_BIRTHDATE,birthDate);
        }
        if (hireDate != null && hireDate.length() > 0) {
            cv.put(AllTables.Employees.COL_HIREDATE,hireDate);
        }
        if (address != null && address.length()> 0) {
            cv.put(AllTables.Employees.COL_ADDRESS,address);
        }
        if (city != null && city.length() > 0) {
            cv.put(AllTables.Employees.COL_CITY,city);
        }
        if (state != null && state.length() >0) {
            cv.put(AllTables.Employees.COL_STATE,state);
        }
        if (country != null && country.length() >0) {
            cv.put(AllTables.Employees.COL_COUNTRY,country);
        }
        if (postCode != null && postCode.length() > 0) {
            cv.put(AllTables.Employees.COL_POSTCODE,postCode);
        }
        if (phone != null && phone.length()>0) {
            cv.put(AllTables.Employees.COL_PHONE,phone);
        }
        if (fax != null && fax.length() > 0) {
            cv.put(AllTables.Employees.COl_FAX,fax);
        }
        if (email != null && email.length() >0) {
            cv.put(AllTables.Employees.COL_EMAIL,email);
        }
        if (reportsTo != null && reportsTo > 0) {
            cv.put(AllTables.Employees.COL_REPORTSTO,reportsTo);
        }
        if (cv.size() < 1) return -1;
        return db.update(AllTables.Employees.TABLENAME,
                cv,
                AllTables.Employees.COL_EMPLOYEEID + "=?",
                new String[]{String.valueOf(employeeId)}
                );
    }

    public int deleteEmployeeById(long employeeId) {
        return db.delete(
                AllTables.Employees.TABLENAME,
                AllTables.Employees.COL_EMPLOYEEID + "=?",
                new String[]{String.valueOf(employeeId)}
                );
    }

    public long getLastEmployeeId() {
        long rv = 0;
        String[] columns = new String[]{
                "max(" +
                        AllTables.Employees.COL_EMPLOYEEID +
                        ") AS " + BaseColumns._COUNT
        };
        Cursor csr = db.query(AllTables.Employees.TABLENAME,columns,null,null,null,null,null);
        if (csr.moveToFirst()) {
            rv = csr.getLong(csr.getColumnIndex(BaseColumns._COUNT));
        }
        csr.close();
        return rv;
    }

    public Employee getEmployeeById(long employeeId) {
        Employee rv = null;
        Cursor csr = db.query(
                AllTables.Employees.TABLENAME,
                null,
                AllTables.Employees.COL_EMPLOYEEID + "=?",
                new String[]{String.valueOf(employeeId)},
                null,null,null
        );
        if (csr.moveToFirst()) {
            rv = extractEmployeeFromCursorRow(csr);
        }
        csr.close();
        return rv;
    }

    public List<Employee> getAllEmployees() {
        ArrayList<Employee> rv = new ArrayList<>();
        Cursor csr = db.query(
                AllTables.Employees.TABLENAME,
                null,
                null,null,null,null,null
        );
        while (csr.moveToNext()) {
            rv.add(extractEmployeeFromCursorRow(csr));
        }
        csr.close();
        return rv;
    }

    private Employee extractEmployeeFromCursorRow(Cursor csr) {
        Employee rv = new Employee();
        rv.setmEmployeeId(csr.getLong(csr.getColumnIndex(AllTables.Employees.COL_EMPLOYEEID)));
        rv.setmFirstName(csr.getString(csr.getColumnIndex(AllTables.Employees.COL_FIRSTNAME)));
        rv.setmLastName(csr.getString(csr.getColumnIndex(AllTables.Employees.COL_LASTNAME)));
        rv.setmTitle(csr.getString(csr.getColumnIndex(AllTables.Employees.COL_TITLE)));
        rv.setmReportsTo(csr.getLong(csr.getColumnIndex(AllTables.Employees.COL_REPORTSTO)));
        rv.setmBirthDate(csr.getString(csr.getColumnIndex(AllTables.Employees.COL_BIRTHDATE)));
        rv.setmHireDate(csr.getString(csr.getColumnIndex(AllTables.Employees.COL_HIREDATE)));
        rv.setmAddress(csr.getString(csr.getColumnIndex(AllTables.Employees.COL_ADDRESS)));
        rv.setmCity(csr.getString(csr.getColumnIndex(AllTables.Employees.COL_CITY)));
        rv.setmState(csr.getString(csr.getColumnIndex(AllTables.Employees.COL_STATE)));
        rv.setmCountry(csr.getString(csr.getColumnIndex(AllTables.Employees.COL_COUNTRY)));
        rv.setmPostCode(csr.getString(csr.getColumnIndex(AllTables.Employees.COL_POSTCODE)));
        rv.setmPhone(csr.getString(csr.getColumnIndex(AllTables.Employees.COL_PHONE)));
        rv.setmFax(csr.getString(csr.getColumnIndex(AllTables.Employees.COl_FAX)));
        rv.setmEmail(csr.getString(csr.getColumnIndex(AllTables.Employees.COL_EMAIL)));
        return rv;
    }

    public long getRowCount() {
        return DatabaseUtils.queryNumEntries(db,AllTables.Employees.TABLENAME);
    }
}
