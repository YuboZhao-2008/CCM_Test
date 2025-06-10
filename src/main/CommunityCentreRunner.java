package main;

import event.EventManager;
import facility.FacilityManager;
import member.MemberManager;
import staff.StaffManager;
import time.TimeManager;

public class CommunityCentreRunner {
    public static final String EVENTS_FILEPATH = "data/events.txt";
    public static final String FACILITIES_FILEPATH = "data/facilites.txt";
    public static final String MEMBERS_FILEPATH = "data/members.txt";
    public static final String STAFF_FILEPATH = "data/staff.txt";

    private static MemberManager memberManager = new MemberManager(MEMBERS_FILEPATH);
    private static TimeManager timeManager = new TimeManager();
    private static EventManager eventManager = new EventManager(EVENTS_FILEPATH);
    private static FacilityManager facilityManager = new FacilityManager(FACILITIES_FILEPATH);
    private static StaffManager staffManager = new StaffManager(STAFF_FILEPATH);

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
    }
}
