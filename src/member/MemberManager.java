package member;

import java.util.*;

public class MemberManager {
    public ArrayList<Member> members = new ArrayList<>();
    public void addMember(Member member) {
        member.setId(generateId());
        members.add(member);
    }

    public int generateId() {
        if (members.isEmpty()) {
            return 1;
        }
        return members.getLast().getId() + 1;
    }

    public Member searchById(int id) {
        return searchByIdRecursive(id, 0, members.size() - 1);
    }

    private Member searchByIdRecursive(int id, int low, int high) {
        if (low > high) {
            return null;
        }
        int mid   = (low + high) / 2;
        int midId = members.get(mid).getId();

        if (midId == id) {
            return members.get(mid);
        } else if (midId > id) {
            return searchByIdRecursive(id, low, mid - 1);
        } else {
            return searchByIdRecursive(id, mid + 1, high);
        }
    }

    public void printAllBills() {
        for (Member m : members) {
            m.printBill();
        }
    }

    public void printAlphabetical() {
        ArrayList<String> sorted = new ArrayList<>();
        for (Member m : members) {
            sorted.add(m.getName());
        }
        sort(sorted);
        for (String name : sorted) {
            System.out.println(name);
        }
    }
}
