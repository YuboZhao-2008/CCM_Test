/**
 * SearchMenu
 * contains the menu to search objects
 *
 * @author Yubo Zhao, Sean Yang
 * @since June 12, 2025
 */

package main.menu;

import java.util.Scanner;

import event.Event;
import event.EventManager;
import facility.Facility;
import facility.FacilityManager;
import main.CommunityCentreRunner.MenuStatus;
import main.ValidateInput;
import member.Member;
import member.MemberManager;
import staff.Staff;
import staff.StaffManager;
import time.TimeBlock;

public class SearchMenu {
    public static Scanner scan = main.CommunityCentreRunner.scan;
    public static MemberManager memberManager = main.CommunityCentreRunner.getMemberManager();
    public static StaffManager staffManager = main.CommunityCentreRunner.getStaffManager();
    public static FacilityManager facilityManager = main.CommunityCentreRunner.getFacilityManager();
    public static EventManager eventManager = main.CommunityCentreRunner.getEventManager();

    public static MenuStatus show() {
        System.out.println("What would you like to search for?");
        // all options for searching
        // facilities
        System.out.println("(1) Facility using ID");
        System.out.println("(2) Facility using Room Num.");
        System.out.println("(3) Facilities above Capacity");
        System.out.println("(4) Facilities Available within Time Range");
        System.out.println("(5) Facilities Available within Time Range above Capacity");
        System.out.println("-");
        // search events
        System.out.println("(6) Event using ID");
        System.out.println("(7) Events within Time Range");
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

        int searchChoice = ValidateInput.menu(11);
        main.CommunityCentreRunner.separate();

        switch (searchChoice) {
            case 1 -> {
                System.out.println("Enter facility ID");
                int fid = ValidateInput.posInt();
                Facility fac = facilityManager.searchById(fid);
                if (fac != null)
                    System.out.println(fac);
                else
                    System.out.println("Facility with ID " + fid + " not found.");
            }
            case 2 -> {
                System.out.println("Enter room number");
                int roomNum = ValidateInput.posInt();
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
            case 3 -> {
                System.out.println("Enter minimum capacity");
                int minCap = ValidateInput.posInt();
                if (!facilityManager.printFacilitesWithCapacity(minCap)) {
                    System.out.println("No matching facilities found.");
                }
            }
            case 4, 5 -> {
                // build a TimeBlock
                TimeBlock date = ValidateInput.date();
                double[] startDur;

                if (searchChoice == 4) {
                    startDur = ValidateInput.startDuration();
                } else {
                    startDur = ValidateInput.startDuration();
                }

                double start = startDur[0];
                double dur = startDur[1];

                TimeBlock tb = new TimeBlock(date, start, dur);

                if (searchChoice == 4) {
                    if (!facilityManager.printAvailableFacilities(tb)) {
                        System.out.println("No matching facilities found.");
                    }
                } else {
                    System.out.println("Enter minimum capacity");
                    int minCap = ValidateInput.posInt();
                    if (!facilityManager.printAvailableFacilities(tb, minCap)) {
                        System.out.println("No matching facilities found.");
                    }
                }
            }
            case 6 -> {
                System.out.println("Enter event ID");
                int eid = ValidateInput.posInt();
                Event ev = eventManager.searchById(eid);
                if (ev != null)
                    System.out.println(ev);
                else
                    System.out.println("Event with ID " + eid + " not found.");
            }
            case 7 -> {
                // build a TimeBlock
                TimeBlock date = ValidateInput.date();
                double[] startDur = ValidateInput.startDuration();
                double start = startDur[0];
                double dur = startDur[1];
                TimeBlock tb = new TimeBlock(date, start, dur);
                eventManager.printEventsWithin(tb);
            }
            case 8 -> {
                System.out.println("Enter member ID");
                int mid = ValidateInput.posInt();
                Member m = memberManager.searchById(mid);
                if (m != null)
                    System.out.println(m);
                else
                    System.out.println("Member with ID " + mid + " not found.");
            }
            case 9 -> {
                System.out.println("Enter member name");
                System.out.print(" > ");
                String mName = scan.nextLine();
                Member member = memberManager.searchByName(mName);
                if (member != null) {
                    System.out.println(member);
                } else {
                    System.out.println("No member named \"" + mName + "\".");
                }
            }
            case 10 -> {
                System.out.println("Enter staff ID");
                int sid = ValidateInput.posInt();
                Staff s = staffManager.searchById(sid);
                if (s != null)
                    System.out.println(s);
                else
                    System.out.println("Member with ID " + sid + " not found.");
            }
            case 11 -> {
                System.out.println("Enter staff name");
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

        return MenuStatus.CONTINUE;
    }
}
