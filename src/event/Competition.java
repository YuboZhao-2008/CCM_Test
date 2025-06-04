/**
 * This class represents a scheduled competition.
 * It contains all the functions that a competition needs.
 *
 * @author Mansour Abdelsalam
 * @version 1.0
 * @since 2025-06-04
 */

package event;

import java.util.Scanner;

import facility.*;
import time.*;
import member.*;
import staff.*;
import main.*;

import java.util.ArrayList;
import java.io.*;

public class Competition extends Event {
    // fields
    private double prize;
    private double participationCost;
    private Member winner;
    
    /**
     * Constructor for Competition;
     * creates a competition given information.
     * Host can be null to represent no host.
     * 
     * @param facility
     * @param timeBlock
     * @param host
     * @param prize
     * @param participationCost
     */
    public Competition(Facility facility, TimeBlock timeBlock, Member host, double prize, double participationCost) {
        super(facility, timeBlock, host);

        this.prize = prize;
        this.participationCost = participationCost;
        this.winner = null;
    }

    // accessors
    public Member getWinner() {
        return this.winner;
    }

    // mutators
    //

    /**
     * setWinner
     * sets the specified member as the winner of the event
     * 
     * @param member
     * @return whether or not the winner was successfully set
     */
    public boolean setWinner(Member member) {
        if(participants.contains(member)) {
            winner = member;
            return true;
        }

        return false;
    }

    
    /**
     * setCompleted
     * description
     */
    @Override
    public void setCompleted() {
        isCompleted = true;
        
        setWinner(member)
    }

    /*
     * toString
     */
    public String toString() {
        String s = super.toString() + "\nPrize: "+prize+"\nParticipation Cost: "+participationCost;
        if (winner != null) {
            s += "\nWinner: "+winner.getName();
        }

        return s;
    }
}