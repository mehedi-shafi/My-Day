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
                + VARS.STATUS + " TEXT, " + VARS.REMINDER + " TEXT, " + VARS.NOTIFY_BEFORE + " INT);";
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
                    new Date(cursor.getString(cursor.getColumnIndex(VARS.DATE))), cursor.getInt(cursor.getColumnIndex(VARS.TIME)), cursor.getInt(cursor.getColumnIndex(VARS.NOTIFY_BEFORE)),
                    Utilities.getBool(cursor.getString(cursor.getColumnIndex(VARS.REMINDER))));
            tasks.add(dayTask);
        }
        return tasks;
    }

    public ArrayList<DayTask> getTaskOfDate(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DayTask> tasks = new ArrayList<>();
        String query = "SELECT * FROM " + VARS.TABLE_NAME + " WHERE " + VARS.DATE  + " = '" + date + "'";
        Cursor cursor = db.rawQuery(query, null);
        while(!cursor.isAfterLast()){
            DayTask dayTask = new DayTask(cursor.getInt(cursor.getColumnIndex(VARS.ID_NAME)), Utilities.getBool(cursor.getString(cursor.getColumnIndex(VARS.STATUS))),
                    cursor.getString(cursor.getColumnIndex(VARS.TITLE)), cursor.getString(cursor.getColumnIndex(VARS.DESCRIPTION)),
                    new Date(cursor.getString(cursor.getColumnIndex(VARS.DATE))), cursor.getInt(cursor.getColumnIndex(VARS.TIME)), cursor.getInt(cursor.getColumnIndex(VARS.NOTIFY_BEFORE)),
                    Utilities.getBool(cursor.getString(cursor.getColumnIndex(VARS.REMINDER))));
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
        values.put(VARS.REMINDER, Utilities.getBoolString(task.isReminder()));
        values.put(VARS.TIME, task.getTime());
        values.put(VARS.NOTIFY_BEFORE, task.getReminder_time());

        db.insert(VARS.TABLE_NAME, null, values);
        Toast.makeText(context, "Task Added Successfully", Toast.LENGTH_SHORT).show();
    }

}
