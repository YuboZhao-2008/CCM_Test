package staff;

/**
 * Represents a part-time staff member who is paid by the hour.
 * Calculates weekly pay up to a maximum number of hours.
 *
 * @author Yubo-Zhao
 * @version 1.0
 * @since 2025-06-03
 */
public class PartTimeStaff extends Staff {
    /**
     * The number of hours this staff has worked in the week.
     */
    private int hoursWorked;

    /**
     * The hourly wage for this staff.
     */
    private double hourlySalary;

    /**
     * The maximum number of hours this staff is allowed to work per week.
     */
    private int maxWeeklyHours;

    /**
     * Constructs a new PartTimeStaff with the given name and pay parameters.
     *
     * @param name           the staff member's name
     * @param hoursWorked    the initial hours worked
     * @param hourlySalary   the wage per hour
     * @param maxWeeklyHours the cap on hours per week
     */
    public PartTimeStaff(String name, int hoursWorked, double hourlySalary, int maxWeeklyHours) {
        super(name);
        this.hoursWorked = hoursWorked;
        this.hourlySalary = hourlySalary;
        this.maxWeeklyHours = maxWeeklyHours;
    }

    /**
     * Calculates the weekly pay. If hoursWorked exceeds maxWeeklyHours,
     * it is capped before calculating pay.
     *
     * @return the total pay for this week
     */
    @Override
    public double calculatePay() {
        if (hoursWorked > maxWeeklyHours) {
            hoursWorked = maxWeeklyHours;
        }
        return hoursWorked * hourlySalary;
    }

    /**
     * Prints this staff member's payroll information to stdout.
     */
    @Override
    public void printPayroll() {
        System.out.println("Your pay is: " + calculatePay() +
                " | Hours worked: " + hoursWorked);
    }

    /**
     * Returns a string representation of this PartTimeStaff.
     *
     * @return a string containing ID, name, salary info, and max hours
     */
    @Override
    public String toString() {
        return "Staff #" + id +
                " | name: " + name +
                " | hourly salary: " + hourlySalary +
                " | max weekly hours: " + maxWeeklyHours;
    }

    /**
     * Gets the number of hours worked.
     *
     * @return hours worked this week
     */
    public int getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Sets the number of hours worked.
     *
     * @param hoursWorked the new hours worked value
     */
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    /**
     * Gets the hourly wage.
     *
     * @return the hourly salary
     */
    public double getHourlySalary() {
        return hourlySalary;
    }

    /**
     * Sets the hourly wage.
     *
     * @param hourlySalary the new hourly salary
     */
    public void setHourlySalary(double hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    /**
     * Gets the maximum weekly hours allowed.
     *
     * @return the max weekly hours
     */
    public int getMaxWeeklyHours() {
        return maxWeeklyHours;
    }

    /**
     * Sets the maximum weekly hours allowed.
     *
     * @param maxWeeklyHours the new max weekly hours
     */
    public void setMaxWeeklyHours(int maxWeeklyHours) {
        this.maxWeeklyHours = maxWeeklyHours;
    }
}
