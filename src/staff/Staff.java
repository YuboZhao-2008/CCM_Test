/**
 * Yubo
 */

package staff;

import event.Event;
import time.Schedule;
import time.TimeBlock;

/**
 * Abstract base class for all staff members.
 * Tracks basic staff information, scheduled shifts, and defines
 * methods for pay calculation and payroll printing.
 *
 * @author Yubo-Zhao
 * @version 1.0
 * @since 2025-06-04
 */
public abstract class Staff {
    /**
     * Unique identifier for this staff.
     */
    protected int id = 0;

    /**
     * Full name of the staff member.
     */
    protected String name;

    /**
     * Schedule of shifts (events) assigned to this staff.
     */
    protected Schedule shifts;

    /**
     * Constructs a new Staff with the given name.
     * Initializes an empty shift schedule.
     *
     * @param name the staff member's name
     */
    public Staff(String name) {
        this.name = name;
        this.shifts = new Schedule();
    }

    /**
     * Calculate this staff member's pay for the current period.
     * Implementation depends on the concrete staff type.
     *
     * @return the calculated pay amount
     */
    public abstract double calculatePay();

    /**
     * Schedule a staff shift by assigning an event.
     *
     * @param event the Event to schedule
     * @return true if the shift was added successfully, false if it conflicts
     */
    public boolean scheduleShift(Event event) {
        return this.shifts.add(event);
    }

    /**
     * Print payroll information to standard output.
     * Implementation depends on the concrete staff type.
     */
    public abstract void printPayroll();

    /**
     * Returns a string representation of this staff member.
     * Implementation depends on the concrete staff type.
     *
     * @return a descriptive string for this staff
     */
    @Override
    public abstract String toString();

    /**
     * Checks if the staff is available during the given time block.
     *
     * @param block the TimeBlock to check
     * @return true if no scheduled shift conflicts, false otherwise
     */
    public boolean isAvailable(TimeBlock block) {
        return shifts.isBlockFree(block);
    }

    /**
     * Gets this staff member's ID.
     *
     * @return the staff ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets this staff member's unique ID.
     *
     * @param id the ID to assign
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the staff member's name.
     *
     * @return the staff name
     */
    public String getName() {
        return name;
    }

    // accessor for shifts
    public Schedule getShifts() {
        return shifts;
    }

    /**
     * Updates the staff member's name.
     *
     * @param name the new name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
