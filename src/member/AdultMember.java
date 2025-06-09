package member;

import java.util.ArrayList;
import java.util.List;
import event.Event;

public class AdultMember extends Member {
    private String contactPhone;
    private String address;
    private double totalBillAmount;
    private double paidBillAmount;
    private boolean billPaid;
    private ArrayList<YouthMember> children = new ArrayList<>();

    public AdultMember(int age, String name, PlanType planType,
                       String contactPhone, String address,
                       double totalBillAmount, boolean billPaid) {
        super(age, name, planType);
        this.contactPhone = contactPhone;
        this.address = address;
        this.totalBillAmount = totalBillAmount;
        this.billPaid = billPaid;
        this.paidBillAmount = billPaid ? totalBillAmount : 0.0;
    }

    public double calculateBill() {
        double base;
        switch (planType) {
            case BIWEEKLY_BASE:
                base = BIWEEKLY_BASE;
                break;
            case MONTHLY_BASE:
                base = MONTHLY_BASE;
                break;
            case ANNUAL_BASE:
                base = ANNUAL_BASE;
                break;
            default:
                base = 0.0;
                break;
        }
        return base + totalBillAmount;
    }

    public double payBill(double amount) {
        paidBillAmount += amount;
        if (paidBillAmount >= totalBillAmount) {
            paidBillAmount = totalBillAmount;
            billPaid = true;
        }
        return paidBillAmount;
    }

    public void printBill() {
        super.printBill();
    }

    public String toString() {
        return super.toString();
    }

    public void addHosting(Event event) {
        registerFor(event);
    }

    public void addChild(YouthMember child) {
        children.add(child);
    }

    public List<YouthMember> getChildren() {
        return children;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalBillAmount() {
        return totalBillAmount;
    }

    public void setTotalBillAmount(double totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }

    public double getPaidBillAmount() {
        return paidBillAmount;
    }

    public void setPaidBillAmount(double paidBillAmount) {
        this.paidBillAmount = paidBillAmount;
    }

    public boolean isBillPaid() {
        return billPaid;
    }

    public void setBillPaid(boolean billPaid) {
        this.billPaid = billPaid;
    }
}
