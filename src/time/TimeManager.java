/**
 * TimeManager keeps track of the current time and controls time progression
 * 
 * @author Sean Yang
 * @since June 06, 2025
 */

package time;

import time.TimeBlock.Month;

public class TimeManager {
    private TimeBlock time;

    // default time manager constructor
    public TimeManager() {
        this.time = new TimeBlock(2025, Month.JUN, 1, 12);
    }
  
    /**
     * constructor for a time manager
     * 
     * @param time the current time
     */
    public TimeManager(TimeBlock time) {
        this.time = time;
    }

    /**
     * advances the current time by specified hours
     * 
     * @param hours a double, the number of hours
     */
    public void advanceHours(double hours) {
        double newHour = time.getEndHour() + hours;

        while (newHour >= 24) {
            Month prev_month = time.getMonth();
            int prev_year = time.getYear();

            time = new TimeBlock(time.nextDay(), 0, 0);
            newHour -= 24;

            if (prev_month != time.getMonth()) {
                // bill monthly members
                main.CommunityCentreRunner.getMemberManager().billMonthlyMembers();
                // pay part-time staff
                main.CommunityCentreRunner.getStaffManager().payPartTimeStaff();
            }
            if (prev_year != time.getYear()) {
                // bill yearly members
                main.CommunityCentreRunner.getMemberManager().billAnnualMembers();
                // pay full-time staff
                main.CommunityCentreRunner.getStaffManager().payFullTimeStaff();
            }
        }

        time = new TimeBlock(time, newHour, 0);

        main.CommunityCentreRunner.getEventManager().advanceTime(time);
    }

    /**
     * advances the time by one hour
     */
    public void advanceHour() {
        advanceHours(1);
    }

    /**
     * advances the time until a specified date
     * 
     * @param year
     * @param month
     * @param day
     */
    public void advanceToDay(int year, Month month, int day) {
        time = new TimeBlock(year, month, day, 0);
    }

    /**
     * checks if a time block is currently ongoing
     * 
     * @param timeBlock
     * @return whether the time block coincides with the current time
     */
    public boolean isOngoing(TimeBlock timeBlock) {
        if (timeBlock.isConflicting(time)) {
            return true;
        }

        return false;
    }

    /**
     * returns the current time as a time block
     * 
     * @return
     */
    public TimeBlock getCurrentTime() {
        return time;
    }
}
