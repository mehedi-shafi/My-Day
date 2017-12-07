package Navigations;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import enf.course.project.myday.R;

/**
 * Created by Shafi on 12/7/2017.
 */

public class HomeFragment extends Fragment {

    private static Button test;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.home_navigation, container, false);
        test = (Button) root.findViewById(R.id.home_test_button);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Worked", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}
