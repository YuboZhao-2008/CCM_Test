/**
 * This class represents a scheduled event.
 * It contains all functions that an event needs.
 *
 * @author Mansour Abdelsalam
 * @version 1.0
 * @since 2025-05-30
 */

package event;

import facility.*;
import time.*;
import member.*;
import staff.*;

import java.util.ArrayList;
import java.io.*;

abstract class Event {
    // fields
    protected Facility facility;
    protected TimeBlock timeBlock;
    protected Member host;
    protected ArrayList<Staff> staffSupervising = new ArrayList<Staff>();
    protected ArrayList<Member> participants = new ArrayList<Member>();
    private int id;

    /**
     * Event constructor,
     * creates an event given information.
     * Host can be null to represent no host.
     * 
     * @param facility
     * @param timeBlock
     * @param host
     */
    public Event(Facility facility, TimeBlock timeblock, Member host) {
        this.facility = facility;
        this.timeBlock = timeblock;
        this.host = host;
    }

    /**
     * isFull
     * determines whether the event's facility has reached maximum participants.
     * 
     * @return whether or not the event is full
     */
    public boolean isFull() {
        return (participants.size() >= this.facility.getMaxCapacity());
    }

    /**
     * registerParticipant
     * registers a member to the event.
     * 
     * @param member
     * @return whether or not the member was member was successfully added
     */
    public boolean registerParticipant(Member member) {
        // guard clauses
        if (member == null) {
            return false;
        }
        
        if (participants.contains(member)) {
            return false; // already registered
        }

        if (isFull()) {
            return false; // event is full
        }

        if (!member.getRegistrations().isBlockFree(timeBlock)) {
            return false; // member has a conflicting time block
        }

        // all conditions are valid for member to be added now
        participants.add(member);
        member.getRegistrations().add(this);

        return true;
    }

    /**
     * assignStaff
     * assigns a staff to supervise the event.
     * 
     * @param staff
     * @return whether or not the staff was successfully added
     */
    public boolean assignStaff(Staff staff) {
        // guard clauses
        if (staff == null) {
            return false;
        }

        if (staffSupervising.contains(staff)) {
            return false; // already supervising
        }

        if (!staff.getShifts().isBlockFree(timeBlock)) {
            return false; // the staff has a conflicting time block
        }

        // all conditions are valid for the staff member to be added now
        staffSupervising.add(staff);
        staff.getShifts().add(this);
    }
 }