package Navigations;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import enf.course.project.myday.R;

/**
 * Created by Shafi on 12/7/2017.
 */

public class CalendarFragment extends Fragment {

    MaterialCalendarView calendarView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.calendar_navigation, container, false);
        calendarView = (MaterialCalendarView) root.findViewById(R.id.calendarView);
        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SATURDAY)
                .setMinimumDate(CalendarDay.from(2017, 11,26))
                .setMaximumDate(CalendarDay.from(2099, 5, 11))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        return root;
    }
}
