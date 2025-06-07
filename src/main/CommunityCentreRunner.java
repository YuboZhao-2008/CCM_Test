package main;

import member.MemberManager;
import time.TimeManager;

public class CommunityCentreRunner {
    private static MemberManager memberManager = new MemberManager();
    private static TimeManager timeManager = new TimeManager();

    public static MemberManager getMemberManager() {
        return memberManager;
    }

    public static TimeManager getTimeManager() {
        return timeManager;
    }

    public static void main(String[] args) {

    }
}
