package activity;

/**
 * Created by Russell on 6/16/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import russellhowell.ushuttle.R;


public class HomeFragment extends Fragment {

    uShuttle_home homeActivity;
    public HomeFragment(){
        //required empty constructor
    }

    Context context;
    public HomeFragment(Context c) {
        //constructor that accepts context
        context=c;
        homeActivity = (uShuttle_home) c;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment


        //set transparancy for bottom "get started bar"
       LinearLayout bottomBar = (LinearLayout)rootView.findViewById(R.id.get_started_bar_layout);
        bottomBar.setAlpha((float) 0.75);

        //click listener for "get started"
        LinearLayout getStartedLL;
        getStartedLL = (LinearLayout) rootView.findViewById(R.id.get_started_bar_layout);
        getStartedLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeActivity.openNavDrawer();

            }
        });



        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

