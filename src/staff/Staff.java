package staff;

import time.Schedule;

public abstract class Staff {
    public Schedule getShifts() {
        return new Schedule();
    }
}
