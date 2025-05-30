package member;
import time.*;
import event.*;
import facility.*;

public abstract class Member {
    public double BIWEEKLY_BASE;
    public double MONTHLY_BASE;
    public double ANNUAL_BASE;

    protected int id;
    protected int age;
    protected String name;
    protected Schedule registrations;

    enum PlanType {
        BIWEEKLY_BASE,
        MONTHLY_BASE,
        ANNUAL_BASE
    }

    public Member (int id, String name, PlanType) {
        this.id = id;
        this.name = name;
    }

    public double calculateBill()   {

    }

    public String membershipDetails()   {

    }

    public void advanceHours(int hours)   {

    }

    public void printBill() {

    }

    public String toString() {

    }

    public boolean registerFor(Event eventName)   {

    }
}