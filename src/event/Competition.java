package event;

import facility.Facility;
import member.Member;
import time.TimeBlock;

public class Competition extends Event {
    public Competition(Facility facility, TimeBlock timeBlock, Member host) {
        super(facility, timeBlock, host);
    }

    public void advanceHours(int hours) {

    }
}
