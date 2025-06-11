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

import java.util.Scanner;

import event.EventManager;
import facility.FacilityManager;
import member.Member;
import member.MemberManager;
import staff.StaffManager;
import time.TimeBlock;
import time.TimeManager;

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
    public static final String SEPARATOR = "------------------------------------------/------------------------------------------";

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
        System.out.println(SEPARATOR);
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
        System.out.println(SEPARATOR);
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
        System.out.println(SEPARATOR);
        System.out.println("It is currently " + timeManager.getCurrentTime() + ".");
        System.out.println(SEPARATOR);
        System.out.println(); // blank line

        // show events occuring soon
        System.out.println("Events occuring within this month:");
        TimeBlock next_month = new TimeBlock(currentTime.getYear(), TimeBlock.nextMonth(currentTime.getMonth()),
                currentTime.getDay());
        if (!eventManager.printFutureEventsBefore(next_month)) {
            System.out.println("No events.");
        }
        System.out.println(); // blank line
        System.out.println("Events ongoing now:");
        if (!eventManager.printOngoingEvents()) {
            System.out.println("No events.");
        }
        System.out.println(); // blank line

        System.out.println(SEPARATOR);
        System.out.println(); // blank line
        System.out.println("What would you like to do?");
        System.out.println("(1) List");
        System.out.println("(2) Search");
        System.out.println("(3) Create");
        System.out.println("(4) Modify");
        System.out.println("(5) Delete");
        System.out.println("(6) Advance Time");

        choice = menuInputValidation(6);

        // if valid input
        switch (choice) {
            case 1 -> {
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
                System.out.println("(11) Members by Bill");
                System.out.println("-");

                // list staff
                System.out.println("(12) Staff by ID");
                System.out.println("(13) Staff by Alphabet");
                System.out.println("(14) Full-Time Staff by Pay");
                System.out.println("-");

                // back
                System.out.println("(0) Back");

                int viewChoice = menuInputValidation(15);

                switch (viewChoice) {
                    case 1 -> {
                        if (!facilityManager.printAllFacilities()) {
                            System.out.println("No facilities");
                        }
                    }
                    case 2 -> {
                        if (!facilityManager.printSportsFacilitiesByRating()) {
                            System.out.println("No sports facilities");
                        }
                    }
                    case 3 -> {
                        if (!facilityManager.printMeetingFacilitiesBySize()) {
                            System.out.println("No meeting facilities");
                        }
                    }
                    case 4 -> {
                        if (!facilityManager.printFacilitiesByCost()) {
                            System.out.println("No facilities");
                        }
                    }
                    case 5 -> {
                        if (!eventManager.printAllEvents()) {
                            System.out.println("No events");
                        }
                    }
                    case 6 -> {
                        if (!eventManager.printEventsChronologically()) {
                            System.out.println("No events");
                        }
                    }
                    case 7 -> {
                        if (!eventManager.printFutureEvents()) {
                            System.out.println("No future events");
                        }
                    }
                    case 8 -> {
                        if (!eventManager.printPastEvents()) {
                            System.out.println("No past events");
                        }
                    }
                    case 9 -> {
                        if (!memberManager.printAllMembers()) {
                            System.out.println("No members");
                        }
                    }
                    case 10 -> {
                        if (!memberManager.printAlphabetical()) {
                            System.out.println("No members");
                        }
                    }
                    case 11 -> {
                        if (!memberManager.printAllBills()) {
                            System.out.println("No bills");

                        }
                    }
                    case 12 -> {
                        if (!staffManager.printAllStaff()) {
                            System.out.println("No members");
                        }
                    }
                    case 13 -> {
                        if (!staffManager.printAlphabetical()) {
                            System.out.println("No staff");
                        }
                    }
                    case 14 -> {
                        if (!staffManager.printAllPayrolls()) {
                            System.out.println("No payrolls");

                        }
                    }
                }
            }

            case 2 -> {
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
                // search staff
                System.out.println("(10) Members ");
                // back
            }
            case 3 -> System.out.println("What would you like to create?");
            // all options for creating
            // create facilities
            // create events
            // create members
            // create staff
            // back
            case 4 -> System.out.println("What would you like to modify?");
            // all options for modifying
            // modify facilities
            // modify events
            // modify members
            // modify staff
            // back
            case 5 -> {
                System.out.println("What would you like to delete?");
                System.out.println("1. Delete Member");
                System.out.println("2. Delete Staff");
                System.out.println("3. Delete Facility");
                System.out.println("4. Delete Event");
                int deleteChoice = scan.nextInt();
                scan.nextLine();
                switch (deleteChoice) {
                    case 1:
                        System.out.print("Enter the member ID to delete: ");
                        int memberId = scan.nextInt();
                        scan.nextLine();

                        Member membertoDelete = CommunityCentreRunner.getMemberManager().searchById(memberId);
                        if (membertoDelete != null) {
                            CommunityCentreRunner.getMemberManager().members.remove(membertoDelete);
                            System.out.println("Member with ID " + memberId + " has been deleted.");
                        } else {
                            System.out.println("Member with ID " + memberId + " not found.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter the staff ID to delete: ");
                        int staffId = scan.nextInt();
                }
                // all options for deleting
                // delete facilities
                // delete events
                // delete members
                // delete staff
                // back
            }
            case 6 -> System.out.println("What time would you like to advance to?");
            // options to advance time
            // advance time by specified hours
            // advance time by an hour
            // advance time to a date
        }
        scan.close();
    }
}
