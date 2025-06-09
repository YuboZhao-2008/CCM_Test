package member;

import event.Event;
import facility.Schedule;

public abstract class Member {
    public static final double BIWEEKLY_BASE = 20.00;
    public static final double MONTHLY_BASE   = 35.00;
    public static final double ANNUAL_BASE    = 350.00;

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

    public Member(int age, String name, PlanType planType) {
        this.age           = age;
        this.name          = name;
        this.planType      = planType;
        this.registrations = new Schedule();
    }

    public abstract double calculateBill();

    public String membershipDetails() {
        return id
                + " | age: "   + age
                + " | name: "  + name
                + " | plan: "  + planType
                + " | $"
                + String.format("%.2f", calculateBill());
    }

    public void printBill() {
        System.out.println(membershipDetails());
    }

    public String toString() {
        return membershipDetails();
    }

    public void registerFor(Event event) {
        registrations.add(event);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    public Schedule getRegistrations() {
        return registrations;
    }
}
