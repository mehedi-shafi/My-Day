package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import Structures.DayTask;
import Utilities.VARS;
import Utilities.Utilities;

/**
 * Created by Shafi on 11/27/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static Context context;

    public DatabaseHandler(Context context){
        super(context, VARS.TABLE_NAME,null,  VARS.DB_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_EVENT_TABLE = "CREATE TABLE " + VARS.TABLE_NAME + "(" + VARS.ID_NAME + " INTEGER PRIMARY KEY, "
                + VARS.DATE + " TEXT, " + VARS.TIME + " TEXT, " + VARS.TITLE + " TEXT, " + VARS.DESCRIPTION + " TEXT, "
                + VARS.STATUS + " TEXT);";
        sqLiteDatabase.execSQL(CREATE_EVENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + VARS.TABLE_NAME;
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }


    public ArrayList<DayTask> getAllTasks(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DayTask> tasks = new ArrayList<>();
        String query = "SELECT * FROM " + VARS.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            DayTask dayTask = new DayTask(cursor.getInt(cursor.getColumnIndex(VARS.ID_NAME)), Utilities.getBool(cursor.getString(cursor.getColumnIndex(VARS.STATUS))),
                    cursor.getString(cursor.getColumnIndex(VARS.TITLE)), cursor.getString(cursor.getColumnIndex(VARS.DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(VARS.DATE)), cursor.getString(cursor.getColumnIndex(VARS.TIME)));
            tasks.add(dayTask);
        }
        return tasks;
    }

    public ArrayList<DayTask> getAllUndoneTasks(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DayTask> tasks = new ArrayList<>();
        String query = "SELECT * FROM " + VARS.TABLE_NAME + " WHERE " + VARS.STATUS + " = 'false'";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            DayTask dayTask = new DayTask(cursor.getInt(cursor.getColumnIndex(VARS.ID_NAME)), Utilities.getBool(cursor.getString(cursor.getColumnIndex(VARS.STATUS))),
                    cursor.getString(cursor.getColumnIndex(VARS.TITLE)), cursor.getString(cursor.getColumnIndex(VARS.DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(VARS.DATE)), cursor.getString(cursor.getColumnIndex(VARS.TIME)));
            tasks.add(dayTask);
        }
        return tasks;
    }

    public ArrayList<DayTask> getTaskOfDate(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DayTask> tasks = new ArrayList<>();
        String query = "SELECT * FROM " + VARS.TABLE_NAME + " WHERE " + VARS.DATE  + " = '" + date + "' AND " + VARS.STATUS + " = 'false'";
        Cursor cursor = db.rawQuery(query, null);
        while(!cursor.isAfterLast()){
            DayTask dayTask = new DayTask(cursor.getInt(cursor.getColumnIndex(VARS.ID_NAME)), Utilities.getBool(cursor.getString(cursor.getColumnIndex(VARS.STATUS))),
                    cursor.getString(cursor.getColumnIndex(VARS.TITLE)), cursor.getString(cursor.getColumnIndex(VARS.DESCRIPTION)),
                    cursor.getString(cursor.getColumnIndex(VARS.DATE)), cursor.getString(cursor.getColumnIndex(VARS.TIME)));
            tasks.add(dayTask);
        }
        return tasks;
    }


    public void addTask(DayTask task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(VARS.ID_NAME, task.getId());
        values.put(VARS.TITLE, task.getTitle());
        values.put(VARS.DESCRIPTION, task.getDescription());
        values.put(VARS.DATE, task.getDate());
        values.put(VARS.STATUS, "false");
        values.put(VARS.TIME, task.getTime());

        db.insert(VARS.TABLE_NAME, null, values);
        Toast.makeText(context, "Task Added Successfully", Toast.LENGTH_SHORT).show();
    }

    public void markDone(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + VARS.TABLE_NAME + " SET " + VARS.STATUS + " 'true' WHERE " + VARS.ID_NAME + " = '" + id + "'";
        final Cursor cursor = db.rawQuery(query, null);
        Toast.makeText(context, "Task Done", Toast.LENGTH_SHORT).show();
    }
}
