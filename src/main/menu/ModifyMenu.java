/**
 * ModifyMenu
 * contains the menu to modify modify objects
 *
 * @author
 * @since
 */

package main.menu;

import main.CommunityCentreRunner.MenuStatus;
import main.ValidateInput;

public class ModifyMenu {
    // shows the menu
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

            }
            case 2 -> {

            }
            case 3 -> {

            }
            case 4 -> {

            }
            case 0 -> {
                return MenuStatus.BACK;
            }
        }

        return MenuStatus.CONTINUE;
    }
}
