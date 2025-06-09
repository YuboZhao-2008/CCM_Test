package main;

import event.EventManager;
import member.MemberManager;

public class CommunityCentreRunner {
    private static MemberManager memberManager = new MemberManager();

    private static EventManager eventManager = new EventManager();

    public static MemberManager getMemberManager() {
        return memberManager;
    }

    public static EventManager getEventManager() {
        return eventManager;
    }

    public static void main(String[] args) {

    }
}
