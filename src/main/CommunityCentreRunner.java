/**
 * CommunityCentreRunner
 * runs and implements all features of TimeManager, EventManager, MemberManager, FacilityManager,
 * and StaffManager, in a GUI for user interaction.
 *
 * @author Mansour Abdelsalam, Sean Yang, Yubo Zhao
 * @version 1.1
 * @since 2025-06-11
 */

package main;

// import scanner
import java.util.Scanner;

// import folders
import event.*;
import facility.*;
import member.*;
import staff.*;
import time.*;

import member.Member.PlanType;
import time.TimeBlock.Month;

public class CommunityCentreRunner {
    // file paths
    public static final String EVENTS_FILEPATH = "data/events.txt";
    public static final String FACILITIES_FILEPATH = "data/facilites.txt";
    public static final String MEMBERS_FILEPATH = "data/members.txt";
    public static final String STAFF_FILEPATH = "data/staff.txt";

    // initialize managers
    private static MemberManager memberManager = new MemberManager();
    private static TimeManager timeManager = new TimeManager();
    private static EventManager eventManager = new EventManager();
    private static FacilityManager facilityManager = new FacilityManager();
    private static StaffManager staffManager = new StaffManager();


    // scanner
    public static Scanner scan = new Scanner(System.in);

    // separator for formatting
    public static final String separator = "------------------------------------------/------------------------------------------";

    // accessor methods for managers
    public static MemberManager getMemberManager() {
        return memberManager;
    }

    public static EventManager getEventManager() {
        return eventManager;
    }

    public static TimeManager getTimeManager() {
        return timeManager;
    }

    public static StaffManager getStaffManager() {
        return staffManager;
    }

    public static FacilityManager getFacilityManager() {
        return facilityManager;
    }

    public static int numberInputValidation() {
        int choice = -1;
        
        while (choice < 0) {
            // input
            System.out.print(" > ");
            String userInput = scan.nextLine();
            // validate the input to a choice
            try {
                choice = Integer.parseInt(userInput);
            } catch (NumberFormatException nfe) {
                System.out.println("Must enter a number");
            }
        }
        
        System.out.println(); // blank line
        System.out.println(separator);
        System.out.println(); // blank line

        return choice;
    }

    public static int menuInputValidation(int max) {
        int choice = -1;
        
        while (choice < 0) {
            // input
            System.out.print(" > ");
            String userInput = scan.nextLine();
            // validate the input to a choice
            try {
                choice = Integer.parseInt(userInput);
                if (choice > max) {
                    choice = -1;
                    System.out.println("Not an option");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Must enter a number");
            }
        }
        
        System.out.println(); // blank line
        System.out.println(separator);
        System.out.println(); // blank line

        return choice;
    }
    
    public static void main(String[] args) {
        // variables
        TimeBlock currentTime = timeManager.getCurrentTime(); // initialize current time at beginning of program

        // separator for formatting
        String userInput = "";
        int choice = -1;
        
        // GUI main loop ----
        System.out.println(separator);
        System.out.println("It is currently "+timeManager.getCurrentTime()+".");
        System.out.println(separator);
        System.out.println(); // blank line
        
        // show events occuring soon
        System.out.println("Events occuring within this month:");
        TimeBlock next_month = new TimeBlock(currentTime.getYear(), TimeBlock.nextMonth(currentTime.getMonth()), currentTime.getDay());
        if (!eventManager.printFutureEventsBefore(next_month)) {
            System.out.println("No events.");
        }
        System.out.println(); // blank line
        System.out.println("Events ongoing now:");
        if (!eventManager.printOngoingEvents()) {
            System.out.println("No events.");
        }
        System.out.println(); // blank line

        System.out.println(separator);
        System.out.println(); // blank line
        System.out.println("What would you like to do?");
        System.out.println("(1) List");
        System.out.println("(2) Search");
        System.out.println("(3) Create");
        System.out.println("(4) Modify");
        System.out.println("(5) Book");
        System.out.println("(6) Delete");
        System.out.println("(7) Advance Time");
        
        choice = menuInputValidation(6);

        // if valid input
        switch (choice) {
            case 1:
                facilityManager.printAllFacilities();

                System.out.println("What would you like to view?");
                // all options for listing
                // list facilities
                System.out.println("(1) Facilities by ID");
                System.out.println("(2) Sports Facilities by Rating");
                System.out.println("(3) Meeting Faciltiies by Size");
                System.out.println("(4) Facilties by Cost to Rent");
                System.out.println("-");
                
                // list events
                System.out.println("(5) Events by ID");
                System.out.println("(6) Events in Chronological Order");
                System.out.println("(7) Future Events");
                System.out.println("(8) Past Events");
                System.out.println("-");

                // list members
                System.out.println("(9) Members by ID");
                System.out.println("(10) Members by Alphabet");
                System.out.println("(12) Members by Bill");
                System.out.println("-");

                // list staff
                System.out.println("(13) Staff by ID");
                System.out.println("(14) Staff by Alphabet");
                System.out.println("(15) Full-Time Staff by Pay");
                System.out.println("-");

                // back
                System.out.println("(0) Back");

                break;
            case 2:
                System.out.println("What would you like to search for?");
                // all options for searching
                
                // search facilities
                System.out.println("(1) Facility using ID");
                System.out.println("(2) Facility using Room Num.");
                System.out.println("(3) Facilities above Capacity");
                System.out.println("(4) Facilities Available using Time");
                System.out.println("(5) Facilties Available using Time above Capacity");
                System.out.println("-");
                
                // search events
                System.out.println("(6) Event using ID");
                System.out.println("(7) Events using Time");
                System.out.println("-");

                // search members
                System.out.println("(8) Members using ID");
                System.out.println("(9) Members using Name");
                System.out.println("-");
   
                // search staff
                System.out.println("(10) Staff using ID");
                System.out.println("(11) Staff using Name");
                System.out.println("-");

                // back
                System.out.println("(0) Back");
                // code

                break;
            case 3:
                System.out.println("What would you like to create?");
                // all options for creating

                // create facilities
                System.out.println("(1) New Sports Facility");
                System.out.println("(2) New Meeting Facility");
                System.out.println("-");

                // create events
                System.out.println("(3) New Competition Event");
                System.out.println("(4) New Fundraiser Event");
                System.out.println("-");

                // create members
                System.out.println("(5) New Adult Member");
                System.out.println("(6) New Youth Member");
                System.out.println("-");

                // create staff
                System.out.println("(7) New Full-Time Staff");
                System.out.println("(8) New Part-Time Staff");
                System.out.println("-");

                // back
                System.out.println("(0) Back");
                
                break;
            case 4:
                // first search for the event to book for
                // then list options to book a member or assign a staff
                break;
            case 5:
                System.out.println("What would you like to modify?");
                // all options for modifying

                // modify facilities
                System.out.println("(1) Facility Room Num.");
                System.out.println("(2) Sports Facility Rating");
                System.out.println("(3) Meeting Facility Size");
                System.out.println("(4) ");

                // modify events

                // modify members

                // modify staff

                // back
                System.out.println("(0) Back");

                break;
            case 6:
                System.out.println("What would you like to delete?");
                System.out.println("1. Delete Member");
                System.out.println("2. Delete Staff");
                System.out.println("3. Delete Facility");
                System.out.println("4. Delete Event");

                // all options for deleting
                // delete facilities
                // delete events
                // delete members
                // delete staff
                // back
                System.out.println("(0) Back");
                break;
            case 7:
                System.out.println("What time would you like to advance to?");
                // options to advance time
                // advance time by specified hours
                // advance time by an hour
                // advance time to a date
                // back
                System.out.println("(0) Back");
                break;
        }
        scan.close();
    }
}
