package Structures;

import android.app.Activity;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Shafi on 11/27/2017.
 */

public class DayTask implements Serializable{

    private String status;
    private String title;
    private String description;
    private String date;
    private String time;
    private int id;


    public DayTask(){

    }

    public DayTask(int id, String status, String title, String description, String date, String time){
        this.id = id;
        this.status = status;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public DayTask(String status, String title, String description, String date, String time){
        this.status = status;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
    }


    public void DONE(Activity activity){
        this.status = "true";
        Toast.makeText(activity, "Task done", Toast.LENGTH_SHORT).show();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
