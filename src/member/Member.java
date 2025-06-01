package member;
import time.*;
import event.*;
import facility.*;

public abstract class Member {
    public static final double BIWEEKLY_BASE = 20.00;
    public static final double MONTHLY_BASE = 35.00;
    public static final double ANNUAL_BASE = 350.00;

    protected int id;
    protected int age;
    protected String name;
    protected Schedule registrations;
    protected PlanType planType;

    public enum PlanType {
        BIWEEKLY_BASE,
        MONTHLY_BASE,
        ANNUAL_BASE
    }

    public Member (int id, String name, PlanType plantype) {
        this.id = id;
        this.name = name;
        this.planType = plantype;
        this.registrations = new Schedule();
    }

    public abstract double calculateBill();

    public String membershipDetails() {
        return id
                + " | "
                + name
                + " | "
                + planType
                + " | $"
                + String.format("%.2f", calculateBill());
    }

    public void advanceHours(int hours)   {
        registrations.advanceHours(hours);
    }

    public void printBill() {
        System.out.println(membershipDetails());
    }

    public String toString() {
        return membershipDetails();
    }

    public boolean registerFor(Event eventName)   {

    }
}