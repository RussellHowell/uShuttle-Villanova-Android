package activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import timetable.Shuttle;

import java.util.Calendar;

import russellhowell.ushuttle.R;

public class OnCampusFragment extends Fragment {

    Context context;
    private static int tripType = 0;
    private int tripTime;
    private TextView timeTextView;
    private TextView meridianTextView;
    private ImageView imageView;
    private FloatingActionButton goFAB;
    private RelativeLayout.LayoutParams fabLayoutParams;
    private RelativeLayout.LayoutParams fabClickedLayoutParams;
    private Scene scene1;
    private Scene scene2;
    private Calendar calendar;
    private ViewGroup sceneRoot;
    Spinner startingLocationSpinner;
    Spinner destinationSpinner;

  //  public BasicTransitionFragment newInstance(){return new BasicTransitionFragment();}

    public OnCampusFragment() {

        // Required empty public constructor
    }


    @SuppressLint("ValidFragment")
    public OnCampusFragment(Context c){
        //Get context from main activity
        context = c;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfHour) {

                  tripTime = setFormattedTime(hourOfDay, minuteOfHour);
                
                }
            };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_on_campus_root, container, false);
        assert rootView != null;

        //allow access to views created in xml
        imageView = (ImageView) rootView.findViewById(R.id.onCampusImageView);

        goFAB = (FloatingActionButton) rootView.findViewById(R.id.goFabOnCampus);

        sceneRoot = (ViewGroup) rootView.findViewById(R.id.on_campus_scene_root);
        scene1 = new Scene(sceneRoot,(ViewGroup) sceneRoot.findViewById(R.id.onCampusLayout));
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.fragment_on_campus2, getActivity());

        timeTextView = (TextView) rootView.findViewById(R.id.on_campus_time_text);
        meridianTextView = (TextView) rootView.findViewById(R.id.meridian_text_view);

        //call spinner creation method
        startingLocationSpinner =(Spinner) rootView.findViewById(R.id.on_campus_start_loc_spinner);
        populateSpinners(startingLocationSpinner, context, R.array.on_campus_locations );

        destinationSpinner = (Spinner) rootView.findViewById(R.id.on_campus_end_loc_spinner);
        populateSpinners(destinationSpinner, context,R.array.on_campus_locations);



        //Set displayed time as current system time
        tripTime = setFormattedTime(Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE));

        //Set up click listener to bring up the time picker view when fired
        timeTextView.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                public void onClick(View v){
                        TimePickerDialog picker = new TimePickerDialog(context, timePickerListener,
                                Calendar.getInstance().get(Calendar.HOUR), Calendar.getInstance().get(Calendar.MINUTE), false );
                        picker.show();

                    }
                }
        );


        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        final ViewTreeObserver observer = imageView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    observer.removeOnGlobalLayoutListener(this);
                } else {
                    observer.removeOnGlobalLayoutListener(this);
                }

                //Call setup for FAB after all views have been drawn in fragment
                setUpFloatingActionButton();
            }
        });

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

    private void setUpFloatingActionButton(){
        //Get dimensions of imageView at top of fragment

        int imageHeight = imageView.getHeight();
        int imageWidth = imageView.getWidth();

        //Set up FAB's location based on imageView's dimensions and FAB's dimensions
        fabLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        int fabYLocation = imageHeight - goFAB.getHeight()/2;
        int fabXLocation = imageWidth - goFAB.getWidth() - 40;
        fabLayoutParams.setMargins(fabXLocation, fabYLocation, 0, 0); //imageView will sit between two layouts
        goFAB.setLayoutParams(fabLayoutParams);

        fabClickedLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        fabClickedLayoutParams.setMargins(fabXLocation + 80, fabYLocation, 0, 0);
        goFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TransitionManager.go(scene2);
                //goFAB.setLayoutParams(fabClickedLayoutParams);
                Shuttle shuttle = new Shuttle(tripType);
                shuttle.findTrip(startingLocationSpinner.getSelectedItemPosition(),
                        destinationSpinner.getSelectedItemPosition(), tripTime);

                //Toast toast = new Toast(context);
                //toast.makeText(context, shuttle.Arrival, Toast.LENGTH_LONG);
                //toast.show();

            }
        });

    }
    
    private int setFormattedTime(int hour, int minute){
        String tripTimeString;
     //if the minute is less than 10 then the leading 0 is not shown
        if (minute < 10) {
            timeTextView.setText("0"+ minute);
        } else
            timeTextView.setText("" + minute);
        tripTimeString = "" + minute;
        tripTime = Integer.parseInt("" + hour + timeTextView.getText());

        //Get meridian from 24 hour format and convert hour to 12-hour-time
        if (hour>12){ //PM
            meridianTextView.setText("PM");
            timeTextView.setText((hour-12) + ":" +timeTextView.getText());
            tripTimeString =  (hour-12) + tripTimeString;
        }else if(hour<12 && hour > 0) { //AM
            meridianTextView.setText("AM");
            timeTextView.setText(hour + ":" + timeTextView.getText());
            tripTimeString =  (hour) + tripTimeString;
        }else if(hour == 0){ //midnight
            meridianTextView.setText("AM");
            timeTextView.setText(12 + ":" + timeTextView.getText());
            tripTimeString =  (12) + tripTimeString;
        }else if(hour==12){ //noon
            meridianTextView.setText("PM");
            timeTextView.setText(12 + ":" + timeTextView.getText());
            tripTimeString =  (12) + tripTimeString;
        }
                        
        return(Integer.parseInt(tripTimeString));
    
    }

}//End of class
