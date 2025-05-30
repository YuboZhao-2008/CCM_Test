package member;
import time.*;
import staff.*;
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

    public Member (int id, String name, Schedule registrations) {
        this.id = id;
        this.name = name;
        this.registrations = registrations;
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