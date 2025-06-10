package staff;
public class PartTimeStaff extends Staff{
    private int hoursWorked;
    private double hourlySalary;
    private int maxWeeklyHours;

    public PartTimeStaff (String name, int hoursWorked, double hourlySalary, int maxWeeklyHours)    {
        super(name);
        this.hoursWorked = hoursWorked;
        this.hourlySalary = hourlySalary;
        this.maxWeeklyHours = maxWeeklyHours;
    }

    public double calculatePay()    {
        if (hoursWorked >= maxWeeklyHours)   {
            hoursWorked = maxWeeklyHours;
        }
        return hoursWorked * hourlySalary;
    }

    public void printPayroll() {
        System.out.println("Your pay is: " + calculatePay() + " years worked: " + hoursWorked);
    }

    public String toString()    {
        return ("staff id: " + this.getId() + " staff name: " + this.getName() + " employee hourly Salary: " + hourlySalary + " employee maximum hours per week: " + maxWeeklyHours);
    }

    public int getHoursWorked ()    {
        return hoursWorked;
    }

    public void setHoursWorked (int hoursWorked)    {
        this.hoursWorked = hoursWorked;
    }

    public double getHourlySalary() {
        return hourlySalary;
    }

    public void setHourlySalary (double hourlySalary)   {
        this.hourlySalary = hourlySalary;
    }

    public int getMaxWeeklyHours()  {
        return maxWeeklyHours;
    }

    public void setMaxWeeklyHours (int maxWeeklyHours)  {
        this.maxWeeklyHours = maxWeeklyHours;
    }
}
