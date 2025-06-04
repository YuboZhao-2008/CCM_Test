/**
 * This class represents a scheduled fundraiser.
 * It contains all the functions that a fundraiser needs.
 *
 * @author Mansour Abdelsalam
 * @version 1.0
 * @since 2025-06-04
 */

package event;

import facility.*;
import time.*;
import member.*;
import staff.*;

import java.util.ArrayList;
import java.io.*;

public class Fundraiser extends Event {
    // fields
    private double goal;
    private double amountRaised;

    /**
     * Constructor for Fundraiser;
     * creates a fundraiser given information.
     * Host can be null to represent no host.
     * 
     * @param facility
     * @param timeBlock
     * @param host
     * @param goal
     */
    public Fundraiser(Facility facility, TimeBlock timeBlock, Member host, double goal) {
        super(facility, timeBlock, host);

        this.goal = goal;
    }

    // accessors
    //

    // mutators
    //

    /**
     * setCompleted
     * updates isCompleted to true and generates/asks for information.
     * It will also print the information of the completed event.
     * 
     * @param hours
     */
    @Override
    public void setCompleted() {
        if 
    }
    
    /*
     * toString
     */
    public String toString() {
        String s = super.toString() + "\nGoal: "+goal;
        if (isCompleted) {
            s += "\nTotal Raised: "+amountRaised;
        }

        return s;
    }
}
