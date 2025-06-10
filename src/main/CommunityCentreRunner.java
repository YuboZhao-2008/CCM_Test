package main;

import member.MemberManager;
import time.TimeManager;
import staff.StaffManager;
import facility.FacilityManager;
import event.EventManager;

public class CommunityCentreRunner {
    private static MemberManager memberManager = new MemberManager();
    private static TimeManager timeManager = new TimeManager();
    private static StaffManager staffManager = new StaffManager();
    private static FacilityManager facilityManager = new FacilityManager();
    private static EventManager eventManager = new EventManager();

    public static MemberManager getMemberManager() {
        return memberManager;
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

    public static EventManager getEventManager() {
        return eventManager;
    }

    public static void main(String[] args) {

    }
}
