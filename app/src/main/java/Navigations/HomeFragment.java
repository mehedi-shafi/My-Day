package Navigations;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import Adapters.TaskListAdapter;
import Database.DatabaseHandler;
import enf.course.project.myday.MainActivity;
import enf.course.project.myday.NewTask;
import enf.course.project.myday.R;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by Shafi on 12/7/2017.
 */

public class HomeFragment extends Fragment {

    private static FloatingActionButton add_button;
    private ListView list;
    private DatabaseHandler dbh;
    private TaskListAdapter adapter;
    private String todate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.home_navigation, container, false);

        add_button = (FloatingActionButton) root.findViewById(R.id.add_floating_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getActivity(), NewTask.class), 1);
            }
        });
        list = (ListView) root.findViewById(R.id.home_task_list);
        Calendar cal = Calendar.getInstance();
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        todate = sdf.format(cal.getTime());

        dbh = new DatabaseHandler(getActivity().getApplicationContext());

        adapter = new TaskListAdapter(getActivity(), R.layout.task_row, dbh.getTaskOfDate(todate));
        list.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if(resultCode == RESULT_OK){
                //Update List
                adapter = new TaskListAdapter(getActivity(), R.layout.task_row, dbh.getTaskOfDate(todate));
                list.setAdapter(adapter);
            }
            if (resultCode == RESULT_CANCELED) {
                //Do nothing?
            }
        }
    }//onActivityResult
}
