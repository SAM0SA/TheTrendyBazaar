package com.thetrendybazaar.thetrendybazaar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ReportsToManager {
    static SQLiteDatabase readDb, writeDb;
    static String tableName = "ReportsTo";

    public long add(Employee supervisor, Employee subordinate){
        ContentValues vals = new ContentValues();
        vals.put("SupervisorId", supervisor.employeeId);
        vals.put("SubordinateId", subordinate.employeeId);
        return writeDb.insert(tableName, null, vals);
    }
    public void delete(Employee supervisor, Employee subordinate){
        writeDb.delete(tableName, "SupervisorId= ? AND SubordinateId = ?", new String[] {supervisor.employeeId + "", subordinate.employeeId + ""});
    }

    public static void setDb(SQLiteDatabase rDb, SQLiteDatabase wDb){
        readDb = rDb;
        writeDb = wDb;
    }

    public ArrayList<Employee> getSubordinates(Employee employee){
        Cursor cursor = readDb.query(tableName, null, "SupervisorId = ?", new String[]{employee.employeeId + ""}, null, null, null, null);
        ArrayList<Employee> subordinates = new ArrayList<>();
        Employee e;
        if(cursor != null){
            cursor.moveToFirst();
            do{
                e = DatabaseManager.employees.select(cursor.getInt(1));
                subordinates.add(e);
            }while(cursor.moveToNext());
            cursor.close();
            cursor.close();
        }
        return subordinates;
    }
//    //purpose of this method?
//    public void updateSupervisor(Employee employeeToUpdate, Employee newSupervisor){
//        ContentValues vals = new ContentValues();
//        vals.put("SupervisorId", newSupervisor.employeeId);
//        vals.put("SubordinateId", employeeToUpdate.employeeId);
//        writeDb.update(tableName, vals, "SupervisorId=" + newSupervisor.employeeId + " AND SubordinateId=" + employeeToUpdate.employeeId, null);
//    }
}
