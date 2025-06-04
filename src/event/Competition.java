package event;

import java.util.Scanner;

import facility.Facility;
import main.CommunityCentreRunner;
import member.Member;
import time.TimeBlock;

public class Competition extends Event {
    public Competition(Facility facility, TimeBlock timeBlock, Member host) {
        super(facility, timeBlock, host);
    }

    public void advanceHours(int hours) {
        Scanner sc = new Scanner(System.in);
        System.out.println("who won ");
        String winner = sc.nextLine();

        CommunityCentreRunner.getMemberManager().searchByName(winner);
    }
}
