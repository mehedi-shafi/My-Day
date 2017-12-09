package enf.course.project.myday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ListView;
import android.widget.TextView;

import Adapters.TaskListAdapter;
import Database.DatabaseHandler;

public class DayView extends AppCompatActivity {

    private TextView dateText;
    private ListView list;
    private TaskListAdapter adapter;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.80), (int) (height*.80));

        dateText = (TextView) findViewById(R.id.tasklist_date);
        list = (ListView) findViewById(R.id.task_list);

        db = new DatabaseHandler(getApplicationContext());

        adapter = new TaskListAdapter(this, R.layout.task_row, db.getTaskOfDate(getIntent().getStringExtra("date")));

        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
