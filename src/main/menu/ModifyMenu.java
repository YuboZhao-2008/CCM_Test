/**
 * ModifyMenu
 * contains the menu to modify modify objects
 *
 * @author
 * @since
 */

package main.menu;

import java.util.Scanner;

import event.EventManager;
import facility.Facility;
import facility.FacilityManager;
import main.CommunityCentreRunner.MenuStatus;
import main.ValidateInput;
import member.MemberManager;
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
        System.out.println("-");
        System.out.println("(0) Back");

        int modifyChoice = ValidateInput.menu(4);
        main.CommunityCentreRunner.separate();

        switch (modifyChoice) {
            case 1 -> {
                System.out.println("Enter member ID");
                int fid = ValidateInput.posInt();
            }
            case 2 -> {
                System.out.println("Enter staff ID");
                int fid = ValidateInput.posInt();
            }
            case 3 -> {
                System.out.println("Enter facility ID");
                int fid = ValidateInput.posInt();

                Facility fac = facilityManager.searchById(fid);
                if (fac != null)
                    System.out.println(fac);
                else
                    System.out.println("Facility with ID " + fid + " not found.");
            }
            case 4 -> {
                System.out.println("Enter event ID");
                int fid = ValidateInput.posInt();
            }
            case 0 -> {
                return MenuStatus.BACK;
            }
        }

        return MenuStatus.CONTINUE;
    }
}
