package activity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import russellhowell.ushuttle.R;

public class OnCampusFragment extends Fragment {

    Context context;
    private static int DIALOG_ID;
    int hour;
    int minute;
    private TextView timeTextView;

    public OnCampusFragment() {

        // Required empty public constructor
    }


    public OnCampusFragment(Context c){
        //Get context from main activity
        context = c;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener(){
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour){
                    hour = hourOfDay;
                    minute = minuteOfHour;
                    timeTextView.setText(hour + ":" + minute);
                }
            };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_on_campus, container, false);

        //call spinner creation method
        Spinner startingLocationSpinner =(Spinner) rootView.findViewById(R.id.onCampusStartLocationsSpinner);
        populateSpinners(startingLocationSpinner, context, R.array.on_campus_locations );

        Spinner destinationSpinner = (Spinner) rootView.findViewById(R.id.onCampusDestinationSpinner);
        populateSpinners(destinationSpinner, context,R.array.on_campus_locations);



        //Set displayed time as current system time
        timeTextView = (TextView) rootView.findViewById(R.id.onCampusTime);
        timeTextView.setText(Calendar.getInstance().get(Calendar.HOUR)+":"+
                                Calendar.getInstance().get(Calendar.MINUTE));

        //Set up click listener to bring up the time picker view when fired
        timeTextView.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                public void onClick(View v){
                        TimePickerDialog picker = new TimePickerDialog(context, timePickerListener,hour, minute, false );
                        picker.show();
                    }
                }
        );



        // Inflate the layout for this fragment


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

    //Populate spinner function
    private void populateSpinners(Spinner spinner, Context context, int arrayId){
        //Create list from array in Strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,arrayId,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

}//End of class
