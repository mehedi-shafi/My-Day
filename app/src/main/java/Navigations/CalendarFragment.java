package Navigations;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Locale;

import enf.course.project.myday.DayView;
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
                .setMinimumDate(CalendarDay.from(2017, 11,1))
                .setMaximumDate(CalendarDay.from(2099, 5, 11))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Intent i = new Intent(getActivity(), DayView.class);

                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                System.out.println(sdf.format(date.getDate()));
                i.putExtra("date", String.valueOf(sdf.format(date.getDate())));
                getActivity().startActivity(i);
            }
        });
        return root;
    }
}
