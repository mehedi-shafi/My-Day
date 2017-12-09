package Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import Adapters.TaskListAdapter;
import Database.DatabaseHandler;
import Structures.DayTask;
import enf.course.project.myday.MainActivity;
import enf.course.project.myday.NewTask;
import enf.course.project.myday.R;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Shafi on 12/7/2017.
 */

public class TaskList extends Fragment{

    private ListView taskList;
    private TaskListAdapter listAdapter;
    private DatabaseHandler db;

    private String todate;

    private FloatingActionButton add_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.task_list, container, false);
        add_button = (FloatingActionButton) root.findViewById(R.id.add_floating_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), NewTask.class), 1);
            }
        });

        taskList = (ListView) root.findViewById(R.id.task_list);
        Calendar cal = Calendar.getInstance();
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        todate = sdf.format(cal.getTime());

        db = new DatabaseHandler(getActivity().getApplicationContext());
        listAdapter = new TaskListAdapter(getActivity(), R.layout.task_row, db.getTaskOfDate(todate));
        taskList.setAdapter(listAdapter);

        listAdapter.notifyDataSetChanged();

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if(resultCode == RESULT_OK){
                //Update List
                listAdapter = new TaskListAdapter(getActivity(), R.layout.task_row, db.getTaskOfDate(todate));
                taskList.setAdapter(listAdapter);
            }
            if (resultCode == RESULT_CANCELED) {
                //Do nothing?
            }
        }
    }//onActivityResult
}
