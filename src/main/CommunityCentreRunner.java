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

import static java.util.Collections.newSetFromMap;

// import folders
import event.*;
import facility.*;
import member.*;
import staff.*;
import time.*;

// import enums
import member.Member.PlanType;
import time.TimeBlock.Month;

// import scanner
import java.util.Scanner;

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
    
    public static void main(String[] args) {
        /*
        System.out.println("Hello world");

        AdultMember member = new AdultMember(30, "John Doe", PlanType.ANNUAL_BASE, "647-999-9999", "494 Chair Rd.", 1000, 400);
        memberManager.addMember(member);
        YouthMember member2 = new YouthMember(4, "Baby Boy", PlanType.ANNUAL_BASE, member);
        memberManager.addMember(member2);

        System.out.println(memberManager.searchById(0));
        System.out.println(memberManager.searchById(1));

        FullTimeStaff staff = new FullTimeStaff("John Cena", 20);
        staffManager.addStaff(staff);

        System.out.println(staffManager.searchById(0));

        SportsFacility sportsFacility = new SportsFacility(101, 50, 9);
        facilityManager.addFacility(sportsFacility);

        TimeBlock timeBlock = new TimeBlock(2000, Month.APR, 10, 12.0, 2.0);
        Competition competition = new Competition(sportsFacility, timeBlock, member, 1000, 10);
        eventManager.book(competition);
        eventManager.searchById(0).registerParticipant(member2);

        System.out.println(eventManager.searchById(0));
        System.out.println(member2);
        System.out.println(member.getId());

        eventManager.cancelEvent(0);

System.out.println(eventManager.searchById(0));*/


        // temp
        SportsFacility facility1 = new SportsFacility(101, 50, 10);
        facilityManager.addFacility(facility1);



        // variables
        TimeBlock currentTime = timeManager.getCurrentTime(); // initialize current time at beginning of program
        Scanner scan = new Scanner(System.in);

        // separator for formatting
        String separator = "------------------------------------------/------------------------------------------";
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
        System.out.println("(5) Delete");
        System.out.println("(6) Advance Time");
        System.out.print(" > ");
        // input
        userInput = scan.nextLine();
        // validate the input to a choice
        choice = Integer.parseInt(userInput);
        
        System.out.println(); // blank line
        System.out.println(separator);
        System.out.println(); // blank line

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
                System.out.println("(14) Staff by ");
                // back
                break;
            case 2:
                System.out.println("What would you like to search for?");
                // all options for searching
                // search facilities
                // search events
                // search members
                // search staff
                // back
                break;
            case 3:
                System.out.println("What would you like to create?");
                // all options for creating
                // create facilities
                // create events
                // create members
                // create staff
                // back
                break;
            case 4:
                System.out.println("What would you like to modify?");
                // all options for modifying
                // modify facilities
                // modify events
                // modify members
                // modify staff
                // back
                break;
            case 5:
                System.out.println("What would you like to delete?");
                // all options for deleting
                // delete facilities
                // delete events
                // delete members
                // delete staff
                // back
                break;
            case 6:
                System.out.println("What time would you like to advance to?");
                // options to advance time
                // advance time by specified hours
                // advance time by an hour
                // advance time to a date
                break;
        }

        scan.close();
    }
}
