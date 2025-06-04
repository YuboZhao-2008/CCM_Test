/**
 * This class represents a scheduled event.
 * It contains all the functions that an event needs.
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
    protected int id;
    protected boolean isCompleted;

    /**
     * Constructor for Event;
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
        
        this.isCompleted = false;
        // id is set within eventManager, which will generate a unique ID for the event.
    }

    // accessors
    //

    // mutators
    public void setID(int id) {
        this.id = id;
    }

    /**
     * isFull
     * determines if the event's facility has reached maximum capacity.
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
            return false; // already registered for this event
        }

        if (isFull()) {
            return false; // event is full
        }

        if (!member.getRegistrations().isBlockFree(timeBlock)) {
            return false; // member has a conflicting time block
        }

        // all conditions are valid for member to be added now
        participants.add(member);
        member.registerFor(this)

        return true;
    }

    /**
     * assignStaff
     * assigns a staff member to supervise the event.
     * 
     * @param staff
     * @return whether or not the staff member was successfully added
     */
    public boolean assignStaff(Staff staff) {
        // guard clauses
        if (staff == null) {
            return false;
        }

        if (staffSupervising.contains(staff)) {
            return false; // already supervising this event
        }

        if (!staff.getShifts().isBlockFree(timeBlock)) {
            return false; // the staff has a conflicting time block
        }

        // all conditions are valid for the staff member to be added now
        staffSupervising.add(staff);
        staff.scheduleShift(this);
        
        return true;
    }

    /**
     * advanceHours
     * advances the time by the specified hours.
     * Prints the information of all events that passed and asks the user to enter any required information about the completed event.
     * 
     * @param hours
     */
    abstract void advanceHours(int hours);

    /*
     * toString
     */
    public String toString() {
        String s = "Event ID: "+id+"\nFacility #"+facility.getRoomNum()+"\nScheduled for "+timeBlock+"\nHeld by "+host.getName();
        return s;
    }
 }
