/**
 * BookMenu
 * contains the menu to book an event
 *
 * @author Mansour Abdelsalam
 * @since 2025-06-12
 */

package main.menu;

import main.CommunityCentreRunner.MenuStatus;
import main.ValidateInput;

public class BookMenu {
    // show the menu
    public static MenuStatus show() {
        System.out.println("What would you like to book?");
        System.out.println("(1) Book a Member");
        System.out.println("(2) ");
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
