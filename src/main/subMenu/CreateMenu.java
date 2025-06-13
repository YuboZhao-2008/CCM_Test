/**
 * CreateMenu
 * menu to create objects
 *
 * @author Yubo Zhao, Sean Yang
 * @since June 12, 2025
 */

package main.subMenu;

import java.util.Scanner;

import event.Competition;
import event.Event;
import event.Fundraiser;
import facility.Facility;
import facility.FacilityManager;
import facility.MeetingFacility;
import facility.SportsFacility;
import main.CommunityCentreRunner.MenuStatus;
import main.ValidateInput;
import member.AdultMember;
import member.Member;
import member.Member.PlanType;
import member.MemberManager;
import member.YouthMember;
import staff.FullTimeStaff;
import staff.PartTimeStaff;
import staff.StaffManager;
import time.TimeBlock;

public class CreateMenu {
    public static Scanner scan = main.CommunityCentreRunner.scan;
    public static MemberManager memberManager = main.CommunityCentreRunner.getMemberManager();
    public static StaffManager staffManager = main.CommunityCentreRunner.getStaffManager();
    public static FacilityManager facilityManager = main.CommunityCentreRunner.getFacilityManager();

    public static MenuStatus show() {
        System.out.println("What would you like to create?");
        System.out.println("(1) Create Member");
        System.out.println("(2) Create Staff");
        System.out.println("(3) Create Facility");
        System.out.println("(4) Create Event");
        System.out.println("<0> Back");

        int createChoice = ValidateInput.menu(4);
        main.CommunityCentreRunner.separate();

        switch (createChoice) {
            case 1 -> {
                // --- CREATE MEMBER ---
                System.out.println("Age");
                int age = ValidateInput.posInt();

                System.out.println("Full name");
                System.out.print(" > ");
                String name = scan.nextLine().trim().toUpperCase();

                PlanType planType = ValidateInput.planType();

                if (age >= Member.ADULT_AGE) {
                    System.out.println("Contact phone ###-###-####");
                    System.out.print(" > ");
                    String contactPhone = scan.nextLine().trim();

                    System.out.println("Address");
                    System.out.print(" > ");
                    String address = scan.nextLine().trim();

                    memberManager.addMember(
                            new AdultMember(age, name, planType, contactPhone, address));
                    System.out.println("Adult member created successfully.");
                } else {
                    System.out.println("Guardian ID or name");
                    System.out.print(" > ");
                    String guardianIdOrName = scan.nextLine().trim().toUpperCase();
                    Member guardian = null;

                    try {
                        int id = Integer.parseInt(guardianIdOrName);
                        guardian = memberManager.searchById(id);
                    } catch (NumberFormatException ignored) {
                        guardian = memberManager.searchByName(guardianIdOrName);
                    }

                    if (guardian instanceof AdultMember adult) {
                        memberManager.addMember(
                                new YouthMember(age, name, planType, adult));
                        System.out.println("Youth member created successfully.");
                    } else {
                        System.out.println("Adult member not found.");
                    }
                }
            }
            case 2 -> {
                // --- CREATE STAFF ---
                System.out.println("Full-time or Part-time?   (0) Full-time   (1) Part-time");
                int staffType = ValidateInput.menu(1);

                System.out.println("Full name");
                System.out.print(" > ");
                String staffName = scan.nextLine().trim().toUpperCase();

                if (staffType == 0) {
                    System.out.println("Years worked");
                    int years = ValidateInput.posInt();
                    staffManager.addStaff(new FullTimeStaff(staffName, years));
                } else {
                    System.out.println("Hours worked");
                    int hours = ValidateInput.posInt();

                    System.out.println("Hourly rate");
                    double rate = ValidateInput.posDouble();

                    System.out.println("Max weekly hours");
                    int maxH = ValidateInput.posInt();

                    staffManager.addStaff(
                            new PartTimeStaff(staffName, hours, rate, maxH));
                }
                System.out.println("Staff created successfully.");
            }
            case 3 -> {
                // --- CREATE FACILITY ---
                System.out.println("Meeting or Sports?   (0) Meeting Room   (1) Sports Facility");
                int type = ValidateInput.menu(1);

                System.out.println("Enter room number");
                int room = ValidateInput.posInt();

                System.out.println("Enter max capacity");
                int cap = ValidateInput.posInt();

                if (type == 0) {
                    double size;
                    do {
                        System.out.println("Enter room size (positive):");
                        System.out.print(" > ");
                        size = ValidateInput.posDouble();
                        if (size <= 0)
                            System.out.println("Room size must be positive.");
                    } while (size <= 0);

                    facilityManager.addFacility(
                            new MeetingFacility(room, cap, size));
                } else {
                    double rating;
                    do {
                        System.out.println("Enter facility rating (0–10):");
                        System.out.print(" > ");
                        rating = ValidateInput.posDouble();
                        if (rating < 0 || rating > 10)
                            System.out.println("Rating must be between 0 and 10 inclusive.");
                    } while (rating < 0 || rating > 10);

                    facilityManager.addFacility(
                            new SportsFacility(room, cap, rating));
                }
                System.out.println("Facility created successfully.");
            }
            case 4 -> {
                // --- CREATE EVENT ---
                System.out.println("Event type   (0) Competition   (1) Fundraiser");
                int eventType = ValidateInput.menu(1);

                System.out.println("Enter prize (or goal) amount:");
                double prizeOrGoal = ValidateInput.posDouble();

                double participationCost = 0;
                if (eventType == 0) {
                    System.out.println("Enter participation cost:");
                    participationCost = ValidateInput.posDouble();
                }

                Facility fac;
                do {
                    System.out.println("Enter facility ID:");
                    System.out.print(" > ");
                    int facId = ValidateInput.posInt();
                    fac = facilityManager.searchById(facId);
                    if (fac == null)
                        System.out.println("Facility not found, try again.");
                } while (fac == null);

                // date portion
                TimeBlock d = ValidateInput.date();

                // duration choice
                System.out.println("Enter duration type   (0) Set duration   (1) All-day");
                int durationChoice = ValidateInput.menu(1);

                TimeBlock tb;
                if (durationChoice == 1) {
                    tb = new TimeBlock(
                            d.getYear(), d.getMonth(), d.getDay(),
                            0.0, 24.0);
                } else {
                    double[] sd = ValidateInput.startDuration();
                    tb = new TimeBlock(
                            d.getYear(), d.getMonth(), d.getDay(),
                            sd[0], sd[1]);
                }

                Member host = null;
                System.out.println("Enter hosting type   (0) Member ID   (1) None");
                int hostingChoice = ValidateInput.menu(1);
                if (hostingChoice == 0) {
                    do {
                        System.out.println("Enter host (member) ID:");
                        System.out.print(" > ");
                        int hid = ValidateInput.posInt();
                        host = memberManager.searchById(hid);
                        if (host == null)
                            System.out.println("Member not found, try again.");
                    } while (host == null);
                }

                Event newEvent = (eventType == 0)
                        ? new Competition(fac, tb, host, prizeOrGoal, participationCost)
                        : new Fundraiser(fac, tb, host, prizeOrGoal);

                main.CommunityCentreRunner.getEventManager().book(newEvent);
                System.out.println("Event created successfully.");
            }
            case 0 -> {
                return MenuStatus.BACK;
            }
        }

        return MenuStatus.CONTINUE;
    }
}
