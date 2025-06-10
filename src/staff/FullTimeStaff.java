package staff;

public class FullTimeStaff extends Staff{
    public static final double BASE_SALARY = 60000;
    public static final double YEARLY_RAISE = 0.05;

    private int yearsWorked;

    public FullTimeStaff (String name, int yearsWorked) {
        super(name);
        this.yearsWorked = yearsWorked;
    }

    public double calculatePay() {
        return (BASE_SALARY + YEARLY_RAISE * yearsWorked)/12;
    }

    public void printPayroll()  {
        System.out.println("Your yearly pay is: " + calculatePay() + " years worked: " + yearsWorked);
    }

    public String toString()    {
        return ("Employee id: " + this.getId() + " name is: " + this.getName() + " years worked: " + yearsWorked);
    }

    public int getYearsWorked() {
        return yearsWorked;
    }

    public void setYearsWorked(int yearsWorked)    {
        this.yearsWorked = yearsWorked;
    }
}
