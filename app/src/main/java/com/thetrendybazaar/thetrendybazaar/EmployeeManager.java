package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;

public class EmployeeManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "Employee";

    public long add(Employee employee){
        ContentValues vals = new ContentValues();
        vals.put("EmployeeId", employee.employeeId);
        vals.put("Position", employee.position);
        vals.put("DateJoined", employee.dateJoined.toString());
        vals.put("SupervisorId", employee.supervisorId );
        vals.put("FirstName", employee.firstName);
        vals.put("LastName", employee.lastName);

        return writeDb.insert(tableName, null, vals);
    }
    public void delete(Employee employee){
        writeDb.delete(tableName, "EmployeeId=" + employee.employeeId, null);
    }

    public void update(Employee employee){
        ContentValues vals = new ContentValues();
        vals.put("EmployeeId", employee.employeeId);
        vals.put("Position", employee.position);
        vals.put("DateJoined", employee.dateJoined.toString());
        vals.put("SupervisorId", employee.supervisorId );
        vals.put("FirstName", employee.firstName);
        vals.put("LastName", employee.lastName);
        writeDb.update(tableName, vals, "EmployeeId=" + employee.employeeId, null);
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public Employee select(int employeeId){
        Cursor cursor = readDb.query(tableName, null, "EmployeeId = ?", new String[]{employeeId + ""}, null, null, null, null);
        Employee e = null;
        if(cursor != null){
            cursor.moveToFirst();
            e = new Employee(
                    cursor.getInt(0),
                    cursor.getString(1),
                    new Date(cursor.getLong(2)),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            cursor.close();
        }
        return e;
    }
}
