package enf.course.project.myday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import Database.DatabaseHandler;
import Structures.DayTask;

public class TaskViewPop extends AppCompatActivity {

    private TextView title, description, time, send_message, done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view_pop);

        Bundle dataBundle = getIntent().getExtras();

        final DayTask dayTask = (DayTask) dataBundle.get("data");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.75), (int) (height*.75));

        title = (TextView) findViewById(R.id.details_title);
        time = (TextView) findViewById(R.id.details_time);
        description = (TextView) findViewById(R.id.detail_description);

        send_message = (TextView) findViewById(R.id.send_message);
        done = (TextView) findViewById(R.id.done_text_pop);

        title.setText(dayTask.getTitle());
        time.setText(dayTask.getTime());
        description.setText(dayTask.getDescription());

        send_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, dayTask.getTitle());
                sharingIntent.putExtra(Intent.EXTRA_TEXT, dayTask.getDescription());
                startActivity(Intent.createChooser(sharingIntent, "Share Via"));
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler dbh = new DatabaseHandler(getApplicationContext());
                dbh.markDone(dayTask.getId());
                TaskViewPop.this.finish();
            }
        });
    }
}
