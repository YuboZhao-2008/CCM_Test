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
// import scanner
import java.util.Scanner;

import event.Event;
// import folders
// import folders
import event.EventManager;
import facility.Facility;
import facility.FacilityManager;
import facility.MeetingFacility;
import facility.SportsFacility;
import member.AdultMember;
import member.Member;
import member.Member.PlanType;
import member.MemberManager;
import member.YouthMember;
import staff.FullTimeStaff;
import staff.PartTimeStaff;
import staff.Staff;
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

    public enum MenuStatus {
        EXIT, BACK, CONTINUE
    }

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

    public static int posIntInputValidation(boolean useSeparator) {
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

        if (useSeparator) {
            System.out.println(); // blank line
            System.out.println(SEPARATOR);
            System.out.println(); // blank line
        }

        return choice;
    }

    public static int posIntInputValidation() {
        return posIntInputValidation(true);
    }

    public static double doubleInputValidation(boolean useSeparator) {
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

        if (useSeparator) {
            System.out.println(); // blank line
            System.out.println(SEPARATOR);
            System.out.println(); // blank line
        }

        return choice;
    }

    public static double doubleInputValidation() {
        return doubleInputValidation(true);
    }

    public static PlanType planTypeValidation() {
        PlanType planType = null;

        while (planType == null) {
            // input
            System.out.println("Enter the plan type (MONTHLY/ANNUAL)");
            System.out.print(" > ");
            String userInput = scan.nextLine().trim().toUpperCase();
            // validate the input to a plan type
            try {
                planType = PlanType.valueOf(userInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Must enter a valid plan type");
            }
        }

        return planType;
    }

    public static TimeBlock dateInputValidation() {
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

        TimeBlock date = new TimeBlock(year, month, day, 0);
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

    public static MenuStatus menuLoop() {
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
        System.out.println("(8) Quit");

        int choice = menuInputValidation(8);

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
                    case 0 -> {
                        return MenuStatus.BACK;
                    }
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
                System.out.println("(10) Staff using ID");
                System.out.println("(11) Staff using Name");
                System.out.println("-");
                // back
                System.out.println("(0) Back");
                int searchChoice = menuInputValidation(7);

                switch (searchChoice) {
                    case 1 -> {
                        System.out.print("Enter facility ID: ");
                        int fid = posIntInputValidation();
                        Facility fac = facilityManager.searchById(fid);
                        if (fac != null)
                            System.out.println(fac);
                        else
                            System.out.println("Facility with ID " + fid + " not found.");
                    }
                    case 2 -> {
                        System.out.print("Enter room number: ");
                        int roomNum = posIntInputValidation();
                        Facility found = null;
                        for (Facility f : facilityManager.getFacilities()) {
                            if (f.getRoomNum() == roomNum) {
                                found = f;
                                break;
                            }
                        }
                        if (found != null)
                            System.out.println(found);
                        else
                            System.out.println("Facility with room number " + roomNum + " not found.");
                    }
                    case 3, 4 -> {
                        // build a TimeBlock
                        TimeBlock date = dateInputValidation();
                        System.out.print("Enter start hour (e.g. 14.5): ");
                        double start = doubleInputValidation(false);
                        System.out.print("Enter duration (hours): ");
                        double dur = doubleInputValidation();
                        TimeBlock tb = new TimeBlock(date, start, dur);

                        ArrayList<Facility> avail = facilityManager.availableFacilities(tb);

                        if (searchChoice == 4) {
                            System.out.print("Enter minimum capacity: ");
                            int minCap = posIntInputValidation();
                            avail.removeIf(f -> f.getMaxCapacity() < minCap);
                        }

                        if (avail.isEmpty()) {
                            System.out.println("No matching facilities found.");
                        } else {
                            for (Facility f : avail)
                                System.out.println(f);
                        }
                    }
                    case 5 -> {
                        System.out.print("Enter event ID: ");
                        int eid = posIntInputValidation();
                        Event ev = eventManager.searchById(eid);
                        if (ev != null)
                            System.out.println(ev);
                        else
                            System.out.println("Event with ID " + eid + " not found.");
                    }
                    case 6 -> {
                        System.out.println("Enter member name: ");
                        System.out.print(" > ");
                        String mName = scan.nextLine();
                        Member member = memberManager.searchByName(mName);
                        if (member == null) {
                            System.out.println("No member named \"" + mName + "\".");
                        } else {
                            System.out.println(member);
                        }
                    }
                    case 7 -> {
                        System.out.println("Enter staff name: ");
                        System.out.print(" > ");
                        String sName = scan.nextLine();
                        Staff staff = staffManager.searchByName(sName);
                        if (staff == null) {
                            System.out.println("No staff named \"" + sName + "\".");
                        } else {
                            System.out.println(staff);
                        }
                    }
                    case 0 -> {
                        return MenuStatus.BACK;
                    }
                }
            }

            case 3 -> {
                System.out.println("What would you like to create?");
                System.out.println("(1) Create Member");
                System.out.println("(2) Create Staff");
                System.out.println("(3) Create Facility");
                System.out.println("-");
                System.out.println("(0) Back");
                int createChoice = menuInputValidation(3);

                switch (createChoice) {
                    case 1 -> {
                        System.out.println("Age");
                        int age = posIntInputValidation(false);
                        System.out.println("Full name");
                        System.out.print(" > ");
                        String name = scan.nextLine().trim().toUpperCase();
                        PlanType planType = planTypeValidation();

                        if (age >= Member.ADULT_AGE) {
                            System.out.println("Contact phone ###-###-####");
                            System.out.print(" > ");
                            String contactPhone = scan.nextLine().trim();
                            System.out.println("Address");
                            System.out.print(" > ");
                            String address = scan.nextLine().trim();

                            memberManager.addMember(new AdultMember(age, name, planType, contactPhone, address));
                            System.out.println("Adult member created successfully.");
                        } else {
                            System.out.println("Guardian ID or name");
                            System.out.print(" > ");
                            String guardianIdOrName = scan.nextLine().trim().toUpperCase();
                            Member guardian;
                            try {
                                int id = Integer.parseInt(guardianIdOrName);
                                guardian = memberManager.searchById(id);
                            } catch (NumberFormatException nfe) {
                                guardian = memberManager.searchByName(guardianIdOrName);
                            }
                            if (guardian instanceof AdultMember adult) {
                                memberManager.addMember(new YouthMember(age, name, planType, adult));
                                System.out.println("Youth member created successfully.");
                            } else {
                                System.out.println("No matching adult.");
                            }
                        }
                    }
                    case 2 -> {
                        // --- CREATE STAFF ---
                        System.out.println("Full-time or Part-time?   (0) Full-time   (1) Part-time");
                        int staffType = menuInputValidation(1);

                        System.out.println("Full name");
                        System.out.print(" > ");
                        String staffName = scan.nextLine().trim().toUpperCase();

                        if (staffType == 0) {
                            System.out.println("Years worked");
                            int years = posIntInputValidation(false);
                            staffManager.addStaff(
                                    new FullTimeStaff(staffName, years));
                        } else {
                            System.out.println("Hours worked");
                            int hours = posIntInputValidation(false);

                            System.out.println("Hourly rate");
                            double rate = doubleInputValidation(false);

                            System.out.println("Max weekly hours");
                            int maxH = posIntInputValidation();

                            staffManager.addStaff(
                                    new PartTimeStaff(staffName, hours, rate, maxH));
                        }
                        System.out.println("Staff created successfully.");
                    }
                    case 3 -> {
                        // --- CREATE FACILITY ---
                        System.out.println("Meeting or Sports?   (0) Meeting Room   (1) Sports Facility");
                        int type = menuInputValidation(1);

                        System.out.println("Enter room number");
                        int room = posIntInputValidation(false);

                        System.out.println("Enter max capacity");
                        int cap = posIntInputValidation(false);

                        if (type == 0) {
                            System.out.println("Enter room size");
                            double size = doubleInputValidation();
                            facilityManager.addFacility(
                                    new MeetingFacility(room, cap, size));
                        } else {
                            System.out.println("Enter facility rating");
                            double rating = doubleInputValidation();
                            facilityManager.addFacility(
                                    new SportsFacility(room, cap, rating));
                        }
                        System.out.println("Facility created successfully.");
                    }
                    case 0 -> {
                        return MenuStatus.BACK;
                    }
                }
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
                    case 0 -> {
                        return MenuStatus.BACK;
                    }
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
                        timeManager.advanceToTimeBlock(dateInput);
                        System.out.println("Time set to " + timeManager.getCurrentTime() + ".");
                    }
                    case 0 -> {
                        return MenuStatus.BACK;
                    }
                }
            }
            case 8 -> {
                return MenuStatus.EXIT;
            }
        }

        return MenuStatus.CONTINUE;
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
            switch (menuLoop()) {
                case CONTINUE -> {
                    // ask if user wants to continue
                    System.out.print("Enter (Q) to quit or enter to continue: ");
                    String continueChoice = scan.nextLine().trim().toUpperCase();
                    if (continueChoice.equals("Q")) {
                        exit = true; // exit the loop
                        System.out.println();
                        System.out.println(SEPARATOR);
                        System.out.println();
                    }
                }
                case EXIT -> exit = true;
                case BACK -> System.out.println("Returning to main menu.\n");
            }

        }

        eventManager.save(EVENTS_FILEPATH);
        memberManager.save(MEMBERS_FILEPATH);
        staffManager.save(STAFFS_FILEPATH);
        facilityManager.save(FACILITIES_FILEPATH);
        timeManager.save(TIME_FILEPATH);

        scan.close();
    }
}
