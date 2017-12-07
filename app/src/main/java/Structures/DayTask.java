package Structures;

import android.app.Activity;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Shafi on 11/27/2017.
 */

public class DayTask implements Serializable{

    private static boolean status;
    private static String title;
    private static String description;
    private static Date date;
    private static long time;
    private static int id;
    private static boolean reminder;
    private static long reminder_time;


    public DayTask(){

    }

    public DayTask(int id, boolean status, String title, String description, Date date, long time, long reminder_time, boolean reminder){
        this.id = id;
        this.status = status;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.reminder_time = reminder_time;
        this.reminder = reminder;
    }

    public DayTask(boolean status, String title, String description, Date date, long time){
        this.status = status;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
    }


    public void DONE(Activity activity){
        this.status = true;
        Toast.makeText(activity, "Task done", Toast.LENGTH_SHORT).show();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        DayTask.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        DayTask.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        DayTask.description = description;
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(Date date) {
        DayTask.date = date;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        DayTask.time = time;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        DayTask.id = id;
    }

    public boolean isReminder() {
        return reminder;
    }

    public void setReminder(boolean reminder) {
        DayTask.reminder = reminder;
    }

    public long getReminder_time() {
        return reminder_time;
    }

    public void setReminder_time(long reminder_time) {
        DayTask.reminder_time = reminder_time;
    }
}
