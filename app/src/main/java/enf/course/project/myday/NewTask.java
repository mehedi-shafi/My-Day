package enf.course.project.myday;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import Database.DatabaseHandler;
import Structures.DayTask;


public class NewTask extends AppCompatActivity {

    private static TextView datePicker;
    private static Calendar myCalendar;
    private static DatePickerDialog.OnDateSetListener date;
    private static EditText title, description;
    private static TimePicker timePicker;
    private static Button submit;
    private DatabaseHandler dbh;

    //Strings for storing values
    private static String dateString;
    private static String timeStrng;
    private static String descriptionString;
    private static String titleString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.75), (int) (height*.75));

        datePicker = (TextView) findViewById(R.id.date_picker);
        title = (EditText) findViewById(R.id.title_input);
        description = (EditText) findViewById(R.id.description_input);
        timePicker = (TimePicker) findViewById(R.id.time_picker);
        submit = (Button) findViewById(R.id.submit_new);

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(NewTask.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (notNull()) {
                    dbh = new DatabaseHandler(getApplicationContext());
                    dbh.addTask(createTask());
                    Toast.makeText(NewTask.this, "Added successfully", Toast.LENGTH_SHORT).show();
                    NewTask.this.finish();
                }
                else{
                    Toast.makeText(NewTask.this, "Please fill the necessary fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private static boolean notNull(){
        titleString = title.getText().toString();
        descriptionString = description.getText().toString();
        timeStrng = String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute());
        if (titleString != null && titleString.compareTo("") != 0 && descriptionString.compareTo("") != 0 && descriptionString != null)
            return true;
        return false;
    }
    private static DayTask createTask(){

        DayTask day = new DayTask();
        day.setDate(dateString);
        day.setTime(timeStrng);
        day.setTitle(titleString);
        day.setDescription(descriptionString);
        
        return day;
    }

    private static void updateLabel(){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateString = sdf.format(myCalendar.getTime());
        datePicker.setText(dateString);
    }
}
