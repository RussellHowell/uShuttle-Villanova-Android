package timetable;

/**
 * Created by Russell on 6/25/2015.
 */
public class Shuttle {

    public int Arrival, Departure;
    private ShuttleTimetable shuttle_info;
    public boolean tripFound;

    public Shuttle(int tripTypeFlag) {

        //Set up correct shuttle timetable based on trip type
        shuttle_info = new ShuttleTimetable(tripTypeFlag);
    }


    public void findTrip(int start_loc, int end_loc, int time_int) {

        //This switch case finds the arrival time based on
        //the "arrive by" and destination parameters set by the user

		/* tripType: OnCampus			: 0
		 * 			 On Campus Saturday : 1
		 * 			 Off Campus 		: 2
		 * 			 Main Line			: 3
		 * 			 KOP Mall			: 4
		 */

        switch(end_loc){

            case 0:
                Arrival = findInArray(time_int, shuttle_info.loc0);
               // Arrival_String = shuttle_info.s0;
                break;
            case 1:
                Arrival = findInArray(time_int, shuttle_info.loc1);
                //Arrival_String = shuttle_info.s1;
                break;
            case 2:
                Arrival = findInArray(time_int, shuttle_info.loc2);
               // Arrival_String = shuttle_info.s2;
                break;
            case 3:
                Arrival = findInArray(time_int, shuttle_info.loc3);
               // Arrival_String = shuttle_info.s3;
                break;

            case 4:
                Arrival = findInArray(time_int, shuttle_info.loc4);
               // Arrival_String = shuttle_info.s4;
                break;

            case 5:
                Arrival = findInArray(time_int, shuttle_info.loc5);
                //Arrival_String = shuttle_info.s5;
                break;
            case 6:
                Arrival = findInArray(time_int, shuttle_info.loc6);
                //Arrival_String = shuttle_info.s6;
                break;

        }

        //This switch case finds the departure time based on
        //the arrival time and departure location parameter set by the user
        switch(start_loc){

            case 0:
                Departure = findInArray(Arrival, shuttle_info.loc0);
                //Departure_String = shuttle_info.s0;
                break;
            case 1:
                Departure = findInArray(Arrival, shuttle_info.loc1);
                //Departure_String = shuttle_info.s1;
                break;
            case 2:
                Departure = findInArray(Arrival, shuttle_info.loc2);
                //Departure_String = shuttle_info.s2;
                break;
            case 3:
                Departure = findInArray(Arrival, shuttle_info.loc3);
                //Departure_String = shuttle_info.s3;
                break;

            case 4:
                Departure = findInArray(Arrival, shuttle_info.loc4);
                //Departure_String = shuttle_info.s4;
                break;

            case 5:
                Departure= findInArray(Arrival, shuttle_info.loc5);
                //Departure_String = shuttle_info.s5;
                break;
            case 6:
                Departure = findInArray(Arrival, shuttle_info.loc6);
                //Departure_String = shuttle_info.s6;
                break;

        }
    }

    //this method finds the closest lower value in the passed array
    private int findInArray(int time_int, int[] timeArray) {
        int i = 0;
        int result=0;


        try{
            outerloop:
            while(i<= timeArray.length + 1){

                if(timeArray[i]>time_int){
                    result = timeArray[i-1];
                    tripFound = true;
                    break outerloop;
                }
                else if (timeArray[i]==time_int){
                    result = timeArray[i];
                    tripFound = true;
                    break outerloop;
                }else if(i + 1 == timeArray.length){
                    result = timeArray[i];
                    tripFound = false;
                    break outerloop;
                }//end of if - else
                ++i;
            }//end of while loop
        }catch(Exception e){
            tripFound = false;
        }

        return result;
    }



}
