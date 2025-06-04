package member;

import time.Schedule;

public abstract class Member {
    public Schedule getRegistrations() {
        return new Schedule();
    }

    public String getName() {
        return "Member Name";
    }
}
