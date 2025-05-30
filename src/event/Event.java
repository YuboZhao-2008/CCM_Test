package event;

import time.TimeBlock;
import time.TimeBlock.Month;

public abstract class Event {
    public TimeBlock getTimeBlock() {
        return new TimeBlock(1, Month.JAN, 1);
    }
}
