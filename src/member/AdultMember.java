package member;

import java.util.ArrayList;
import java.util.List;
import event.Event;

/**
 * Represents an adult member with contact information, billing details,
 * and a list of dependent youth members.
 * Provides methods to calculate and pay bills, register for events,
 * and manage children.
 *
 * @author Yubo-Zhao
 * @version 1.0
 * @since 2025-06-03
 */
public class AdultMember extends Member {
    /** Contact phone number for this adult member. */
    private String contactPhone;

    /** Residential address of this adult member. */
    private String address;

    /** Amount due in addition to the base membership fee. */
    private double totalBillAmount;

    /** Amount already paid toward the total bill. */
    private double paidBillAmount;

    /** Indicates whether the full bill has been paid. */
    private boolean billPaid;

    /** List of youth members (children) linked to this guardian. */
    private List<YouthMember> children = new ArrayList<>();

    /**
     * Constructs an AdultMember with the specified details.
     *
     * @param age              the member’s age
     * @param name             the member’s full name
     * @param planType         the membership billing plan
     * @param contactPhone     phone number for contact
     * @param address          residential address
     * @param totalBillAmount  additional bill amount beyond base fee
     * @param billPaid         true if the bill is already paid in full
     */
    public AdultMember(int age, String name, PlanType planType,
                       String contactPhone, String address,
                       double totalBillAmount, boolean billPaid) {
        super(age, name, planType);
        this.contactPhone   = contactPhone;
        this.address        = address;
        this.totalBillAmount = totalBillAmount;
        this.billPaid       = billPaid;
        this.paidBillAmount = billPaid ? totalBillAmount : 0.0;
    }

    /**
     * Calculates this member’s total bill by adding the plan base fee
     * to any additional charges.
     *
     * @return the total amount due
     */
    @Override
    public double calculateBill() {
        double base;
        switch (planType) {
            case BIWEEKLY_BASE: base = BIWEEKLY_BASE; break;
            case MONTHLY_BASE:  base = MONTHLY_BASE;  break;
            case ANNUAL_BASE:   base = ANNUAL_BASE;   break;
            default:            base = 0.0;          break;
        }
        return base + totalBillAmount;
    }

    /**
     * Applies a payment toward the bill and updates the paid status.
     *
     * @param amount the payment amount
     * @return the updated paid amount
     */
    public double payBill(double amount) {
        paidBillAmount += amount;
        if (paidBillAmount >= totalBillAmount) {
            paidBillAmount = totalBillAmount;
            billPaid = true;
        }
        return paidBillAmount;
    }

    /**
     * Prints this member’s billing details to standard output.
     */
    @Override
    public void printBill() {
        super.printBill();
    }

    /**
     * Returns a string representation of this member’s details.
     *
     * @return membership details string
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Registers this member as host for an event.
     *
     * @param event the Event to host
     */
    public void addHosting(Event event) {
        registerFor(event);
    }

    /**
     * Adds a youth member as this guardian’s child.
     *
     * @param child the YouthMember to add
     */
    public void addChild(YouthMember child) {
        children.add(child);
    }

    /**
     * Retrieves the list of this guardian’s children.
     *
     * @return list of YouthMember
     */
    public List<YouthMember> getChildren() {
        return children;
    }

    /**
     * Gets the contact phone number.
     *
     * @return the phone number string
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * Updates the contact phone number.
     *
     * @param contactPhone the new phone number
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * Gets the residential address.
     *
     * @return the address string
     */
    public String getAddress() {
        return address;
    }

    /**
     * Updates the residential address.
     *
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the additional bill amount.
     *
     * @return the extra amount due
     */
    public double getTotalBillAmount() {
        return totalBillAmount;
    }

    /**
     * Sets the additional bill amount.
     *
     * @param totalBillAmount the new extra amount
     */
    public void setTotalBillAmount(double totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }

    /**
     * Gets the amount already paid.
     *
     * @return the paid amount
     */
    public double getPaidBillAmount() {
        return paidBillAmount;
    }

    /**
     * Sets the amount already paid.
     *
     * @param paidBillAmount the updated paid amount
     */
    public void setPaidBillAmount(double paidBillAmount) {
        this.paidBillAmount = paidBillAmount;
    }

    /**
     * Checks if the bill is fully paid.
     *
     * @return true if fully paid, false otherwise
     */
    public boolean isBillPaid() {
        return billPaid;
    }

    /**
     * Marks the bill as paid or unpaid.
     *
     * @param billPaid true to mark paid, false for outstanding
     */
    public void setBillPaid(boolean billPaid) {
        this.billPaid = billPaid;
    }
}
