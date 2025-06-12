/**
 * CreateMenu
 * menu to create objects
 *
 * @author Yubo Zhao, Sean Yang
 * @since June 12, 2025
 */

package main.menu;

import java.util.Scanner;

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

public class CreateMenu {
    public static Scanner scan = main.CommunityCentreRunner.scan;
    public static MemberManager memberManager = main.CommunityCentreRunner.getMemberManager();
    public static StaffManager staffManager = main.CommunityCentreRunner.getStaffManager();
    public static FacilityManager facilityManager = main.CommunityCentreRunner.getFacilityManager();

    // show the menu
    public static MenuStatus show() {
        System.out.println("What would you like to create?");
        System.out.println("(1) Create Member");
        System.out.println("(2) Create Staff");
        System.out.println("(3) Create Facility");
        System.out.println("-");
        System.out.println("(0) Back");

        int createChoice = ValidateInput.menu(3);
        main.CommunityCentreRunner.separate();

        switch (createChoice) {
            case 1 -> {
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
                int staffType = ValidateInput.menu(1);

                System.out.println("Full name");
                System.out.print(" > ");
                String staffName = scan.nextLine().trim().toUpperCase();

                if (staffType == 0) {
                    System.out.println("Years worked");
                    int years = ValidateInput.posInt();
                    staffManager.addStaff(
                            new FullTimeStaff(staffName, years));
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
                    System.out.println("Enter room size");
                    double size = ValidateInput.posDouble();
                    facilityManager.addFacility(
                            new MeetingFacility(room, cap, size));
                } else {
                    System.out.println("Enter facility rating");
                    double rating = ValidateInput.posDouble();
                    facilityManager.addFacility(
                            new SportsFacility(room, cap, rating));
                }
                System.out.println("Facility created successfully.");
            }
            case 0 -> {
                return MenuStatus.BACK;
            }
        }

        return MenuStatus.CONTINUE;
    }
}
