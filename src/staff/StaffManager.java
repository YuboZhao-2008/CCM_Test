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

import java.util.ArrayList;
import java.util.List;
// import scanner
import java.util.Scanner;

// import folders
import event.*;
import facility.*;
import member.*;
import staff.*;
import time.*;

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
        System.out.println("It is currently " + timeManager.getCurrentTime() + ".");
        System.out.println(separator);
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

        System.out.println(separator);
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

                int viewChoice = menuInputValidation(15);

                switch (viewChoice) {
                    case 1:
                        for (Facility facility : facilityManager)
                            break;
                    case 2:

                }

                break;
            case 2: {
                System.out.println("What would you like to search for?");
                System.out.println("1. Facility by ID");
                System.out.println("2. Facility by Room Number");
                System.out.println("3. Facilities Available at a Given Time");
                System.out.println("4. Facilities Available at a Given Time AND Minimum Capacity");
                System.out.println("5. Event by ID");
                System.out.println("6. Member by Name");
                System.out.println("7. Staff by Name");
                System.out.println("0. Go back to main menu");
                int searchChoice = menuInputValidation(7);

                switch (searchChoice) {
                    case 1: {
                        System.out.print("Enter facility ID: ");
                        int fid = numberInputValidation();
                        Facility fac = CommunityCentreRunner
                                .getFacilityManager()
                                .searchById(fid);
                        if (fac != null)
                            System.out.println(fac);
                        else
                            System.out.println("Facility with ID " + fid + " not found.");
                        break;
                    }
                    case 2: {
                        System.out.print("Enter room number: ");
                        int roomNum = numberInputValidation();
                        Facility found = null;
                        for (Facility f : CommunityCentreRunner.getFacilityManager().getFacilities()) {
                            if (f.getRoomNum() == roomNum) {
                                found = f;
                                break;
                            }
                        }
                        if (found != null)
                            System.out.println(found);
                        else
                            System.out.println("Facility with room number " + roomNum + " not found.");
                        break;
                    }
                    case 3:
                    case 4: {
                        System.out.print("Enter year: ");
                        int year = numberInputValidation();
                        System.out.print("Enter month (1–12): ");
                        int m = numberInputValidation();
                        TimeBlock.Month month = TimeBlock.Month.values()[m - 1];
                        System.out.print("Enter day: ");
                        int day = numberInputValidation();
                        System.out.print("Enter start hour (e.g. 14.5): ");
                        double start = Double.parseDouble(scan.nextLine());
                        System.out.print("Enter duration (hours): ");
                        double dur = Double.parseDouble(scan.nextLine());
                        TimeBlock tb = new TimeBlock(year, month, day, start, dur);

                        ArrayList<Facility> avail = CommunityCentreRunner
                                .getFacilityManager()
                                .availableFacilities(tb);

                        if (searchChoice == 4) {
                            System.out.print("Enter minimum capacity: ");
                            int minCap = numberInputValidation();
                            avail.removeIf(f -> f.getMaxCapacity() < minCap);
                        }

                        if (avail.isEmpty()) {
                            System.out.println("No matching facilities found.");
                        } else {
                            for (Facility f : avail)
                                System.out.println(f);
                        }
                        break;
                    }
                    case 5: {
                        System.out.print("Enter event ID: ");
                        int eid = numberInputValidation();
                        Event ev = CommunityCentreRunner
                                .getEventManager()
                                .searchById(eid);
                        if (ev != null)
                            System.out.println(ev);
                        else
                            System.out.println("Event with ID " + eid + " not found.");
                        break;
                    }
                    case 6: {
                        System.out.print("Enter member name: ");
                        String mName = scan.nextLine();
                        List<Member> members = CommunityCentreRunner
                                .getMemberManager()
                                .searchByName(mName);
                        if (members.isEmpty()) {
                            System.out.println("No members named \"" + mName + "\".");
                        } else {
                            for (Member mbr : members)
                                System.out.println(mbr);
                        }
                        break;
                    }
                    case 7: {
                        System.out.print("Enter staff name: ");
                        String sName = scan.nextLine();
                        List<Staff> matches = new ArrayList<>();
                        for (Staff s : CommunityCentreRunner.getStaffManager().getStaffs()) {
                            if (s.getName().equalsIgnoreCase(sName)) {
                                matches.add(s);
                            }
                        }
                        if (matches.isEmpty()) {
                            System.out.println("No staff named \"" + sName + "\".");
                        } else {
                            for (Staff s : matches)
                                System.out.println(s);
                        }
                        break;
                    }
                    case 0:
                        System.out.println("Returning to main menu.");
                        break;
                    default: {
                        System.out.println("Invalid choice. Please try again.");
                        break;
                    }
                }
                break;
            }

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
                System.out.println("1. Modify Member");
                System.out.println("2. Modify Staff");
                System.out.println("3. Modify Facility");
                System.out.println("4. Modify Event");
                System.out.println("0. Go back to main menu");
                int modifyChoice = menuInputValidation(4);

            case 5:
                // you guys add it
                break;
            case 6:
                System.out.println("What would you like to delete?");
                System.out.println("1. Delete Member");
                System.out.println("2. Delete Staff");
                System.out.println("3. Delete Facility");
                System.out.println("4. Delete Event");
                System.out.println("0. Go back to main menu");
                int deleteChoice = menuInputValidation(4);

                switch (deleteChoice) {
                    case 1:
                        System.out.print("Enter the member ID to delete: ");
                        int memberId = numberInputValidation();
                        boolean removedMember = CommunityCentreRunner
                                .getMemberManager()
                                .removeMember(memberId);
                        if (removedMember) {
                            System.out.println("Member with ID " + memberId + " has been deleted.");
                        } else {
                            System.out.println("Member with ID " + memberId + " not found.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter the staff ID to delete: ");
                        int staffId = numberInputValidation();
                        boolean removedStaff = CommunityCentreRunner
                                .getStaffManager()
                                .removeStaff(staffId);
                        if (removedStaff) {
                            System.out.println("Staff with ID " + staffId + " has been deleted.");
                        } else {
                            System.out.println("Staff with ID " + staffId + " not found.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter the facility ID to delete: ");
                        int facilityId = numberInputValidation();
                        boolean removedFacility = CommunityCentreRunner
                                .getFacilityManager()
                                .removeFacility(facilityId);
                        if (removedFacility) {
                            System.out.println("Facility with ID " + facilityId + " has been deleted.");
                        } else {
                            System.out.println("Facility with ID " + facilityId + " not found.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter the event ID to delete: ");
                        int eventId = numberInputValidation();
                        boolean cancelled = CommunityCentreRunner
                                .getEventManager()
                                .cancelEvent(eventId); // uses cancelEvent to clean up registrations
                        if (cancelled) {
                            System.out.println("Event with ID " + eventId + " has been deleted.");
                        } else {
                            System.out.println("Event with ID " + eventId + " not found.");
                        }
                        break;
                    case 0:
                        System.out.println("Returning to main menu.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
                break;
            case 7:
                System.out.println("What time would you like to advance to?");
                // options to advance time
                // advance time by specified hours
                // advance time by an hour
                // advance time to a date
                System.out.println("(1) Advance by specified hours");
                System.out.println("(2) Advance by an hour");
                System.out.println("(3) Advance to a specific date");
                System.out.println("(0) Back");
                int advanceChoice = menuInputValidation(3);
                switch (advanceChoice) {
                    case 1:
                        System.out.print("Enter the number of hours to advance: ");
                        int hours = numberInputValidation();
                        timeManager.advanceHours(hours);
                        System.out.println("Time advanced by " + hours + " hours.");
                        break;
                    case 2:
                        timeManager.advanceHour();
                        System.out.println("Time advanced by one hour.");
                        break;
                    case 3:
                        System.out.print("Enter the year: ");
                        int year = numberInputValidation();

                        System.out.print("Enter the month (1-12): ");
                        int monthInput = numberInputValidation();

                        // Convert int (1–12) to enum (0-based index)
                        TimeBlock.Month month;
                        if (monthInput >= 1 && monthInput <= 12) {
                            month = TimeBlock.Month.values()[monthInput - 1];
                        } else {
                            System.out.println("Invalid month. Please enter a number between 1 and 12.");
                            break;
                        }

                        System.out.print("Enter the day: ");
                        int day = numberInputValidation();

                        timeManager.advanceToDay(year, month, day);
                        System.out.println("Time set to " + timeManager.getCurrentTime() + ".");
                        break;

                    case 0:
                        System.out.println("Returning to main menu.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
                break;
        }
        scan.close();
    }
}
