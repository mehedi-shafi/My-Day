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
    private static String date;
    private static String time;
    private static int id;


    public DayTask(){

    }

    public DayTask(int id, boolean status, String title, String description, String date, String time){
        this.id = id;
        this.status = status;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public DayTask(boolean status, String title, String description, String date, String time){
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
        return date;
    }

    public void setDate(String date) {
        DayTask.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        DayTask.time = time;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        DayTask.id = id;
    }
}
