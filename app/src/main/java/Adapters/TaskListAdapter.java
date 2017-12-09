package Adapters;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import Database.DatabaseHandler;
import Structures.DayTask;
import enf.course.project.myday.R;
import enf.course.project.myday.TaskViewPop;

/**
 * Created by Shafi on 12/8/2017.
 */

public class TaskListAdapter extends ArrayAdapter {

    private Integer colors[] = new Integer[] {R.color.acceptance_blue, R.color.ash, R.color.black, R.color.bloody_orange, R.color.bloody_red, R.color.blue_900,
                    R.color.bright_yellow, R.color.brown, R.color.dripping_red, R.color.blue_ash_grey, R.color.shadow_dark, R.color.cyan_bluish, R.color.cyan_bluish,
                    R.color.deep_blue_grey, R.color.deep_teal, R.color.light_grey};

    private ArrayList<DayTask> tasks;
    private int layoutResource;
    private Activity activity;

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
            holder.done = (ImageView) row.findViewById(R.id.done_button_row);
            holder.colorBorder = (TextView) row.findViewById(R.id.color_border);

            row.setTag(holder);
        }
        else{
            holder = (DayTaskHolder) row.getTag();
        }

        holder.dayTask = (DayTask) getItem(position);

        holder.title.setText(holder.dayTask.getTitle());
        holder.description.setText(holder.dayTask.getDescription());
        holder.time.setText(holder.dayTask.getTime());
        int rnd = new Random().nextInt(colors.length);
        holder.colorBorder.setBackgroundResource(colors[rnd]);

        final DayTaskHolder finalHolder = holder;

        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler dbh = new DatabaseHandler(activity.getApplicationContext());
                dbh.markDone(finalHolder.dayTask.getId());
                System.out.println(finalHolder.dayTask.getId());
                remove(getItem(position));
            }
        });

        row.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, TaskViewPop.class);
                Bundle mBundle = new Bundle();

                mBundle.putSerializable("data", finalHolder.dayTask);
                i.putExtras(mBundle);
                activity.startActivity(i);
            }
        });

        return row;
    }

    public class DayTaskHolder{

        DayTask dayTask;

        TextView colorBorder;

        TextView title;
        TextView description;
        TextView time;

        ImageView done;
    }
}
