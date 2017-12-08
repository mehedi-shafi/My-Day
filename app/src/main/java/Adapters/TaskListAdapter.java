package Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import Database.DatabaseHandler;
import Structures.DayTask;
import enf.course.project.myday.R;
import enf.course.project.myday.TaskViewPop;

/**
 * Created by Shafi on 12/8/2017.
 */

public class TaskListAdapter extends ArrayAdapter {

    private static ArrayList<DayTask> tasks;
    private static int layoutResource;
    private static Activity activity;

    public TaskListAdapter(Activity activity, int layoutResource, ArrayList<DayTask> data){
        super(activity, layoutResource, data);

        this.tasks = data;
        this.activity = activity;
        this.layoutResource = layoutResource;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        DayTaskHolder holder = null;

        if (holder == null || row.getTag() ==  null){
            LayoutInflater inflater = LayoutInflater.from(activity);

            row = inflater.inflate(layoutResource, null);

            holder = new DayTaskHolder();

            holder.title = (TextView) row.findViewById(R.id.task_title_row);
            holder.description = (TextView) row.findViewById(R.id.description_row);
            holder.time = (TextView) row.findViewById(R.id.time_row);
            holder.done = (Button) row.findViewById(R.id.done_button_row);

            row.setTag(holder);
        }
        else{
            holder = (DayTaskHolder) row.getTag();
        }

        holder.dayTask = (DayTask) getItem(position);

        holder.title.setText(holder.dayTask.getTitle());
        holder.description.setText(holder.dayTask.getDescription());
        holder.time.setText(holder.dayTask.getTime());

        final DayTaskHolder finalHolder = holder;

        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler dbh = new DatabaseHandler(activity.getApplicationContext());
                dbh.markDone(finalHolder.dayTask.getId());
                remove(getItem(position));
            }
        });

        row.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, TaskViewPop.class));
            }
        });

        return super.getView(position, convertView, parent);
    }

    public class DayTaskHolder{

        DayTask dayTask;

        TextView title;
        TextView description;
        TextView time;

        Button done;
    }
}
