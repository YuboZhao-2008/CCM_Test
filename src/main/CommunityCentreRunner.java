package main;

import event.EventManager;
import facility.FacilityManager;
import member.AdultMember;
import member.Member.PlanType;
import member.MemberManager;
import member.YouthMember;
import staff.StaffManager;
import time.TimeManager;

public class CommunityCentreRunner {
    public static final String EVENTS_FILEPATH = "data/events.txt";
    public static final String FACILITIES_FILEPATH = "data/facilities.txt";
    public static final String MEMBERS_FILEPATH = "data/members.txt";
    public static final String STAFF_FILEPATH = "data/staff.txt";

    private static MemberManager memberManager = new MemberManager(MEMBERS_FILEPATH);
    private static TimeManager timeManager = new TimeManager();
    private static EventManager eventManager = new EventManager();
    private static FacilityManager facilityManager = new FacilityManager();
    private static StaffManager staffManager = new StaffManager();

    public static MemberManager getMemberManager() {
        return memberManager;
    }

    public static EventManager getEventManager() {
        return eventManager;
    }

    public static TimeManager getTimeManager() {
        return timeManager;
    }

    public static StaffManager getStaffManager() {
        return staffManager;
    }

    public static FacilityManager getFacilityManager() {
        return facilityManager;
    }

    public static void main(String[] args) {
        System.out.println("Hello world");

        AdultMember member = new AdultMember(30, "John Doe", PlanType.ANNUAL_BASE, "647-999-9999", "494 Chair Rd.", 1000, 400);
        memberManager.addMember(member);
        YouthMember member2 = new YouthMember(4, "Baby Boy", PlanType.ANNUAL_BASE, member);
        memberManager.addMember(member2);

        System.out.println(memberManager.searchById(0));
        System.out.println(memberManager.searchById(1));

        System.out.println();
    }
}
