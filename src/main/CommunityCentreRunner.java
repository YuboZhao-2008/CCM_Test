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
// import folders
import event.EventManager;
import facility.FacilityManager;
import member.MemberManager;
import staff.StaffManager;
import time.TimeBlock;
import time.TimeBlock.Month;
import time.TimeManager;

public class CommunityCentreRunner {
    // file paths
    public static final String EVENTS_FILEPATH = "data/events.txt";
    public static final String FACILITIES_FILEPATH = "data/facilities.txt";
    public static final String MEMBERS_FILEPATH = "data/members.txt";
    public static final String STAFFS_FILEPATH = "data/staffs.txt";
    public static final String TIME_FILEPATH = "data/time.txt";

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

    public static int posIntInputValidation() {
        int choice = -1;

        while (choice < 0) {
            // input
            System.out.print(" > ");
            String userInput = scan.nextLine().trim();
            // validate the input to a choice
            try {
                choice = Integer.parseInt(userInput);
            } catch (NumberFormatException nfe) {
                System.out.println("Must enter an integer greater than or equal to 0");
            }
        }

        System.out.println(); // blank line
        System.out.println(SEPARATOR);
        System.out.println(); // blank line

        return choice;
    }

    public static double doubleInputValidation() {
        boolean valid = false;
        double choice = 0;

        while (!valid) {
            // input
            System.out.print(" > ");
            String userInput = scan.nextLine().trim();
            // validate the input to a choice
            try {
                choice = Double.parseDouble(userInput);
                valid = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Must enter a number");
            }
        }

        System.out.println(); // blank line
        System.out.println(SEPARATOR);
        System.out.println(); // blank line

        return choice;
    }

    public static TimeBlock dateInputValidation() {
        boolean valid = false;
        int year = -1;
        Month month = null;
        int day = 0;

        while (month == null) {
            // input
            System.out.println("Enter the month (3-letter abbreviation, e.g. JAN)");
            System.out.print(" > ");
            String userInput = scan.nextLine().trim().toUpperCase();
            if (userInput.length() != 3) {
                System.out.println("Must enter 3 characters");
                continue;
            }
            // validate the input to a month
            try {
                month = TimeBlock.Month.valueOf(userInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Must enter a valid month");
            }
        }

        while (day < 1 || day > 31) {
            // input
            System.out.println("Enter the day (1-31)");
            System.out.print(" > ");
            String userInput = scan.nextLine().trim();
            // validate the input to a choice
            try {
                day = Integer.parseInt(userInput);
                if (day < 1 || day > 31) {
                    System.out.println("Must enter a valid day");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Must enter a valid day");
            }
        }

        while (year < 0) {
            // input
            System.out.println("Enter the year (YYYY)");
            System.out.print(" > ");
            String userInput = scan.nextLine().trim();
            // validate the input to a choice
            try {
                year = Integer.parseInt(userInput);
                if (year < 0) {
                    System.out.println("Must enter a valid year");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Must enter a valid year");
            }
        }

        TimeBlock date = new TimeBlock(year, month, day);
        if (!date.isValid()) {
            System.out.println("Invalid date. Please try again.");
            return dateInputValidation();
        }

        System.out.println(); // blank line
        System.out.println(SEPARATOR);
        System.out.println(); // blank line

        return date;
    }

    public static int menuInputValidation(int max) {
        int choice = -1;

        while (choice < 0) {
            // input
            System.out.print(" > ");
            String userInput = scan.nextLine().trim();
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

    public static void guiLoop() {
        TimeBlock currentTime = timeManager.getCurrentTime(); // update current time

        // GUI main loop ----
        System.out.println(SEPARATOR);
        System.out.println("It is currently " + timeManager.getCurrentTime() + ".");
        System.out.println(SEPARATOR);
        System.out.println(); // blank line

        // show events occurring soon
        System.out.println("Events occurring within this month:");
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
        System.out.println("(5) Book");
        System.out.println("(6) Delete");
        System.out.println("(7) Advance Time");

        int choice = menuInputValidation(7);

        // if valid input
        switch (choice) {
            case 1 -> {
                facilityManager.printAllFacilities();

                System.out.println("What would you like to view?");
                // all options for listing facilities
                System.out.println("(1) Facilities by ID");
                System.out.println("(2) Sports Facilities by Rating");
                System.out.println("(3) Meeting Facilities by Size");
                System.out.println("(4) Facilities by Cost to Rent");
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
                            System.out.println("No facilities found.");
                        }
                    }
                    case 2 -> {
                        if (!facilityManager.printSportsFacilitiesByRating()) {
                            System.out.println("No sports facilities found.");
                        }
                    }
                    case 3 -> {
                        if (!facilityManager.printMeetingFacilitiesBySize()) {
                            System.out.println("No meeting facilities found.");
                        }
                    }
                    case 4 -> {
                        if (!facilityManager.printFacilitiesByCost()) {
                            System.out.println("No facilities found.");
                        }
                    }
                    case 5 -> {
                        if (!eventManager.printAllEvents()) {
                            System.out.println("No events found.");
                        }
                    }
                    case 6 -> {
                        if (!eventManager.printEventsChronologically()) {
                            System.out.println("No events found.");
                        }
                    }
                    case 7 -> {
                        if (!eventManager.printFutureEvents()) {
                            System.out.println("No future events found.");
                        }
                    }
                    case 8 -> {
                        if (!eventManager.printPastEvents()) {
                            System.out.println("No past events found.");
                        }
                    }
                    case 9 -> {
                        if (!memberManager.printAllMembers()) {
                            System.out.println("No members found");
                        }
                    }
                    case 10 -> {
                        if (!memberManager.printAlphabetical()) {
                            System.out.println("No members found.");
                        }
                    }
                    case 11 -> {
                        if (!memberManager.printAllBills()) {
                            System.out.println("No member bills found.");

                        }
                    }
                    case 12 -> {
                        if (!staffManager.printAllStaff()) {
                            System.out.println("No members found.");
                        }
                    }
                    case 13 -> {
                        if (!staffManager.printAlphabetical()) {
                            System.out.println("No staff found");
                        }
                    }
                    case 14 -> {
                        if (!staffManager.printAllPayrolls()) {
                            System.out.println("No payrolls found.");

                        }
                    }
                    case 0 -> System.out.println("Returning to main menu.");
                }
            }

            case 2 -> {
                System.out.println("What would you like to search for?");
                // all options for searching
                // facilities
                System.out.println("(1) Facility using ID");
                System.out.println("(2) Facility using Room Num.");
                System.out.println("(3) Facilities above Capacity");
                System.out.println("(4) Facilities Available using Time");
                System.out.println("(5) Facilities Available using Time above Capacity");
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
                // back
                System.out.println("(0) Back");
                // code
            }

            case 3 -> {
                System.out.println("What would you like to create?");
                // create facilities
                // create events
                // create members
                // create staff
                // back
                System.out.println("(0) Back");
            }

            case 4 -> {
                // first search for the event to book for
                // then list options to book a member or assign a staff
            }

            case 5 -> {
                System.out.println("What would you like to modify?");
                System.out.println("(1) Modify Member");
                System.out.println("(2) Modify Staff");
                System.out.println("(3) Modify Facility");
                System.out.println("(4) Modify Event");
                System.out.println("-");
                System.out.println("(0) Back");
                int modifyChoice = menuInputValidation(4);
            }

            case 6 -> {
                System.out.println("What would you like to delete?");
                System.out.println("(1) Delete Member");
                System.out.println("(2) Delete Staff");
                System.out.println("(3) Delete Facility");
                System.out.println("(4) Delete Event");
                System.out.println("-");
                System.out.println("(0) Back");
                int deleteChoice = menuInputValidation(4);

                switch (deleteChoice) {
                    case 1 -> {
                        System.out.print("Enter the member ID to delete: ");
                        int memberId = posIntInputValidation();
                        boolean removedMember = CommunityCentreRunner
                                .getMemberManager()
                                .removeMember(memberId);
                        if (removedMember) {
                            System.out.println("Member with ID " + memberId + " has been deleted.");
                        } else {
                            System.out.println("Member with ID " + memberId + " not found.");
                        }
                    }
                    case 2 -> {
                        System.out.print("Enter the staff ID to delete: ");
                        int staffId = posIntInputValidation();
                        boolean removedStaff = staffManager.removeStaff(staffId);
                        if (removedStaff) {
                            System.out.println("Staff with ID " + staffId + " has been deleted.");
                        } else {
                            System.out.println("Staff with ID " + staffId + " not found.");
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter the facility ID to delete: ");
                        int facilityId = posIntInputValidation();
                        boolean removedFacility = facilityManager.removeFacility(facilityId);
                        if (removedFacility) {
                            System.out.println("Facility with ID " + facilityId + " has been deleted.");
                        } else {
                            System.out.println("Facility with ID " + facilityId + " not found.");
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter the event ID to delete: ");
                        int eventId = posIntInputValidation();
                        boolean cancelled = eventManager.cancelEvent(eventId); // uses cancelEvent to clean up
                        // registrations
                        if (cancelled) {
                            System.out.println("Event with ID " + eventId + " has been deleted.");
                        } else {
                            System.out.println("Event with ID " + eventId + " not found.");
                        }
                    }
                    case 0 -> System.out.println("Returning to main menu.");
                }
            }
            case 7 -> {
                System.out.println("What time would you like to advance to?");
                // options to advance time
                System.out.println("(1) Advance by specified hours");
                System.out.println("(2) Advance by an hour");
                System.out.println("(3) Advance to a specific date");
                System.out.println("-");
                System.out.println("(0) Back");
                int advanceChoice = menuInputValidation(3);
                switch (advanceChoice) {
                    case 1 -> {
                        System.out.print("Enter the number of hours to advance: ");
                        double hours = doubleInputValidation();
                        timeManager.advanceHours(hours);
                        System.out.println("Time advanced by " + hours + " hours.");
                    }
                    case 2 -> {
                        timeManager.advanceHour();
                        System.out.println("Time advanced by one hour.");
                    }
                    case 3 -> {
                        TimeBlock dateInput = dateInputValidation();
                        timeManager.advanceToDay(dateInput.getYear(), dateInput.getMonth(), dateInput.getDay());
                        System.out.println("Time set to " + timeManager.getCurrentTime() + ".");
                    }
                    case 0 -> System.out.println("Returning to main menu.");
                }
            }
        }
    }

    public static void main(String[] args) {
        // load data from files=
        memberManager = new MemberManager(MEMBERS_FILEPATH);
        eventManager = new EventManager(EVENTS_FILEPATH);
        facilityManager = new FacilityManager(FACILITIES_FILEPATH);
        staffManager = new StaffManager(STAFFS_FILEPATH);
        timeManager = new TimeManager(TIME_FILEPATH);

        boolean exit = false;

        while (!exit) {
            // run the GUI loop
            guiLoop();

            // ask if user wants to continue
            System.out.println("Enter (Q) to quit or any other key to continue.");
            String continueChoice = scan.nextLine().trim().toUpperCase();
            if (continueChoice.equals("Q")) {
                eventManager.save(EVENTS_FILEPATH);
                memberManager.save(MEMBERS_FILEPATH);
                staffManager.save(STAFFS_FILEPATH);
                facilityManager.save(FACILITIES_FILEPATH);
                timeManager.save(TIME_FILEPATH);
                exit = true; // exit the loop
            }
        }

        scan.close();
    }
}
