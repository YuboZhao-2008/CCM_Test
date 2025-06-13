/**
 * ModifyMenu
 * contains the menu to modify modify objects
 *
 * @author Sean Yang
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

public class ModifyMenu {
    public static Scanner scan = main.CommunityCentreRunner.scan;
    public static MemberManager memberManager = main.CommunityCentreRunner.getMemberManager();
    public static StaffManager staffManager = main.CommunityCentreRunner.getStaffManager();
    public static FacilityManager facilityManager = main.CommunityCentreRunner.getFacilityManager();
    public static EventManager eventManager = main.CommunityCentreRunner.getEventManager();

    // show the menu
    public static MenuStatus show() {
        System.out.println("What would you like to modify?");
        System.out.println("(1) Modify Member");
        System.out.println("(2) Modify Staff");
        System.out.println("(3) Modify Facility");
        System.out.println("(4) Modify Event");
        System.out.println("<0> Back");

        int modifyChoice = ValidateInput.menu(4);
        main.CommunityCentreRunner.separate();

        switch (modifyChoice) {
            case 1 -> {
                System.out.println("Enter member ID");
                int mid = ValidateInput.posInt();
                Member member = memberManager.searchById(mid);
                if (member != null) {
                    System.out.println(member);
                    main.CommunityCentreRunner.separate();
                } else {
                    System.out.println("Member with ID #" + mid + " not found.");
                }
            }
            case 2 -> {
                System.out.println("Enter staff ID");
                int sid = ValidateInput.posInt();
                Staff staff = staffManager.searchById(sid);
                if (staff != null) {
                    System.out.println(staff);
                    main.CommunityCentreRunner.separate();
                } else {
                    System.out.println("Staff with ID #" + sid + " not found.");
                }
            }
            case 3 -> {
                System.out.println("Enter facility ID");
                int fid = ValidateInput.posInt();

                Facility facility = facilityManager.searchById(fid);
                if (facility != null) {
                    System.out.println(facility);
                    main.CommunityCentreRunner.separate();
                } else
                    System.out.println("Facility with ID " + fid + " not found.");
            }
            case 4 -> {
                System.out.println("Enter event ID");
                int eid = ValidateInput.posInt();
                Event event = eventManager.searchById(eid);
                if (event != null) {
                    System.out.println(event);
                    main.CommunityCentreRunner.separate();
                } else {
                    System.out.println("Event with ID #" + eid + " not found.");
                }
            }
            case 0 -> {
                return MenuStatus.BACK;
            }
        }

        return MenuStatus.CONTINUE;
    }
}
