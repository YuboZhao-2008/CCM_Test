/**
 * This class represents a scheduled competition.
 * It contains all the functions that a competition needs.
 *
 * @author Mansour Abdelsalam
 * @version 1.1
 * @since 2025-06-04
 */

package event;

import java.util.InputMismatchException;
import java.util.Scanner;

import facility.Facility;
import member.Member;
import time.TimeBlock;

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

    public double getPrize() {
        return this.prize;
    }

    public double getParticipationCost() {
        return this.participationCost;
    }

    // mutators
    //

    /**
     * setWinner
     * sets the specified member as the winner of the event; used solely within
     * setCompleted
     * 
     * @param member
     * @return whether or not the winner was successfully set
     */
    private boolean setWinner(Member member) {
        if (participants.contains(member)) {
            winner = member;
            return true;
        }

        return false;
    }

    /**
     * setCompleted
     * will ask the user for the winner, affecting their balance in calculateBill
     * accordingly.
     */
    @Override
    public void setCompleted() {
        Scanner scan = new Scanner(System.in);

        isCompleted = true;
        boolean valid_winner = false;
        int winner_id = 0;

        while (!valid_winner) {
            try {
                System.out.print("Enter the winner's member ID: ");
                winner_id = scan.nextInt();

                valid_winner = setWinner(main.CommunityCentreRunner.getMemberManager().searchById(winner_id));

                if (!valid_winner) {
                    System.out.println("Invalid ID.");
                    System.out.println("Please try again.");
                    System.out.println(); // blank line
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid ID.");
                System.out.println("Please try again.");
                System.out.println(); // blank line
            }
        }

        scan.close();
    }

    /*
     * toString
     */
    public String toString() {
        String s = super.toString() + "\nPrize: " + prize + "\nParticipation Cost: " + participationCost;
        if (winner != null) {
        }

        return s;
    }
}
