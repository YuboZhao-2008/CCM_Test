package main;

import event.EventManager;
import facility.FacilityManager;
import member.MemberManager;

public class CommunityCentreRunner {
    private static MemberManager memberManager = new MemberManager();
    private static EventManager eventManager = new EventManager();
    private static FacilityManager facilityManager = new FacilityManager();

    public static final String EVENTS_FILEPATH = "data/events.txt";
    public static final String FACILITIES_FILEPATH = "data/facilites.txt";
    public static final String MEMBERS_FILEPATH = "data/members.txt";
    public static final String STAFF_FILEPATH = "data/staff.txt";

    public static MemberManager getMemberManager() {
        return memberManager;
    }

    public static EventManager getEventManager() {
        return eventManager;
    }

    public static FacilityManager getFacilityManager() {
        return facilityManager;
    }

    public static void main(String[] args) {
    }
}
