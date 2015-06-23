package activity;

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

import com.github.clans.fab.FloatingActionButton;

import java.util.Calendar;

import russellhowell.ushuttle.R;

public class OnCampusFragment extends Fragment {

    Context context;
    private static int DIALOG_ID;
    int hour;
    int minute;
    private TextView timeTextView;
    private ImageView imageView;
    private FloatingActionButton goFAB;
    private RelativeLayout.LayoutParams fabLayoutParams;
    private RelativeLayout.LayoutParams fabClickedLayoutParams;

    private Scene scene1;
    private Scene scene2;
    private Calendar calender;
    private ViewGroup sceneRoot;

  //  public BasicTransitionFragment newInstance(){return new BasicTransitionFragment();}

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
        View rootView = inflater.inflate(R.layout.fragment_on_campus_root, container, false);
        assert rootView != null;

        imageView = (ImageView) rootView.findViewById(R.id.onCampusImageView);
        goFAB = (FloatingActionButton) rootView.findViewById(R.id.goFabOnCampus);

        sceneRoot = (ViewGroup) rootView.findViewById(R.id.on_campus_scene_root);

        scene1 = new Scene(sceneRoot,(ViewGroup) sceneRoot.findViewById(R.id.onCampusLayout));

        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.fragment_on_campus2, getActivity());




        //call spinner creation method
        Spinner startingLocationSpinner =(Spinner) rootView.findViewById(R.id.on_campus_start_loc_spinner);
        populateSpinners(startingLocationSpinner, context, R.array.on_campus_locations );

        Spinner destinationSpinner = (Spinner) rootView.findViewById(R.id.on_campus_end_loc_spinner);
        populateSpinners(destinationSpinner, context,R.array.on_campus_locations);



        //Set displayed time as current system time
        timeTextView = (TextView) rootView.findViewById(R.id.on_campus_time_text);
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

            }
        });

    }

}//End of class
