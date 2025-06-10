package main;

import member.MemberManager;
import time.TimeManager;

public class CommunityCentreRunner {
    private static MemberManager memberManager = new MemberManager();
    private static TimeManager timeManager = new TimeManager();
    private static StaffManager staffManager = new StaffManager();
    private static FacilityManager facilityManager = new FacilityManager();

    public static MemberManager getMemberManager() {
        return memberManager;
    }

    public static TimeManager getTimeManager() {
        return timeManager;
    }
    
    public static StaffManager getStaffManager() {
        return staffManager;
    }

    public static TimeManager getFacilityManager() {
        return facilityManager;
    }

    public static void main(String[] args) {

    }
}
