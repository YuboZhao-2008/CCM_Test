package staff;

import event.Event;
import time.Schedule;
import time.TimeBlock;

public abstract class Staff {
    private int id;
    private String name;
    private Schedule shifts;

    public Staff(String name) {
        this.name = name;
        this.shifts = new Schedule();
    }

    public abstract double calculatePay();

    public boolean scheduleShift(Event event) {
        return this.shifts.add(event);
    }

    public abstract void printPayroll();

    public abstract String toString();

    public boolean isAvailalbe(TimeBlock block) {
        return shifts.isBlockFree(block);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
